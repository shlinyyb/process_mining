package entity;

import lombok.Getter;

@Getter
public enum RelationEnum {
    INHERIT("inherit", 1),
    DEPEND("depend", 2),
    RELATED("related", 3),
    AGGR("aggregation", 4);

    private String relation;
    private Integer relationCode;

    RelationEnum(String relation, Integer relationCode) {
        this.relation = relation;
        this.relationCode = relationCode;
    }
}
