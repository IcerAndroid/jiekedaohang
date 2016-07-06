package com.yxr.jiekedaohang.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by icer on 16/6/7.
 */
public class FileUtils {

    /**
     * 按行读取txt
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static String readTextFromIs(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
