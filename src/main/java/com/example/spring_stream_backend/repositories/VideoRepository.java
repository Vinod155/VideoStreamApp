package com.example.spring_stream_backend.repositories;

import com.example.spring_stream_backend.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VideoRepository  extends JpaRepository<Video,String> {
    Optional<Video> findByTitle(String title);
}
