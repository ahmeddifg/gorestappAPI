package com.gorestapp.gorest.model.response;

import com.gorestapp.gorest.integration.responseModel.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostViewModel {
    private String title;
    private String body;

    public PostViewModel(Post post){
        this.title=post.getTitle();
        this.body=post.getBody();
    }
}
