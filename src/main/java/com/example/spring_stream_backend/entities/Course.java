package com.example.spring_stream_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    private String id;

    private String title;

//   @OneToMany(mappedBy = "course")
//   private List<Video> list=new ArrayList<>();
}
