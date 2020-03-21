package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 日志读取初始化类
 * @Author: Kobe
 * @Date: 2020/3/20 17:04
 */
public class LogUtil {
    public static List<String> readLogFile(String filePath, String encode) {
        List<String> logDemoStrings = new ArrayList<String>();

        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader inputStreamReader = new InputStreamReader(
                        new FileInputStream(file), encode);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    logDemoStrings.add(lineTxt);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logDemoStrings;

    }
}
