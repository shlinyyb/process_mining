package utils;

import entity.*;

import java.io.*;
import java.util.*;

/**
 * @Description: 日志读取初始化类
 * @Author: Kobe
 * @Date: 2020/3/20 17:04
 */
public class LogUtil {
    // public static final

    public static List<String> readLogFile(String filePath, String encode) {
        List<String> logDemoStrings = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader inputStreamReader = new InputStreamReader(
                        new FileInputStream(file), encode);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineTxt;

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    logDemoStrings.add(lineTxt);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logDemoStrings;

    }

    /**
     * 切割属性
     */
    private static List<ClassAttribute> getAttr(String attrString) {
        List<ClassAttribute> classAttributes = new ArrayList<>();

        //处理为null的情况
        if (!StringUtil.isMeaningLess(attrString)) {
            return classAttributes;
        }

        String[] attribute = attrString.split(";");

        for (String s : attribute) {
            String[] tokens = s.split("\\?");
            if (tokens.length != 3) {
                break;
            }
            ClassAttribute classAttribute = new ClassAttribute();
            classAttribute.setAuthority(Integer.parseInt(tokens[0]));
            classAttribute.setReturnType(tokens[1]);
            classAttribute.setAttributeName(tokens[2]);
            classAttributes.add(classAttribute);
        }

        return classAttributes;
    }

    /**
     * 切割方法
     */
    private static List<ClassFunction> getFunc(String funcString) {
        List<ClassFunction> classFunctionList = new ArrayList<>();

        String[] funcStrings = funcString.split(";");
        // 此处有空指针风险
        // 还是得补Null
        for (String token : funcStrings) {
            String[] tokens = token.split("\\?");
            if (tokens.length != 4) {
                return classFunctionList;
            }

            ClassFunction classFunction = new ClassFunction();
            classFunction.setAuthority(Integer.parseInt(tokens[0]));
            classFunction.setReturnType(tokens[1]);
            classFunction.setFunctionName(tokens[2]);

            String[] attribute = tokens[3].split(",");
            List<ClassAttribute> classAttributeList = new ArrayList<>();

            for (String s : attribute) {
                String[] attrToken = s.split(":");
                if (attrToken.length != 2) {
                    break;
                }
                ClassAttribute classAttribute = new ClassAttribute();
                classAttribute.setAuthority(Authority.DEFAULT_AUTHORITY.statusCode);
                classAttribute.setReturnType(attrToken[0]);
                classAttribute.setAttributeName(attrToken[1]);
                classAttributeList.add(classAttribute);
            }
            classFunction.setFuncAttrs(classAttributeList);
            classFunctionList.add(classFunction);
        }
        return classFunctionList;
    }

    /**
     * 获取类名
     */
    private static String getClassName(String funcString) {
        int location = 0;
        for (int i = funcString.length() - 1; i > 0; i--) {
            if (funcString.charAt(i) == '(') {
                location = i;
                break;
            }
        }
        return funcString.substring(0, location - 1);
    }

    /**
     * 实例化日志
     */
    public static List<LogDemo> instantiate(List<String> rawData) {
        List<LogDemo> logDemoList = new ArrayList<>();
        for (String rawLog : rawData) {
            rawLog = rawLog.replace(" ","");
            String[] tokens = rawLog.split("\\|");
            LogDemo logDemo = new LogDemo();
            logDemo.setNowFunction(tokens[3]);
            logDemo.setFuncDes(getFunc(tokens[11]));
            logDemo.setStatus(tokens[4]);
            logDemo.setTimeStamp(tokens[5]);
            logDemo.setClassName(tokens[13]);
            logDemo.setSuperClass(tokens[8]);
            logDemo.setInterfaces(Arrays.asList(tokens[9].split(";")));
            logDemo.setAllAttributes(getAttr(tokens[10]));

            logDemoList.add(logDemo);
        }
        return logDemoList;
    }
}
