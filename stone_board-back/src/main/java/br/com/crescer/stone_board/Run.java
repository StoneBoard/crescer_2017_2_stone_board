package br.com.crescer.stone_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Marcele
 */
@SpringBootApplication
public class Run {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Run.class, args);
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename("classpath:messages");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }

}
