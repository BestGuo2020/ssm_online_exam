package top.bestguo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName exam
 */
public class Exam implements Serializable {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 考试名称
     */
    private String examname;

    /**
     * 考试开始时间
     */
    private Date starttime;

    /**
     * 考试结束时间
     */
    private Date stoptime;

    /**
     * 选择题分数
     */
    private Integer selectpoint;

    /**
     * 填空题分数（按每空计分）
     */
    private Integer fillpoint;

    /**
     * 总分
     */
    private Integer score;

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
     * 考试名称
     */
    public String getExamname() {
        return examname;
    }

    /**
     * 考试名称
     */
    public void setExamname(String examname) {
        this.examname = examname;
    }

    /**
     * 考试开始时间
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * 考试开始时间
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 考试结束时间
     */
    public Date getStoptime() {
        return stoptime;
    }

    /**
     * 考试结束时间
     */
    public void setStoptime(Date stoptime) {
        this.stoptime = stoptime;
    }

    /**
     * 选择题分数
     */
    public Integer getSelectpoint() {
        return selectpoint;
    }

    /**
     * 选择题分数
     */
    public void setSelectpoint(Integer selectpoint) {
        this.selectpoint = selectpoint;
    }

    /**
     * 填空题分数（按每空计分）
     */
    public Integer getFillpoint() {
        return fillpoint;
    }

    /**
     * 填空题分数（按每空计分）
     */
    public void setFillpoint(Integer fillpoint) {
        this.fillpoint = fillpoint;
    }

    /**
     * 总分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 总分
     */
    public void setScore(Integer score) {
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
        Exam other = (Exam) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExamname() == null ? other.getExamname() == null : this.getExamname().equals(other.getExamname()))
            && (this.getStarttime() == null ? other.getStarttime() == null : this.getStarttime().equals(other.getStarttime()))
            && (this.getStoptime() == null ? other.getStoptime() == null : this.getStoptime().equals(other.getStoptime()))
            && (this.getSelectpoint() == null ? other.getSelectpoint() == null : this.getSelectpoint().equals(other.getSelectpoint()))
            && (this.getFillpoint() == null ? other.getFillpoint() == null : this.getFillpoint().equals(other.getFillpoint()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExamname() == null) ? 0 : getExamname().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getStoptime() == null) ? 0 : getStoptime().hashCode());
        result = prime * result + ((getSelectpoint() == null) ? 0 : getSelectpoint().hashCode());
        result = prime * result + ((getFillpoint() == null) ? 0 : getFillpoint().hashCode());
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
        sb.append(", examname=").append(examname);
        sb.append(", starttime=").append(starttime);
        sb.append(", stoptime=").append(stoptime);
        sb.append(", selectpoint=").append(selectpoint);
        sb.append(", fillpoint=").append(fillpoint);
        sb.append(", score=").append(score);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}