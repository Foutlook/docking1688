package cn.fxkoutlook.docking1688.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 单例定时器
 *
 * @author xingkai.fan
 * @create 2018 04 27
 */
public class SingletonTimer {
    private volatile static ScheduledExecutorService scheduledExecutorService;

    public static ScheduledExecutorService getTokenTimer(){
        if(scheduledExecutorService==null){
            synchronized (ScheduledExecutorService.class){
                if (scheduledExecutorService==null){
                    scheduledExecutorService = Executors.newScheduledThreadPool(1);
                }
            }
        }
        return scheduledExecutorService;
    }
    public static void setScheduledExecutorService(ScheduledExecutorService scheduledExecutor){
        scheduledExecutorService = scheduledExecutor;
    }
}