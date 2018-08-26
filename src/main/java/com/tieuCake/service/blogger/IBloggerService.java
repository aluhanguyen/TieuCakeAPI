package com.tieuCake.service.blogger;

import com.tieuCake.model.entities.PostEntity;
import com.tieuCake.util.exception.InvalidValueException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by nguye on 23-Jul-17.
 */
public interface IBloggerService {

    public String getBloggerName() throws Exception;

    public Integer synchPublicPostDataFromBlogger() throws Exception;

    public List<PostEntity> getAllPostOfBlogger();
}
