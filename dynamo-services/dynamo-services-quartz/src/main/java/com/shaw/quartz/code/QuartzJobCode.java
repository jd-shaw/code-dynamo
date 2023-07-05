package com.shaw.quartz.code;

/**
 * 运行状态
 *
 * @author shaw
 * @date 2023/7/5
 */
public interface QuartzJobCode {

    /**
     * 运行
     */
    int RUNNING = 1;

    /**
     * 停止
     */
    int STOP = 0;

}
