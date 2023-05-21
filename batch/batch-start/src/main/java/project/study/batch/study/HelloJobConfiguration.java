package project.study.batch.study;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;

    public HelloJobConfiguration(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            JobExecutionListener jobExecutionListener
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobExecutionListener = jobExecutionListener;
    }

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("exampleJob")
                .start(helloStep1())
                .next(helloStep2())
                .listener(jobExecutionListener)
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep")
                .tasklet(((contribution, chunkContext) -> {
                    Thread.sleep(3000);
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    @Transactional
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")
                .tasklet(((contribution, chunkContext) -> {
                    Thread.sleep(3000);
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
