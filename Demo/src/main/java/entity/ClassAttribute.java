package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/3/20 12:56
 */
@Getter
@Setter
public class ClassAttribute {
    private String authority;
    private String returnType;
    private String attributeName;

    public ClassAttribute(String authority, String returnType, String attributeName) {
        this.authority = authority;
        this.returnType = returnType;
        this.attributeName = attributeName;
    }


}
