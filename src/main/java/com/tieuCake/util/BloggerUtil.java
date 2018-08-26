package com.tieuCake.util;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguye on 27-Jul-17.
 */
public class BloggerUtil {
    public static final String CLIENT_ID = "371015665476-ivhmk2tn3b25dtsa65b01las69i6qvmo.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "aQ7uEzpXXYQ7IIoG2yj1XET-";
    public static final String REDIRECT_URI = "http://localhost:8080/token/receiveTokenAccess";
    public static final List<String> BLOGGERIDS = new ArrayList<String>() {{add("8274459803872949119");}};

}
