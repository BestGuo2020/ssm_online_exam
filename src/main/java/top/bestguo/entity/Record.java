package top.bestguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName record
 */
@TableName(value ="record")
public class Record implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 考试id
     */
    private Integer examid;

    /**
     * 学生id
     */
    private Integer stuid;

    /**
     * 选择题答案记录
     */
    private String answer;

    /**
     * 错误的题号
     */
    private String wrong;

    /**
     * 正确的题号
     */
    private String correct;

    /**
     * 分数
     */
    private Double score;

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
     * 学生id
     */
    public Integer getStuid() {
        return stuid;
    }

    /**
     * 学生id
     */
    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    /**
     * 选择题答案记录
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 选择题答案记录
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 错误的题号
     */
    public String getWrong() {
        return wrong;
    }

    /**
     * 错误的题号
     */
    public void setWrong(String wrong) {
        this.wrong = wrong;
    }

    /**
     * 正确的题号
     */
    public String getCorrect() {
        return correct;
    }

    /**
     * 正确的题号
     */
    public void setCorrect(String correct) {
        this.correct = correct;
    }

    /**
     * 分数
     */
    public Double getScore() {
        return score;
    }

    /**
     * 分数
     */
    public void setScore(Double score) {
        this.score = score;
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
        Record other = (Record) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExamid() == null ? other.getExamid() == null : this.getExamid().equals(other.getExamid()))
            && (this.getStuid() == null ? other.getStuid() == null : this.getStuid().equals(other.getStuid()))
            && (this.getAnswer() == null ? other.getAnswer() == null : this.getAnswer().equals(other.getAnswer()))
            && (this.getWrong() == null ? other.getWrong() == null : this.getWrong().equals(other.getWrong()))
            && (this.getCorrect() == null ? other.getCorrect() == null : this.getCorrect().equals(other.getCorrect()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExamid() == null) ? 0 : getExamid().hashCode());
        result = prime * result + ((getStuid() == null) ? 0 : getStuid().hashCode());
        result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
        result = prime * result + ((getWrong() == null) ? 0 : getWrong().hashCode());
        result = prime * result + ((getCorrect() == null) ? 0 : getCorrect().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
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
        sb.append(", stuid=").append(stuid);
        sb.append(", answer=").append(answer);
        sb.append(", wrong=").append(wrong);
        sb.append(", correct=").append(correct);
        sb.append(", score=").append(score);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}