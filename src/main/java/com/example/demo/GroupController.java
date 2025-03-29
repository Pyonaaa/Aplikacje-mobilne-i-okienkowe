package com.example.demo;

import com.example.projekt3okienka.ClassContainer;
import com.example.projekt3okienka.ClassEmployee;
import com.example.projekt3okienka.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private ClassContainer classContainer;

    public GroupController() {
        this.classContainer = new ClassContainer();
        this.classContainer.addClass("Grupa1", 10);
        this.classContainer.addClass("Grupa2", 5);
        this.classContainer.addClass("Grupa3", 4);
    }

    @GetMapping
    public List<String> getAllGroupNames() {
        return classContainer.getAllGroupNames();


    }

    @PostMapping
    public ResponseEntity<String> addGroup(@RequestBody Map<String, Object> requestData) {
        String groupName = (String) requestData.get("name");
        int capacity = (int) requestData.get("capacity");

        if (groupName == null || groupName.isEmpty() ||  capacity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nazwa grupy i pojemność są wymagane, a pojemność musi być większa od zera");
        }

        classContainer.addClass(groupName, capacity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dodano grupę: " + groupName + " z pojemnością: " + capacity);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> removeGroup(@PathVariable String name) {
        if (classContainer.getAllGroupNames().contains(name)) {
            classContainer.removeClass(name);
            return ResponseEntity.ok("Usunieto grupe " + name);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupa o nazwie: " + name + " nie istnieje");
        }
    }

       @PostMapping("/{groupName}/employee")
        public ResponseEntity<String> addEmployeeToGroup(@PathVariable String groupName, @RequestBody Employee employee) {
            ClassEmployee group = classContainer.getGroup(groupName);
           if (group == null) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupa o nazwie: " + groupName + " nie istnieje");
           }
           if (employee == null || employee.getImie() == null || employee.getNazwisko() == null || employee.getImie().isEmpty() || employee.getNazwisko().isEmpty()) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Imię i nazwisko pracownika są wymagane");
           }
           group.addEmployee(employee);
           return ResponseEntity.status(HttpStatus.CREATED).body("Dodano pracownika " + employee + " do grupy: " + groupName);
       }


    @GetMapping("/{groupName}/employee")
    public  ResponseEntity<List<Employee>>getEmployeesInGroup(@PathVariable String groupName) {
        ClassEmployee group = classContainer.getGroup(groupName);
        if (group == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupa o nazwie: " + groupName + " nie istnieje");
        }
        return ResponseEntity.ok(group.getAllEmployees());
    }
    }
