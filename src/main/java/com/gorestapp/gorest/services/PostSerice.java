package com.gorestapp.gorest.services;

import com.gorestapp.gorest.exceptions.exceptionTypes.PostNotFoundException;
import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.PostsApiResponse;
import com.gorestapp.gorest.authconfig.UserAuthAccount;
import com.gorestapp.gorest.model.response.PostViewModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostSerice {
    private GorestAPIClient gorestAPIClient;

    public PostSerice(GorestAPIClient gorestAPIClient) {
        this.gorestAPIClient = gorestAPIClient;
    }

    public List<PostViewModel> postsApiResponseList(Integer page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthAccount currentUser = (UserAuthAccount) authentication.getPrincipal();

        PostsApiResponse postsApiResponse = this.gorestAPIClient.loadAllPostByUserId(currentUser.getUserId()+"",page);
        if (postsApiResponse != null && postsApiResponse.getData() != null && postsApiResponse.getData().size()>0) {
            List<PostViewModel> postViewModels = postsApiResponse.getData().stream().
                    map(item -> new PostViewModel(item)).collect(Collectors.toList());
            return postViewModels;
        } else throw new PostNotFoundException();
    }
}
