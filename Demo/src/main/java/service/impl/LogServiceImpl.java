package service.impl;

import entity.LogDemo;
import entity.RelationEnum;
import entity.result.CallMap;
import entity.result.RelationMap;
import service.process.LogService;

import java.util.*;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/3/28 11:59
 */
public class LogServiceImpl implements LogService {
//    @Override
//    public Map<String, List<LogDemo>> logGroup(List<LogDemo> logDemoList, String condition) {
//        return logDemoList.stream().collect(Collectors.groupingBy(LogDemo::getFuncThread));
//    }

    @Override
    public Set<Map.Entry<CallMap, CallMap>> processLog(List<LogDemo> logDemoList) {
        //输入日志长度校验
//        if (logDemoList.size() == 0 || logDemoList.size() == 2 || logDemoList.size() % 2 == 0) {
//            System.out.println("日志长度有问题");
//            return null;
//        }

        // 保存调用关系
        Set<Map.Entry<CallMap, CallMap>> entryList = new HashSet<>();


        Stack<LogDemo> logDemoStack = new Stack<>();
        logDemoStack.push(logDemoList.get(0));

        int start = 1;

        while (start < logDemoList.size()) {
            if (logDemoList.get(start).getStatus().equalsIgnoreCase("start")) {
                logDemoStack.push(logDemoList.get(start));
            } else {
                logDemoStack.pop();
                // 注意对递归的处理,以及最后的元素处理
                if (!logDemoStack.isEmpty()) {
                    if (!logDemoStack.peek().getClassName().equalsIgnoreCase(logDemoList.get(start).getClassName())) {
                        CallMap firstCallMap = new CallMap();
                        firstCallMap.setClassName(logDemoStack.peek().getClassName());
                        firstCallMap.setFunction(logDemoStack.peek().getNowFunction());
                        CallMap secondCallMap = new CallMap();
                        secondCallMap.setClassName(logDemoList.get(start).getClassName());
                        secondCallMap.setFunction(logDemoList.get(start).getNowFunction());
                        Map.Entry<CallMap, CallMap> entry = new AbstractMap.SimpleEntry<>(firstCallMap, secondCallMap);
                        entryList.add(entry);
                    }
                }
            }
            start++;
        }

        return entryList;
    }

    @Override
    public Set<RelationMap> processInherit(List<LogDemo> logDemoList) {
        Set<RelationMap> relationMapList = new HashSet<>();

        for (LogDemo logDemo : logDemoList) {
            if (!logDemo.getSuperClass().equalsIgnoreCase("Nosuperclass")) {
                RelationMap relationMap = new RelationMap();
                relationMap.setFirstClass(logDemo.getClassName());
                relationMap.setSecondClass(logDemo.getSuperClass());
                relationMap.setRelationCode(RelationEnum.INHERIT.getRelationCode());
                relationMapList.add(relationMap);
            }

            // 实现关系挖掘
            if (!logDemo.getInterfaces().isEmpty()) {
                if (!logDemo.getInterfaces().get(0).equalsIgnoreCase("Nointerfaces")) {
                    for (String tempInterface : logDemo.getInterfaces()) {
                        RelationMap relationMap = new RelationMap();
                        relationMap.setFirstClass(logDemo.getClassName());
                        relationMap.setSecondClass(tempInterface);
                        relationMap.setRelationCode(RelationEnum.IMPL.getRelationCode());
                        relationMapList.add(relationMap);
                    }
                }
            }
        }

        return relationMapList;
    }
}
