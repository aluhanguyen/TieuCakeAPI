package com.tieuCake.service.token.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.TokenErrorResponse;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.blogger.BloggerScopes;
import com.tieuCake.dao.IConfigDAO;
import com.tieuCake.model.entities.ConfigEntity;
import com.tieuCake.service.token.IAuthorizationService;
import com.tieuCake.util.BloggerUtil;

import com.tieuCake.util.exception.InvalidValueException;

//import com.tieuCake.service.token.IAuthorizationService;
//import com.tieuCake.util.BloggerUtil;
//import com.tieuCake.util.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.logging.Logger;

/**
 * Created by nguye on 26-Jul-17.
 */
@Service
public class AuthorizationService implements IAuthorizationService {

    private Logger logger = Logger.getLogger("AuthorizationService");

    @Autowired
    private IConfigDAO configDAO;

    @Override
    public boolean checkTheAccessToken() {
        ConfigEntity configEntity = configDAO.findOne(BloggerUtil.CLIENT_ID);
        return (null == configEntity || configEntity.getRefeshToken().isEmpty()) ? false:true;
    }

    public void storeTheAccessToken(String code) throws IOException {
        // End of command line prompt for the authorization code.
        GoogleTokenResponse tokenResponse = getGoogleAuthorizationCodeFlow().newTokenRequest(code)
                .setRedirectUri(BloggerUtil.REDIRECT_URI).execute();
        ConfigEntity configEntity = new ConfigEntity(
                BloggerUtil.CLIENT_ID
                , tokenResponse.getRefreshToken()
        );
        configDAO.save(configEntity);
    }

    public String  getTheAccessToken() throws IOException, InvalidValueException {
        // get token from file
        ConfigEntity configEntity = configDAO.findOne(BloggerUtil.CLIENT_ID);
        if(null == configEntity || configEntity.getRefeshToken().isEmpty()){
            logger.info("There is not a refesh code");
            throw new InvalidValueException("There is not a refesh code");
        }
        return configEntity.getRefeshToken();
    }

    public  GoogleAuthorizationCodeFlow getGoogleAuthorizationCodeFlow(){
        // Set the access type to offline so that the token can be refreshed.
        // By default, the library will automatically refresh tokens when it
        // can, but this can be turned off by setting
        // dfp.api.refreshOAuth2Token=false in your ads.properties file.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                new JacksonFactory(),
                BloggerUtil.CLIENT_ID, // This comes from your Developers Console project
                BloggerUtil.CLIENT_SECRET, // This, as well
                BloggerScopes.all())
                .setApprovalPrompt("force")
                .setAccessType("offline").build();

        return flow;
    }





    public  GoogleCredential getCredential() throws IOException, InvalidValueException {

        GoogleTokenResponse tokenResponse = new GoogleTokenResponse().setRefreshToken(getTheAccessToken());
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(new NetHttpTransport())
                .setJsonFactory(new JacksonFactory())
                .setClientSecrets(BloggerUtil.CLIENT_ID,BloggerUtil.CLIENT_SECRET )
                .addRefreshListener(new CredentialRefreshListener() {
                    @Override
                    public void onTokenResponse(Credential credential, TokenResponse tokenResponse) {
                        // Handle success.
                        System.out.println("Credential was refreshed successfully.");
                    }

                    @Override
                    public void onTokenErrorResponse(Credential credential,
                                                     TokenErrorResponse tokenErrorResponse) {
                        // Handle error.
                        System.err.println("Credential was not refreshed successfully. "
                                + "Redirect to error page or login screen.");
                    }
                })
                        // You can also add a credential store listener to have credentials
                        // stored automatically.
                        //.addRefreshListener(new CredentialStoreRefreshListener(userId, credentialStore))
                .build();

        // Set authorized credentials.
        credential.setFromTokenResponse(tokenResponse);
        // Though not necessary when first created, you can manually refresh the
        // token, which is needed after 60 minutes.
        credential.refreshToken();

        return credential;
    }



}
