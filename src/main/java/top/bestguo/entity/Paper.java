package top.bestguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName paper
 */
@TableName(value ="paper")
public class Paper implements Serializable {
    /**
     * 自增id
     */
    @TableId
    private Integer id;

    /**
     * 考试id
     */
    private Integer examid;

    /**
     * 题目id
     */
    private Integer questionid;

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
     * 考试id
     */
    public Integer getExamid() {
        return examid;
    }

    /**
     * 考试id
     */
    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    /**
     * 题目id
     */
    public Integer getQuestionid() {
        return questionid;
    }

    /**
     * 题目id
     */
    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
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
        Paper other = (Paper) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExamid() == null ? other.getExamid() == null : this.getExamid().equals(other.getExamid()))
            && (this.getQuestionid() == null ? other.getQuestionid() == null : this.getQuestionid().equals(other.getQuestionid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExamid() == null) ? 0 : getExamid().hashCode());
        result = prime * result + ((getQuestionid() == null) ? 0 : getQuestionid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", examid=").append(examid);
        sb.append(", questionid=").append(questionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}