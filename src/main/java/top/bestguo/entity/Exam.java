package top.bestguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName exam
 */
@TableName(value ="exam")
public class Exam implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
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
     * 单选题分数
     */
    private Integer selectone;

    /**
     * 多选题分数
     */
    private Integer selectmore;

    /**
     * 总分
     */
    private Integer score;

    /**
     * 题目列表，题号之间是用逗号隔开
     */
    private String qlist;

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
     * 单选题分数
     */
    public Integer getSelectone() {
        return selectone;
    }

    /**
     * 单选题分数
     */
    public void setSelectone(Integer selectone) {
        this.selectone = selectone;
    }

    /**
     * 多选题分数
     */
    public Integer getSelectmore() {
        return selectmore;
    }

    /**
     * 多选题分数
     */
    public void setSelectmore(Integer selectmore) {
        this.selectmore = selectmore;
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

    /**
     * 题目列表
     */
    public String getQlist() {
        return qlist;
    }

    /**
     * 题目列表
     */
    public void setQlist(String qlist) {
        this.qlist = qlist;
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
            && (this.getSelectone() == null ? other.getSelectone() == null : this.getSelectone().equals(other.getSelectone()))
            && (this.getSelectmore() == null ? other.getSelectmore() == null : this.getSelectmore().equals(other.getSelectmore()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getQlist() == null ? other.getQlist() == null : this.getQlist().equals(other.getQlist()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExamname() == null) ? 0 : getExamname().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getStoptime() == null) ? 0 : getStoptime().hashCode());
        result = prime * result + ((getSelectone() == null) ? 0 : getSelectone().hashCode());
        result = prime * result + ((getSelectmore() == null) ? 0 : getSelectmore().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getQlist() == null) ? 0 : getQlist().hashCode());
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
        sb.append(", selectone=").append(selectone);
        sb.append(", selectmore=").append(selectmore);
        sb.append(", score=").append(score);
        sb.append(", qlist=").append(qlist);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}