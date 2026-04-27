package com.kanodays88.kanodays88aiagent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("kanodays88/agent")
public class AgentController {

    public SseEmitter agent(String userMessage,String chatId){

    }

}
