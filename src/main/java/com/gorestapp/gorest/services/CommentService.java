package com.gorestapp.gorest.services;

import com.gorestapp.gorest.entities.UserAuthAccount;
import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.CommentsApiResponse;
import com.gorestapp.gorest.model.response.CommentViewModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private GorestAPIClient gorestAPIClient;

    public CommentService(GorestAPIClient gorestAPIClient) {
        this.gorestAPIClient = gorestAPIClient;
    }

    public List<CommentViewModel> loadCommentForPost(String postId,Integer page) {
        CommentsApiResponse commentsApiResponse = this.gorestAPIClient.loadCommentsForPost(postId,page);
        if (commentsApiResponse != null && commentsApiResponse.getData() != null) {
            List<CommentViewModel> commentViewModelList = commentsApiResponse.getData().stream().
                    map(item -> new CommentViewModel(item)).collect(Collectors.toList());
            return commentViewModelList;
        } else return null;

    }

    public List<CommentViewModel> loadAllComments(Integer page) {
        CommentsApiResponse commentsApiResponse = this.gorestAPIClient.loadAllComments(page);
        if (commentsApiResponse != null && commentsApiResponse.getData() != null) {
            List<CommentViewModel> commentViewModelList = commentsApiResponse.getData().stream().
                    map(item -> new CommentViewModel(item)).collect(Collectors.toList());
            return commentViewModelList;
        } else return null;

    }

}
