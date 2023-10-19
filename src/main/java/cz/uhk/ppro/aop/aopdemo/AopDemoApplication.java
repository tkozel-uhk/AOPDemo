package cz.uhk.ppro.aop.aopdemo;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopDemoApplication {

    @Bean
    Logger applicationLogger() {
        return org.apache.logging.log4j.LogManager.getLogger("AOPDemo");
    }

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

}
