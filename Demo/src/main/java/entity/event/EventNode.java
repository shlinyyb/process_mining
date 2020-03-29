package entity.event;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2020/3/20 13:05
 */
@Getter
@Setter
public class EventNode {
    private String className;
    private String status;
    private long timeStamp;
}
