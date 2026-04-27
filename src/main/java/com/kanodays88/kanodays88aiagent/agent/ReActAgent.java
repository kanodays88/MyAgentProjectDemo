package com.kanodays88.kanodays88aiagent.agent;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)//自动生成equals和hashcode方法
public abstract class ReActAgent extends BaseAgent{

    //让智能体思考问题是否解决
    public abstract boolean think(String userPrompt,String taskName);
    //让智能体动手解决
    public abstract String act();

    @Override
    public String step(String userPrompt,String taskName) {
        try{
            boolean think = think(userPrompt,taskName);
            if(!think){
                return "思考完成-无需行动";
            }
            return act();
        }catch (Exception e){
            e.printStackTrace();
            return "步骤执行失败: " + e.getMessage();
        }
    }
}
