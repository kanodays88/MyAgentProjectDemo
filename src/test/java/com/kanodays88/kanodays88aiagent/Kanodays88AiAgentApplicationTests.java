package com.kanodays88.kanodays88aiagent;

import com.kanodays88.kanodays88aiagent.app.LoveApp;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@SpringBootTest
class Kanodays88AiAgentApplicationTests {

    @Autowired
    private LoveApp loveApp;

    record LoveReport(String title, List<String> suggestions) {
    }


    @Test
    void contextLoads() {
        ChatClient chatClient = loveApp.makeChatClient();

        String chatId = UUID.randomUUID().toString();

        String message = "有什么恋爱的手机壁纸推荐吗,我想要情侣的,你能从网上找一些吗";

        String response = chatClient.prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .content();

        System.out.println(response);
    }

}
