package entity;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/3/20 13:03
 */
@Data
public class ClassFunction {
    String authority;
    String returnType;
    String functionName;
    List<ClassAttribute> funcAttrs;
}
