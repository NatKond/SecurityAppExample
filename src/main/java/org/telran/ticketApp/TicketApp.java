package org.telran.ticketApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Console app: user -> console in -> app -> console out -> user
 *
 * Web app: user -> (Browser, PostMan, REST API) -> localhost:8080 ->
 *               -> WebServer(Tomcat (контейнер сервлетов)) -> Tomcat(TicketApp)
 *               -> class DispatcherServlet -> use url request -> controller
 */

@SpringBootApplication
public class TicketApp {// implements CommandLineRunner{

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(TicketApp.class,args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Hallo");
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//    }
}
