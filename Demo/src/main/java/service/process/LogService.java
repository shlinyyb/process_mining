package service.process;

import entity.LogDemo;
import entity.result.RelationMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LogService {
    /**
     * 按条件分类日志
     *
     * @param logDemoList
     * @param condition
     * @return
     */
    public Map<String, List<LogDemo>> logGroup(List<LogDemo> logDemoList, String condition);

    /**
     * 事件关系挖掘
     *
     * @param logDemoList
     * @return
     */
    public Set<Map.Entry<String, String>> processLog(List<LogDemo> logDemoList);

    /**
     * 挖掘存在的继承/实现关系
     * @param logDemoList
     * @return
     */
    public List<RelationMap> processInherit(List<LogDemo> logDemoList);
}
