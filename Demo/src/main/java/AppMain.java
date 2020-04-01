import entity.LogDemo;
import service.impl.LogServiceImpl;
import utils.LogUtil;

import java.util.List;
import java.util.Map;

/**
 * @Description: 程序主入口
 * @Author: Kobe
 * @Date: 2020/3/31 17:17
 */
public class AppMain {
    public static void main(String[] args) {
        String logFilePath = "";
        List<String> rawData  = LogUtil.readLogFile(logFilePath, "utf-8");
        List<LogDemo> logDemoList = LogUtil.instantiate(rawData);

        // 挖掘事件关系
        // Map<String, String> callMap = logService.processLog(logDemoList);

    }
}
