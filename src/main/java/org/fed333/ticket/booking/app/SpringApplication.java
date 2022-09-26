package org.fed333.ticket.booking.app;

import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.facade.BookingFacade;
import org.fed333.ticket.booking.app.model.*;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
