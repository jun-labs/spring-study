package project.study.threadpool.core.web.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import project.study.threadpool.common.configuration.CustomThreadPool;

import static project.study.threadpool.common._const.ThreadPoolConst.END;
import static project.study.threadpool.common._const.ThreadPoolConst.START;

@Slf4j
@RestController
@RequestMapping("/api/servers")
public class ClientRequestAPI {

    private final RestTemplate restTemplate;
    private final ServerProperties serverProperties;
    private final CustomThreadPool customThreadPool;
    private final String url;

    public ClientRequestAPI(
            RestTemplate restTemplate,
            ServerProperties serverProperties,
            CustomThreadPool customThreadPool,
            @Value("${custom.url}") String url
    ) {
        this.restTemplate = restTemplate;
        this.serverProperties = serverProperties;
        this.customThreadPool = customThreadPool;
        this.url = url;
    }

    @GetMapping("/info")
    public ResponseEntity<String> readThreads() {
        ServerProperties.Tomcat tomcat = serverProperties.getTomcat();
        log.info("--------------------x>");
        log.info("Tomcat Max ThreadCount: {}", tomcat.getThreads().getMax());
        log.info("Tomcat Min Spare: {}", tomcat.getThreads().getMinSpare());
        log.info("<x--------------------");
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/execution")
    public ResponseEntity<String> execute() {
        log.info("--------------------x>");
        for (int index = START; index < END; index++) {
            customThreadPool.submit(() -> {
                Thread newThread = new Thread(() ->
                        log.info("Thread Execution: {}", Thread.currentThread().getId())
                );
                newThread.start();
            });
        }
        log.info("<x--------------------");
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<String> ok() {
        for (int index = 0; index < 8; index++) {
            Thread newThread = new Thread(() -> {
                log.info("--------------------x>");
                String result = restTemplate
                        .getForObject(url, String.class);
                log.info("Thread: {}, {}", Thread.currentThread(), result);
                log.info("<x--------------------");
            });
            newThread.start();
        }
        return ResponseEntity
                .ok("OK");
    }

    @GetMapping("/health-check-delay")
    public ResponseEntity<String> delayHealthCheck() throws InterruptedException {
        log.info("start");
        Thread.sleep(1000);
        log.info("end");
        return ResponseEntity.ok("OK");
    }
}
