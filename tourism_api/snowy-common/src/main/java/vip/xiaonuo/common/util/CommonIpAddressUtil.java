
package vip.xiaonuo.common.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import vip.xiaonuo.common.exception.CommonException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;

/**
 * 根据ip地址定位工具类，离线方式
 * 参考地址：https://gitee.com/lionsoul/ip2region/tree/master/binding/java
 *
 * @author gtc
 *
 */
@Slf4j
public class CommonIpAddressUtil {

    private static final String LOCAL_REMOTE_HOST = "0:0:0:0:0:0:0:1";

    private static final Searcher searcher;

    static {
        String fileName = "/ip2region.xdb";
        File existFile = FileUtil.file(FileUtil.getTmpDir() + FileUtil.FILE_SEPARATOR + fileName);
        if(!FileUtil.exist(existFile)) {
            InputStream resourceAsStream = CommonIpAddressUtil.class.getResourceAsStream(fileName);
            if(ObjectUtil.isEmpty(resourceAsStream)) {
                throw new CommonException("CommonIpAddressUtil初始化失败，原因：IP地址库数据不存在");
            }
            FileUtil.writeFromStream(resourceAsStream, existFile);
        }

        String dbPath = existFile.getPath();

        // 1、从 dbPath 加载整个 xdb 到内存。
        byte[] cBuff;
        try {
            cBuff = Searcher.loadContentFromFile(dbPath);
        } catch (Exception e) {
            log.error(">>> CommonIpAddressUtil初始化异常：", e);
            throw new CommonException("CommonIpAddressUtil初始化异常");
        }

        // 2、使用上述的 cBuff 创建一个完全基于内存的查询对象。
        try {
            searcher = Searcher.newWithBuffer(cBuff);
        } catch (Exception e) {
            log.error(">>> CommonIpAddressUtil初始化异常：", e);
            throw new CommonException("CommonIpAddressUtil初始化异常");
        }
    }

    /**
     * 获取客户端ip
     *
     * @author gtc
     *
     */
    public static String getIp(HttpServletRequest request) {
        if (ObjectUtil.isEmpty(request)) {
            return Ipv4Util.LOCAL_IP;
        } else {
            try {
                String remoteHost = ServletUtil.getClientIP(request);
                return LOCAL_REMOTE_HOST.equals(remoteHost) ? Ipv4Util.LOCAL_IP : remoteHost;
            } catch (Exception e) {
                log.error(">>> 获取客户端ip异常：", e);
                return Ipv4Util.LOCAL_IP;
            }
        }
    }

    /**
     * 根据IP地址离线获取城市
     *
     * @author gtc
     *
     */
    public static String getCityInfo(String ip) {
        try {
            ip = ip.trim();
            // 3、执行查询
            String region = searcher.searchByStr(ip);
            return region.replace("0|", "").replace("|0", "");
        } catch (Exception e) {
            return "未知";
        }
    }
}
