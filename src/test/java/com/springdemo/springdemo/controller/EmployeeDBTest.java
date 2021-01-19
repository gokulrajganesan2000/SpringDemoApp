package com.springdemo.springdemo.controller;

import com.springdemo.springdemo.exception.EmailAddressNotValidException;
import com.springdemo.springdemo.exception.NameNotFoundException;
import com.springdemo.springdemo.exception.ResourceNotFoundException;
import com.springdemo.springdemo.helper.GetSalaryRange;
import com.springdemo.springdemo.models.Person;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDBTest {

    @Test
    void getEmployeeDetailByID() {
        EmployeeDB employeeDB = new EmployeeDB();

//        Testcase 1
        HashMap<Integer, Person> response = employeeDB.getEmployeeDetailByID(1);

        assertEquals(true, response.get(1).getName().equals("Gokulraj")
                && response.get(1).getEmail().equals("gokulrajganesan2000@gmail.com")
                && Double.compare(response.get(1).getSalary(), 20000.0) == 0);

//        Testcase 2
        Throwable error = assertThrows(ResourceNotFoundException.class, () -> employeeDB.getEmployeeDetailByID(11));

        assertEquals("UserID Not Found", error.getMessage());

    }

    @Test
    void getEmployeeDetailByName() {
        EmployeeDB employeeDB = new EmployeeDB();
        HashMap<Integer, Person> response = employeeDB.getEmployeeDetailByName("Sanjay");

//        Testcase 1
        assertEquals(true, response.get(2).getName().equals("Sanjay")
                && response.get(2).getEmail().equals("sanjay@gmail.com")
                && Double.compare(response.get(2).getSalary(), 15000.0) == 0);

//        Testcase 2
        assertEquals(false, response.get(2).getName().equals("Sanjay")
                && response.get(2).getEmail().equals("sanjay@gmail..com")
                && Double.compare(response.get(2).getSalary(), 15000.0) == 0);
    }

    @Test
    void getEmployeeDetailBySalaryRange() {
        EmployeeDB employeeDB = new EmployeeDB();
        HashMap<Integer, Person> employeeDetailBySalaryRange = employeeDB.getEmployeeDetailBySalaryRange(new GetSalaryRange(9000, 15000));
        assertEquals(true, employeeDetailBySalaryRange.get(2).getName().equals("Sanjay")
                && employeeDetailBySalaryRange.get(2).getEmail().equals("sanjay@gmail.com")
                && Double.compare(employeeDetailBySalaryRange.get(2).getSalary(), 15000.0) == 0);

    }

    @Test
    void addEmployeeDetail() {
//        Testcase 1
        EmployeeDB employeeDB = new EmployeeDB();
        assertEquals(true,
                employeeDB.addEmployeeDetail(new Person("Sharang", "sharangramana@gmail.com", 10000.0)));

//        Testcase 2
        EmployeeDB employeeDB1 = new EmployeeDB();
        Throwable error = assertThrows(NameNotFoundException.class,
                () -> employeeDB1.addEmployeeDetail(new Person("", "sharangramana@gmail.com", 10000.0)));

        assertEquals("User name is Empty!", error.getMessage());
    }


    @Test
    void deleteEmployeeDetail() {
//        Testcase 1
        EmployeeDB employeeDB = new EmployeeDB();

        Throwable error = assertThrows(ResourceNotFoundException.class, () -> employeeDB.deleteEmployeeDetail(20));

        assertEquals("UserID Not Found", error.getMessage());
    }

    @Test
    void updateEmployeeDetail() {
//        Testcase 1
        EmployeeDB employeeDB = new EmployeeDB();
        Throwable error = assertThrows(EmailAddressNotValidException.class,
                () -> employeeDB.updateEmployeeDetail(1, new Person("Gokulraj", "", 20000.0)));

        assertEquals("Enter an valid Email!", error.getMessage());
    }
}