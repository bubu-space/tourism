
package com.snowy.dev.modular.monitor.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 服务器监控结果
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class DevMonitorServerResult {

    /* ==============概览数据============ */
    /** CPU信息 */
    @ApiModelProperty(value = "CPU信息", position = 1)
    private DevMonitorCpuInfo devMonitorCpuInfo;

    /** 内存信息 */
    @ApiModelProperty(value = "内存信息", position = 2)
    private DevMonitorMemoryInfo devMonitorMemoryInfo;

    /** 存储信息 */
    @ApiModelProperty(value = "存储信息", position = 3)
    private DevMonitorStorageInfo devMonitorStorageInfo;

    /** 网络信息 */
    @ApiModelProperty(value = "网络信息", position = 4)
    private DevMonitorNetworkInfo devMonitorNetworkInfo;

    /* ==============服务器数据============ */
    /** 服务器信息 */
    @ApiModelProperty(value = "服务器信息", position = 5)
    private DevMonitorServerInfo devMonitorServerInfo;

    /* ==============JVM数据============ */
    /** JVM信息 */
    @ApiModelProperty(value = "JVM信息", position = 6)
    private DevMonitorJvmInfo devMonitorJvmInfo;

    /**
     * CPU信息类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class DevMonitorCpuInfo {

        /** CPU名称 */
        @ApiModelProperty(value = "CPU名称", position = 1)
        private String cupName;

        /** CPU数量 */
        @ApiModelProperty(value = "CPU数量", position = 2)
        private String cupNum;

        /** CPU物理核心数 */
        @ApiModelProperty(value = "CPU物理核心数", position = 3)
        private String cpuPhysicalCoreNum;

        /** CPU逻辑核心数 */
        @ApiModelProperty(value = "CPU逻辑核心数", position = 4)
        private String cpuLogicalCoreNum;

        /** CPU系统使用率 */
        @ApiModelProperty(value = "CPU系统使用率", position = 5)
        private String cpuSysUseRate;

        /** CPU用户使用率 */
        @ApiModelProperty(value = "CPU用户使用率", position = 6)
        private String cpuUserUseRate;

        /** CPU当前总使用率 */
        @ApiModelProperty(value = "CPU当前总使用率", position = 7)
        private Double cpuTotalUseRate;

        /** CPU当前等待率 */
        @ApiModelProperty(value = "CPU当前等待率", position = 8)
        private String cpuWaitRate;

        /** CPU当前空闲率 */
        @ApiModelProperty(value = "CPU当前空闲率", position = 9)
        private String cpuFreeRate;
    }

    /**
     * 内存信息类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class DevMonitorMemoryInfo {

        /** 内存总量 */
        @ApiModelProperty(value = "内存总量", position = 1)
        private String memoryTotal;

        /** 内存已用 */
        @ApiModelProperty(value = "内存已用", position = 2)
        private String memoryUsed;

        /** 内存剩余 */
        @ApiModelProperty(value = "内存剩余", position = 3)
        private String memoryFree;

        /** 内存使用率 */
        @ApiModelProperty(value = "内存使用率", position = 4)
        private Double memoryUseRate;
    }

    /**
     * 存储信息
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class DevMonitorStorageInfo {

        /** 存储总量 */
        @ApiModelProperty(value = "存储总量", position = 1)
        private String storageTotal;

        /** 存储已用 */
        @ApiModelProperty(value = "存储已用", position = 2)
        private String storageUsed;

        /** 存储剩余 */
        @ApiModelProperty(value = "存储剩余", position = 3)
        private String storageFree;

        /** 存储使用率 */
        @ApiModelProperty(value = "存储使用率", position = 4)
        private Double storageUseRate;
    }

    /**
     * 网络信息类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class DevMonitorNetworkInfo {

        /** 上行速率 */
        @ApiModelProperty(value = "上行速率", position = 1)
        private String upLinkRate;

        /** 下行速率 */
        @ApiModelProperty(value = "下行速率", position = 2)
        private String downLinkRate;

    }

    /**
     * 服务器信息类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class DevMonitorServerInfo {

        /** 服务器名称 */
        @ApiModelProperty(value = "服务器名称", position = 1)
        private String serverName;

        /** 服务器操作系统 */
        @ApiModelProperty(value = "服务器操作系统", position = 2)
        private String serverOs;

        /** 服务器IP */
        @ApiModelProperty(value = "服务器IP", position = 3)
        private String serverIp;

        /** 服务器架构 */
        @ApiModelProperty(value = "服务器架构", position = 4)
        private String serverArchitecture;
    }

    /**
     * JVM信息类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class DevMonitorJvmInfo {

        /** JVM名称 */
        @ApiModelProperty(value = "JVM名称", position = 1)
        private String jvmName;

        /** JVM版本 */
        @ApiModelProperty(value = "JVM版本", position = 2)
        private String jvmVersion;

        /** JVM总分配内存 */
        @ApiModelProperty(value = "JVM总分配内存", position = 3)
        private String jvmMemoryTotal;

        /** JVM已用内存 */
        @ApiModelProperty(value = "JVM已用内存", position = 4)
        private String jvmMemoryUsed;

        /** JVM剩余内存 */
        @ApiModelProperty(value = "JVM剩余内存", position = 5)
        private String jvmMemoryFree;

        /** JVM内存使用率 */
        @ApiModelProperty(value = "JVM内存使用率", position = 6)
        private Double jvmUseRate;

        /** JVM启动时间 */
        @ApiModelProperty(value = "JVM启动时间", position = 7)
        private String jvmStartTime;

        /** JVM运行时长 */
        @ApiModelProperty(value = "JVM运行时长", position = 8)
        private String jvmRunTime;

        /** Java版本 */
        @ApiModelProperty(value = "Java版本", position = 9)
        private String javaVersion;

        /** Java安装路径 */
        @ApiModelProperty(value = "Java安装路径", position = 10)
        private String javaPath;
    }
}
