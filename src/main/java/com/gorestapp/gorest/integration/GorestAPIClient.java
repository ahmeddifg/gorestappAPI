package com.gorestapp.gorest.integration;

import com.gorestapp.gorest.integration.responseModel.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public PostsApiResponse getUserPosts(@RequestParam("user_id") Integer user_id,
                                         @RequestParam("page") Integer page);

    @GetMapping("/posts")
    public PostsApiResponse getAllPost(@RequestParam("page") Integer page);


    @GetMapping("/comments")
    public CommentsApiResponse loadCommentsForPost(@RequestParam("post_id") String postId,
                                                   @RequestParam("page") Integer page);

    @GetMapping("/comments")
    public CommentsApiResponse loadAllComments(@RequestParam("page") Integer page);

    @PostMapping("/users")
    RegisterUserAccountResponse saveUserAccount(@RequestBody UserAccount userAccount, @RequestHeader("Authorization") String token);

    @PostMapping("/posts")
    PostApiSendResponse saveUserPost(@RequestBody Post post, @RequestHeader("Authorization") String token);
}
