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
package jp.co.acroquest.endosnipe.javelin.jdbc.common;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import jp.co.acroquest.endosnipe.common.logger.SystemLogger;

/**
 * Javelinのメッセージ取得クラス。
 * @author tooru
 */
public final class JdbcJavelinMessages
{
    /** メッセージプロパティファイルの名称 */
    private static final String BUNDLE_NAME = "JdbcJavelin";

    /**
     * デフォルトコンストラクタ
     */
    private JdbcJavelinMessages()
    {
        //何もしない
    }

    /**
     * リソースバンドルよりメッセージを取得する。
     * @param messageId メッセージID
     * @param args メッセージの引数
     * @return メッセージ
     */
    public static String getMessage(final String messageId, final Object... args)
    {
        String message = null;
        try
        {
            message = ResourceBundle.getBundle(BUNDLE_NAME).getString(messageId);
            message = MessageFormat.format(message, args);
        }
        catch (MissingResourceException mre)
        {
            // 無視する。
            SystemLogger.getInstance().warn(mre);
        }
        return message;
    }
}
