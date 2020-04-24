import com.alibaba.fastjson.JSON;
import entity.ClassAttribute;
import entity.ClassInfo;
import entity.LogDemo;
import entity.RelationEnum;
import entity.result.CallMap;
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
        String logFilePath = "D:\\GitHub\\process_mining\\Demo\\src\\main\\resources\\log.txt";
        List<String> rawData = LogUtil.readLogFile(logFilePath, "utf-8");
        List<LogDemo> logDemoList = LogUtil.instantiate(rawData);
        System.out.println("very good");

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
        System.out.println(classInfoSet.size());
        System.out.println("very good");

        // 加速查找
        HashMap<String, ClassInfo> classInfoHashMap = new HashMap<>();
        for (ClassInfo classInfo : classInfoSet) {
            classInfoHashMap.put(classInfo.getClassName(), classInfo);
        }

        System.out.println(JSON.toJSON(classInfoHashMap));

        // 挖掘事件关系
        Set<Map.Entry<CallMap, CallMap>> callMap = logService.processLog(logDemoList);
        System.out.println("very good");

        // 记录关系，用list可以满足多种关系的情况
        // 先保存继承，实现关系
        List<RelationMap> relationList = new ArrayList<>(logService.processInherit(logDemoList));

        /*
         * 最终的目标为输出relationMap
         *
         */
        for (Map.Entry<CallMap, CallMap> callEntry : callMap) {
            boolean isInherit = false;
            String firstFunc = callEntry.getKey().getFunction();
            firstFunc = firstFunc.substring(0, firstFunc.length() - 2);
            String secondFunc = callEntry.getValue().getFunction();
            secondFunc = secondFunc.substring(0, secondFunc.length() - 2);
            // 先已知继承关系
            for (RelationMap temp : relationList) {
                if (temp.getFirstClass().equalsIgnoreCase(firstFunc) && temp.getSecondClass().equalsIgnoreCase(secondFunc)
                        && temp.getRelationCode().equals(RelationEnum.INHERIT.getRelationCode())) {
                    isInherit = true;
                    break;
                }
            }
            if (!isInherit) {
                ClassInfo firstClass = classInfoHashMap.get(callEntry.getKey().getClassName());
                ClassInfo secondClass = classInfoHashMap.get(callEntry.getValue().getClassName());

                RelationMap relationMap = new RelationMap();
                relationMap.setFirstClass(firstClass.getClassName());
                relationMap.setSecondClass(secondClass.getClassName());

                boolean flag = false;

                // 是否为全局变量
                for (ClassAttribute classAttribute : firstClass.getAttributes()) {
                    if (classAttribute.getReturnType().equals(secondClass.getClassName())) {
                        relationMap.setRelationCode(RelationEnum.RELATED.getRelationCode());
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    relationMap.setRelationCode(RelationEnum.DEPEND.getRelationCode());
                }

                relationList.add(relationMap);
            }
        }
        System.out.println(relationList.size());

        for (RelationMap relationMap : relationList) {
            if (relationMap.getRelationCode() != 2) {
                System.out.println(relationMap.toString());
            }
        }
    }
}
