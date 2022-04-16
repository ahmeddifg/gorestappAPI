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
    public UserApiResponse loadUsers(@RequestParam("page") Integer page);

    @GetMapping("/users")
    public UserApiResponse findUserByEmailAndId(@RequestParam("email") String email, @RequestParam("id") Integer id );

    @GetMapping("/users")
    public UserApiResponse findUserByEmail(@RequestParam("email") String email);

    @GetMapping("/users")
    public UserApiResponse findUserByName(@RequestParam("name") String name);

    @GetMapping("/posts")
    public PostsApiResponse loadAllPostByUserId(@RequestParam("user_id")String userId,
                                                @RequestParam("page") Integer page);


    @GetMapping("/comments")
    public CommentsApiResponse loadCommentsForPost(@RequestParam("post_id") String postId,
                                                   @RequestParam("page") Integer page);


}
