package top.bestguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName question
 */
@TableName(value ="question")
public class Question implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 问题描述
     */
    private String questionname;

    /**
     * 是否为多选？1为是，0为否
     */
    private Boolean ismulti;

    /**
     * 选项A
     */
    private String option1;

    /**
     * 选项B
     */
    private String option2;

    /**
     * 选项C
     */
    private String option3;

    /**
     * 选项D
     */
    private String option4;

    /**
     * 选项E
     */
    private String option5;

    /**
     * 正确选项
     */
    private String answer;

    /**
     * 解析
     */
    private String reason;

    /**
     * 属于那个班级
     */
    private Integer belongclass;

    /**
     * 难度系数：1.简单、2.中等、3.困难
     */
    private Integer level;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 问题描述
     */
    public String getQuestionname() {
        return questionname;
    }

    /**
     * 问题描述
     */
    public void setQuestionname(String questionname) {
        this.questionname = questionname;
    }

    /**
     * 是否为多选？1为是，0为否
     */
    public Boolean getIsmulti() {
        return ismulti;
    }

    /**
     * 是否为多选？1为是，0为否
     */
    public void setIsmulti(Boolean ismulti) {
        this.ismulti = ismulti;
    }

    /**
     * 选项A
     */
    public String getOption1() {
        return option1;
    }

    /**
     * 选项A
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * 选项B
     */
    public String getOption2() {
        return option2;
    }

    /**
     * 选项B
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * 选项C
     */
    public String getOption3() {
        return option3;
    }

    /**
     * 选项C
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * 选项D
     */
    public String getOption4() {
        return option4;
    }

    /**
     * 选项D
     */
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    /**
     * 选项E
     */
    public String getOption5() {
        return option5;
    }

    /**
     * 选项E
     */
    public void setOption5(String option5) {
        this.option5 = option5;
    }

    /**
     * 正确选项
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 正确选项
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 解析
     */
    public String getReason() {
        return reason;
    }

    /**
     * 解析
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 属于那个班级
     */
    public Integer getBelongclass() {
        return belongclass;
    }

    /**
     * 属于那个班级
     */
    public void setBelongclass(Integer belongclass) {
        this.belongclass = belongclass;
    }

    /**
     * 难度系数：1.简单、2.中等、3.困难
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 难度系数：1.简单、2.中等、3.困难
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Question other = (Question) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getQuestionname() == null ? other.getQuestionname() == null : this.getQuestionname().equals(other.getQuestionname()))
            && (this.getIsmulti() == null ? other.getIsmulti() == null : this.getIsmulti().equals(other.getIsmulti()))
            && (this.getOption1() == null ? other.getOption1() == null : this.getOption1().equals(other.getOption1()))
            && (this.getOption2() == null ? other.getOption2() == null : this.getOption2().equals(other.getOption2()))
            && (this.getOption3() == null ? other.getOption3() == null : this.getOption3().equals(other.getOption3()))
            && (this.getOption4() == null ? other.getOption4() == null : this.getOption4().equals(other.getOption4()))
            && (this.getOption5() == null ? other.getOption5() == null : this.getOption5().equals(other.getOption5()))
            && (this.getAnswer() == null ? other.getAnswer() == null : this.getAnswer().equals(other.getAnswer()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getBelongclass() == null ? other.getBelongclass() == null : this.getBelongclass().equals(other.getBelongclass()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getQuestionname() == null) ? 0 : getQuestionname().hashCode());
        result = prime * result + ((getIsmulti() == null) ? 0 : getIsmulti().hashCode());
        result = prime * result + ((getOption1() == null) ? 0 : getOption1().hashCode());
        result = prime * result + ((getOption2() == null) ? 0 : getOption2().hashCode());
        result = prime * result + ((getOption3() == null) ? 0 : getOption3().hashCode());
        result = prime * result + ((getOption4() == null) ? 0 : getOption4().hashCode());
        result = prime * result + ((getOption5() == null) ? 0 : getOption5().hashCode());
        result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getBelongclass() == null) ? 0 : getBelongclass().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", questionname=").append(questionname);
        sb.append(", ismulti=").append(ismulti);
        sb.append(", option1=").append(option1);
        sb.append(", option2=").append(option2);
        sb.append(", option3=").append(option3);
        sb.append(", option4=").append(option4);
        sb.append(", option5=").append(option5);
        sb.append(", answer=").append(answer);
        sb.append(", reason=").append(reason);
        sb.append(", belongclass=").append(belongclass);
        sb.append(", level=").append(level);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}