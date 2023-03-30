package com.devweb.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Email(message = "Unsupported email format")
    @NotEmpty(message = "Email is required")
    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<Task> tasks;

    public Boolean addTask(Task task){
        if(tasks == null) tasks = new HashSet<>();
        return tasks.add(task);
    }

    public Boolean removeTask(Task task){
        return tasks.remove(task);
    }
}
