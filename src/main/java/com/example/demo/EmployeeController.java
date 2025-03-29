package com.example.demo;

import com.example.projekt3okienka.ClassContainer;
import com.example.projekt3okienka.ClassEmployee;
import com.example.projekt3okienka.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {


    private final List<Employee> employees = new ArrayList<>();
    private Map<String, ClassEmployee> groups = new HashMap<>();

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        if (employee.getImie() == null || employee.getNazwisko() == null || employee.getImie().isEmpty() || employee.getNazwisko().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Imię i nazwisko są wymagane");
        }
        employees.add(employee);
        System.out.println("Dodano pracownika: " + employee);
        return employee;
    }

    @DeleteMapping("/{imie}/{nazwisko}")
    public String removeEmployee(@PathVariable String imie, @PathVariable String nazwisko) {
        Employee employeeToRemove = employees.stream().filter
                        (employee -> employee.getImie().equals(imie) && employee.getNazwisko().equals(nazwisko))
                .findAny().orElse(null);
        if (employeeToRemove != null) {
            employees.remove(employeeToRemove);
            System.out.println("Usunieto pracownika: " + employeeToRemove);
            return "Usunieto pracownika " + imie + " " + nazwisko;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pracownik o tym imieniu i nazwisku nie znajduje sie w bazie");
        }
    }


        @GetMapping
        public List<Employee> getAllEmployees() {
            return employees;
        }
    }
