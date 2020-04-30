package entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/4/14 22:03
 */
public class TestEquals {
    @Test
    public void testEqual(){
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassName("class");
        ClassInfo classInfo1 = new ClassInfo();
        classInfo1.setClassName("class");
        Assert.assertEquals(classInfo, classInfo1);
    }
}
