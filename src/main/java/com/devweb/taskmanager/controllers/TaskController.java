package com.devweb.taskmanager.controllers;

import com.devweb.taskmanager.dto.GeneralResponse;
import com.devweb.taskmanager.entities.Task;
import com.devweb.taskmanager.entities.User;
import com.devweb.taskmanager.services.TaskService;
import com.devweb.taskmanager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<GeneralResponse> addTask(@Valid @RequestBody Task task, @RequestParam("id") Long userId){
        User user = userService.findById(userId);
        user.addTask(task);
        taskService.save(task);
        return new ResponseEntity<>(GeneralResponse.builder()
                .status(200)
                .data("Task created")
                .error(false)
                .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteTask(@PathVariable Long id){
        Task task = taskService.findById(id);
        taskService.remove(task);
        return new ResponseEntity<>(GeneralResponse.builder()
                .status(HttpStatus.OK.value())
                .data("Task delete")
                .error(false)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAllTask(){
        return new ResponseEntity<>(GeneralResponse.builder()
                .status(200)
                .data(taskService.findAll())
                .error(false)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("/user/{id}/tasks")
    public ResponseEntity<GeneralResponse> getAllByUserId(@PathVariable Long id){
        User user = userService.findById(id);
        List<Task> tasks = taskService.findByUser(user);
        return new ResponseEntity<>(GeneralResponse.builder()
                .status(200)
                .data(tasks)
                .error(false)
                .build(),
                HttpStatus.OK);
    }
}
