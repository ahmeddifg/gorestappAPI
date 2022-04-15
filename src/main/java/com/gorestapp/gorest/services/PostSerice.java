package com.gorestapp.gorest.services;

import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.PostsApiResponse;
import com.gorestapp.gorest.model.response.AuthKey;
import com.gorestapp.gorest.model.response.PostViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostSerice {
    private GorestAPIClient gorestAPIClient;

    public PostSerice(GorestAPIClient gorestAPIClient) {
        this.gorestAPIClient = gorestAPIClient;
    }

    public List<PostViewModel> postsApiResponseList(Integer page,String userId) {
        PostsApiResponse postsApiResponse = this.gorestAPIClient.loadAllPostByUserId(userId,page);
        if (postsApiResponse != null && postsApiResponse.getData() != null) {
            List<PostViewModel> postViewModels = postsApiResponse.getData().stream().
                    map(item -> new PostViewModel(item)).collect(Collectors.toList());
            return postViewModels;
        } else return null;
    }
}
