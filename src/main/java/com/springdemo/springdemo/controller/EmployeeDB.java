package com.springdemo.springdemo.controller;

import com.springdemo.springdemo.helper.GetSalaryRange;
import com.springdemo.springdemo.models.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeDB {

    HashMap<Integer, Person> emp;
    private int counter;

    public EmployeeDB(HashMap<Integer, String> emp) {
        this.emp = new HashMap<Integer, Person>();
        this.emp.put(1, new Person("Gokulraj", "gokulrajganesan2000@gmail.com", 10000.0));
        this.emp.put(2, new Person("Sanjay", "sanjay@gmail.com", 10000.0));
        counter = 3;
    }

    @GetMapping("/employees/get/id/{id}")
    public HashMap getEmployeeDetailByID(@PathVariable(value = "id") int id) {
        HashMap<Integer, Person> getEmployeeDetailByID = new HashMap<>();
        getEmployeeDetailByID.put(id,this.emp.get(id));
        return getEmployeeDetailByID;
    }

    @GetMapping("/employees/get/name/{name}")
    public HashMap getEmployeeDetailByName(@PathVariable(value = "name") String name) {
        HashMap<Object, Object> searchByNameResult = new HashMap<>();

        for (Map.Entry detail: this.emp.entrySet()){
            Person individualPerson = (Person) detail.getValue();
            if(individualPerson.getName().equals(name)) {
                searchByNameResult.put(detail.getKey(),detail.getValue());
//                System.out.println(detail.getKey()+" "+detail.getValue());
            }
        }
        return searchByNameResult;
    }

    @GetMapping("/employees/query/salaryrange/")
    public HashMap getEmployeeDetailBySalaryRange(@RequestBody GetSalaryRange salaryRange) {
        HashMap<Object, Object> searchBySalaryResult = new HashMap<>();

        for (Map.Entry detail: this.emp.entrySet()){
            Person individualPerson = (Person) detail.getValue();
            if(individualPerson.getSalary()>=salaryRange.getStart() && individualPerson.getSalary()<=salaryRange.getEnd()) {
                searchBySalaryResult.put(detail.getKey(),detail.getValue());
//                System.out.println(detail.getKey()+" "+detail.getValue());
            }
        }

        return searchBySalaryResult;
    }

    @PostMapping("/employees/add")
    public void addEmployeeDetail(@RequestBody Person person) throws Exception {

        System.out.println(person.getName());
        if(person.getName()==null || person.getName().equals("")){
            throw new Exception("User Name Not Found");
        }

        this.emp.put(counter, new Person(person.getName(), person.getEmail(), person.getSalary()));

    }

    @PutMapping("/employees/update/{id}")
    public void updateEmployeeDetail(@PathVariable(value = "id") int id, @RequestBody Person person) throws Exception {
        if(this.emp.containsKey(id)){
            throw new Exception("User ID Not Found.");
        }

        emp.put(id, new Person(person.getName() != null ? person.getName() : this.emp.get(id).getName(),
                person.getEmail() != null ? person.getEmail() : this.emp.get(id).getEmail(),
                person.getSalary() != 0.0 ? person.getSalary() : this.emp.get(id).getSalary()));

    }

    @DeleteMapping("/employees/delete/{id}")
    public void deleteEmployeeDetail(@PathVariable(value = "id") int id) {

        this.emp.remove(id);
    }

}
