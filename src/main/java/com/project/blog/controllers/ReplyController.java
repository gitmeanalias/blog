package com.project.blog.controllers;

import com.project.blog.entities.Reply;
import com.project.blog.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping(path = "/users/{id}/posts/{postId}/comments/{commentId}/replies")
    public ResponseEntity<List<Reply>> list() {
        return ResponseEntity.ok(replyService.getReplies());
    }

    @GetMapping(path = "/users/{id}/posts/{postId}/comments/{commentId}/replies/{replyId}")
    public ResponseEntity<Reply> listReply(@PathVariable(value = "replyId") Long replyId) {
        return ResponseEntity.ok(replyService.getReplyById(replyId).get());
    }

    @PostMapping(path = "/users/{id}/posts/{postId}/comments/{commentId}/replies")
    public ResponseEntity<Reply> saveReply(@PathVariable(value = "commentId") Long commentId, @RequestBody Reply reply) {
        return ResponseEntity.ok(replyService.createReply(commentId, reply));
    }

    @PostMapping(path = "/users/{id}/posts/{postId}/comments/{commentId}/replies/{replyId}/reply")
    public ResponseEntity<Reply> saveReplytoReply(@PathVariable(value = "replyId") Long replyId, @RequestBody Reply reply) {
        return ResponseEntity.ok(replyService.replyToReply(replyId, reply));
    }
}
