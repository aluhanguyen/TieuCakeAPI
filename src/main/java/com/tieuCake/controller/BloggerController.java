package com.tieuCake.controller;

//import com.tieuCake.model.entities.PostEntities;
import com.tieuCake.model.entities.PostEntities;
import com.tieuCake.model.entities.PostEntity;
import com.tieuCake.service.blogger.IBloggerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by nguye on 23-Jul-17.
 */
@RestController
@RequestMapping("/blogger")
public class BloggerController {

    @Autowired
    private IBloggerService bloggerService;




    @RequestMapping("/getBlogName")
    public String getBloggerName() throws Exception {
        if(true)
            throw new RuntimeException();
        String blogName = bloggerService.getBloggerName();
        return blogName;
    }

    @RequestMapping("/getAllPosts")
    public PostEntities getAllPostsOfBlogger(){
        PostEntities postEntities = new PostEntities();
        try{
            List<PostEntity> postEntityList = bloggerService.getAllPostOfBlogger();
            postEntities.setPostEntities(postEntityList);
        }catch(Exception e){
            e.printStackTrace();
            System.out.append("++++++++++++++++++++++++++ Error+++++++++++++" + e.getMessage() );
        }
        return postEntities;
    }


    @RequestMapping("/synchPostDataFromBlogger")
    public Integer synchPostDataFromBlogger(){
        int rowNumber = 0;
        try {
            rowNumber = bloggerService.synchPublicPostDataFromBlogger();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.append("++++++++++++++++++++++++++ Error+++++++++++++" + e.getMessage() );
        }
        return rowNumber;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleResourceNotFoundException( ModelAndView modelAndView) {
        modelAndView.setViewName("hello");
        return modelAndView;
    }



}
