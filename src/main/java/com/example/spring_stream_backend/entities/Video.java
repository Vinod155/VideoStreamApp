package com.example.spring_stream_backend.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    private String videId;

    private String title;

    private String description;

    private String contentType;

//    @ManyToOne
//    private Course course;

}
