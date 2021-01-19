package com.springdemo.springdemo.models;


import com.springdemo.springdemo.exception.NameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Person {

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be empty")
    private String email;

    @Min(value = 0,
            message = "Salary can't be negative")
    private double salary;

    public Person(String name, String email, double salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}
