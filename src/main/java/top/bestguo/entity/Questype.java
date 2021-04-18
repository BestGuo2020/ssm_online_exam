package top.bestguo.entity;

import java.io.Serializable;

/**
 * 
 * @TableName questype
 */
public class Questype implements Serializable {
    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 题库类别名称
     */
    private String typename;

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 题库类别名称
     */
    public String getTypename() {
        return typename;
    }

    /**
     * 题库类别名称
     */
    public void setTypename(String typename) {
        this.typename = typename;
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
        Questype other = (Questype) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTypename() == null ? other.getTypename() == null : this.getTypename().equals(other.getTypename()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTypename() == null) ? 0 : getTypename().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typename=").append(typename);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}