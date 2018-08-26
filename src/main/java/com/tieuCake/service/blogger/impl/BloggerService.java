package com.tieuCake.service.blogger.impl;

//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.blogger.Blogger;
//import com.google.api.services.blogger.BloggerScopes;
//import com.google.api.services.blogger.model.Blog;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.blogger.Blogger;
import com.google.api.services.blogger.model.Blog;
import com.google.api.services.blogger.model.Post;
import com.google.api.services.blogger.model.PostList;
import com.tieuCake.dao.IPostDAO;
import com.tieuCake.model.entities.PostEntity;
import com.tieuCake.service.blogger.IBloggerService;
import com.tieuCake.service.token.IAuthorizationService;
import com.tieuCake.service.token.impl.AuthorizationService;
import com.tieuCake.util.BloggerUtil;
import com.tieuCake.util.PostStatusEnum;
import com.tieuCake.util.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
//import java.util.List;

/**
 * Created by nguye on 23-Jul-17.
 */
@Service
public class BloggerService implements IBloggerService {
    // Configure the Java API Client for Installed Native App
    private Logger logger = Logger.getLogger("BloggerService");

    @Autowired
    private IPostDAO postDAO;

    @Autowired
    private IAuthorizationService authorizationService;

    private Blogger blogger = null;


    public Blogger getBlogger() throws IOException, InvalidValueException {
        if(null == blogger){
            GoogleCredential credential = authorizationService.getCredential();
            blogger =  new Blogger.Builder(
                    new NetHttpTransport(),
                    new JacksonFactory(), credential)
                    .setApplicationName("Blogger")
                    .build();
        }

        return blogger;
    }


    /**
     * Get the blogger name
     *
     * @return the blogger name
     */
    public String getBloggerName() throws Exception {
        try {
            String result = "";
            logger.config("Get the google credential");

            logger.info("Process The blogger info");
            Blogger blogger = getBlogger();
            for (String blogId : BloggerUtil.BLOGGERIDS) {
                // The BlogId for the http://code.blogger.com/ blog.
                // This is the request action that you can configure before sending the request.
                Blogger.Blogs.Get blogGetAction = blogger.blogs().get(blogId);
                // Restrict the result content to just the data we need.
                blogGetAction.setFields("description,name,posts/totalItems,updated");
                // This step sends the request to the server.
                Blog blog = blogGetAction.execute();
                // Now we can navigate the response.
                System.out.println("Name: " + blog.getName());
                result = result + "Name: " + blog.getName() + "\t Post Count:" + blog.getPosts().getTotalItems();
                System.out.println("Description: " + blog.getDescription());
                System.out.println("Post Count: " + blog.getPosts().getTotalItems());
                System.out.println("Last Updated: " + blog.getUpdated());
            }
            return result;
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Synch the Post Data from the blogger
     *
     * @return a row number
     * @throws IOException
     * @throws ParseException
     */
    public Integer synchPublicPostDataFromBlogger() throws Exception {
        try {
            logger.config("Get the google credential");
            Blogger blogger =  getBlogger();
            List<Post>  results = new ArrayList<Post>();
            for (String blogId : BloggerUtil.BLOGGERIDS) {
                results = new ArrayList<Post>();
                logger.config("Set the related info to get the Posts from Blogger");
                // The request action.
                Blogger.Blogs.Get blogGetAction = blogger.blogs().get(blogId);
                Blog blog = blogGetAction.execute();
                Blogger.Posts.List postsListAction = blogger.posts().list(blogId);
                // Restrict the result content to just the data we need.
//                postsListAction.setFields("items(id,author/displayName,content,published,title,url),nextPageToken");
                // the row number is 500 in a request
                postsListAction.setMaxResults(Long.valueOf(500));
                int i = 0;
                logger.config("Set the Posts into the result list");
                //get the time loop
                int time = (blog.getPosts().getTotalItems() / 500.0)  <= 0 ? 1 : (int) (Math.ceil(blog.getPosts().getTotalItems() / 500.0));
                while (i < time) {
                    PostList posts = postsListAction.execute();
                    List<Post> tmpPost = posts.getItems().stream()
                            .filter(p -> (p.getId() != null && !p.getId().isEmpty()))
                            .filter(p -> (!p.getContent().contains("Xem tiếp &gt;&gt;&gt;") && !p.getContent().contains("Còn tiếp &gt;&gt;&gt;")))
                            .collect(Collectors.toList());
                    results.addAll(tmpPost);
                    System.out.println("-- Next page of posts");
                    System.out.println();
                    if(null == posts.getNextPageToken()){
                        break;
                    }
                    postsListAction.setPageToken(posts.getNextPageToken());
                    i++;
                }
                logger.config("There are " + results.size() + "");
                for (Post post : results) {
                    PostEntity postEntity = new PostEntity(
                            post.getId()
                            , post.getTitle()
                            , post.getAuthor().getDisplayName()
                            , post.getContent()
                            , post.getPublished()
                            , post.getUrl()
                            , PostStatusEnum.PUBLIC.toString()
                    );
                    postDAO.save(postEntity);
                }

            }
            return results.size();
        } catch (IOException  e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<PostEntity> getAllPostOfBlogger() {
     List<PostEntity> postEntities = new ArrayList<>();
//      Page<PostEntity> entities = postDAO.findAll(new PageRequest(1,2));
     
//        for(PostEntity entity : entities){
//            postEntities.add(entity);
//        }
        return postDAO.findAll();
//    	return postEntities;
    }
}
