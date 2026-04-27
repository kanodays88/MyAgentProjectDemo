package com.kanodays88.kanodays88aiagent.agent;

import com.kanodays88.kanodays88aiagent.agent.plan.PlanExecute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class Kanodays88ManusTest {

    @Autowired
    private PlanExecute planExecute;
    @Test
    void run() {
//        String userPrompt = """
//                我的另一半居住在上海静安区，请帮我找到 5 公里内合适的约会地点，
//                并结合一些网络图片，制定一份约会计划，
//                并以 PDF 格式输出""";
//        String answer = planExecute.planExecute(userPrompt);
//        System.out.println(answer);
//        Assertions.assertNotNull(answer);
    }

}