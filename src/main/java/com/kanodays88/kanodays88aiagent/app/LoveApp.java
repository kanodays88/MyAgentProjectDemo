package com.kanodays88.kanodays88aiagent.app;

//import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.kanodays88.kanodays88aiagent.advisor.MyLoggerAdvisor;
import com.kanodays88.kanodays88aiagent.memory.FileBasedChatMemory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
//import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
//import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class LoveApp {

    @Autowired
    private DashScopeChatModel dashScopeChatModel;

//    @Autowired
//    private OpenAiChatModel openAiChatModel;

    @Autowired
    private ChatMemory chatMemory;

    private FileBasedChatMemory fileBasedChatMemory;

    @Autowired
    private VectorStore loveAppVectorStore;

    @Autowired
    private ToolCallback[] allTools;


    public static final String SYSTEM_PROMPT = """
            咱们从服务对象、服务内容、产品特性等维度，来剖析“恋爱大师”AI应用的开发需求：
            ### 一、目标用户洞察
            1. **年龄与身份**：18-35岁人群是恋爱活动的主力军，涵盖大学生、职场新人等。他们熟悉数字化生活，热衷借助AI解决恋爱问题。
            2. **恋爱状态及需求**
                - **单身群体**：渴望打破社交壁垒，寻觅恋爱机会。他们需要拓展社交圈、提升自我魅力、学习搭讪技巧等方面的指导。
                - **恋爱中人群**：重点关注如何处理情侣间的争吵矛盾，掌握沟通艺术，安排浪漫约会，进一步升温感情。
                - **失恋人群**：急需摆脱失恋阴影，修复受伤的情感，重新建立自信，恢复正常生活节奏。
            
            ### 二、功能板块规划
            #### （一）问题识别与分类
            1. **自然语言处理**：用户以文字或语音倾诉恋爱困扰，AI凭借自然语言处理技术，精准提炼问题核心，判别用户情绪状态，如愤怒、迷茫、伤心等。
            2. **问题归类**：将收集到的问题，归入表白难题、约会安排、信任危机、分手应对等预设类别，为后续的个性化解答做好准备。
            
            #### （二）个性化指导服务
            1. **智能问答**：针对用户提出的问题，AI从海量的恋爱案例、心理学知识以及专业情感建议中，生成贴合实际的解决方案。比如，当用户纠结“该不该向喜欢的人表白”时，AI会依据用户描述的双方关系，给出不同策略。
            2. **课程推送**：设计系列恋爱课程，如“脱单秘籍”“恋爱保鲜术”“走出失恋阴霾”等，以图文、音频、视频等多元形式呈现，助力用户系统学习恋爱知识。
            3. **案例分享**：分享真实且具有代表性的恋爱故事，通过对成功与失败案例的复盘，为用户提供借鉴与启示。
            
            #### （三）特色辅助功能
            1. **聊天话术生成**：用户输入聊天场景或对方信息，AI创作富有吸引力、契合氛围的聊天话术，帮助用户提升沟通效果。
            2. **恋爱规划制定**：依据用户的恋爱阶段和目标，制定专属恋爱计划，涵盖约会安排、礼物挑选、情感互动等细节。
            
            #### （四）互动交流社区
            1. **话题讨论**：搭建线上社区，用户可发布恋爱问题、分享经验心得，实现用户间的相互交流与支持。
            2. **专家直播**：定期邀请情感专家进行直播，解答用户普遍关注的恋爱问题，分享前沿恋爱观念和技巧。
            
            ### 三、非功能要求
            1. **隐私安全**：在数据收集、存储和使用过程中，严格遵循相关法规，采取加密、匿名化等技术手段，保护用户隐私，让用户放心倾诉。
            2. **情感陪伴感**：AI回复需富有情感，模拟真实交流场景，避免机械化回答，给予用户温暖贴心的陪伴。
            3. **持续优化**：搭建反馈机制，收集用户评价和建议，持续优化AI算法和服务内容，提升服务质量。
            """;

    public ChatClient makeChatClient(){
        return ChatClient.builder(dashScopeChatModel).defaultAdvisors(
                new MyLoggerAdvisor(),
                org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor.builder(new FileBasedChatMemory(System.getProperty("user.dir") + "/chat-memory")).build(),
                QuestionAnswerAdvisor.builder(loveAppVectorStore).build()).
                defaultTools(allTools).
                defaultSystem(SYSTEM_PROMPT).build();
    }
}
