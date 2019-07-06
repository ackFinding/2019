package com.lock.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtils {


    /**
     *
     * @param runnable
     * @param delay 延迟执行时间
     * @param period 定时执行间隔
     */
    public static Timer scheduleEnduranceTask(String name,TimerTask runnable, long delay, long period) {
        Timer timer = new Timer(name,true);
        timer.scheduleAtFixedRate(runnable, delay, period);
        return timer;
    }

}
