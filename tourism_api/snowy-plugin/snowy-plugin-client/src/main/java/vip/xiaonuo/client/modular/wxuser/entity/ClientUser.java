
package vip.xiaonuo.client.modular.wxuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import vip.xiaonuo.common.pojo.CommonEntity;

/**
 * 用户表实体
 *
 * @author gtc
 **/
@Data
@TableName("biz_user")
public class ClientUser extends CommonEntity {

    /** ID */
    private String id;

    /** 用户名 */
    private String name;

    /** 手机号 */
    private String phone;

    /** 密码 */
    private String password;

    private String sex;

    private Integer age;

    /** 用户头像 */
    private String avatar;

    /** 用户状态 */
    private String status;

    private String idCard;
}
