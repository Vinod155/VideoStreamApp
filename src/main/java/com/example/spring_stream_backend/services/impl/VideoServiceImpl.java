package com.example.spring_stream_backend.services.impl;

import ch.qos.logback.core.util.StringUtil;
import com.example.spring_stream_backend.entities.Video;
import com.example.spring_stream_backend.repositories.VideoRepository;
import com.example.spring_stream_backend.services.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Value("${files.video}")
    String DIR;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PostConstruct
    public void init()
    {
        File file=new File(DIR);
        if(!file.exists())
        {
            file.mkdir();
        }
    }

    @Override
    public Video save(Video video, MultipartFile file) {
        try {
            //original filename
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            //folder path: create

            String cleanFileName= StringUtils.cleanPath(filename);//remove unwanted slash etc
            String cleanFolder= StringUtils.cleanPath(DIR);

            //folder path with filename

            Path path= Paths.get(cleanFolder,cleanFileName);//cleanFolder+cleanFilename


            //copy file to the folder
            Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);


            //video meta data
             video.setContentType(contentType);
             video.setFilePath(path.toString());

            //meta data save

            return videoRepository.save(video);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Video get(String videoId) {
        return null;
    }

    @Override
    public Video getByTitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return List.of();
    }
}
