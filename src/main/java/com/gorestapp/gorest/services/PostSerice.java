package com.gorestapp.gorest.services;

import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.PostsApiResponse;
import com.gorestapp.gorest.model.response.AuthKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSerice {
    private GorestAPIClient gorestAPIClient;

    public PostSerice(GorestAPIClient gorestAPIClient){
        this.gorestAPIClient=gorestAPIClient;
    }

    public PostsApiResponse postsApiResponseList(String userId){
        return this.gorestAPIClient.loadAllPostByUserId(userId);
    }
}
