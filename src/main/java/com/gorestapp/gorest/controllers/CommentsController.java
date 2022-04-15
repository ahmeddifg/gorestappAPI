package com.gorestapp.gorest.controllers;

import com.gorestapp.gorest.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{postid}")
    public ResponseEntity<?> loadCommentsForPost(@PathVariable("postid") String postid){
        return ResponseEntity.ok(this.commentService.loadCommentForPost(postid));
    }
}
