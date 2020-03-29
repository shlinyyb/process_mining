package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

/**
 * @Description: 用于扫描保存类信息
 * @Author: Kobe
 * @Date: 2020/3/21 21:51
 */
@Getter
@Setter
public class ClassInfo {
    @NonNull
    private String className;

    private List<ClassFunction> functions;
    private List<ClassAttribute> attributes;

    public ClassInfo(String className){
        this.className = className;
    }
}
