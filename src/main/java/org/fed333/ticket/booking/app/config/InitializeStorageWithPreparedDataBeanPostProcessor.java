package org.fed333.ticket.booking.app.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.impl.EventImpl;
import org.fed333.ticket.booking.app.model.impl.TicketImpl;
import org.fed333.ticket.booking.app.model.impl.UserImpl;
import org.fed333.ticket.booking.app.repository.impl.component.StorageData;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
public class InitializeStorageWithPreparedDataBeanPostProcessor implements BeanPostProcessor {

    private static final String TARGET_BEAN_NAME = "storage";

    @Getter @Setter
    private String storageSourceJson;
    private static final String JSON_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals(TARGET_BEAN_NAME)) {
            StorageData storage = (StorageData) bean;
            log.info("Post processing of bean {} before initialization. The state {}.", beanName, bean);
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat(JSON_DATE_PATTERN);
            mapper.setDateFormat(df);
            try {
                JsonNode jsonNode = mapper.readTree(getClass().getResource(storageSourceJson));
                String eventsJsonString = jsonNode.get("events").toPrettyString();
                String usersJsonString = jsonNode.get("users").toPrettyString();
                String ticketsJsonString = jsonNode.get("tickets").toPrettyString();

                List<? extends Event> events = mapper.readValue(eventsJsonString, new TypeReference<List<EventImpl>>(){});
                List<? extends User> users = mapper.readValue(usersJsonString, new TypeReference<List<UserImpl>>(){});
                List<? extends Ticket> tickets = mapper.readValue(ticketsJsonString, new TypeReference<List<TicketImpl>>(){});

                log.info("events: {}", events);
                log.info("users: {}", users);
                log.info("tickets: {}", tickets);

                events.forEach(e->storage.getEventRepository().save(e));
                users.forEach(u->storage.getUserRepository().save(u));
                tickets.forEach(t->storage.getTicketRepository().save(t));

                Long maxEventId = events.stream().map(Event::getId).max(Long::compare).orElse(0L);
                Long maxUserId = users.stream().map(User::getId).max(Long::compare).orElse(0L);
                Long maxTicketId = tickets.stream().map(Ticket::getId).max(Long::compare).orElse(0L);

                storage.getUserRepository().getIdGenerator().setCurrId(maxUserId);
                storage.getEventRepository().getIdGenerator().setCurrId(maxEventId);
                storage.getTicketRepository().getIdGenerator().setCurrId(maxTicketId);

                log.info("Filling of the prepared data, has been completed!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals(TARGET_BEAN_NAME)) {
            log.info("Post processing of bean {} after initialization. The state {}.", beanName, bean);
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
