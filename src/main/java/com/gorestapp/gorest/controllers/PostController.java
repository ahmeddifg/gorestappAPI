package com.gorestapp.gorest.controllers;

import com.gorestapp.gorest.model.request.PostRequest;
import com.gorestapp.gorest.model.response.AuthKey;
import com.gorestapp.gorest.services.PostSerice;
import com.gorestapp.gorest.utils.AesEncryption;
import com.gorestapp.gorest.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostSerice postSerice;

    @Autowired
    public PostController(PostSerice postSerice){
        this.postSerice = postSerice;
    }


    // load all posts
    @GetMapping("/all")
    public ResponseEntity<?> loadAllPost(@RequestParam(name="page", required = false) Integer page){
        return ResponseEntity.ok(this.postSerice.allPostsApiResponseList(page));
    }

    // Load only my posts
    @GetMapping("/myposts")
    public ResponseEntity<?> loadMyPost(@RequestParam(name="page", required = false) Integer page){
        return ResponseEntity.ok(this.postSerice.myPostsApiResponseList(page));
    }

    // when try to make a new post make sure the user already have one post.
    // otherwise gorest will send response with status 302 !!
    @PostMapping
    public ResponseEntity<?> loadAllPost(@RequestBody PostRequest postRequest ){
        return ResponseEntity.ok(this.postSerice.sendPost(postRequest));
    }



}
