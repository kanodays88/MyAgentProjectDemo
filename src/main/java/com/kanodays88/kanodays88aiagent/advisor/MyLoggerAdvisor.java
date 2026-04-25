package com.kanodays88.kanodays88aiagent.advisor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientMessageAggregator;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.*;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.MessageAggregator;
import org.springframework.ai.model.ModelOptionsUtils;
import reactor.core.publisher.Flux;

import java.util.function.Function;


//阿里版
@Slf4j
public class MyLoggerAdvisor implements CallAroundAdvisor, StreamAroundAdvisor {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private AdvisedRequest before(AdvisedRequest request) {
        log.info("userRequest: {}",request.userText());
        return request;
    }

    private void observeAfter(AdvisedResponse advisedResponse) {
        log.info("AiResponse: {}",advisedResponse.response().getResult().getOutput().getText());
    }

    @Override
    public String toString() {
        return org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor.class.getSimpleName();
    }

    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {

        advisedRequest = before(advisedRequest);

        AdvisedResponse advisedResponse = chain.nextAroundCall(advisedRequest);

        observeAfter(advisedResponse);

        return advisedResponse;
    }

    @Override
    public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {

        advisedRequest = before(advisedRequest);

        Flux<AdvisedResponse> advisedResponses = chain.nextAroundStream(advisedRequest);

        return new MessageAggregator().aggregateAdvisedResponse(advisedResponses, this::observeAfter);
    }

}

////OpenAi版
//@Slf4j
//public class MyLoggerAdvisor implements CallAdvisor, StreamAdvisor {
//
//    @Override
//    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
//        logRequest(chatClientRequest);
//
//        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
//
//        logResponse(chatClientResponse);
//
//        return chatClientResponse;
//    }
//
//    @Override
//    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest,
//                                                 StreamAdvisorChain streamAdvisorChain) {
//        logRequest(chatClientRequest);
//
//        Flux<ChatClientResponse> chatClientResponses = streamAdvisorChain.nextStream(chatClientRequest);
//
//        return new ChatClientMessageAggregator().aggregateChatClientResponse(chatClientResponses, this::logResponse);
//    }
//
//    protected void logRequest(ChatClientRequest request) {
////        logger.debug("request: {}", this.requestToString.apply(request));
//        log.info("UserRequest: {}",request.prompt().getUserMessage().getText());
//    }
//
//    protected void logResponse(ChatClientResponse chatClientResponse) {
////        logger.debug("response: {}", this.responseToString.apply(chatClientResponse.chatResponse()));
//        log.info("AiResponse: {}",chatClientResponse.chatResponse().getResult().getOutput().getText());
//    }
//
//    @Override
//    public String getName() {
//        return this.getClass().getSimpleName();
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//}