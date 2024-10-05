
package vip.xiaonuo.dev.modular.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import vip.xiaonuo.dev.modular.file.entity.DevFile;
import vip.xiaonuo.dev.modular.file.param.DevFileIdParam;
import vip.xiaonuo.dev.modular.file.param.DevFileListParam;
import vip.xiaonuo.dev.modular.file.param.DevFilePageParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件Service接口
 *
 * @author gtc
 * 
 **/
public interface DevFileService extends IService<DevFile> {

    /**
     * MultipartFile文件上传，返回文件id
     *
     * @author gtc
     * 
     **/
    String uploadReturnId(String engine, MultipartFile file);

    /**
     * MultipartFile文件上传，返回文件Url
     *
     * @author gtc
     * 
     **/
    String uploadReturnUrl(String engine, MultipartFile file);

    /**
     * 文件分页列表接口
     *
     * @author gtc
     * 
     **/
    Page<DevFile> page(DevFilePageParam devFilePageParam);

    /**
     * 文件列表接口
     *
     * @author gtc
     * 
     **/
    List<DevFile> list(DevFileListParam devFileListParam);

    /**
     * 下载文件
     *
     * @author gtc
     * 
     **/
    void download(DevFileIdParam devFileIdParam, HttpServletResponse response) throws IOException;

    /**
     * 删除文件
     *
     * @author gtc
     * 
     **/
    void delete(List<DevFileIdParam> devFileIdParamList);

    /**
     * 获取文件详情
     *
     * @author gtc
     * 
     */
    DevFile detail(DevFileIdParam devFileIdParam);

    /**
     * 获取文件详情
     *
     * @author gtc
     * 
     */
    DevFile queryEntity(String id);
}
