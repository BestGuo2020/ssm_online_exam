package top.bestguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName student_class
 */
@TableName(value ="student_class")
public class StudentClass implements Serializable {
    /**
     * 关系id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer stuid;

    /**
     * 班级id
     */
    private Integer classid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 关系id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 关系id
     */
    public void setId(Integer id) {
        this.id = id;
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
        StudentClass other = (StudentClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStuid() == null ? other.getStuid() == null : this.getStuid().equals(other.getStuid()))
            && (this.getClassid() == null ? other.getClassid() == null : this.getClassid().equals(other.getClassid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStuid() == null) ? 0 : getStuid().hashCode());
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
        sb.append(", stuid=").append(stuid);
        sb.append(", classid=").append(classid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}