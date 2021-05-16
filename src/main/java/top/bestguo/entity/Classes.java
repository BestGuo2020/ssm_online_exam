package top.bestguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * 
 * @TableName classes
 */
@TableName(value ="classes")
public class Classes implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 8位的班级码
     */
    private String classcode;


    /**
     * 班级名
     */
    private String classname;

    /**
     * 班级描述
     */
    private String classdesc;

    /**
     * 属于哪个老师
     */
    private Integer belongteacher;

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
     * 8位的班级码
     */
    public String getClasscode() {
        return classcode;
    }

    /**
     * 8位的班级码
     */
    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    /**
     * 班级名
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 班级名
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * 班级描述
     */
    public String getClassdesc() {
        return classdesc;
    }

    /**
     * 班级描述
     */
    public void setClassdesc(String classdesc) {
        this.classdesc = classdesc;
    }

    /**
     * 属于哪个老师
     */
    public Integer getBelongteacher() {
        return belongteacher;
    }

    /**
     * 属于哪个老师
     */
    public void setBelongteacher(Integer belongteacher) {
        this.belongteacher = belongteacher;
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
        Classes other = (Classes) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClasscode() == null ? other.getClasscode() == null : this.getClasscode().equals(other.getClasscode()))
            && (this.getClassname() == null ? other.getClassname() == null : this.getClassname().equals(other.getClassname()))
            && (this.getClassdesc() == null ? other.getClassdesc() == null : this.getClassdesc().equals(other.getClassdesc()))
            && (this.getBelongteacher() == null ? other.getBelongteacher() == null : this.getBelongteacher().equals(other.getBelongteacher()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClasscode() == null) ? 0 : getClasscode().hashCode());
        result = prime * result + ((getClassname() == null) ? 0 : getClassname().hashCode());
        result = prime * result + ((getClassdesc() == null) ? 0 : getClassdesc().hashCode());
        result = prime * result + ((getBelongteacher() == null) ? 0 : getBelongteacher().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", classcode=").append(classcode);
        sb.append(", classname=").append(classname);
        sb.append(", classdesc=").append(classdesc);
        sb.append(", belongteacher=").append(belongteacher);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}