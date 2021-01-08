package com.springdemo.springdemo.controller;

import com.springdemo.springdemo.models.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class EmployeeDB {

    HashMap<Integer, Person> emp;
    private int counter;

    public EmployeeDB(HashMap<Integer, String> emp) {
        this.emp = new HashMap<Integer, Person>();
        this.emp.put(1, new Person("Gokulraj", "gokulrajganesan2000@gmail.com"));
        this.emp.put(2, new Person("Sanjay", "sanjay@gmail.com"));
        counter = 2;
    }

    @GetMapping("/employees/get/{id}")
    public String getEmployeeDetail(@PathVariable(value = "id") int id) {

        return String.valueOf(this.emp.get(id));
    }

    @PostMapping("/employees/add")
    public void addEmployeeDetail(@RequestBody Person person) {

        this.emp.put(counter, new Person(person.getName(), person.getEmail()));

    }

    @PutMapping("/employees/update/{id}")
    public void updateEmployeeDetail(@PathVariable(value = "id") int id, @RequestBody Person person) {


        emp.put(id, new Person(person.getName() != null ? person.getName() : this.emp.get(id).getName(),
                person.getEmail() != null ? person.getEmail() : this.emp.get(id).getEmail()));

    }

    @DeleteMapping("/employees/delete/{id}")
    public void deleteEmployeeDetail(@PathVariable(value = "id") int id) {

        this.emp.remove(id);
    }

}
