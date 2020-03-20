package entity;

import lombok.Data;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/3/20 12:56
 */
@Data
public class ClassAttribute {
    private String authority;
    private String returnType;
    private String attributeName;

    public ClassAttribute(String authority, String returnType, String attributeName){
        this.authority = authority;
        this.returnType = returnType;
        this.attributeName = attributeName;
    }



}
