/*******************************************************************************
 * ENdoSnipe 5.0 - (https://github.com/endosnipe)
 * 
 * The MIT License (MIT)
 * 
 * Copyright (c) 2012 Acroquest Technology Co.,Ltd.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package jp.co.acroquest.endosnipe.javelin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.net.URLDecoder;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import jp.co.acroquest.endosnipe.common.config.ConfigPreprocessor;
import jp.co.acroquest.endosnipe.common.config.JavelinConfig;
import jp.co.acroquest.endosnipe.common.logger.ENdoSnipeLogger;
import jp.co.acroquest.endosnipe.common.logger.SystemLogger;
import jp.co.acroquest.endosnipe.javelin.common.JavassistUtil;
import jp.co.acroquest.endosnipe.javelin.conf.ExcludeConversionConfig;
import jp.co.acroquest.endosnipe.javelin.conf.IncludeConversionConfig;
import jp.co.acroquest.endosnipe.javelin.conf.JavelinMessages;
import jp.co.acroquest.endosnipe.javelin.conf.JavelinTransformConfig;
import jp.co.acroquest.endosnipe.javelin.converter.Converter;
import jp.co.acroquest.endosnipe.javelin.util.HashSet;
import jp.co.acroquest.endosnipe.javelin.util.ThreadUtil;
import jp.co.acroquest.endosnipe.javelin.util.WeakHashMap;
import jp.co.smg.endosnipe.javassist.ClassPool;
import jp.co.smg.endosnipe.javassist.CtClass;
import jp.co.smg.endosnipe.javassist.LoaderClassPath;

/**
 * Java Instrumentation API�ɂ��Ajavaagent�Ƃ��ăN���X�̕ϊ����s���N���X�ł��B<br />
 * 
 * @author yamasaki
 * 
 */
public class JavelinTransformer implements ClassFileTransformer
{
    /** Javelin�p�X���b�h���������J�n����܂ł̑҂����ԁB */
    public static final int WAIT_FOR_THREAD_START = 1000 * 10;
    
    /** �N���X���[�h���ɓǂ܂��N���X */
    private static JavelinTransformer transformer__;

    /** �N���Xpool��o�^����Map */
    private static Map<ClassLoader, ClassPool> loaderPoolMap__;

    private static ConverterPool converterPool__ = new ConverterPool();

    static
    {
        loaderPoolMap__ = Collections.synchronizedMap(new WeakHashMap<ClassLoader, ClassPool>());
    }

    /** ���[�h�ς݂̃N���X�̏W�� */
    private static Set<String> loadedClassSet__ = new HashSet<String>();

    /** Javelin�̐ݒ�l��ǂݍ��ރN���X */
    private JavelinTransformConfig transformConfig_;

    /** �V�X�e���v���p�e�B�\���̐擪 */
    private static final String SYS_PROP_HEAD = "\n>>>> System Properties\n";

    /** �V�X�e���v���p�e�B�\���̖��� */
    private static final String SYS_PROP_END = "<<<<\n";

    /** StringBuilder�̃f�t�H���g�T�C�Y */
    private static final int DEF_BUILDER_SIZE = 1024;

    /**
     * ���O�o�͏����𖄂ߍ��݂܂��B<br />
     * 
     * @param loader �N���X���[�_
     * @param className �N���X��
     * @param classBeingRedefined �Ē�`���ꂽ�N���X
     * @param protectionDomain �ی�̈�
     * @param classfileBuffer �N���X�t�@�C���o�b�t�@
     * @return �R�[�h���ߍ��݌�̃N���X�̃o�C�g�R�[�h
     */
    public byte[] transform(final ClassLoader loader, String className,
            final Class<?> classBeingRedefined, final ProtectionDomain protectionDomain,
            final byte[] classfileBuffer)
    {
        className = className.replaceAll("/", "\\.");
        
        if (!isTargetClassName(className))
        {
            return null;
        }

        if (loadedClassSet__.contains(className))
        {
            return null;
        }

        // ���O���X�g�ŃN���X���Ə��O����Ă���ꍇ�́A�ȍ~�̏������s��Ȃ��B
        if (this.transformConfig_.isExcludeClass(className))
        {
            return null;
        }

        
        SystemLogger logger = SystemLogger.getInstance();
        if (logger.isDebugEnabled())
        {
            logger.debug("Class Loaded:" + className);
        }

        byte[] transformedBytes = null;
        try
        {
            transformedBytes = transformClass(loader, className, classfileBuffer);
        }
        catch (Throwable ex)
        {
            logger.warn(ex);
            return null;
        }

        if (transformedBytes == classfileBuffer)
        {
            return null;
        }

        return transformedBytes;
    }


    /**
     * �N���X������ϊ��Ώۂł��邩�A���肷��B
     * Java API�̃N���X�����javassist�AJavelin�̃N���X�ł���΁Afalse��Ԃ��B
     * 
     * @param className
     * @return �ϊ��Ώۂł����true���A�����łȂ����false��Ԃ��B
     */
    private static boolean isTargetClassName(String className)
    {
        // Java API�̃N���X�����javassist�AJavelin�̃N���X�ɑ΂��Ă͏������s��Ȃ��B
        if (className.startsWith("sun.misc") 
                || className.startsWith("javassist.")
                || className.startsWith("org.seasar.javelin")
                || className.startsWith("jp.co.acroquest.endosnipe.javelin")
                || className.startsWith("jp.co.smg.endosnipe.javassist"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * �w�肵���N���X�̃o�C�g�R�[�h��ϊ����܂��B<br />
     *
     * @param loader �N���X���[�_
     * @param className �ϊ�����N���X��
     * @param classfileBuffer �ϊ��O�̃o�C�g�R�[�h
     * @return �ϊ���̃o�C�g�R�[�h
     * @throws Exception �ϊ����ɃG���[�����������ꍇ
     */
    private byte[] transformClass(final ClassLoader loader, final String className, final byte[] classfileBuffer)
        throws Exception
    {
        byte[] transformedBytes = null;

        ClassPool pool = getClassPool(loader);

        InputStream input = new ByteArrayInputStream(classfileBuffer);

        CtClass ctClass = pool.makeClass(input);
        ctClass.defrost();
        ctClass.stopPruning(true);

        transformedBytes = transformClass(loader, pool, className, ctClass);
        
        return transformedBytes;
    }

    private byte[] transformClass(final ClassLoader loader, ClassPool pool, final String className, CtClass ctClass)
        throws Exception
    {
        byte[] transformedBytes = null;
        
        try
        {
            // �Ώۃ��X�g�Ƀ}�b�`����΁A�������s���B
            List<IncludeConversionConfig> includeConfigList =
                    this.transformConfig_.matchesToInclude(className, ctClass, pool);

            // 1�̃N���X�ɑ΂��A�����R���o�[�^�N���X��1��̂ݓK�p����B
            // 1�̃N���X�ɑ΂��āA�����R���o�[�^�N���X�������s�ɓn�蓖�Ă͂܂�ꍇ�́A
            // ��`�t�@�C���̏�̍s��D�悷��B
            // �ȉ��̒�`�̏ꍇ�ATestTarget�ɂ́AConverterA�AConverterB�AConverterC�̏��œK�p�����B
            // [Converter]Converter1=yyy.zzz.ConverterA
            // [Converter]Converter2=yyy.zzz.ConverterB,yyy.zzz.ConverterA,yyy.zzz.ConverterC
            // TestTarget,Converter1
            // TestTarget,Converter2

            // �Ώۃ��X�g�Ƀ}�b�`���Ȃ��ꍇ�́A�������s��Ȃ�
            List<ExcludeConversionConfig> exludeConfigList =
                    this.transformConfig_.matchesToExclude(className);

            Set<String> appliedConverterSet = new HashSet<String>();

            // �R���o�[�^�������K�p����
            for (IncludeConversionConfig includeConfig : includeConfigList)
            {
                for (String converterName : includeConfig.getConverterNameList())
                {
                    List<String> converterClassNames =
                            this.transformConfig_.getConverterClassNames(converterName);
                    for (String converterClassName : converterClassNames)
                    {
                        if (appliedConverterSet.contains(converterClassName))
                        {
                            SystemLogger logger = SystemLogger.getInstance();
                            logger.warn("(" + converterName + ")" + " ignored convert.pattern="
                                    + includeConfig.getClassNamePattern() + "#"
                                    + includeConfig.getMethodNamePattern() + ",class=" + className);
                        }

                        transformedBytes =
                                applyConverter(className, transformedBytes, pool, ctClass,
                                               includeConfig, exludeConfigList, converterClassName);
                        appliedConverterSet.add(converterClassName);
                    }
                }
            }
        }
        finally
        {
            JavelinConfig config = new JavelinConfig();
            if (config.getDetach())
            {
                JavassistUtil.addDetachClass(ctClass);
                JavassistUtil.detachAll();
            }
            JavassistUtil.clearDetachClass();
        }
        
        return transformedBytes;
    }
    
    private static ClassPool getClassPool(final ClassLoader loader)
    {
        ClassPool pool = null;
        if (loader != null)
        {
            pool = loaderPoolMap__.get(loader);
        }

        if (pool == null)
        {
            pool = new ClassPool();
            pool.appendSystemPath();

            appendLoaderClassPath(pool, loader);

            if (loader != null)
            {
                loaderPoolMap__.put(loader, pool);
            }
        }
        return pool;
    }

    /**
     * ClassPool�Ƀp�X��ǉ����܂��B<br />
     * 
     * @param pool {@link ClassPool}�I�u�W�F�N�g
     * @param loader {@link ClassLoader}�I�u�W�F�N�g
     */
    private static void appendLoaderClassPath(final ClassPool pool, final ClassLoader loader)
    {
        pool.appendClassPath(new LoaderClassPath(loader));

        if (loader == null)
        {
            return;
        }

        ClassLoader parentLoader = loader.getParent();
        appendLoaderClassPath(pool, parentLoader);
    }

    /**
     * �R���o�[�^��K�p���܂��B<br />
     * 
     * @param className �N���X��
     * @param classfileBuffer �N���X�t�@�C���̃o�b�t�@
     * @param pool {@link ClassPool}�I�u�W�F�N�g
     * @param ctClass {@link CtClass}�I�u�W�F�N�g
     * @param includeConfig �R�[�h���ߍ��ݑΏۃN���X�������̓��\�b�h�������ݒ�
     * @param excludeConfigList �R�[�h���ߍ��ݏ��O�ΏۃN���X�������̓��\�b�h�������ݒ�
     * @param converterClassName �R���o�[�^�̃N���X��
     * @return �R�[�h���ߍ��݌�̃N���X�̃o�C�g�R�[�h
     */
    private byte[] applyConverter(final String className, byte[] classfileBuffer,
            final ClassPool pool, final CtClass ctClass,
            final IncludeConversionConfig includeConfig,
            final List<ExcludeConversionConfig> excludeConfigList, final String converterClassName)
    {
        Converter converter = converterPool__.request(converterClassName);
        if (converter == null)
        {
            return classfileBuffer;
        }

        try
        {
            ctClass.defrost();
            ctClass.stopPruning(true);

            classfileBuffer =
                    converter.convert(className, classfileBuffer, pool, ctClass, includeConfig,
                                      excludeConfigList);

        }
        finally
        {
            converterPool__.release(converter);
        }
        return classfileBuffer;
    }

    /**
     * �����������ł��B �ݒ�t�@�C���̓ǂݍ��݂��s���܂��B<br />
     * 
     * @throws IOException ���o�͎��̗�O
     */
    public void init()
        throws IOException
    {
        // ���s���Ă���Jar�t�@�C�������݂���f�B���N�g���̃p�X���擾
        String absoluteJarDirectory = getAbsoluteDirectoryPath();

        // �I�v�V�����t�@�C������A�I�v�V�����ݒ��ǂݍ��ށB
        JavelinConfig config = new JavelinConfig(absoluteJarDirectory);
        SystemLogger.initSystemLog(config);

        this.transformConfig_ = new JavelinTransformConfig();

        InputStream includeStream = ConfigPreprocessor.process(new File(config.getInclude()));
        InputStream excludeStream = ConfigPreprocessor.process(new File(config.getExclude()));

        try
        {
            this.transformConfig_.readConfig(includeStream, excludeStream);
        }
        finally
        {
            includeStream.close();
            excludeStream.close();
        }
    }

    /**
     * javaagent�I���f�}���h�E�A�^�b�`�p�G���g���|�C���g�B
     * 
     * @param args ����
     * @param instrumentation Instrumentation
     */
    public static void agentmain(final String args, final Instrumentation instrumentation)
    {
        ENdoSnipeLogger.setSystemLoggerMode(true);
        
        if (loadedClassSet__.size() == 0)
        {
            premain(args, instrumentation);
        }
        else
        {
            SystemLogger.getInstance().warn("Javelin agent is already loaded.");
        }
    }

    /**
     * javaagent�p�G���g���|�C���g�B<br> ���[�h�ς݃N���X�̕ύX���s���B
     * 
     * @param args ����
     * @param instrumentation Instrumentation
     */
    public static void premain(final String args, final Instrumentation instrumentation)
    {
        ENdoSnipeLogger.setSystemLoggerMode(true);

        // �C���X�^���X�̐����Ə��������s���B
        transformer__ = new JavelinTransformer();
        try
        {
            transformer__.init();
        }
        catch (Throwable th)
        {
            SystemLogger.getInstance().warn(th);
            return;
        }

        JavelinConfig javelinConfig = new JavelinConfig();

        // �ݒ�l��\������B
        transformer__.printConfig(javelinConfig);
        transformer__.printSystemProperty();

        loadClasses();

        useLinkedList();

        Class<?>[] classes = instrumentation.getAllLoadedClasses();

        // ���Ƀ��[�h�ς݂̃N���X�̕ύX���s���B
        ClassReserveTransformer classReserveTransformer = new ClassReserveTransformer();
        instrumentation.addTransformer(classReserveTransformer);
        transformLoadedClasses(instrumentation, classes);
        instrumentation.removeTransformer(classReserveTransformer);
        transformLoadedClasses(instrumentation, classReserveTransformer.getLoadedClasses());

        try
        {
            // �����������C���X�^���X��o�^����B
            instrumentation.addTransformer(transformer__);
        }
        catch (Throwable th)
        {
            SystemLogger.getInstance().warn(th);
        }

        // �X���b�h���[�e�B���e�B������������B
        ThreadUtil.init(javelinConfig);

        // �ȉ��̏����́Ainstrumentation.addTransformer() ���Ăяo������Ɏ��s����B
        // �X���b�h�𐶐����鏈���́AaddTransformer() ���s��łȂ��ƁA
        // ���[�h���ꂽ�N���X�� transformer__.transform() �ɓn����Ȃ����Ƃ����蓾�邽�߁B
        ////////// �������� //////////

        // �|�[�g���I�[�v������B
        StatsJavelinRecorder.javelinInit(javelinConfig);

        ////////// �����܂� //////////

        // ���\�[�X���擾���Ă݂�B
        JavelinCompatibilityChecker checker = new JavelinCompatibilityChecker();
        checker.check();
    }

    private static void loadClasses()
    {
        // ClassPool�̏������Ńf�b�h���b�N������邽�߁AClassPool�����[�h���Ă���
        ClassPool.getDefault();

        new HashMap<String, String>();
    }

    /**
     * ���łɃ��[�h���ꂽ�N���X�̃o�C�g�R�[�h��ϊ����܂��B<br />
     *
     * @param instrumentation �o�C�g�R�[�h����T�[�r�X
     * @param classes �ϊ����郍�[�h�ς݃N���X
     */
    private static void transformLoadedClasses(final Instrumentation instrumentation,
            final Class<?>[] classes)
    {
        for (Class<?> element : classes)
        {
            try
            {
                ClassPool pool = getClassPool(element.getClassLoader());
                
                String className = element.getName();
                if (!isTargetClassName(className))
                {
                    continue;
                }
                
                if (loadedClassSet__.contains(className))
                {
                    continue;
                }
                
                if (element.isArray() || element.isInterface() || element.isAnnotation()
                        || element.isEnum() || element.isPrimitive())
                {
                    continue;
                }

                CtClass ctClass = pool.get(className);
                ctClass.defrost();
                
                byte[] newBytecode =
                                     transformer__.transformClass(element.getClassLoader(), pool,
                                                                  className, ctClass);
                loadedClassSet__.add(element.getCanonicalName());

                if (newBytecode != null)
                {
                    ClassDefinition definition = new ClassDefinition(element, newBytecode);
                    instrumentation.redefineClasses(new ClassDefinition[]{definition});
                }
            }
            catch (Throwable th)
            {
                String key = "javelin.JavelinTransformer.ExceptionInTransforming";
                String message = JavelinMessages.getMessage(key, element.getName());
                SystemLogger.getInstance().warn(message, th);
            }
        }
    }

    /**
     * LinkedList �N���X�����[�h���܂��B<br />
     */
    private static void useLinkedList()
    {
        try
        {
            Class.forName("java.util.LinkedList");
        }
        catch (Throwable th)
        {
            SystemLogger.getInstance().warn(th);
        }
    }

    /**
     * �p�����[�^�̐ݒ�l��\�����܂��B<br />
     * 
     * @param config Javelin�̐ݒ�l��ۑ����Ă���I�u�W�F�N�g
     */
    private void printConfig(final JavelinConfig config)
    {
        // Javelin�̐ݒ�l���Ăяo���B
        String license = config.getLicensePath();
        String include = config.getInclude();
        String exclude = config.getExclude();
        boolean skipClass = config.isSkipClassOnProcessing();
        int inheritanceDepth = config.getInheritanceDepth();
        int intervalMax = config.getIntervalMax();
        int throwableMax = config.getThrowableMax();
        long statisticsThreshold = config.getStatisticsThreshold();
        long alarmThreshold = config.getAlarmThreshold();
        long minimumAlarmInterval = config.getAlarmMinimumInterval();
        long alarmCpuThreshold = config.getAlarmCpuThreashold();
        boolean logJvnFile = config.isLogJvnFile();
        String javelinFileDir = config.getJavelinFileDir();
        boolean alarmException = config.isAlarmException();
        boolean logStacktrace = config.isLogStacktrace();
        boolean logArgs = config.isLogArgs();
        boolean argsDetail = config.isArgsDetail();
        int argsDetailDepth = config.getArgsDetailDepth();
        boolean logReturn = config.isLogReturn();
        boolean returnDetail = config.isReturnDetail();
        int returnDetailDepth = config.getReturnDetailDepth();
        boolean logMBeanInfo = config.isLogMBeanInfo();
        boolean logMBeanInfoRoot = config.isLogMBeanInfoRoot();
        String rootCallerName = config.getRootCallerName();
        String endCalleeName = config.getEndCalleeName();
        int threadModel = config.getThreadModel();
        boolean threadMonitor = config.getThreadMonitor();
        int threadMonitorDepth = config.getThreadMonitorDepth();
        long threadMonitorInterval = config.getThreadMonitorInterval();
        boolean threadContentionMonitor = config.isThreadContentionMonitor();
        long blockThreshold = config.getBlockThreshold();
        long blockTimeThreshold = config.getBlockTimeThreshold();
        int blockThreadInfoNum = config.getBlockThreadInfoNum();
        boolean threadDump = config.isThreadDump();
        int threadDumpInterval = config.getThreadDumpInterval();
        int threadDumpThreadNum = config.getThreadDumpThreadNum();
        int threadDumpCpu = config.getThreadDumpCpu();
        boolean fullGCMonitor = config.isFullGCMonitor();
        String fullGCList = config.getFullGCList();
        int fullGCThreshold = config.getFullGCThreshold();
        boolean deadLockMonitor = config.isDeadLockMonitor();
        int acceptPort = config.getAcceptPort();
        int bindInterval = config.getJavelinBindInterval();
        boolean isAcceptPortRange = config.isAcceptPortIsRange();
        int acceptPortRangeMax = config.getAcceptPortRangeMax();
        String connectHost = config.getConnectHost();
        int connectPort = config.getConnectPort();
        String databaseName = config.getDatabaseName();
        int stringLimitLength = config.getStringLimitLength();
        String systemLog = config.getSystemLog();
        int logJvnMax = config.getLogJvnMax();
        int logZipMax = config.getLogZipMax();
        int bytecodeLengthMax = config.getBytecodeLengthMax();
        int bytecodeControlCountMax = config.getBytecodeControlCountMax();
        int byteCodeExcludePolicy = config.getByteCodeExcludePolicy();
        int systemLogNumMax = config.getSystemLogNumMax();
        int systemLogSizeMax = config.getSystemLogSizeMax();
        String systemLogLevel = config.getSystemLogLevel();
        String eventLevel = config.getEventLevel();
        long eventInterval = config.getEventInterval();
        int callTreeMax = config.getCallTreeMax();
        boolean classNameSimplify = config.isClassNameSimplify();
        boolean recordJMX = config.isRecordJMX();
        String recordStrategy = config.getRecordStrategy();
        String alarmListeners = config.getAlarmListeners();
        String telegramListeners = config.getTelegramListeners();
        String serializeFile = config.getSerializeFile();
        boolean classHisto = config.getClassHisto();
        int classHistoInterval = config.getClassHistoInterval();
        int classHistoMax = config.getClassHistoMax();
        boolean classHistoGC = config.getClassHistoGC();
        boolean collectionMonitor = config.isCollectionMonitor();
        int collectionInterval = config.getCollectionInterval();
        int collectionTraceMax = config.getCollectionTraceMax();
        int traceDepth = config.getTraceDepth();
        int collectionLeakDetectDepth = config.getCollectionLeakDetectDepth();
        int collectionSizeThreshold = config.getCollectionSizeThreshold();
        boolean leakCollectionSizeOut = config.isLeakCollectionSizePrint();
        boolean linearSearchMonitor = config.isLinearSearchMonitor();
        int linearSearchListSize = config.getLinearSearchListSize();
        double linearSearchListRatio = config.getLinearSearchListRatio();
        boolean netInputMonitor = config.isNetInputMonitor();
        boolean netOutputMonitor = config.isNetOutputMonitor();
        boolean fileInputMonitor = config.isFileInputMonitor();
        boolean fileOutputMonitor = config.isFileOutputMonitor();
        int recInvocationMax = config.getRecordInvocationMax();
        boolean tatEnabled = config.isTatEnabled();
        long tatKeepTime = config.getTatKeepTime();
        long tatZeroKeepTime = config.getTatZeroKeepTime();
        boolean finalizationCount = config.isFinalizationCount();
        boolean httpSessionCount = config.isHttpSessionCount();
        boolean httpSessionSize = config.isHttpSessionSize();
        boolean concurrentAccessMonitored = config.isConcurrentAccessMonitored();
        boolean timeoutMonitor = config.isTimeoutMonitor();
        boolean intervalMonitor = config.isIntervalMonitor();
        String intervalThreshold = config.getIntervalThreshold();
        String intervalPerArgssThreshold = config.getIntervalPerArgsThreshold();
        String log4jprintStackLevel = config.getLog4jPrintStackLevel();
        int measurementCount = config.getAutoExcludeThresholdCount();
        int measurementTime = config.getAutoExcludeThresholdTime();
        boolean ejbSessionMonitor = config.isEjbSessionMonitor();
        boolean methodStallMonitor = config.isMethodStallMonitor();
        int methodStallInterval = config.getMethodStallInterval();
        int methodStallThreshold = config.getMethodStallThreshold();
        int methodStallTraceDepth = config.getMethodStallTraceDepth();
        boolean httpStatusError = config.isHttpStatusError();

        // Javelin�̐ݒ�l��W���o�͂���
        String version = ResourceBundle.getBundle("version").getString("version");
        System.out.println(">>>> Properties related with Javelin (Version " + version + ")");
        System.out.println("\tjavelin.license.path                 : " + license);
        System.out.println("\tjavelin.include                      : " + include);
        System.out.println("\tjavelin.exclude                      : " + exclude);
        System.out.println("\tjavelin.skipClassOnProcessing        : " + skipClass);
        System.out.println("\tjavelin.inheritance.depth            : " + inheritanceDepth);
        System.out.println("\tjavelin.intervalMax                  : " + intervalMax);
        System.out.println("\tjavelin.throwableMax                 : " + throwableMax);
        System.out.println("\tjavelin.statisticsThreshold          : " + statisticsThreshold);
        System.out.println("\tjavelin.alarmThreshold               : " + alarmThreshold);
        System.out.println("\tjavelin.minimumAlarmInterval         : " + minimumAlarmInterval);
        System.out.println("\tjavelin.alarmCpuThreshold            : " + alarmCpuThreshold);
        System.out.println("\tjavelin.log.enable                   : " + logJvnFile);
        System.out.println("\tjavelin.javelinFileDir               : " + javelinFileDir);
        System.out.println("\tjavelin.alarmException               : " + alarmException);
        System.out.println("\tjavelin.log.stacktrace               : " + logStacktrace);
        System.out.println("\tjavelin.log.args                     : " + logArgs);
        System.out.println("\tjavelin.log.args.detail              : " + argsDetail);
        System.out.println("\tjavelin.log.args.detail.depth        : " + argsDetailDepth);
        System.out.println("\tjavelin.log.return                   : " + logReturn);
        System.out.println("\tjavelin.log.return.detail            : " + returnDetail);
        System.out.println("\tjavelin.log.return.detail.depth      : " + returnDetailDepth);
        System.out.println("\tjavelin.log.mbeaninfo                : " + logMBeanInfo);
        System.out.println("\tjavelin.log.mbeaninfo.root           : " + logMBeanInfoRoot);
        System.out.println("\tjavelin.log.jvn.max                  : " + logJvnMax);
        System.out.println("\tjavelin.log.zip.max                  : " + logZipMax);
        System.out.println("\tjavelin.stringLimitLength            : " + stringLimitLength);
        System.out.println("\tjavelin.className.simplify           : " + classNameSimplify);
        System.out.println("\tbytecode.exclude.length              : " + bytecodeLengthMax);
        System.out.println("\tbytecode.exclude.controlCount        : " + bytecodeControlCountMax);
        System.out.println("\tbytecode.exclude.policy              : " + byteCodeExcludePolicy);
        System.out.println("\tjavelin.autoExcludeThreshold.count   : " + measurementCount);
        System.out.println("\tjavelin.autoExcludeThreshold.time    : " + measurementTime);
        System.out.println("\tjavelin.system.log                   : " + systemLog);
        System.out.println("\tjavelin.system.log.num.max           : " + systemLogNumMax);
        System.out.println("\tjavelin.system.log.size.max          : " + systemLogSizeMax);
        System.out.println("\tjavelin.system.log.level             : " + systemLogLevel);
        System.out.println("\tjavelin.event.level                  : " + eventLevel);
        System.out.println("\tjavelin.eventInterval                : " + eventInterval);
        System.out.println("\tjavelin.rootCallerName               : " + rootCallerName);
        System.out.println("\tjavelin.endCalleeName                : " + endCalleeName);
        System.out.println("\tjavelin.threadModel                  : " + threadModel);
        System.out.println("\tjavelin.thread.monitor               : " + threadMonitor);
        System.out.println("\tjavelin.thread.monitor.depth         : " + threadMonitorDepth);
        System.out.println("\tjavelin.thread.monitor.interval      : " + threadMonitorInterval);
        System.out.println("\tjavelin.thread.contention.monitor    : " + threadContentionMonitor);
        System.out.println("\tjavelin.thread.block.threshold       : " + blockThreshold);
        System.out.println("\tjavelin.thread.blocktime.threshold   : " + blockTimeThreshold);
        System.out.println("\tjavelin.thread.block.threadinfo.num  : " + blockThreadInfoNum);
        System.out.println("\tjavelin.thread.dump.monitor          : " + threadDump);
        System.out.println("\tjavelin.thread.dump.interval         : " + threadDumpInterval);
        System.out.println("\tjavelin.thread.dump.threadnum        : " + threadDumpThreadNum);
        System.out.println("\tjavelin.thread.dump.cpu              : " + threadDumpCpu);
        System.out.println("\tjavelin.fullgc.monitor               : " + fullGCMonitor);
        System.out.println("\tjavelin.fullgc.list                  : " + fullGCList);
        System.out.println("\tjavelin.fullgc.threshold             : " + fullGCThreshold);
        System.out.println("\tjavelin.thread.deadlock.monitor      : " + deadLockMonitor);
        System.out.println("\tjavelin.acceptPort                   : " + acceptPort);
        System.out.println("\tjavelin.bind.interval                : " + bindInterval);
        System.out.println("\tjavelin.acceptPort.isRange           : " + isAcceptPortRange);
        System.out.println("\tjavelin.acceptPort.rangeMax          : " + acceptPortRangeMax);
        System.out.println("\tjavelin.connectHost                  : " + connectHost);
        System.out.println("\tjavelin.connectPort                  : " + connectPort);
        System.out.println("\tjavelin.databaseName                 : " + databaseName);        
        System.out.println("\tjavelin.call.tree.enable             : " + config.isCallTreeEnabled());
        System.out.println("\tjavelin.call.tree.max                : " + callTreeMax);
        System.out.println("\tjavelin.record.invocation.num.max    : " + recInvocationMax);
        System.out.println("\tjavelin.record.jmx                   : " + recordJMX);
        System.out.println("\tjavelin.recordStrategy               : " + recordStrategy);
        System.out.println("\tjavelin.alarmListeners               : " + alarmListeners);
        System.out.println("\tjavelin.telegramListeners            : " + telegramListeners);
        System.out.println("\tjavelin.serializeFile                : " + serializeFile);
        System.out.println("\tjavelin.traceDepth                   : " + traceDepth);
        System.out.println("\tjavelin.leak.collection.monitor      : " + collectionMonitor);
        System.out.println("\tjavelin.leak.interval                : " + collectionInterval);
        System.out.println("\tjavelin.leak.traceMax                : " + collectionTraceMax);
        System.out.println("\tjavelin.leak.detect.traceDepth       : " + collectionLeakDetectDepth);
        System.out.println("\tjavelin.leak.class.histo             : " + classHisto);
        System.out.println("\tjavelin.leak.class.histo.interval    : " + classHistoInterval);
        System.out.println("\tjavelin.leak.class.histo.max         : " + classHistoMax);
        System.out.println("\tjavelin.leak.class.histo.gc          : " + classHistoGC);
        System.out.println("\tjavelin.leak.collectionSizeOut       : " + leakCollectionSizeOut);
        System.out.println("\tjavelin.leak.collectionSizeThreshold : " + collectionSizeThreshold);
        System.out.println("\tjavelin.linearsearch.monitor         : " + linearSearchMonitor);
        System.out.println("\tjavelin.linearsearch.size            : " + linearSearchListSize);
        System.out.println("\tjavelin.linearsearch.ratio           : " + linearSearchListRatio);
        System.out.println("\tjavelin.net.input.monitor            : " + netInputMonitor);
        System.out.println("\tjavelin.net.output.monitor           : " + netOutputMonitor);
        System.out.println("\tjavelin.file.input.monitor           : " + fileInputMonitor);
        System.out.println("\tjavelin.file.output.monitor          : " + fileOutputMonitor);
        System.out.println("\tjavelin.tat.monitor                  : " + tatEnabled);
        System.out.println("\tjavelin.tat.keepTime                 : " + tatKeepTime);
        System.out.println("\tjavelin.tat.zeroKeepTime             : " + tatZeroKeepTime);
        System.out.println("\tjavelin.finalizationCount.monitor    : " + finalizationCount);
        System.out.println("\tjavelin.httpSessionCount.monitor     : " + httpSessionCount);
        System.out.println("\tjavelin.httpSessionSize.monitor      : " + httpSessionSize);
        System.out.println("\tjavelin.concurrent.monitor           : " + concurrentAccessMonitored);
        System.out.println("\tjavelin.timeout.monitor              : " + timeoutMonitor);
        System.out.println("\tjavelin.interval.monitor             : " + intervalMonitor);
        System.out.println("\tjavelin.interval.threshold           : " + intervalThreshold);
        System.out.println("\tjavelin.interval.perargs.threshold   : " + intervalPerArgssThreshold);
        System.out.println("\tjavelin.log4j.printstack.level       : " + log4jprintStackLevel);
        System.out.println("\tjavelin.ejb.session.monitor          : " + ejbSessionMonitor);
        System.out.println("\tjavelin.method.stall.monitor         : " + methodStallMonitor);
        System.out.println("\tjavelin.method.stall.interval        : " + methodStallInterval);
        System.out.println("\tjavelin.method.stall.threshold       : " + methodStallThreshold);
        System.out.println("\tjavelin.method.stall.traceDepth      : " + methodStallTraceDepth);
        System.out.println("\tjavelin.httpStatusError              : " + httpStatusError);
        System.out.println(SYS_PROP_END);
    }

    /**
     * �V�X�e���v���p�e�B���g���[�X�t�@�C���ɏo�͂��܂��B<br />
     * 
     */
    public void printSystemProperty()
    {
        StringBuilder builder = new StringBuilder(DEF_BUILDER_SIZE);
        builder.append(SYS_PROP_HEAD);

        Properties systemProperties = System.getProperties();
        Set<Map.Entry<Object, Object>> entrySet = systemProperties.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet)
        {
            builder.append(entry.getKey());
            builder.append('=');
            builder.append(entry.getValue());
            builder.append('\n');
        }
        builder.append(SYS_PROP_END);
        SystemLogger.getInstance().info(builder.toString());
    }

    /**
     * javelin.jar �t�@�C�������݂���A�f�B���N�g���̕����p�X���擾���܂��B<br />
     * 
     * @return javelin.jar �t�@�C�������݂���f�B���N�g���̕����p�X
     */
    private String getAbsoluteDirectoryPath()
    {
        URL resourceJarUrl = JavelinTransformer.class.getResource("JavelinTransformer.class");
        String resourceJarPath = resourceJarUrl.getFile();
        try
        {
            resourceJarPath = URLDecoder.decode(resourceJarPath, "UTF-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            // �������Ȃ��B
        }

        int startIndex = resourceJarPath.indexOf("/");
        int endIndex = resourceJarPath.lastIndexOf("!");

        String absoluteJarPath = resourceJarPath.substring(startIndex, endIndex);

        int dirDelimIndex = absoluteJarPath.lastIndexOf("/");

        String absoluteJarDirectory = absoluteJarPath.substring(0, dirDelimIndex + 1);

        return absoluteJarDirectory;
    }
}