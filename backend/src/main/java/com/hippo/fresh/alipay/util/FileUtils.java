package com.hippo.fresh.alipay.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: Dai Junfeng
 * @create: 2021-04-05
 */
public class FileUtils {

    public static String readFileOfTxt(String path){

        StringBuilder sb = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            char[] buf = new char[1024];
            int len = -1;
            while ((len = bufferedReader.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
