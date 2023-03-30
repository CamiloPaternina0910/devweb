package com.devweb.taskmanager.services;

import com.devweb.taskmanager.entities.Task;
import com.devweb.taskmanager.entities.User;

import java.util.List;

public interface TaskService {

    List<Task> findAll();
    List<Task> findByUser(User user);
    Task findById(Long id);
    void save(Task task);
    void remove(Task task);

}
