package project.study.spring.test.bean_scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ProviderTestV2 {

    @Test
    @DisplayName("프로토타입 빈은 매번 새로 생성된다.")
    void prototype_bean_test() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(
                        SingletonTypeBean.class,
                        ProtoTypeBean.class
                );

        SingletonTypeBean bean = applicationContext.getBean(SingletonTypeBean.class);

        ProtoTypeBean prototypeA = bean.prototypeBean();
        prototypeA.increase();

        ProtoTypeBean prototypeB = bean.prototypeBean();
        prototypeB.increase();

        Assertions.assertThat(prototypeA).isNotSameAs(prototypeB);
        assertEquals(1, prototypeA.count());
        assertEquals(1, prototypeB.count());

        prototypeB.destroy();
        prototypeA.destroy();
        bean.destroy();
    }

    @Scope("prototype")
    class SingletonTypeBean {

        private final Provider<ProtoTypeBean> provider;

        @Autowired
        public SingletonTypeBean(Provider<ProtoTypeBean> provider) {
            this.provider = provider;
        }

        public ProtoTypeBean prototypeBean() {
            return provider.get();
        }

        private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

        private int count = 0;

        public int count() {
            return count;
        }

        public void increase() {
            this.count++;
        }

        @PostConstruct
        public void init() {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            log.info("INIT: {}", this);
            log.info("---------------------------------------------------------------");
        }

        @PreDestroy
        public void destroy() {
            log.info("---------------------------------------------------------------");
            log.info("DESTROY: {}", this);
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

    @Scope("prototype")
    static class ProtoTypeBean {

        private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

        private int count = 0;

        public int count() {
            return count;
        }

        public void increase() {
            this.count++;
        }

        @PostConstruct
        public void init() {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            log.info("INIT: {}", this);
            log.info("---------------------------------------------------------------");
        }

        @PreDestroy
        public void destroy() {
            log.info("---------------------------------------------------------------");
            log.info("DESTROY: {}", this);
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }
}
