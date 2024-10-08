package com.example.capstone1.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {

    @Id
    private Integer id;

    private String title;
    private String content;
    private String author;
}
