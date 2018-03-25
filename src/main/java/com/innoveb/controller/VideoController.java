package com.innoveb.controller;

import com.innoveb.data.MultipartFileSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Created by prabhu on 24/3/18.
 */
@RestController
public class VideoController {
    Path videoLocation = FileSystems.getDefault().getPath("myvideo.mp4");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        return "videos";
    }

    @GetMapping(value = "/video")
    public void getVideo(HttpServletRequest request,
                         HttpServletResponse response){
        try {
            MultipartFileSender.fromPath(videoLocation)
                    .with(request)
                    .with(response)
                    .serveResource();
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
        }
    }

}
