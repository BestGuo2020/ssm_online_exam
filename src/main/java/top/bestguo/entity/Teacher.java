package top.bestguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 
 * @TableName teacher
 */
public class Teacher implements Serializable {
    /**
     * 用户自增id
     */
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 电子邮箱
     */
    private Integer teacherId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别：0为男，1为女
     */
    private Byte gender;

    /**
     * 教师角色：1为管理员，2为普通教师
     */
    private Byte role;

    private static final long serialVersionUID = 1L;

    /**
     * 用户自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 用户自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 教师工号
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 教师工号
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 性别：0为男，1为女
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 性别：0为男，1为女
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * 教师角色：1为管理员，2为普通教师
     */
    public Byte getRole() {
        return role;
    }

    /**
     * 教师角色：1为管理员，2为普通教师
     */
    public void setRole(Byte role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                '}';
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
        Teacher other = (Teacher) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        return result;
    }

}