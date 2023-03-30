package com.devweb.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Table(name = "taks")
@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @NotEmpty(message = "Title task is required")
    private String titleTask;

    @NotEmpty(message = "Description task is required")
    private String descriptionTask;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private User user;

}
