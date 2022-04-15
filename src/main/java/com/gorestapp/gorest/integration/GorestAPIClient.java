package com.gorestapp.gorest.integration;

import com.gorestapp.gorest.integration.responseModel.CommentsApiResponse;
import com.gorestapp.gorest.integration.responseModel.PostsApiResponse;
import com.gorestapp.gorest.integration.responseModel.UserApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GorestAPIClient", url = "${gorestservice.path}")
public interface GorestAPIClient {

    @GetMapping("/users")
    public UserApiResponse loadUsers();

    @GetMapping("/users")
    public UserApiResponse findUserByEmailAndId(@RequestParam("email") String email, @RequestParam("id") Integer id );

    @GetMapping("/posts")
    public PostsApiResponse loadAllPostByUserId(@RequestParam("user_id")String userId);


    @GetMapping("/comments")
    public CommentsApiResponse loadComentsForPost(@RequestParam("post_id") String  post_id);


}
