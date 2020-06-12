package com.pankaj.springit;

import com.pankaj.springit.config.SpringitProperties;
import com.pankaj.springit.model.Comment;
import com.pankaj.springit.model.Link;
import com.pankaj.springit.repository.CommentRepository;
import com.pankaj.springit.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperties.class)
@EnableJpaAuditing
public class SpringitApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringitApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SpringitApplication.class, args);
        System.out.println("Welcome to sprintit!");
    }

    @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
        return args -> {
            Link link = new Link("Getting Started with Spring Boot 2","https://therealdanvega.com/spring-boot-2");
            linkRepository.save(link);

            Comment comment = new Comment("This Spring Boot 2 link is awesome!", link);
            commentRepository.save(comment);
            link.addComment(comment);

            System.out.println("We just inserted a link and a comment");
            System.out.println("===========================================================");
        };
    }
}