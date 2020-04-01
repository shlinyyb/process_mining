package entity;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class ClassInfo {
    @NonNull
    private String className;
    private List<ClassFunction> functions;
    private List<ClassAttribute> attributes;
}
