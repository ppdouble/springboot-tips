package com.pxample.pemo.pojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;


@Data
public class User {

    @NotNull(message = "Id should not be null")
    private Integer id;

    @NotBlank(message = "username cannot be empty")
    @Length(min=6, max=10, message="length of name should between 6 to 10 characters")
    private String name;

    @NotBlank(message = "password cannot be empty")
    @Length(min=6, max=10, message="length of password should between 6 to 10 characters")
    private String password;

    @NotNull(message = "age cannot be null")
    @Min(value = 3, message = "age should greater than 3")
    @Max(value = 100, message = "age should less than 100")
    private Integer age;

    @NotNull(message = "grade cannot be null")
    @DecimalMin(value = "0.0", message = "grade should greater than 0.0")
    @DecimalMax(value = "100.0", message = "grade should less than 100.0")
    private Double grade;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "email format: x@x.x")
    private String email;



    public User(Integer id, String name, String password, Integer age, Double grade, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.grade = grade;
        this.email = email;

    }


    public void setId(Integer id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Double getGrade() {
        return grade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


}