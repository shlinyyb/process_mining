package entity;

import java.util.List;

/**
 * @Description: 存储日志
 * @Author: Kobe
 * @Date: 2020/3/20 12:35
 */
public class LogDemo {
    /**
     * 方法的完整描述
     */
    private String funcDes;

    /**
     * 方法执行状态
     */
    private String status;

    /**
     * 运行时刻时间戳
     */
    private String timeStamp;

    /**
     * 执行方法的线程
     */
    public String funcThread;

    /**
     * 通信资源
     */
    private String resource;

    /**
     * 类名称
     */
    private String className;

    /**
     * 父类
     */
    private String superClass;

    /**
     * 接口
     */
    private String interfaces;

    /**
     * 类的所有属性
     */
    private List<ClassAttribute> allAttributes;
}