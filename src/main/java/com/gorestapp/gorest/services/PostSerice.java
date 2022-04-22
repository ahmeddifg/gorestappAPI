package com.gorestapp.gorest.services;

import com.gorestapp.gorest.exceptions.exceptionTypes.PostNotFoundException;
import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.Post;
import com.gorestapp.gorest.integration.responseModel.PostApiSendResponse;
import com.gorestapp.gorest.integration.responseModel.PostsApiResponse;
import com.gorestapp.gorest.entities.UserAuthAccount;
import com.gorestapp.gorest.model.request.PostRequest;
import com.gorestapp.gorest.model.response.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostSerice {

    @Autowired
    private GorestAPIClient gorestAPIClient;


    @Value("${gorest.token}")
    private String gorestToken;

    public PostSerice(GorestAPIClient gorestAPIClient) {
        this.gorestAPIClient = gorestAPIClient;
    }

    public List<PostViewModel> postsApiResponseList(Integer page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthAccount currentUser = (UserAuthAccount) authentication.getPrincipal();

        PostsApiResponse posts = this.gorestAPIClient.getUserPosts(currentUser.getGorestId());
        if (posts.getData().size() > 0) {
            List<PostViewModel> postViewModels = posts.getData().stream().
            map(item -> new PostViewModel(item)).collect(Collectors.toList());
            return postViewModels;
        } else throw new PostNotFoundException();
    }

    public PostApiSendResponse sendPost(PostRequest postRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthAccount currentUser = (UserAuthAccount) authentication.getPrincipal();
        return this.gorestAPIClient.saveUserPost(new Post(postRequest, currentUser.getGorestId()), "Bearer " + gorestToken);
    }
}
