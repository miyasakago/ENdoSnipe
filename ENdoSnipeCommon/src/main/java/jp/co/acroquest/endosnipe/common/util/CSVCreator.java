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
package jp.co.acroquest.endosnipe.common.util;

import java.util.Collection;

/**
 * CSV�𐶐����郆�[�e�B���e�B�N���X�ł��B<br />
 *
 * @author Tatsuo Suzuki
 * @author Nakamura Yuuki
 */
public class CSVCreator
{
    /**
     * {@link CSVCreator} ���\�z���܂��B<br />
     */
    public CSVCreator()
    {
    }

    /**
     * String�^�̔z����ACSV�`���ɏo�͂���B
     * �z���null���܂܂�Ă���ꍇ�̓���́AsetNullTreatment()�ɂ���Č��܂�B
     * �ǂ̃N�H�[�e�[�V�������������邩�́AsetQuotation()�ɂ���Č��܂�B
     *
     * @param list String�^�̔z��
     * @return �ϊ����ꂽCSV�`���̕�����
     * @throws NullPointerException
     * @see #setNullTreatment()
     * @see #setQuotation()
     */
    public String createCSVString(final String[] list)
    {
        // ���ʏo�͗p�̃o�b�t�@
        StringBuffer buffer = new StringBuffer();

        for (int cnt = 0; cnt < list.length; cnt++)
        {
            String element = list[cnt];
            if (element == null)
            {
                if (getNullTreatment() == CSVCreator.NULL_TO_EMPTY_STRING())
                {
                    element = "";
                }
                else
                {
                    throw new NullPointerException("list[" + cnt + "] is null");
                }
            }
            // �󕶎���ł͖����ꍇ�A�_�u���N�H�[�e�[�V�����ň͂ށB
            if (element.length() > 0)
            {
                if (getQuotation() == CSVCreator.DOUBLE_QUOTATION())
                {
                    element = insertDoubleQuote(element);
                }
            }
            buffer.append(element);
            // �Ō�̗v�f�Ŗ�������A","������B
            if (cnt < list.length - 1)
            {
                buffer.append(CSVCreator.SEPARATOR);
            }
        }
        return buffer.toString();
    }

    /**
     * @param element ���̕�����B
     * @return �O��Ƀ_�u���N�H�[�g������t������������B
     */
    private String insertDoubleQuote(final String element)
    {
        // ������Ƀ_�u���N�H�[�e�[�V�������܂܂�Ă�����A�_�u���N�H�[�e�[�V����
        // ��ɒu��������B
        StringBuffer tmpBuf = new StringBuffer();
        for (int cnt = 0; cnt < element.length(); cnt++)
        {
            char chr = element.charAt(cnt);
            tmpBuf.append(chr);
            if (chr == CSVCreator.DOUBLE_QUOTE_CHAR)
            {
                tmpBuf.append(CSVCreator.DOUBLE_QUOTE_CHAR);
            }
        }
        return tmpBuf.toString();
    }

    /**
     * �R���N�V������ CSV �`���ɕϊ����܂��B<br />
     *
     * @param list �R���N�V����
     * @return �R���N�V�����̂��ׂĂ̗v�f���J���}�Ō�������������
     * @see #createCSVString(String[])
     */
    public String createCSVString(final Collection<?> list)
    {
        return createCSVString(list.toArray(new String[0]));
    }

    //:=====================================================

    /**
     * String�^�̔z����ACSV�`���ɏo�͂���B
     * �z���null���܂܂�Ă���ꍇ�́ANullPointerException����������B
     * �݊����̃e�X�g������������A���̃��\�b�h�̎�����createCSVString(String[])�ɒu��������B
     *
     * @param list String�^�̔z��
     * @return �ϊ����ꂽCSV�`���̕�����
     */
    public static String createCSV(final String[] list)
    {
        try
        {
            // ���ʏo�͗p�̃o�b�t�@
            StringBuffer buffer = new StringBuffer();

            for (int listCnt = 0; listCnt < list.length; listCnt++)
            {
                String element = list[listCnt];

                // �󕶎���ł͖����ꍇ�A�_�u���N�H�[�e�[�V�����ň͂ށB
                if (element.length() > 0)
                {
                    // ������Ƀ_�u���N�H�[�e�[�V�������܂܂�Ă�����A�_�u���N�H�[�e�[�V����
                    // ��ɒu��������B
                    if (element.indexOf("\"") > -1)
                    {
                        StringBuffer tmpBuffer = new StringBuffer();
                        for (int cnt = 0; cnt < element.length(); cnt++)
                        {
                            char chr = element.charAt(cnt);
                            tmpBuffer.append(chr);

                            if (chr == '\"')
                            {
                                tmpBuffer.append('\"');
                            }
                        }
                        element = tmpBuffer.toString();
                    }

                    buffer.append("\"" + element + "\"");
                }

                // �Ō�̗v�f�Ŗ�������A","������B
                if (listCnt < list.length - 1)
                {
                    buffer.append(",");
                }
            }

            return buffer.toString();
        }
        catch (NullPointerException ex)
        {
            throw ex;
        }

    }

    /**
     * Collection���ACSV�`���ɏo�͂���B
     * Collection��null���܂܂�Ă���ꍇ�́ANullPointerException����������B
     * ������^�ɕϊ��ł��Ȃ��ꍇ�́AArrayStoreException����������B
     * �݊����̃e�X�g������������A���̃��\�b�h�̎�����createCSVString(Collection)�ɒu��������B
     *
     * @param list Collection
     * @return �ϊ����ꂽCSV�`���̕�����
     */
    public static String createCSV(final Collection<?> list)
    {
        try
        {
            String[] arrays = list.toArray(new String[0]);

            return createCSV(arrays);
        }
        catch (ArrayStoreException aex)
        {
            throw aex;
        }
        catch (NullPointerException nex)
        {
            throw nex;
        }
    }

    /**
     * �N�H�[�e�[�V�������Z�b�g���܂��B<br />
     *
     * @param newQuotation �N�H�[�e�[�V����
     */
    public void setQuotation(final int newQuotation)
    {
        quotation_ = newQuotation;
    }

    /**
     * �N�H�[�e�[�V������Ԃ��܂��B<br />
     *
     * �f�t�H���g�l�� DOUBLE_QUOTATION �B
     *
     * @return �N�H�[�e�[�V����
     */
    public int getQuotation()
    {
        return quotation_;
    }

    public void setNullTreatment(final int newNullTreatment)
    {
        nullTreatment_ = newNullTreatment;
    }

    /**
     * �f�t�H���g�l�� NULL_POINTER_EXCEPTION �B
     */
    public int getNullTreatment()
    {
        return nullTreatment_;
    }

    private int quotation_ = CSVCreator.DOUBLE_QUOTATION();

    private int nullTreatment_ = CSVCreator.NULL_POINTER_EXCEPTION();

    /**
     * null�ɑ��������ꍇ�ANullPointerException�𓊂���w���B
     */
    public static final int NULL_POINTER_EXCEPTION()
    {
        return 1 << CSVCreator.NULL_TREATMENT_GROUP;
    }

    /**
     * null�ɑ��������ꍇ�A""�Ƃ��Ĉ����w���B
     */
    public static final int NULL_TO_EMPTY_STRING()
    {
        return 2 << CSVCreator.NULL_TREATMENT_GROUP;
    }

    /**
     * null�ɑ��������ꍇ�̎w���̃r�b�g�͈́B
     */
    private static final int NULL_TREATMENT_GROUP = 0;

    /**
     * �N�H�[�e�[�V������t�����Ȃ��w���B
     */
    public static final int NO_QUOTATION()
    {
        return 1 << CSVCreator.QUOTATION_GROUP;
    }

    /**
     * �_�u���N�H�[�e�[�V����"\""��t������w���B
     */
    public static final int DOUBLE_QUOTATION()
    {
        return 2 << CSVCreator.QUOTATION_GROUP;
    }

    /**
     * �N�H�[�e�[�V������t������w���̃r�b�g�͈́B
     */
    private static final int QUOTATION_GROUP = 4;

    /**
     * CSV������̋�؂蕶���B
     */
    private static final String SEPARATOR = ",";

    /**
     * �����񒆂ɂ���_�u���N�H�[�e�[�V�����ɕt�����镶���B
     */
    private static final char DOUBLE_QUOTE_CHAR = '\"';
}