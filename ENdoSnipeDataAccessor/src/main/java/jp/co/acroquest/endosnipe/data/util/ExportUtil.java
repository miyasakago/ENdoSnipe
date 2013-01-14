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
package jp.co.acroquest.endosnipe.data.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import jp.co.acroquest.endosnipe.common.util.IOUtil;
import jp.co.acroquest.endosnipe.data.dao.JavelinLogDao;
import jp.co.acroquest.endosnipe.data.db.DBManager;
import jp.co.acroquest.endosnipe.data.entity.JavelinLog;

public class ExportUtil
{
    /** �J�n�^�I���������w�肷�镶����`���B */
    private static final String TIME_FORMAT = "yyyyMMdd_HHmmss";

    public static void main(String[] args)
    {
        if (args.length < 5)
        {
            System.out.println("usage: ExportUtil db_host db_port db_user db_password dbName startTime endTime");
            System.out.println("startTime format=\"" + TIME_FORMAT + "\"");
            System.out.println("endTime format=\"" + TIME_FORMAT + "\"");
            return;
        }

        // DB�̏��ݒ���擾
        String dbHost = args[0];
        String dbPort = args[1];
        String dbUser = args[2];
        String dbPass = args[3];
        String dbName = args[4];

        String startTime = null;
        String endTime = null;

        if (args.length > 5)
        {
            startTime = args[5];
        }
        if (args.length > 6)
        {
            endTime = args[6];
        }

        // ���|�[�g�̏o�͐�ݒ�
        String reportPath = "./jvn_logs";

        // ���|�[�g�쐬�Ɏg�p����DB���w�肷��
        DBManager.updateSettings(false, "", dbHost, dbPort, dbUser, dbPass);

        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        Timestamp start = null;
        try
        {
            if(startTime != null)
            {
                start = new Timestamp(format.parse(startTime).getTime());
            }
        }
        catch (ParseException ex)
        {
            System.out.println("start time format invalid:" + args[5]);
        }
        Timestamp end = null;
        try
        {
            if(endTime != null)
            {
                end = new Timestamp(format.parse(endTime).getTime());
            }
        }
        catch (ParseException ex)
        {
            System.out.println("end time format invalid:" + args[6]);
        }

        // Javelin���O���o�͂���B
        outputJvnLog(dbName, start, end, reportPath);
    }

    /**
     * Javelin���O���f�[�^�x�[�X����ǂݍ��݁A�t�@�C���o�͂���B
     * 
     * @param database �f�[�^�x�[�X��
     * @param start �J�n����
     * @param end �J�n����
     * @param outputDir �o�͐�f�B���N�g��
     * 
     * @return {@code true}����/{@code false}���s
     */
    public static boolean outputJvnLog(String database, Timestamp start, Timestamp end,
        String outputDir)
    {
        File outputDirFile = new File(outputDir);

        if (outputDirFile.exists() == false)
        {
            boolean isSuccess = outputDirFile.mkdirs();
            if (isSuccess == false)
            {
                System.err.println("jvn���O�o�̓f�B���N�g���̍쐬�Ɏ��s���܂����B");
                return false;
            }
        }

        try
        {
            List<JavelinLog> jvnLogList = JavelinLogDao.selectByTermWithLog(database, start, end);
            for (JavelinLog log : jvnLogList)
            {
                String fileName = log.logFileName;
                OutputStream output = null;
                try
                {
                    Timestamp startTime = log.startTime;
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(startTime.getTime());
                    
                    String outputSubDirName =
                        MessageFormat.format(
                                             "{0,number,0000}{1,number,00}{2,number,00}\\{3,number,00}{4,number,00}",
                                             cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                                             cal.get(Calendar.DAY_OF_MONTH),
                                             cal.get(Calendar.HOUR_OF_DAY),
                                             cal.get(Calendar.MINUTE));
                    
                    File ouputSubDir = new File(outputDir + File.separator + outputSubDirName);
                    if(ouputSubDir.exists() == false)
                    {
                        boolean isSuccess = ouputSubDir.mkdirs();
                        if (isSuccess == false)
                        {
                            System.err.println("jvn���O�o�̓f�B���N�g���̍쐬�Ɏ��s���܂����B");
                            return false;
                        }
                    }
                    output =
                        new BufferedOutputStream(new FileOutputStream(outputDir + File.separator
                            + outputSubDirName + File.separator + fileName));
                    IOUtil.copy(log.javelinLog, output);
                    System.out.println("outpu jvn:" + fileName);
                }
                catch (FileNotFoundException fnfe)
                {
                    System.err.println("jvn���O�o�̓f�B���N�g����������܂���B");
                }
                catch (IOException ioe)
                {
                    System.err.println("jvn���O�o�͒��ɗ�O���������܂����B");
                }
                finally
                {
                    if (output != null)
                    {
                        try
                        {
                            output.close();
                        }
                        catch (IOException ioe)
                        {
                            System.err.println("jvn���O�N���[�Y���ɗ�O���������܂����B");
                        }
                    }
                }
            }

        }
        catch (SQLException sqle)
        {
            System.err.println("DB�����jvn���O�ǂݍ��ݒ��ɗ�O���������܂����B");
            return false;
        }

        return true;
    }

}