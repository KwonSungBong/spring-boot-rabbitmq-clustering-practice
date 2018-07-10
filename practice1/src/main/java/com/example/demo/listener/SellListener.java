package com.example.demo.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SellListener implements MessageListener {

    public static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message) {
        String result = new String(message.getBody());
        log.info("result : {}", result);
    }
}
