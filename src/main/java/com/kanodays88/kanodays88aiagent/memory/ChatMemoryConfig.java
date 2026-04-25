package com.kanodays88.kanodays88aiagent.memory;

import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryConfig {


    @Bean
    public org.springframework.ai.chat.memory.ChatMemory chatMemory(){
        return new InMemoryChatMemory();


    }
}
