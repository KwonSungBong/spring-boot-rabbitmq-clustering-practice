package com.example.demo.controller;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ApplicationContext ctxt;

    @GetMapping
    public String test() {
        return "test";
    }

    @GetMapping("/check")
    public ResponseEntity checkState() {
        Map res = new HashMap();
        boolean buyStatus = ctxt.getBean("buyContainer", SimpleMessageListenerContainer.class).isRunning();
        boolean sellStatus = ctxt.getBean("sellContainer", SimpleMessageListenerContainer.class).isRunning();
        res.put("Order.Buy", buyStatus);
        res.put("Order.Sell", sellStatus);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/stop/all")
    public ResponseEntity stopAll() {
        try {
            Map res = new HashMap();
            SimpleMessageListenerContainer buy = ctxt.getBean("buyContainer", SimpleMessageListenerContainer.class);
            SimpleMessageListenerContainer sell = ctxt.getBean("sellContainer", SimpleMessageListenerContainer.class);
            buy.stop();
            sell.stop();
            res.put("Order.Buy", buy.isRunning());
            res.put("Order.Sell", sell.isRunning());
            return ResponseEntity.ok().body(res);
        } catch (BeansException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/start/all")
    public ResponseEntity startAll() {
        try {
            Map res = new HashMap();
            SimpleMessageListenerContainer buy = ctxt.getBean("buyContainer", SimpleMessageListenerContainer.class);
            SimpleMessageListenerContainer sell = ctxt.getBean("sellContainer", SimpleMessageListenerContainer.class);
            buy.start();
            sell.start();
            res.put("Order.Buy", buy.isRunning());
            res.put("Order.Sell", sell.isRunning());
            return ResponseEntity.ok().body(res);
        } catch (BeansException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
