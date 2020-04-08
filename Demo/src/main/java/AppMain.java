import entity.ClassAttribute;
import entity.ClassInfo;
import entity.LogDemo;
import entity.RelationEnum;
import entity.result.RelationMap;
import service.impl.LogServiceImpl;
import utils.LogUtil;

import java.util.*;

/**
 * @Description: 程序主入口
 * @Author: Kobe
 * @Date: 2020/3/31 17:17
 */
public class AppMain {
    public static void main(String[] args) {
        String logFilePath = "log.txt";
        List<String> rawData = LogUtil.readLogFile(logFilePath, "utf-8");
        List<LogDemo> logDemoList = LogUtil.instantiate(rawData);

        LogServiceImpl logService = new LogServiceImpl();

        // set去重
        Set<ClassInfo> classInfoSet = new HashSet<>();
        for (LogDemo logDemo : logDemoList) {
            ClassInfo classInfo = new ClassInfo();
            classInfo.setClassName(logDemo.getClassName());
            classInfo.setAttributes(logDemo.getAllAttributes());
            classInfo.setFunctions(logDemo.getFuncDes());
            classInfoSet.add(classInfo);
        }

        // 加速查找
        HashMap<String, ClassInfo> classInfoHashMap = new HashMap<>();
        for (ClassInfo classInfo : classInfoSet) {
            classInfoHashMap.put(classInfo.getClassName(), classInfo);
        }


        // 挖掘事件关系
        Set<Map.Entry<String, String>> callMap = logService.processLog(logDemoList);

        // 记录关系，用list可以满足多种关系的情况
        List<RelationMap> relationList = new ArrayList<>();

        /*
         * 最终的目标为输出relationMap
         *
         */
        for (Map.Entry<String, String> callEntry : callMap) {
            ClassInfo firstClass = classInfoHashMap.get(callEntry.getKey());
            ClassInfo secondClass = classInfoHashMap.get(callEntry.getValue());

            RelationMap relationMap = new RelationMap();
            relationMap.setFirstClass(firstClass.getClassName());
            relationMap.setSecondClass(secondClass.getClassName());

            // 是否为全局变量
            for (ClassAttribute classAttribute : firstClass.getAttributes()){
                if (classAttribute.getReturnType().equals(secondClass.getClassName())){
                    relationMap.setRelationCode(RelationEnum.RELATED.getRelationCode());
                    break;
                }
            }

            relationList.add(relationMap);
        }

    }
}
