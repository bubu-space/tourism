
package vip.xiaonuo.dev.api;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件API接口，可参考vip.xiaonuo.dev.core.util.file包下的工具类扩展更多需要的方法
 *
 * @author gtc
 * 
 **/
public interface DevFileApi {

    /* =========本地文件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     * @author gtc
     * 
     **/
    String storageFileWithReturnUrlLocal(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     * @author gtc
     * 
     **/
    String storageFileWithReturnIdLocal(MultipartFile file);

    /* =========阿里云文件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     * @author gtc
     * 
     **/
    String storageFileWithReturnUrlAliyun(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     * @author gtc
     * 
     **/
    String storageFileWithReturnIdAliyun(MultipartFile file);

    /* =========腾讯云件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     * @author gtc
     * 
     **/
    String storageFileWithReturnUrlTencent(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     * @author gtc
     * 
     **/
    String storageFileWithReturnIdTencent(MultipartFile file);

    /* =========MINIO件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     * @author gtc
     * 
     **/
    String storageFileWithReturnUrlMinio(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     * @author gtc
     * 
     **/
    String storageFileWithReturnIdMinio(MultipartFile file);
}
