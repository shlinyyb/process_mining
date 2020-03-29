package service.process;

import entity.LogDemo;

import java.util.List;
import java.util.Map;

public interface LogService {
    /**
     * 按条件分类日志
     * @param logDemoList
     * @param condition
     * @return
     */
    public Map<String, List<LogDemo>> logGroup(List<LogDemo> logDemoList, String condition);

    /**
     * 事件关系挖掘
     * @param logDemoList
     * @return
     */
    public Map<String, String> processLog(List<LogDemo> logDemoList);
}
