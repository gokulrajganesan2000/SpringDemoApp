package com.springdemo.springdemo.controller;

import com.springdemo.springdemo.exception.EmailAddressNotValidException;
import com.springdemo.springdemo.exception.NameNotFoundException;
import com.springdemo.springdemo.exception.NumberLessThanZeroError;
import com.springdemo.springdemo.exception.ResourceNotFoundException;
import com.springdemo.springdemo.helper.GetSalaryRange;
import com.springdemo.springdemo.models.Person;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class EmployeeDB {

    HashMap<Integer, Person> emp;
    private int counter;
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    Pattern pattern ;
    public EmployeeDB() {
        this.emp = new HashMap<Integer, Person>();
        this.emp.put(1, new Person("Gokulraj", "gokulrajganesan2000@gmail.com", 20000.0));
        this.emp.put(2, new Person("Sanjay", "sanjay@gmail.com", 15000.0));
        counter = 3;

        pattern = Pattern.compile(emailRegex);
    }

    @GetMapping("/employees/get/id/{id}")
    public HashMap getEmployeeDetailByID(@PathVariable(value = "id") int id) {

        if(!this.emp.containsKey(id)){

            throw new ResourceNotFoundException("UserID Not Found");
        }

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
        Map<Object, Object> searchBySalaryResult = new HashMap<>();

        searchBySalaryResult = this.emp.entrySet().stream()
                .filter(salary -> salary.getValue().getSalary()>= salaryRange.getStart()
                        && salary.getValue().getSalary()<=salaryRange.getEnd())
                .collect(Collectors.toMap(detail -> detail.getKey(), detail -> detail.getValue()));

//                .forEach(detail -> searchBySalaryResult.put(detail.getKey(),detail.getValue()));

//        for (Map.Entry detail: this.emp.entrySet()){
//            Person individualPerson = (Person) detail.getValue();
//            if(individualPerson.getSalary()>=salaryRange.getStart() && individualPerson.getSalary()<=salaryRange.getEnd()) {
//                searchBySalaryResult.put(detail.getKey(),detail.getValue());
////                System.out.println(detail.getKey()+" "+detail.getValue());
//            }
//        }

        return (HashMap) searchBySalaryResult;
    }

    @PostMapping("/employees/add")
    public boolean addEmployeeDetail( @Valid @RequestBody Person person) {


        if(person.getName()==null || person.getName().equals("")){
            throw new NameNotFoundException("User name is Empty!");
        }
        if(person.getEmail()==null || person.getEmail().equals("") || !pattern.matcher(person.getEmail()).matches()){
            throw new NameNotFoundException("Enter an valid Email!");
        }
        if(person.getSalary()<0){
            throw new NumberLessThanZeroError("Salary must be a positive number");
        }

        this.emp.put(counter, new Person(person.getName(), person.getEmail(), person.getSalary()));
        return true;
    }

    @PutMapping("/employees/update/{id}")
    public boolean updateEmployeeDetail(@PathVariable(value = "id") int id, @RequestBody Person person){
        if(!this.emp.containsKey(id)){

            throw new ResourceNotFoundException("user id "+ id +" not found!" );
        }

        if(person.getName()==null || person.getName().equals("")){
            throw new NameNotFoundException("User name is Empty!");
        }
        if(person.getEmail()==null || person.getEmail().equals("") || !pattern.matcher(person.getEmail()).matches()){
            throw new EmailAddressNotValidException("Enter an valid Email!");
        }
        if(person.getSalary()<0){
            throw new NumberLessThanZeroError("Salary must be a positive number");
        }

        this.emp.put(id, new Person(person.getName(), person.getEmail(), person.getSalary()));



//        emp.put(id, new Person(person.getName() != null ? person.getName() : this.emp.get(id).getName(),
//                person.getEmail() != null ? person.getEmail() : this.emp.get(id).getEmail(),
//                person.getSalary() != 0.0 ? person.getSalary() : this.emp.get(id).getSalary()));

        return true;
    }



    @DeleteMapping("/employees/delete/{id}")
    public boolean deleteEmployeeDetail(@PathVariable(value = "id") int id) {
        if(!this.emp.containsKey(id)){

            throw new ResourceNotFoundException("UserID Not Found");
        }

        this.emp.remove(id);
        return true;
    }


}
