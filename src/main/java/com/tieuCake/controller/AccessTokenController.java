package com.tieuCake.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.tieuCake.service.token.IAuthorizationService;
import com.tieuCake.util.BloggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nguye on 26-Jul-17.
 */
@RestController
@RequestMapping("/token")
public class AccessTokenController {


    @Autowired
    private IAuthorizationService authorizationService;

    @RequestMapping("/getAccessToken")
    public void getTokenAccess(HttpServletResponse response) throws IOException {
        // check the access token : false => require A new Token
        if (!authorizationService.checkTheAccessToken()) {
            GoogleAuthorizationCodeFlow authorizationCodeFlow = authorizationService.getGoogleAuthorizationCodeFlow();
            String url = authorizationCodeFlow.newAuthorizationUrl().setRedirectUri(BloggerUtil.REDIRECT_URI).build();
            response.sendRedirect(url);
        }
    }

    @RequestMapping("/receiveTokenAccess")
    public void receiveTokenAccess(@RequestParam("code") String code) throws IOException {
        authorizationService.storeTheAccessToken(code);
//        response.sendRedirect(url);
    }

}
