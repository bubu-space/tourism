
package vip.xiaonuo.dev.modular.job.enums;

import lombok.Getter;
import vip.xiaonuo.common.exception.CommonException;

/**
 * 定时任务分类枚举
 *
 * @author gtc
 *
 */
@Getter
public enum DevJobCategoryEnum {

    /**
     * 框架
     */
    FRM("FRM"),

    /**
     * 业务
     */
    BIZ("BIZ");

    private final String value;

    DevJobCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = FRM.getValue().equals(value) || BIZ.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的定时任务分类：{}", value);
        }
    }
}
