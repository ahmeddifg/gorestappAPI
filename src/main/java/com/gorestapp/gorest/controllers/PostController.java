package com.gorestapp.gorest.controllers;

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

    @GetMapping("/all")
    public ResponseEntity<?> loadAllPost(@RequestHeader(value = "token",required = true) String token,
                                         @RequestParam(name="page", required = false) Integer page){
        AuthKey authResponse = new AuthKey(AesEncryption.decrypt(token,"myKey2213"));
        return ResponseEntity.ok(this.postSerice.postsApiResponseList(page,AuthUtil.getUserid(authResponse.getAccessToken())));
    }



}
