package com.hy.springbootquickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * Description: 全局线程池
 * Author: yhong
 * Date: 2023/9/18
 */
@Configuration
public class ThreadPoolConfig {
    @Bean(name = "globalTaskExecutor")
    public ThreadPoolExecutor globalTaskExecutor() {
        return new ThreadPoolExecutor(
                5,
                10,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new MyThreadFactory("global-thread-"),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
