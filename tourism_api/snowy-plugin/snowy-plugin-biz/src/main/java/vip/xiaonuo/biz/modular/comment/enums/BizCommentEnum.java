
package vip.xiaonuo.biz.modular.comment.enums;

import lombok.Getter;

/**
 * 评价留言表枚举
 *
 * @author gtc
 * 
 **/
@Getter
public enum BizCommentEnum {

    /** 测试 */
    TEST("TEST");

    private final String value;

    BizCommentEnum(String value) {
        this.value = value;
    }
}
