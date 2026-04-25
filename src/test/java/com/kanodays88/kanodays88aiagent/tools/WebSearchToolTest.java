package com.kanodays88.kanodays88aiagent.tools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebSearchToolTest {

    @Autowired
    private WebSearchTool webSearchTool;
    @Test
    public void testTool() throws IOException {
        String key = "永雏塔菲";
        String s = webSearchTool.webSearch(key);

        PDFGenerationTool tool = new PDFGenerationTool();
        String fileName = "永雏塔菲.pdf";
        String content = s;
        String result = tool.generatePDF(fileName, content);
        assertNotNull(result);
    }
}