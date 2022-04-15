package com.gorestapp.gorest.services;

import com.gorestapp.gorest.exceptions.exceptionTypes.LoginException;
import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.UserApiResponse;
import com.gorestapp.gorest.model.response.AuthKey;
import com.gorestapp.gorest.model.response.UserViewModel;
import com.gorestapp.gorest.utils.AesEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private GorestAPIClient gorestAPIClient;

    @Autowired
    public UserService(GorestAPIClient gorestAPIClient) {
        this.gorestAPIClient = gorestAPIClient;
    }

    public List<UserViewModel> loadAllUsers(Integer page) {
        List<UserViewModel> userViewModels = this.gorestAPIClient.loadUsers(page).getData().stream().
                map(item -> new UserViewModel(item)).collect(Collectors.toList());
        return userViewModels;
    }


    public AuthKey login(String email, Integer userId) {
        String accessToken = null;
        UserApiResponse userApiResponse = this.gorestAPIClient.findUserByEmailAndId(email, userId);
        if (userApiResponse != null && userApiResponse.getData().size() > 0) {
            accessToken = AesEncryption.encrypt(userApiResponse.getData().get(0).getEmail() + "(|-|)" +
                    userApiResponse.getData().get(0).getId(), "myKey2213");
            return new AuthKey(accessToken);
        } else throw new LoginException();
    }
}
