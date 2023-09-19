package com.hy.springbootquickstart.service.impl;


import com.hy.springbootquickstart.service.LoopCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description: 循环调用实现类
 * Author: yhong
 * Date: 2023/9/18
 */
@Service
@Slf4j
public class LoopCalServiceImpl implements LoopCallService {
    private final ThreadPoolExecutor globalTaskExecutor;

    public LoopCalServiceImpl(@Qualifier("globalTaskExecutor") ThreadPoolExecutor globalTaskExecutor) {
        this.globalTaskExecutor = globalTaskExecutor;
    }


    @Override
    public List<Long> cal(int n) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Long> result = loop(n);
//        List<Long> result = multiThread(n);
        System.out.println("result: " + result.get(n-1));
        stopWatch.stop();
        System.out.println("方法执行时长:" + stopWatch.getTotalTimeSeconds() + "秒");
        return result;
    }

    private List<Long> multiThread(int n) {
        List<Long> result = new ArrayList<>();
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i <n; i++) {
            int finalI = i;
            Future<Long> future = globalTaskExecutor.submit(() -> calculate(finalI));
            futures.add(future);
        }
        for (Future<Long> future : futures) {
            try {
                result.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("error is {}", e.getMessage());
            }
        }
        return result;
    }

    private Long calculate(int i) {
        long res = 0L;
        for (int j = i; j < 99999999; j++) {
            res += j;
        }
        return res;
    }

    private List<Long> loop(int n) {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Long num = calculate(i);
            result.add(num);
        }
        return result;
    }

}
