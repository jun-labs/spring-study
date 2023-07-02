package project.study.threadpool.common.configuration;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CustomThreadPool {

    private static final int THREAD_COUNT = 64;

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public void submit(Runnable runnable) {
        executorService.submit(runnable);
    }
}
