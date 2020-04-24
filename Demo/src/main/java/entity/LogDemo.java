package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description: 存储日志
 * @Author: Kobe
 * @Date: 2020/3/20 12:35
 */
@Getter
@Setter
public class LogDemo {
    /**
     * 方法的完整描述
     */
    private List<ClassFunction> funcDes;

    /**
     * 方法执行状态
     */
    private String status;

    /**
     * 运行时刻时间戳
     */
    private String timeStamp;

    //public String funcThread;

    // private String resource;

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
    private List<String> interfaces;

    /**
     * 类的所有属性
     */
    private List<ClassAttribute> allAttributes;

    /**
     * 当前运行方法
     */
    private String nowFunction;
}
