package com.example.agileengine.agileengine;

import com.example.agileengine.agileengine.service.MainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AgileEngineApplication {

    public static void main(String[] args) {

        final ApplicationContext context = SpringApplication.run(AgileEngineApplication.class, args);

        MainService mainService = context.getBean(MainService.class);

        mainService.appMenu();
    }

}
