package entity.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 记录first对second的关系
 * @Author: Kobe
 * @Date: 2020/3/30 16:46
 */
@Setter
@Getter
public class RelationMap {
    private String firstClass;
    private String secondClass;
    private Integer relationCode;
}
