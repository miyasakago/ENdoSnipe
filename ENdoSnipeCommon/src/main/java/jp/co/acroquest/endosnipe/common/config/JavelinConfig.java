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
package jp.co.acroquest.endosnipe.common.config;

import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Javelinの設定を保持するためのクラスです。<br />
 * 
 * @author eriguchi
 */
public class JavelinConfig extends JavelinConfigBase
{
    private static int acceptPort__;

    private static int acceptPortRangeMax__;

    private static String connectHost__;

    private static int connectPort__;

    private static String agentName__;

    private static long alarmCpuThreashold__;

    private static String alarmListeners__;

    private static long alarmMinimumInterval__;

    private static long alarmThreshold__;

    private static int argsDetailDepth__;

    private static int autoExcludeThresholdCount__;

    private static int autoExcludeThresholdTime__;

    private static int blockThreadInfoNum__;

    private static long blockThreshold__;

    private static long blockTimeThreshold__;

    private static int bytecodeControlCountMax__;

    private static int byteCodeExcludePolicy__;

    private static int bytecodeLengthMax__;

    private static int callTreeMax__;

    private static boolean callTreeAll__;

    private static boolean classHisto__;

    private static boolean classHistoGC__;

    private static int classHistoInterval__;

    private static int classHistoMax__;

    private static int collectionInterval__;

    private static int collectionLeakDetectDepth__;

    private static int collectionSizeThreshold__;

    private static int collectionTraceMax__;

    private static boolean monitorJmxResource__;

    private static boolean collectSystemResources__;

    private static boolean detach__;

    private static String endCalleeName__;

    private static long eventInterval__;

    private static String eventLevel__;

    private static String exclude__;

    private static String fullGCList__;

    private static int fullGCThreshold__;

    private static String heapDumpDir__;

    private static String include__;

    private static int inheritanceDepth__;

    private static int intervalMax__;

    private static String intervalThreshold__;

    private static String intervalPerArgsThreshold__;

    private static int javelinBindInterval__;

    private static String javelinFileDir__;

    private static int jvnDownloadMax__;

    private static String licensePath__;

    private static double linearSearchListRatio__;

    private static int linearSearchListSize__;

    private static String log4jPrintStackLevel__;

    private static int logJvnMax__;

    private static int logZipMax__;

    private static int recordInvocationMax__;

    private static String recordStrategy__;

    private static int returnDetailDepth__;

    private static String rootCallerName__;

    private static boolean sendInvocationFullEvent__;

    private static String serializeFile__;

    private static long statisticsThreshold__;

    private static int stringLimitLength__;

    private static String systemLog__;

    private static String systemLogLevel__;

    private static int systemLogNumMax__;

    private static int systemLogSizeMax__;

    private static long tatKeepTime__;

    private static long tatZeroKeepTime__;

    private static String telegramListeners__;

    private static int threadDumpCpu__;

    private static int threadDumpCpuSys__;

    private static int threadDumpCpuUser__;

    private static int threadDumpInterval__;

    private static int threadDumpThreadNum__;

    private static boolean resourceThreadRunnable__;

    private static boolean resourceThreadBlocked__;

    private static boolean resourceJdbcRecord__;

    private static int threadModel__;

    private static boolean threadMonitor__;

    private static int threadMonitorDepth__;

    private static long threadMonitorInterval__;

    private static int throwableMax__;

    private static int traceDepth__;

    private static boolean isAcceptPortIsRange__;

    private static boolean isAlarmException__;

    private static boolean isArgsDetail__;

    private static boolean isClassNameSimplify__;

    private static boolean isCollectionMonitor__;

    private static boolean isConcurrentAccessMonitored__;

    private static boolean isDeadLockMonitor__;

    private static boolean isEjbSessionMonitor__;

    private static boolean isFileInputMonitor__;

    private static boolean isFileOutputMonitor__;

    private static boolean isFinalizationCount__;

    private static boolean isFullGCMonitor__;

    private static boolean isHttpSessionCount__;

    private static boolean isHttpSessionSize__;

    private static boolean isIntervalMonitor__;

    private static boolean isLinearSearchMonitor__;

    private static boolean isLogArgs__;

    private static boolean isLogHttpSession__;

    private static boolean isLogJvnFile__;

    private static boolean isLogMBeanInfo__;

    private static boolean isLogMBeanInfoRoot__;

    private static boolean isLogReturn__;

    private static boolean isLogStacktrace__;

    private static boolean isLogZipMax__;

    private static boolean isNetInputMonitor__;

    private static boolean isNetOutputMonitor__;

    private static boolean isRecordJMX__;

    private static boolean isRecordStrategy__;

    private static boolean isReturnDetail__;

    private static boolean isSkipClassOnProcessing__;

    private static boolean isTatEnabled__;

    private static boolean isThreadContentionMonitor__;

    private static boolean isThreadDump__;

    private static boolean isTimeoutMonitor__;

    private static boolean isInitialized__ = false;

    private static long acceptDelay__;

    private static int cpuTimeUnit__;

    private static boolean isCallTreeEnabled__;

    private static boolean isMethodStallMonitor__;

    private static int methodStallInterval__;

    private static int methodStallThreshold__;

    private static int methodStallTraceDepth__;

    private static boolean httpStatusError__;

    private static String connectionMode__;

    private static int invocationNameLimitLength__;

    private static String itemNamePrefix__;

    private static String itemNameNoPrefixList__;

    /** Javelinを適用したプロセスが属するクラスタ名称。 */
    private static String clusterName__;

    private static boolean collectHadoopAgentResources__;

    private static boolean collectHBaseAgentResources__;

    private static boolean jdbcjavelinLightweightMode__;

    private static Pattern servletExcludePattern__;

    private static int servletExcludePatternCacheSize__;

    private static boolean sslEnable__;

    private static String sslKeyStore__;

    private static String sslKeyStorePass__;

    private static String sslTrustStore__;

    private static String sslTrustStorePass__;

    private static String httpCookieKey__;

    /**
     * {@link JavelinConfig} を構築します。<br />
     *
     * @param absoluteJarDirectory Jar が存在する絶対パス
     */
    public JavelinConfig(final String absoluteJarDirectory)
    {
        super(absoluteJarDirectory);
        init();
    }

    /**
     * {@link JavelinConfig} を構築します。<br />
     */
    public JavelinConfig()
    {
        super();
        init();
    }

    /**
     * 初期化
     */
    private void init()
    {
        if (isInitialized__ == true)
        {
            return;
        }

        isInitialized__ = true;

        acceptPort__ = super.getAcceptPort();
        acceptDelay__ = super.getAcceptDelay();
        acceptPortRangeMax__ = super.getAcceptPortRangeMax();
        connectHost__ = super.getConnectHost();
        connectPort__ = super.getConnectPort();
        agentName__ = super.getAgentName();
        alarmCpuThreashold__ = super.getAlarmCpuThreashold();
        alarmListeners__ = super.getAlarmListeners();
        alarmMinimumInterval__ = super.getAlarmMinimumInterval();
        alarmThreshold__ = super.getAlarmThreshold();
        argsDetailDepth__ = super.getArgsDetailDepth();
        autoExcludeThresholdCount__ = super.getAutoExcludeThresholdCount();
        autoExcludeThresholdTime__ = super.getAutoExcludeThresholdTime();
        blockThreadInfoNum__ = super.getBlockThreadInfoNum();
        blockThreshold__ = super.getBlockThreshold();
        blockTimeThreshold__ = super.getBlockTimeThreshold();
        bytecodeControlCountMax__ = super.getBytecodeControlCountMax();
        byteCodeExcludePolicy__ = super.getByteCodeExcludePolicy();
        bytecodeLengthMax__ = super.getBytecodeLengthMax();
        callTreeMax__ = super.getCallTreeMax();
        callTreeAll__ = super.isCallTreeAll();
        classHisto__ = super.getClassHisto();
        classHistoGC__ = super.getClassHistoGC();
        classHistoInterval__ = super.getClassHistoInterval();
        classHistoMax__ = super.getClassHistoMax();
        collectionInterval__ = super.getCollectionInterval();
        collectionLeakDetectDepth__ = super.getCollectionLeakDetectDepth();
        collectionSizeThreshold__ = super.getCollectionSizeThreshold();
        collectionTraceMax__ = super.getCollectionTraceMax();
        monitorJmxResource__ = super.isMonitorJmxResource();
        collectSystemResources__ = super.getCollectSystemResources();
        detach__ = super.getDetach();
        endCalleeName__ = super.getEndCalleeName();
        eventInterval__ = super.getEventInterval();
        eventLevel__ = super.getEventLevel();
        exclude__ = super.getExclude();
        fullGCList__ = super.getFullGCList();
        fullGCThreshold__ = super.getFullGCThreshold();
        heapDumpDir__ = super.getHeapDumpDir();
        include__ = super.getInclude();
        inheritanceDepth__ = super.getInheritanceDepth();
        intervalMax__ = super.getIntervalMax();
        intervalThreshold__ = super.getIntervalThreshold();
        intervalPerArgsThreshold__ = super.getIntervalPerArgsThreshold();
        javelinBindInterval__ = super.getJavelinBindInterval();
        javelinFileDir__ = super.getJavelinFileDir();
        jvnDownloadMax__ = super.getJvnDownloadMax();
        licensePath__ = super.getLicensePath();
        linearSearchListRatio__ = super.getLinearSearchListRatio();
        linearSearchListSize__ = super.getLinearSearchListSize();
        log4jPrintStackLevel__ = super.getLog4jPrintStackLevel();
        logJvnMax__ = super.getLogJvnMax();
        logZipMax__ = super.getLogZipMax();
        recordInvocationMax__ = super.getRecordInvocationMax();
        recordStrategy__ = super.getRecordStrategy();
        returnDetailDepth__ = super.getReturnDetailDepth();
        rootCallerName__ = super.getRootCallerName();
        sendInvocationFullEvent__ = super.getSendInvocationFullEvent();
        serializeFile__ = super.getSerializeFile();
        statisticsThreshold__ = super.getStatisticsThreshold();
        stringLimitLength__ = super.getStringLimitLength();
        systemLog__ = super.getSystemLog();
        systemLogLevel__ = super.getSystemLogLevel();
        systemLogNumMax__ = super.getSystemLogNumMax();
        systemLogSizeMax__ = super.getSystemLogSizeMax();
        tatKeepTime__ = super.getTatKeepTime();
        tatZeroKeepTime__ = super.getTatZeroKeepTime();
        telegramListeners__ = super.getTelegramListeners();
        threadDumpCpu__ = super.getThreadDumpCpu();
        threadDumpCpuUser__ = super.getThreadDumpCpuUser();
        threadDumpCpuSys__ = super.getThreadDumpCpuSys();
        threadDumpInterval__ = super.getThreadDumpInterval();
        threadDumpThreadNum__ = super.getThreadDumpThreadNum();
        resourceThreadRunnable__ = super.isResourceThreadRunnable();
        resourceThreadBlocked__ = super.isResourceThreadBlocked();
        resourceJdbcRecord__ = super.isResourceJdbcRecord();
        threadModel__ = super.getThreadModel();
        threadMonitor__ = super.getThreadMonitor();
        threadMonitorDepth__ = super.getThreadMonitorDepth();
        threadMonitorInterval__ = super.getThreadMonitorInterval();
        throwableMax__ = super.getThrowableMax();
        traceDepth__ = super.getTraceDepth();
        isAcceptPortIsRange__ = super.isAcceptPortIsRange();
        isAlarmException__ = super.isAlarmException();
        httpStatusError__ = super.isHttpStatusError();
        isArgsDetail__ = super.isArgsDetail();
        isClassNameSimplify__ = super.isClassNameSimplify();
        isCollectionMonitor__ = super.isCollectionMonitor();
        isConcurrentAccessMonitored__ = super.isConcurrentAccessMonitored();
        isDeadLockMonitor__ = super.isDeadLockMonitor();
        isEjbSessionMonitor__ = super.isEjbSessionMonitor();
        isFileInputMonitor__ = super.isFileInputMonitor();
        isFileOutputMonitor__ = super.isFileOutputMonitor();
        isFinalizationCount__ = super.isFinalizationCount();
        isFullGCMonitor__ = super.isFullGCMonitor();
        isHttpSessionCount__ = super.isHttpSessionCount();
        isHttpSessionSize__ = super.isHttpSessionSize();
        httpCookieKey__ = super.getHttpCookieKey();
        isIntervalMonitor__ = super.isIntervalMonitor();
        isLinearSearchMonitor__ = super.isLinearSearchMonitor();
        isLogArgs__ = super.isLogArgs();
        isLogHttpSession__ = super.isLogHttpSession();
        isLogJvnFile__ = super.isLogJvnFile();
        isLogMBeanInfo__ = super.isLogMBeanInfo();
        isLogMBeanInfoRoot__ = super.isLogMBeanInfoRoot();
        isLogReturn__ = super.isLogReturn();
        isLogStacktrace__ = super.isLogStacktrace();
        isLogZipMax__ = super.isLogZipMax();
        isNetInputMonitor__ = super.isNetInputMonitor();
        isNetOutputMonitor__ = super.isNetOutputMonitor();
        isRecordJMX__ = super.isRecordJMX();
        isRecordStrategy__ = super.isRecordStrategy();
        isReturnDetail__ = super.isReturnDetail();
        isSkipClassOnProcessing__ = super.isSkipClassOnProcessing();
        isTatEnabled__ = super.isTatEnabled();
        isThreadContentionMonitor__ = super.isThreadContentionMonitor();
        isThreadDump__ = super.isThreadDump();
        isTimeoutMonitor__ = super.isTimeoutMonitor();
        cpuTimeUnit__ = super.getCpuTimeUnit();
        isCallTreeEnabled__ = super.isCallTreeEnabled();
        isMethodStallMonitor__ = super.isMethodStallMonitor();
        methodStallInterval__ = super.getMethodStallInterval();
        methodStallThreshold__ = super.getMethodStallThreshold();
        methodStallTraceDepth__ = super.getMethodStallTraceDepth();
        connectionMode__ = super.getConnectionMode();
        itemNamePrefix__ = super.getItemNamePrefix();
        itemNameNoPrefixList__ = super.getItemNameNoPrefixList();
        clusterName__ = super.getClusterName();
        collectHadoopAgentResources__ = super.isCollectHadoopAgentResources();
        collectHBaseAgentResources__ = super.isCollectHBaseAgentResources();
        invocationNameLimitLength__ = super.getInvocationNameLimitLength();
        jdbcjavelinLightweightMode__ = super.isJdbcjavelinLightweightMode();
        servletExcludePattern__ = super.getServletExcludePattern();
        servletExcludePatternCacheSize__ = super.getServletExcludePatternCacheSize();
        sslEnable__ = super.isSslEnable();
        sslKeyStore__ = super.getSslKeystore();
        sslKeyStorePass__ = super.getSslKeystorePass();
        sslTrustStore__ = super.getSslTruststore();
        sslTrustStorePass__ = super.getSslTruststorePass();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAcceptPort()
    {
        return acceptPort__;
    }

    /**
     * アクセスポートを設定する。
     * @param acceptPort アクセスポート
     */
    public void setAcceptPort(final int acceptPort)
    {
        acceptPort__ = acceptPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAcceptPortRangeMax()
    {
        return acceptPortRangeMax__;
    }

    /**
     * アクセスポート範囲最大値を設定する。
     * @param acceptPortRangeMax アクセスポート範囲最大値
     */
    public void setAcceptPortRangeMax(final int acceptPortRangeMax)
    {
        acceptPortRangeMax__ = acceptPortRangeMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConnectHost()
    {
        return connectHost__;
    }

    /**
     * 接続ホストを設定する。
     * @param connectHost 接続ホスト
     */
    public void setConnectHost(final String connectHost)
    {
        connectHost__ = connectHost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getConnectPort()
    {
        return connectPort__;
    }

    /**
     * 接続ポートを設定する。
     * @param connectPort 接続ポート
     */
    public void setConnectPort(final int connectPort)
    {
        connectPort__ = connectPort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAgentName()
    {
        return agentName__;
    }

    /**
     * エージェント名を設定する。
     * @param agentName エージェント名
     */
    public void setAgentName(final String agentName)
    {
        agentName__ = agentName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getAlarmCpuThreashold()
    {
        return alarmCpuThreashold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlarmCpuThreashold(final long alarmCpuThreashold)
    {
        alarmCpuThreashold__ = alarmCpuThreashold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAlarmListeners()
    {
        return alarmListeners__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlarmListeners(final String alarmListeners)
    {
        alarmListeners__ = alarmListeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getAlarmMinimumInterval()
    {
        return alarmMinimumInterval__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlarmMinimumInterval(final long alarmMinimumInterval)
    {
        alarmMinimumInterval__ = alarmMinimumInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getAlarmThreshold()
    {
        return alarmThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlarmThreshold(final long alarmThreshold)
    {
        alarmThreshold__ = alarmThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getArgsDetailDepth()
    {
        return argsDetailDepth__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArgsDetailDepth(final int argsDetailDepth)
    {
        argsDetailDepth__ = argsDetailDepth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAutoExcludeThresholdCount()
    {
        return autoExcludeThresholdCount__;
    }

    /**
     * AutoExcludeThresholdCountを設定する。
     * @param autoExcludeThresholdCount AutoExcludeThresholdCount
     */
    public void setAutoExcludeThresholdCount(final int autoExcludeThresholdCount)
    {
        autoExcludeThresholdCount__ = autoExcludeThresholdCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAutoExcludeThresholdTime()
    {
        return autoExcludeThresholdTime__;
    }

    /**
     * AutoExcludeThresholdTimeを設定する。
     * @param autoExcludeThresholdTime AutoExcludeThresholdTime
     */
    public void setAutoExcludeThresholdTime(final int autoExcludeThresholdTime)
    {
        autoExcludeThresholdTime__ = autoExcludeThresholdTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBlockThreadInfoNum()
    {
        return blockThreadInfoNum__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlockThreadInfoNum(final int blockThreadInfoNum)
    {
        blockThreadInfoNum__ = blockThreadInfoNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getBlockThreshold()
    {
        return blockThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlockThreshold(final long blockThreshold)
    {
        blockThreshold__ = blockThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getBlockTimeThreshold()
    {
        return blockTimeThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlockTimeThreshold(final long blockTimeThreshold)
    {
        blockTimeThreshold__ = blockTimeThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBytecodeControlCountMax()
    {
        return bytecodeControlCountMax__;
    }

    /**
     * BytecodeControlCountMaxを設定する。
     * @param bytecodeControlCountMax BytecodeControlCountMax
     */
    public void setBytecodeControlCountMax(final int bytecodeControlCountMax)
    {
        bytecodeControlCountMax__ = bytecodeControlCountMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getByteCodeExcludePolicy()
    {
        return byteCodeExcludePolicy__;
    }

    /**
     * ByteCodeExcludePolicyを設定する。
     * @param byteCodeExcludePolicy ByteCodeExcludePolicy
     */
    public void setByteCodeExcludePolicy(final int byteCodeExcludePolicy)
    {
        byteCodeExcludePolicy__ = byteCodeExcludePolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBytecodeLengthMax()
    {
        return bytecodeLengthMax__;
    }

    /**
     * BytecodeLengthMaxを設定する。
     * @param bytecodeLengthMax BytecodeLengthMax
     */
    public void setBytecodeLengthMax(final int bytecodeLengthMax)
    {
        bytecodeLengthMax__ = bytecodeLengthMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCallTreeMax()
    {
        return callTreeMax__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCallTreeMax(final int callTreeMax)
    {
        callTreeMax__ = callTreeMax;
    }

    @Override
    public boolean isCallTreeAll()
    {
        return callTreeAll__;
    }

    /**
     * CallTreeAllを設定する。
     * @param callTreeAll CallTreeAll
     */
    public void setCallTreeAll(final boolean callTreeAll)
    {
        callTreeAll__ = callTreeAll;
    }

    @Override
    public boolean isCallTreeEnabled()
    {
        return isCallTreeEnabled__;
    }

    /**
     * CallTreeEnabledを設定する。
     * @param isCallTreeEnabled CallTreeEnabled
     */
    public void setCallTreeEnabled(final boolean isCallTreeEnabled)
    {
        isCallTreeEnabled__ = isCallTreeEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getClassHisto()
    {
        return classHisto__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClassHisto(final boolean classHisto)
    {
        classHisto__ = classHisto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getClassHistoGC()
    {
        return classHistoGC__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClassHistoGC(final boolean classHistoGC)
    {
        classHistoGC__ = classHistoGC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getClassHistoInterval()
    {
        return classHistoInterval__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClassHistoInterval(final int classHistoInterval)
    {
        classHistoInterval__ = classHistoInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getClassHistoMax()
    {
        return classHistoMax__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClassHistoMax(final int classHistoMax)
    {
        classHistoMax__ = classHistoMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCollectionInterval()
    {
        return collectionInterval__;
    }

    /**
     * CollectionIntervalを設定する
     * @param collectionInterval CollectionInterval
     */
    public void setCollectionInterval(final int collectionInterval)
    {
        collectionInterval__ = collectionInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCollectionLeakDetectDepth()
    {
        return collectionLeakDetectDepth__;
    }

    /**
     * CollectionLeakDetectDepthを設定する。
     * @param collectionLeakDetectDepth CollectionLeakDetectDepth
     */
    public void setCollectionLeakDetectDepth(final int collectionLeakDetectDepth)
    {
        collectionLeakDetectDepth__ = collectionLeakDetectDepth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCollectionSizeThreshold()
    {
        return collectionSizeThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCollectionSizeThreshold(final int collectionSizeThreshold)
    {
        collectionSizeThreshold__ = collectionSizeThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCollectionTraceMax()
    {
        return collectionTraceMax__;
    }

    /**
     * CollectionTraceMaxを設定する。
     * @param collectionTraceMax CollectionTraceMax
     */
    public void setCollectionTraceMax(final int collectionTraceMax)
    {
        collectionTraceMax__ = collectionTraceMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getDetach()
    {
        return detach__;
    }

    /**
     * Detachを設定する。
     * @param detach
     */
    public void setDetach(final boolean detach)
    {
        detach__ = detach;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEndCalleeName()
    {
        return endCalleeName__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEndCalleeName(final String endCalleeName)
    {
        endCalleeName__ = endCalleeName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getEventInterval()
    {
        return eventInterval__;
    }

    /**
     * EventIntervalを設定する。
     * @param eventInterval EventInterval
     */
    public void setEventInterval(final long eventInterval)
    {
        eventInterval__ = eventInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEventLevel()
    {
        return eventLevel__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEventLevel(final String eventLevel)
    {
        eventLevel__ = eventLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExclude()
    {
        return exclude__;
    }

    /**
     * Excludeを設定する。
     * @param exclude Exclude
     */
    public void setExclude(final String exclude)
    {
        exclude__ = exclude;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFullGCList()
    {
        return fullGCList__;
    }

    /**
     * FullGCListを設定する。
     * @param fullGCList FullGCList
     */
    public void setFullGCList(final String fullGCList)
    {
        fullGCList__ = fullGCList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFullGCThreshold()
    {
        return fullGCThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFullGCThreshold(final int fullGCThreshold)
    {
        fullGCThreshold__ = fullGCThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHeapDumpDir()
    {
        return heapDumpDir__;
    }

    /**
     * HeapDumpDirを設定する。
     * @param heapDumpDir HeapDumpDir
     */
    public void setHeapDumpDir(final String heapDumpDir)
    {
        heapDumpDir__ = heapDumpDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInclude()
    {
        return include__;
    }

    /**
     * Includeを設定する。
     * @param include
     */
    public void setInclude(final String include)
    {
        include__ = include;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInheritanceDepth()
    {
        return inheritanceDepth__;
    }

    /**
     * InheritanceDepthを設定する。
     * @param inheritanceDepth InheritanceDepth
     */
    public void setInheritanceDepth(final int inheritanceDepth)
    {
        inheritanceDepth__ = inheritanceDepth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIntervalMax()
    {
        return intervalMax__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIntervalMax(final int intervalMax)
    {
        intervalMax__ = intervalMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIntervalThreshold()
    {
        return intervalThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIntervalThreshold(final String intervalThreshold)
    {
        intervalThreshold__ = intervalThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIntervalPerArgsThreshold()
    {
        return intervalPerArgsThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIntervalPerArgsThreshold(final String intervalPerArgsThreshold)
    {
        intervalPerArgsThreshold__ = intervalPerArgsThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getJavelinBindInterval()
    {
        return javelinBindInterval__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJavelinBindInterval(final int javelinBindInterval)
    {
        javelinBindInterval__ = javelinBindInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJavelinFileDir()
    {
        return javelinFileDir__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJavelinFileDir(final String javelinFileDir)
    {
        javelinFileDir__ = javelinFileDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getJvnDownloadMax()
    {
        return jvnDownloadMax__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJvnDownloadMax(final int jvnDownloadMax)
    {
        jvnDownloadMax__ = jvnDownloadMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLicensePath()
    {
        return licensePath__;
    }

    /**
     * LicensePathを設定する。
     * @param licensePath LicensePath
     */
    public void setLicensePath(final String licensePath)
    {
        licensePath__ = licensePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLinearSearchListRatio()
    {
        return linearSearchListRatio__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLinearSearchListRatio(final double linearSearchListRatio)
    {
        linearSearchListRatio__ = linearSearchListRatio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLinearSearchListSize()
    {
        return linearSearchListSize__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLinearSearchListSize(final int linearSearchListSize)
    {
        linearSearchListSize__ = linearSearchListSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLog4jPrintStackLevel()
    {
        return log4jPrintStackLevel__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLog4jPrintStackLevel(final String log4jPrintStackLevel)
    {
        log4jPrintStackLevel__ = log4jPrintStackLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLogJvnMax()
    {
        return logJvnMax__;
    }

    /**
     * LogJvnMaxを設定する。
     * @param logJvnMax LogJvnMax
     */
    public void setLogJvnMax(final int logJvnMax)
    {
        logJvnMax__ = logJvnMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLogZipMax()
    {
        return logZipMax__;
    }

    /**
     * LogZipMaxを設定する。
     * @param logZipMax LogZipMax
     */
    public void setLogZipMax(final int logZipMax)
    {
        logZipMax__ = logZipMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRecordInvocationMax()
    {
        return recordInvocationMax__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecordInvocationMax(final int recordInvocationMax)
    {
        recordInvocationMax__ = recordInvocationMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRecordStrategy()
    {
        return recordStrategy__;
    }

    /**
     * RecordStrategyを設定する。
     * @param recordStrategy RecordStrategy
     */
    public void setRecordStrategy(final String recordStrategy)
    {
        recordStrategy__ = recordStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getReturnDetailDepth()
    {
        return returnDetailDepth__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReturnDetailDepth(final int returnDetailDepth)
    {
        returnDetailDepth__ = returnDetailDepth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRootCallerName()
    {
        return rootCallerName__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRootCallerName(final String rootCallerName)
    {
        rootCallerName__ = rootCallerName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSerializeFile()
    {
        return serializeFile__;
    }

    /**
     * SerializeFileを設定する。
     * @param serializeFile SerializeFile
     */
    public void setSerializeFile(final String serializeFile)
    {
        serializeFile__ = serializeFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getStatisticsThreshold()
    {
        return statisticsThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatisticsThreshold(final long statisticsThreshold)
    {
        statisticsThreshold__ = statisticsThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStringLimitLength()
    {
        return stringLimitLength__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStringLimitLength(final int stringLimitLength)
    {
        stringLimitLength__ = stringLimitLength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSystemLog()
    {
        return systemLog__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSystemLog(final String systemLog)
    {
        systemLog__ = systemLog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSystemLogLevel()
    {
        return systemLogLevel__;
    }

    /**
     * SystemLogLevelを設定する。
     * @param systemLogLevel SystemLogLevel
     */
    public void setSystemLogLevel(final String systemLogLevel)
    {
        systemLogLevel__ = systemLogLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSystemLogNumMax()
    {
        return systemLogNumMax__;
    }

    /**
     * SystemLogNumMaxを設定する。
     * @param systemLogNumMax SystemLogNumMax
     */
    public void setSystemLogNumMax(final int systemLogNumMax)
    {
        systemLogNumMax__ = systemLogNumMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSystemLogSizeMax()
    {
        return systemLogSizeMax__;
    }

    /**
     * SystemLogSizeMaxを設定する。
     * @param systemLogSizeMax SystemLogSizeMax
     */
    public void setSystemLogSizeMax(final int systemLogSizeMax)
    {
        systemLogSizeMax__ = systemLogSizeMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTatKeepTime()
    {
        return tatKeepTime__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTatKeepTime(final long tatKeepTime)
    {
        tatKeepTime__ = tatKeepTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTatZeroKeepTime()
    {
        return tatZeroKeepTime__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTatZeroKeepTime(final long tatZeroKeepTime)
    {
        tatZeroKeepTime__ = tatZeroKeepTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTelegramListeners()
    {
        return telegramListeners__;
    }

    /**
     * TelegramListenersを設定する。
     * @param telegramListeners TelegramListeners
     */
    public void setTelegramListeners(final String telegramListeners)
    {
        telegramListeners__ = telegramListeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThreadDumpCpu()
    {
        return threadDumpCpu__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadDumpCpu(final int threadDumpCpu)
    {
        threadDumpCpu__ = threadDumpCpu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThreadDumpCpuSys()
    {
        return threadDumpCpuSys__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadDumpCpuSys(final int threadDumpCpu)
    {
        threadDumpCpuSys__ = threadDumpCpu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThreadDumpCpuUser()
    {
        return threadDumpCpuUser__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadDumpCpuUser(final int threadDumpCpu)
    {
        threadDumpCpuUser__ = threadDumpCpu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThreadDumpInterval()
    {
        return threadDumpInterval__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadDumpInterval(final int threadDumpInterval)
    {
        threadDumpInterval__ = threadDumpInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThreadDumpThreadNum()
    {
        return threadDumpThreadNum__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadDumpThreadNum(final int threadDumpThreadNum)
    {
        threadDumpThreadNum__ = threadDumpThreadNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isResourceThreadRunnable()
    {
        return resourceThreadRunnable__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResourceThreadRunnable(final boolean threadDumpThreadNum)
    {
        resourceThreadRunnable__ = threadDumpThreadNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isResourceThreadBlocked()
    {
        return resourceThreadBlocked__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResourceThreadBlocked(final boolean threadDumpThreadNum)
    {
        resourceThreadBlocked__ = threadDumpThreadNum;
    }

    @Override
    public boolean isResourceJdbcRecord()
    {
        return resourceJdbcRecord__;
    }

    @Override
    public void setResourceJdbcRecord(final boolean resourceJdbcRecord)
    {
        resourceJdbcRecord__ = resourceJdbcRecord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThreadModel()
    {
        return threadModel__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadModel(final int threadModel)
    {
        threadModel__ = threadModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getThreadMonitor()
    {
        return threadMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadMonitor(final boolean threadMonitor)
    {
        threadMonitor__ = threadMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThreadMonitorDepth()
    {
        return threadMonitorDepth__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadMonitorDepth(final int threadMonitorDepth)
    {
        threadMonitorDepth__ = threadMonitorDepth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getThreadMonitorInterval()
    {
        return threadMonitorInterval__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadMonitorInterval(final long threadMonitorInterval)
    {
        threadMonitorInterval__ = threadMonitorInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThrowableMax()
    {
        return throwableMax__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThrowableMax(final int throwableMax)
    {
        throwableMax__ = throwableMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTraceDepth()
    {
        return traceDepth__;
    }

    /**
     * TraceDepthを設定する。
     * @param traceDepth TraceDepth
     */
    public void setTraceDepth(final int traceDepth)
    {
        traceDepth__ = traceDepth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAcceptPortIsRange()
    {
        return isAcceptPortIsRange__;
    }

    /**
     * AcceptPortIsRangeを設定する。
     * @param isAcceptPortIsRange AcceptPortIsRange
     */
    public void setAcceptPortIsRange(final boolean isAcceptPortIsRange)
    {
        isAcceptPortIsRange__ = isAcceptPortIsRange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlarmException()
    {
        return isAlarmException__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlarmException(final boolean isAlarmException)
    {
        isAlarmException__ = isAlarmException;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isArgsDetail()
    {
        return isArgsDetail__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArgsDetail(final boolean isArgsDetail)
    {
        isArgsDetail__ = isArgsDetail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClassNameSimplify()
    {
        return isClassNameSimplify__;
    }

    /**
     * ClassNameSimplifyを設定する。
     * @param isClassNameSimplify
     */
    public void setClassNameSimplify(final boolean isClassNameSimplify)
    {
        isClassNameSimplify__ = isClassNameSimplify;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCollectionMonitor()
    {
        return isCollectionMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCollectionMonitor(final boolean isCollectionMonitor)
    {
        isCollectionMonitor__ = isCollectionMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConcurrentAccessMonitored()
    {
        return isConcurrentAccessMonitored__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConcurrentAccessMonitored(final boolean isConcurrentAccessMonitored)
    {
        isConcurrentAccessMonitored__ = isConcurrentAccessMonitored;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDeadLockMonitor()
    {
        return isDeadLockMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeadLockMonitor(final boolean isDeadLockMonitor)
    {
        isDeadLockMonitor__ = isDeadLockMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEjbSessionMonitor()
    {
        return isEjbSessionMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEjbSessionMonitor(final boolean isEjbSessionMonitor)
    {
        isEjbSessionMonitor__ = isEjbSessionMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFileInputMonitor()
    {
        return isFileInputMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileInputMonitor(final boolean isFileInputMonitor)
    {
        isFileInputMonitor__ = isFileInputMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFileOutputMonitor()
    {
        return isFileOutputMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileOutputMonitor(final boolean isFileOutputMonitor)
    {
        isFileOutputMonitor__ = isFileOutputMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinalizationCount()
    {
        return isFinalizationCount__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFinalizationCount(final boolean isFinalizationCount)
    {
        isFinalizationCount__ = isFinalizationCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFullGCMonitor()
    {
        return isFullGCMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFullGCMonitor(final boolean isFullGCMonitor)
    {
        isFullGCMonitor__ = isFullGCMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHttpSessionCount()
    {
        return isHttpSessionCount__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHttpSessionCount(final boolean isHttpSessionCount)
    {
        isHttpSessionCount__ = isHttpSessionCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHttpSessionSize()
    {
        return isHttpSessionSize__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHttpSessionSize(final boolean isHttpSessionSize)
    {
        isHttpSessionSize__ = isHttpSessionSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIntervalMonitor()
    {
        return isIntervalMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIntervalMonitor(final boolean isIntervalMonitor)
    {
        isIntervalMonitor__ = isIntervalMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLinearSearchMonitor()
    {
        return isLinearSearchMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLinearSearchMonitor(final boolean isLinearSearchMonitor)
    {
        isLinearSearchMonitor__ = isLinearSearchMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogArgs()
    {
        return isLogArgs__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogArgs(final boolean isLogArgs)
    {
        isLogArgs__ = isLogArgs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogJvnFile()
    {
        return isLogJvnFile__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogJvnFile(final boolean isLogJvnFile)
    {
        isLogJvnFile__ = isLogJvnFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogMBeanInfo()
    {
        return isLogMBeanInfo__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogMBeanInfo(final boolean isLogMBeanInfo)
    {
        isLogMBeanInfo__ = isLogMBeanInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogMBeanInfoRoot()
    {
        return isLogMBeanInfoRoot__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogMBeanInfoRoot(final boolean isLogMBeanInfoRoot)
    {
        isLogMBeanInfoRoot__ = isLogMBeanInfoRoot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogReturn()
    {
        return isLogReturn__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogReturn(final boolean isLogReturn)
    {
        isLogReturn__ = isLogReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogStacktrace()
    {
        return isLogStacktrace__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogStacktrace(final boolean isLogStacktrace)
    {
        isLogStacktrace__ = isLogStacktrace;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogZipMax()
    {
        return isLogZipMax__;
    }

    /**
     * LogZipMaxを設定する。
     * @param isLogZipMax
     */
    public void setLogZipMax(final boolean isLogZipMax)
    {
        isLogZipMax__ = isLogZipMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNetInputMonitor()
    {
        return isNetInputMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNetInputMonitor(final boolean isNetInputMonitor)
    {
        isNetInputMonitor__ = isNetInputMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNetOutputMonitor()
    {
        return isNetOutputMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNetOutputMonitor(final boolean isNetOutputMonitor)
    {
        isNetOutputMonitor__ = isNetOutputMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRecordJMX()
    {
        return isRecordJMX__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecordJMX(final boolean isRecordJMX)
    {
        isRecordJMX__ = isRecordJMX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRecordStrategy()
    {
        return isRecordStrategy__;
    }

    /**
     * RecordStrategyを設定する。
     * @param isRecordStrategy RecordStrategy
     */
    public void setRecordStrategy(final boolean isRecordStrategy)
    {
        isRecordStrategy__ = isRecordStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReturnDetail()
    {
        return isReturnDetail__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReturnDetail(final boolean isReturnDetail)
    {
        isReturnDetail__ = isReturnDetail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSkipClassOnProcessing()
    {
        return isSkipClassOnProcessing__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkipClassOnProcessing(final boolean isSkipClassOnProcessing)
    {
        isSkipClassOnProcessing__ = isSkipClassOnProcessing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTatEnabled()
    {
        return isTatEnabled__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTatEnabled(final boolean isTatEnabled)
    {
        isTatEnabled__ = isTatEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isThreadContentionMonitor()
    {
        return isThreadContentionMonitor__;
    }

    /**
     * ThreadContentionMonitorを設定する。
     * @param isThreadContentionMonitor ThreadContentionMonitor
     */
    public void setThreadContentionMonitor(final boolean isThreadContentionMonitor)
    {
        isThreadContentionMonitor__ = isThreadContentionMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isThreadDump()
    {
        return isThreadDump__;
    }

    /**
     * ThreadDumpを設定する。
     * @param isThreadDump ThreadDump
     */
    public void setThreadDump(final boolean isThreadDump)
    {
        isThreadDump__ = isThreadDump;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimeoutMonitor()
    {
        return isTimeoutMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimeoutMonitor(final boolean isTimeoutMonitor)
    {
        isTimeoutMonitor__ = isTimeoutMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetAlarmListeners()
    {
        return super.isSetAlarmListeners();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetAlarmThreshold()
    {

        return super.isSetAlarmThreshold();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetArgsDetail()
    {

        return super.isSetArgsDetail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetArgsDetailDepth()
    {

        return super.isSetArgsDetailDepth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetEndCalleeName()
    {

        return super.isSetEndCalleeName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetIntervalMax()
    {

        return super.isSetIntervalMax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetJavelinFileDir()
    {

        return super.isSetJavelinFileDir();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetLogArgs()
    {

        return super.isSetLogArgs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetLogReturn()
    {
        return super.isSetLogReturn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetLogStacktrace()
    {
        return super.isSetLogStacktrace();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetReturnDetail()
    {
        return super.isSetReturnDetail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetReturnDetailDepth()
    {
        return super.isSetReturnDetailDepth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetRootCallerName()
    {
        return super.isSetRootCallerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetSerializeFile()
    {
        return super.isSetSerializeFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetTelegramListener()
    {
        return super.isSetTelegramListener();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetThreadModel()
    {
        return super.isSetThreadModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSetThrowableMax()
    {

        return super.isSetThrowableMax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void overwriteProperty(final Properties properties)
    {
        super.overwriteProperty(properties);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThreadDumpMonitor(final boolean threadDumpMonitor)
    {
        isThreadDump__ = threadDumpMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBooleanValue(final String key)
    {
        super.updateBooleanValue(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateIntValue(final String key)
    {
        super.updateIntValue(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLongValue(final String key)
    {
        super.updateLongValue(key);
    }

    @Override
    public long getAcceptDelay()
    {
        return acceptDelay__;
    }

    @Override
    public int getCpuTimeUnit()
    {
        return cpuTimeUnit__;
    }

    /**
     * CpuTimeUnitを設定する。
     * @param cpuTimeUnit CpuTimeUnit
     */
    public void setCpuTimeUnit(final int cpuTimeUnit)
    {
        cpuTimeUnit__ = cpuTimeUnit;
    }

    @Override
    public boolean isLogHttpSession()
    {
        return isLogHttpSession__;
    }

    @Override
    public void setLogHttpSession(final boolean isLogHttpSession)
    {
        isLogHttpSession__ = isLogHttpSession;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMonitorJmxResource()
    {
        return monitorJmxResource__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMonitorJmxResource(final boolean monitorJmxResource)
    {
        monitorJmxResource__ = monitorJmxResource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getCollectSystemResources()
    {
        return collectSystemResources__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCollectSystemResources(final boolean collectSystemResources)
    {
        collectSystemResources__ = collectSystemResources;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getSendInvocationFullEvent()
    {
        return sendInvocationFullEvent__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSendInvocationFullEvent(final boolean sendInvocationFullEvent)
    {
        sendInvocationFullEvent__ = sendInvocationFullEvent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMethodStallInterval()
    {
        return methodStallInterval__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMethodStallThreshold()
    {
        return methodStallThreshold__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMethodStallMonitor()
    {
        return isMethodStallMonitor__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMethodStallInterval(final int methodStallInterval)
    {
        methodStallInterval__ = methodStallInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMethodStallMonitor(final boolean methodStallMonitor)
    {
        isMethodStallMonitor__ = methodStallMonitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMethodStallThreshold(final int methodStallThreshold)
    {
        methodStallThreshold__ = methodStallThreshold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMethodStallTraceDepth(final int methodStallTraceDepth)
    {
        methodStallTraceDepth__ = methodStallTraceDepth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMethodStallTraceDepth()
    {
        return methodStallTraceDepth__;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHttpStatusError(final boolean httpStatusError)
    {
        httpStatusError__ = httpStatusError;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHttpStatusError()
    {
        return httpStatusError__;
    }

    @Override
    public String getConnectionMode()
    {
        return connectionMode__;
    }

    @Override
    public void setConnectionMode(final String connectionMode)
    {
        connectionMode__ = connectionMode;
    }

    /**
     * @return itemNamePrefix
     */
    @Override
    public String getItemNamePrefix()
    {
        return itemNamePrefix__;
    }

    /**
     * @param itemNamePrefix セットする itemNamePrefix
     */
    public void setItemNamePrefix(final String itemNamePrefix)
    {
        itemNamePrefix__ = itemNamePrefix;
    }

    /**
     * @return itemNameNoPrefixList
     */
    @Override
    public String getItemNameNoPrefixList()
    {
        return itemNameNoPrefixList__;
    }

    /**
     * @param itemNameNoPrefixList セットする itemNameNoPrefixList
     */
    public void setItemNameNoPrefixList(final String itemNameNoPrefixList)
    {
        itemNameNoPrefixList__ = itemNameNoPrefixList;
    }

    /**
     * @return clusterName
     */
    @Override
    public String getClusterName()
    {
        return clusterName__;
    }

    /**
     * @param clusterName セットする clusterName
     */
    public void setClusterName(final String clusterName)
    {
        clusterName__ = clusterName;
    }

    /**
     * @return collectHadoopAgentResources
     */
    @Override
    public boolean isCollectHadoopAgentResources()
    {
        return collectHadoopAgentResources__;
    }

    /**
     * @param collectHadoopAgentResources セットする collectHadoopAgentResources
     */
    public void setCollectHadoopAgentResources(final boolean collectHadoopAgentResources)
    {
        collectHadoopAgentResources__ = collectHadoopAgentResources;
    }

    /**
     * @return collectHadoopAgentResources
     */
    @Override
    public boolean isCollectHBaseAgentResources()
    {
        return collectHBaseAgentResources__;
    }

    /**
     * @param collectHadoopAgentResources セットする collectHadoopAgentResources
     */
    public void setCollectHBaseAgentResources(final boolean collectHadoopAgentResources)
    {
        collectHBaseAgentResources__ = collectHadoopAgentResources;
    }

    @Override
    public int getInvocationNameLimitLength()
    {
        return invocationNameLimitLength__;
    }

    /**
     * InvocationNameLimitLengthを設定する。
     * @param invocationNameLimitLength InvocationNameLimitLength
     */
    public void setInvocationNameLimitLength(final int invocationNameLimitLength)
    {
        invocationNameLimitLength__ = invocationNameLimitLength;
    }

    @Override
    public boolean isJdbcjavelinLightweightMode()
    {
        return jdbcjavelinLightweightMode__;
    }

    @Override
    public void setJdbcjavelinLightweightMode(final boolean jdbcjavelinLightweightMode)
    {
        jdbcjavelinLightweightMode__ = jdbcjavelinLightweightMode;
    }

    @Override
    public Pattern getServletExcludePattern()
    {
        return servletExcludePattern__;
    }

    @Override
    public void setServletExcludePattern(final Pattern runtimeExcludePattern)
    {
        servletExcludePattern__ = runtimeExcludePattern;
    }

    @Override
    public int getServletExcludePatternCacheSize()
    {
        return servletExcludePatternCacheSize__;
    }

    @Override
    public void setServletExcludePatternCacheSize(final int servletExcludePatternCacheSize)
    {
        servletExcludePatternCacheSize__ = servletExcludePatternCacheSize;
    }

    @Override
    public boolean isSslEnable()
    {
        return sslEnable__;
    }

    @Override
    public String getSslKeystore()
    {
        return sslKeyStore__;
    }

    @Override
    public String getSslKeystorePass()
    {
        return sslKeyStorePass__;
    }

    @Override
    public String getSslTruststore()
    {
        return sslTrustStore__;
    }

    @Override
    public String getSslTruststorePass()
    {
        return sslTrustStorePass__;
    }

    @Override
    public String getHttpCookieKey()
    {
        return httpCookieKey__;
    }

}
