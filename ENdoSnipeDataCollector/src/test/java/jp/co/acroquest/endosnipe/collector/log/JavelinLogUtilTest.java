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
package jp.co.acroquest.endosnipe.collector.log;

import java.util.Arrays;
import java.util.List;

import jp.co.acroquest.endosnipe.data.entity.JavelinLog;
import junit.framework.TestCase;

/**
 * @author eriguchi
 */
public class JavelinLogUtilTest extends TestCase
{
    public void testParseCall()
    {
        String javelinLogStr =
                "Call  ,2008/09/30 22:24:50.717,\"/employee/employeeList.html\",\"/endosnipe-demo\",\"unknown\",\"unknown\",\"root\",\"unknown\",\"\",\"http-8080-1@24(java.lang.Thread@1698eeb)\"";

        List<String> javelinElemList = Arrays.asList(javelinLogStr.split(","));

        JavelinLog javelinLog = new JavelinLog();
        JavelinLogUtil.parse(javelinLog, javelinElemList);

        System.out.println(javelinLog.startTime);
    }
}