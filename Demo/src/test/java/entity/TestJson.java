package entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.result.RelationMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/4/16 10:47
 */
public class TestJson {
    @Test
    public void toJson(){
        List<RelationMap> relationMapList = new ArrayList<>();
        RelationMap relationMap = new RelationMap();
        relationMap.setFirstClass("class1");
        relationMap.setSecondClass("class2");
        relationMap.setRelationCode(2);
        RelationMap relationMap1 = new RelationMap();
        relationMap1.setFirstClass("class1");
        relationMap1.setSecondClass("class3");
        relationMap1.setRelationCode(3);
        relationMapList.add(relationMap);
        relationMapList.add(relationMap1);

        System.out.println(JSON.toJSON(relationMapList));
    }
}
