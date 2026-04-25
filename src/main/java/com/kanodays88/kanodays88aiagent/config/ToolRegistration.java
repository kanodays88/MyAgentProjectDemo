package com.kanodays88.kanodays88aiagent.config;

import com.kanodays88.kanodays88aiagent.tools.*;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolRegistration {

    /**
     * 工厂模式统一注册工具
     * @return
     */
    @Bean
    public ToolCallback[] allTools(WebSearchTool webSearchTool){
        AssignmentFinishTool assignmentFinishTool = new AssignmentFinishTool();
        FileOperationTool fileOperationTool = new FileOperationTool();
        PDFGenerationTool pdfGenerationTool = new PDFGenerationTool();
        ResourceDownloadTool resourceDownloadTool = new ResourceDownloadTool();
        WebScrapingTool webScrapingTool = new WebScrapingTool();
        return ToolCallbacks.from(
                assignmentFinishTool,
                fileOperationTool,
                pdfGenerationTool,
                resourceDownloadTool,
//                webScrapingTool,网页爬取工具由于返回的上下文太长，会导致被阿里拒绝访问
                webSearchTool
        );
    }
}
