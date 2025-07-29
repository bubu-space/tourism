
package com.snowy.dev.api;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件API接口，可参考com.snowy.dev.core.util.file包下的工具类扩展更多需要的方法
 *
 * @author snowy
 * 
 **/
public interface DevFileApi {

    /* =========本地文件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     * @author snowy
     * 
     **/
    String storageFileWithReturnUrlLocal(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     * @author snowy
     * 
     **/
    String storageFileWithReturnIdLocal(MultipartFile file);

    /* =========阿里云文件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     * @author snowy
     * 
     **/
    String storageFileWithReturnUrlAliyun(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     * @author snowy
     * 
     **/
    String storageFileWithReturnIdAliyun(MultipartFile file);

    /* =========腾讯云件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     * @author snowy
     * 
     **/
    String storageFileWithReturnUrlTencent(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     * @author snowy
     * 
     **/
    String storageFileWithReturnIdTencent(MultipartFile file);

    /* =========MINIO件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     * @author snowy
     * 
     **/
    String storageFileWithReturnUrlMinio(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     * @author snowy
     * 
     **/
    String storageFileWithReturnIdMinio(MultipartFile file);
}
