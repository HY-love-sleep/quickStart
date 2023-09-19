package com.hy.springbootquickstart.config;

/**
 * Description: 自定义线程工厂
 * Author: yhong
 * Date: 2023/9/18
 */

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    private final String threadNamePrefix;

    public MyThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(threadNamePrefix + "-" + System.nanoTime());
        return thread;
    }
}

