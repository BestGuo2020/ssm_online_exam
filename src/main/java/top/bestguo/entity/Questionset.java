package top.bestguo.entity;

import java.io.Serializable;

/**
 * 
 * @TableName questionset
 */
public class Questionset implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer questionid;

    /**
     * 
     */
    private Integer classesid;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public Integer getQuestionid() {
        return questionid;
    }

    /**
     * 
     */
    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    /**
     * 
     */
    public Integer getClassesid() {
        return classesid;
    }

    /**
     * 
     */
    public void setClassesid(Integer classesid) {
        this.classesid = classesid;
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
        Questionset other = (Questionset) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getQuestionid() == null ? other.getQuestionid() == null : this.getQuestionid().equals(other.getQuestionid()))
            && (this.getClassesid() == null ? other.getClassesid() == null : this.getClassesid().equals(other.getClassesid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getQuestionid() == null) ? 0 : getQuestionid().hashCode());
        result = prime * result + ((getClassesid() == null) ? 0 : getClassesid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", questionid=").append(questionid);
        sb.append(", classesid=").append(classesid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}