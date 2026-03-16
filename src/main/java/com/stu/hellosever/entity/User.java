package com.stu.hellosever.entity;

/**
 * 用户实体类
 */
public class User {
    // 私有属性
    private String name;
    private Long id;
    private Integer age;

    // 无参构造方法（必须，JSON解析需要）
    public User() {
    }

    // 全参构造方法（方便快速创建对象）
    public User(String name, Long id, Integer age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    // getter 和 setter 方法（框架序列化/反序列化必须）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}