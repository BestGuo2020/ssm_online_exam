package top.bestguo.vo;

import top.bestguo.entity.Question;

/**
 * 问题查询条件类
 */
public class QuestionCondition extends Question {

    /**
     * 多个难度系数
     */
    private String levels;

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }
}
