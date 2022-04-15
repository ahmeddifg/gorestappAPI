package com.gorestapp.gorest.services;

import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.CommentsApiResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private GorestAPIClient gorestAPIClient;

    public CommentService(GorestAPIClient gorestAPIClient){
        this.gorestAPIClient=gorestAPIClient;
    }

    public CommentsApiResponse loadCommentForPost(String postId){
        return this.gorestAPIClient.loadComentsForPost(postId);
    }

}
