package com.lcl.Thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ExcutorTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2020/1/28 8:30 下午
 */
public class ExcutorTest {
    private void parallelInsert(List<Object> list) {
        List<Future<Boolean>> futureList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(24);
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            Future<Boolean> submit = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    //do something

                }
            }, true);
            futureList.add(submit);
        }
        futureList.forEach(booleanFuture -> {
            try {
                booleanFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

}
