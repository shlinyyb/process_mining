package service.impl;

import entity.LogDemo;
import entity.RelationEnum;
import entity.result.RelationMap;
import service.process.LogService;
import utils.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/3/28 11:59
 */
public class LogServiceImpl implements LogService {
    @Override
    public Map<String, List<LogDemo>> logGroup(List<LogDemo> logDemoList, String condition) {
        return logDemoList.stream().collect(Collectors.groupingBy(LogDemo::getFuncThread));
    }

    @Override
    public Map<String, String> processLog(List<LogDemo> logDemoList) {
        //输入日志长度校验
        if (logDemoList.size() == 0 || logDemoList.size() == 2 || logDemoList.size() % 2 == 0) {
            return null;
        }


        // 先按时间顺序排序日志
        logDemoList = logDemoList.stream().sorted((o1, o2) -> (int) (Long.parseLong(o1.getTimeStamp()) - Long.parseLong(o2.getTimeStamp()))).collect(Collectors.toList());

        // 保存调用关系
        Map<String, String> callMap = new HashMap<>();


        Stack<LogDemo> logDemoStack = new Stack<>();
        logDemoStack.push(logDemoList.get(0));
        logDemoStack.push(logDemoList.get(1));

        int start = 2;

        while (start < logDemoList.size()) {
            if (logDemoList.get(start).getStatus().equalsIgnoreCase("start")) {
                logDemoStack.push(logDemoList.get(start));
            } else {
                logDemoStack.pop();
                // 注意对递归的处理
                if (!logDemoStack.peek().getClassName().equalsIgnoreCase(logDemoList.get(start).getClassName())) {
                    callMap.put(logDemoStack.peek().getClassName(), logDemoList.get(start).getClassName());
                }
            }
            start++;
        }

        return callMap;
    }

    @Override
    public List<RelationMap> processInherit(List<LogDemo> logDemoList) {
        List<RelationMap> relationMapList = new ArrayList<>();

        for (LogDemo logDemo : logDemoList) {
            if (!StringUtil.isMeaningLess(logDemo.getSuperClass())){
                RelationMap relationMap = new RelationMap();
                relationMap.setFirstClass(logDemo.getClassName());
                relationMap.setSecondClass(logDemo.getSuperClass());
                relationMap.setRelationCode(RelationEnum.RELATED.getRelationCode());
                relationMapList.add(relationMap);
            }
        }

        return relationMapList;
    }
}
