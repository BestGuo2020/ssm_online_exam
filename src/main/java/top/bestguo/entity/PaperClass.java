package top.bestguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName paper_class
 */
@TableName(value ="paper_class")
public class PaperClass implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 试卷id
     */
    private Integer paperid;

    /**
     * 班级id
     */
    private Integer classid;

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
     * 班级id
     */
    public Integer getClassid() {
        return classid;
    }

    /**
     * 班级id
     */
    public void setClassid(Integer classid) {
        this.classid = classid;
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
        PaperClass other = (PaperClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaperid() == null ? other.getPaperid() == null : this.getPaperid().equals(other.getPaperid()))
            && (this.getClassid() == null ? other.getClassid() == null : this.getClassid().equals(other.getClassid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaperid() == null) ? 0 : getPaperid().hashCode());
        result = prime * result + ((getClassid() == null) ? 0 : getClassid().hashCode());
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
        sb.append(", classid=").append(classid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}