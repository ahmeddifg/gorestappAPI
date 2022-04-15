package com.gorestapp.gorest.model.response;

import com.gorestapp.gorest.integration.responseModel.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentViewModel {
    private String name;
    private String email;
    private String body;

    public CommentViewModel(Comment comment){
        this.name= comment.getName();
        this.email=comment.getEmail();
        this.body=comment.getBody();
    }
}
