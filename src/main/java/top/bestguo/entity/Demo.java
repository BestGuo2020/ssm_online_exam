package top.bestguo.entity;

public class Demo {

    private Integer id;
    private String username;
    private Integer age;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
