package utils;

/**
 * @Description: 字符串统一处理工具类
 * @Author: Kobe
 * @Date: 2020/3/21 22:24
 */
public class StringUtil {
    public static Boolean isMeaningLess(String inputString){
        return inputString != null && !inputString.equals("") && !inputString.equalsIgnoreCase("null");
    }
}
