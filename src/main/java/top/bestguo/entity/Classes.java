package top.bestguo.entity;

import java.io.Serializable;

/**
 * 
 * @TableName classes
 */
public class Classes implements Serializable {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 12位的班级码
     */
    private String classcode;

    /**
     * 班级名
     */
    private String classname;

    /**
     * 班级图片
     */
    private String classimage;

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
     * 12位的班级码
     */
    public String getClasscode() {
        return classcode;
    }

    /**
     * 12位的班级码
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
     * 班级图片
     */
    public String getClassimage() {
        return classimage;
    }

    /**
     * 班级图片
     */
    public void setClassimage(String classimage) {
        this.classimage = classimage;
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
            && (this.getClassimage() == null ? other.getClassimage() == null : this.getClassimage().equals(other.getClassimage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClasscode() == null) ? 0 : getClasscode().hashCode());
        result = prime * result + ((getClassname() == null) ? 0 : getClassname().hashCode());
        result = prime * result + ((getClassimage() == null) ? 0 : getClassimage().hashCode());
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
        sb.append(", classimage=").append(classimage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}