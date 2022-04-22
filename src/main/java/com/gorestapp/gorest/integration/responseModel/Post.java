package com.gorestapp.gorest.integration.responseModel;

import com.gorestapp.gorest.model.request.PostRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private Integer user_id;
    private String title;
    private String body;

    public Post(PostRequest postRequest, Integer userId){
        this.user_id=userId;
        this.title= postRequest.getTitle();
        this.body=postRequest.getBody();

    }
}
