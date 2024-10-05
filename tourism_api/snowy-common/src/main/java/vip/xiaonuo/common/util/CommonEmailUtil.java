
package vip.xiaonuo.common.util;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import vip.xiaonuo.common.exception.CommonException;

/**
 * 通用邮件工具类
 *
 * @author gtc
 *
 **/
public class CommonEmailUtil {

    /**
     * 判断是否邮箱
     *
     * @author gtc
     *
     **/
    public static boolean isEmail(String email) {
        return  Validator.isEmail(email);
    }

    /**
     * 校验邮箱格式
     *
     * @author gtc
     *
     **/
    public static void validEmail(String emails) {
        StrUtil.split(emails, StrUtil.COMMA).forEach(email -> {
            if(!isEmail(email)) {
                throw new CommonException("邮件地址：{}格式错误", email);
            }
        });
    }
}
