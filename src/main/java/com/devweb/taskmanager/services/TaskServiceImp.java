package com.devweb.taskmanager.services;

import com.devweb.taskmanager.entities.Task;
import com.devweb.taskmanager.entities.User;
import com.devweb.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void remove(Task task) {
        taskRepository.delete(task);
    }
}
