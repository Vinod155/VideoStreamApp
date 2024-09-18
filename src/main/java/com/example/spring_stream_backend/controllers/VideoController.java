package com.example.spring_stream_backend.controllers;


import com.example.spring_stream_backend.entities.Video;
import com.example.spring_stream_backend.payload.CustomMessage;
import com.example.spring_stream_backend.services.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
@CrossOrigin("*")
public class VideoController {

    private final VideoService  videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<CustomMessage> create(@RequestParam("file") MultipartFile file,
                                                @RequestParam("title") String title,
                                                @RequestParam("description") String description
                                                )
    {
        Video video=new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideId(UUID.randomUUID().toString());
        Video savedVideo=videoService.save(video,file);

        if(savedVideo!=null)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomMessage.builder()
                            .message("Video is uploaded Successfully")
            .build());
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomMessage.builder()
                            .message("Video is not uploaded")
                            .build());
        }
    }
}
