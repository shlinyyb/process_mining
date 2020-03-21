package entity;

import java.util.List;

/**
 * @Description: 用于扫描保存类信息
 * @Author: Kobe
 * @Date: 2020/3/21 21:51
 */
public class ClassInfo {
    private String className;
    private List<ClassFunction> functions;
    private List<ClassAttribute> attributes;
}
