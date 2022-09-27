package org.fed333.ticket.booking.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.text.ParseException;

@Slf4j
public class SpringApplication {

    public static void main(String[] args) throws ParseException {

        log.info("Start raising ApplicationContext...");
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocations("classpath:services.xml");
        context.refresh();
        log.info("ApplicationContext has been raised.");
    }
}
