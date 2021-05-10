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
    @TableId
    private Integer id;

    /**
     * 试卷id
     */
    private Integer paperid;

    /**
     * 学生id
     */
    private Integer stuid;

    /**
     * 选择题答案记录
     */
    private String selectans;

    /**
     * 填空题答题记录
     */
    private String fillans;

    /**
     * 分数
     */
    private String score;

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
     * 试卷id
     */
    public Integer getPaperid() {
        return paperid;
    }

    /**
     * 试卷id
     */
    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
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
    public String getSelectans() {
        return selectans;
    }

    /**
     * 选择题答案记录
     */
    public void setSelectans(String selectans) {
        this.selectans = selectans;
    }

    /**
     * 填空题答题记录
     */
    public String getFillans() {
        return fillans;
    }

    /**
     * 填空题答题记录
     */
    public void setFillans(String fillans) {
        this.fillans = fillans;
    }

    /**
     * 分数
     */
    public String getScore() {
        return score;
    }

    /**
     * 分数
     */
    public void setScore(String score) {
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
            && (this.getPaperid() == null ? other.getPaperid() == null : this.getPaperid().equals(other.getPaperid()))
            && (this.getStuid() == null ? other.getStuid() == null : this.getStuid().equals(other.getStuid()))
            && (this.getSelectans() == null ? other.getSelectans() == null : this.getSelectans().equals(other.getSelectans()))
            && (this.getFillans() == null ? other.getFillans() == null : this.getFillans().equals(other.getFillans()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaperid() == null) ? 0 : getPaperid().hashCode());
        result = prime * result + ((getStuid() == null) ? 0 : getStuid().hashCode());
        result = prime * result + ((getSelectans() == null) ? 0 : getSelectans().hashCode());
        result = prime * result + ((getFillans() == null) ? 0 : getFillans().hashCode());
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
        sb.append(", paperid=").append(paperid);
        sb.append(", stuid=").append(stuid);
        sb.append(", selectans=").append(selectans);
        sb.append(", fillans=").append(fillans);
        sb.append(", score=").append(score);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}