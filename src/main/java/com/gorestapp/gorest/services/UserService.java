package com.gorestapp.gorest.services;

import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.UserApiResponse;
import com.gorestapp.gorest.model.response.AuthKey;
import com.gorestapp.gorest.utils.AesEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private GorestAPIClient gorestAPIClient;

    @Autowired
    public UserService(GorestAPIClient gorestAPIClient) {
        this.gorestAPIClient = gorestAPIClient;
    }

    public UserApiResponse loadAllUsers() {
        return this.gorestAPIClient.loadUsers();
    }


    public AuthKey login(String email, Integer userId) {
        String accessToken=null;


        UserApiResponse userApiResponse = this.gorestAPIClient.findUserByEmailAndId(email, userId);
        if(userApiResponse !=null && userApiResponse.getData().size()>0){
            accessToken= AesEncryption.encrypt(userApiResponse.getData().get(0).getEmail() +"(|-|)"+
                    userApiResponse.getData().get(0).getId(),"myKey2213");
        return new AuthKey(accessToken);
        }
        else return null;
    }
}
