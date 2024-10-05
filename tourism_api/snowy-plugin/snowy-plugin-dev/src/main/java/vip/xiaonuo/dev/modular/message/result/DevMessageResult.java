
package vip.xiaonuo.dev.modular.message.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 站内信结果
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class DevMessageResult {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 2)
    private String category;

    /** 主题 */
    @ApiModelProperty(value = "主题", position = 3)
    private String subject;

    /** 正文 */
    @ApiModelProperty(value = "正文", position = 4)
    private String content;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 5)
    private String extJson;

    /** 接收信息集合 */
    @ApiModelProperty(value = "接收信息集合", position = 6)
    private List<DevReceiveInfo> receiveInfoList;

    /**
     * 接收信息类
     *
     * @author gtc
     *
     */
    @Getter
    @Setter
    public static class DevReceiveInfo {

        /** 接收人ID */
        @ApiModelProperty(value = "接收人ID", position = 1)
        private String receiveUserId;

        /** 接收人姓名 */
        @ApiModelProperty(value = "接收人姓名", position = 2)
        private String receiveUserName;

        /** 是否已读 */
        @ApiModelProperty(value = "是否已读", position = 3)
        private Boolean read;
    }
}
