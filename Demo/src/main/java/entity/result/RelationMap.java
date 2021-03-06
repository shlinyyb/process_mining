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

    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }

        if (object == null){
            return false;
        }

        if (object instanceof RelationMap){
            RelationMap relationMap = (RelationMap) object;
            return relationMap.getFirstClass().equalsIgnoreCase(this.firstClass) && relationMap.getSecondClass().equalsIgnoreCase(this.secondClass) &&
                    relationMap.getRelationCode().equals(this.getRelationCode());
        }

        return false;
    }

    @Override
    public int hashCode(){
        return firstClass.hashCode()*secondClass.hashCode()*relationCode.hashCode();
    }
}
