package entity.event;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 暂时弃用
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
