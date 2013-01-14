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

import java.util.Map;
import jp.co.acroquest.endosnipe.javelin.util.concurrent.ConcurrentHashMap;

/**
 * �V�X�e���̏�Ԃ��Ǘ�����N���X�ł��B<br />
 * 
 * @author fujii
 *
 */
public class SystemStatusManager
{
    /** �V�X�e���̏�Ԃ��Ǘ�����Map */
    private static Map<String, Object> statusMap__ = new ConcurrentHashMap<String, Object>();

    /**
     * �C���X�^���X����j�~����private�R���X�g���N�^�ł��B<br />
     */
    private SystemStatusManager()
    {
        // Do Nothing.
    }

    /**
     * �V�X�e���̏�Ԃ�ۑ����܂��B<br />
     * 
     * @param key �L�[
     * @param value �l
     */
    public static void setValue(final String key, final Object value)
    {
        statusMap__.put(key, value);
    }

    /**
     * �V�X�e���̏�Ԃ��擾���܂��B<br />
     * 
     * @param key �L�[
     * @return �w�肳�ꂽ�L�[�ɑΉ�������
     */
    public static Object getValue(final String key)
    {
        return statusMap__.get(key);
    }
}