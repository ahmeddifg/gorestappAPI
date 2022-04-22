package com.gorestapp.gorest.controllers;

import com.gorestapp.gorest.model.response.AuthKey;
import com.gorestapp.gorest.services.CommentService;
import com.gorestapp.gorest.utils.AesEncryption;
import com.gorestapp.gorest.utils.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }


    // Load all comments
    @GetMapping("/")
    public ResponseEntity<?> loadCommentsForPost(@RequestParam(name="page", required = false) Integer page){
        return ResponseEntity.ok(this.commentService.loadAllComments(page));
    }

    // Get all comments for a post
    @GetMapping("/{postid}")
    public ResponseEntity<?> loadCommentsForPost(@PathVariable(value = "postid", required = true) String postid,
                                                 @RequestParam(name="page", required = false) Integer page){
        return ResponseEntity.ok(this.commentService.loadCommentForPost(postid,page));
    }
}
