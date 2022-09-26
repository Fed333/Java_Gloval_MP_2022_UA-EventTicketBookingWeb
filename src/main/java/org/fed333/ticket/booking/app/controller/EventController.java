package org.fed333.ticket.booking.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Controller
@RequestMapping("/event")
public class EventController {

    private void init() {
        log.info("EventController init invoked!");
    }

    @RequestMapping
    public @ResponseBody ResponseEntity<Map<String,String>> getAll() {
        return ResponseEntity.ok(Stream.of(new Object[][]{
                {"name", "myEvent"},
                {"ticketPrice", "20"}
        }).collect(toMap(o->(String)o[0], o->(String)o[1])));
    }

}
