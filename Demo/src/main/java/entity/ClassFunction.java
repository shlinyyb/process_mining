package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/3/20 13:03
 */
@Getter
@Setter
public class ClassFunction {
    Integer authority;
    String returnType;
    String functionName;
    List<ClassAttribute> funcAttrs;
}
