package com.tieuCake.service.token;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.tieuCake.util.exception.InvalidValueException;

import java.io.IOException;

/**
 * Created by nguye on 26-Jul-17.
 */
public interface IAuthorizationService {

    public GoogleAuthorizationCodeFlow getGoogleAuthorizationCodeFlow();

    public GoogleCredential getCredential() throws IOException, InvalidValueException;

    public void storeTheAccessToken(String code) throws IOException;

    public boolean checkTheAccessToken();
}
