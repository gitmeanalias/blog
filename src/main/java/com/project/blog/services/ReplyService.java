package com.project.blog.services;

import com.project.blog.entities.Comment;
import com.project.blog.entities.Reply;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired private ReplyRepository replyRepo;
    @Autowired private CommentService commentService;

    public List<Reply> getReplies() {
        return replyRepo.findAll();
    }

    public Optional<Reply> getReplyById(Long replyId) {
        if(!replyRepo.existsById(replyId)) {
            throw new ResourceNotFoundException("Reply with id "+replyId+" does not exist.");
        }
        return replyRepo.findById(replyId);
    }

    public Reply createReply(Long commentId, Reply reply) {
        List<Reply> replies = new ArrayList<Reply>();
        Comment comment = new Comment();
        Optional<Comment> commentFound = commentService.getCommentById(commentId);

        if(!commentFound.isPresent()) {
            throw new ResourceNotFoundException("Comment with id "+commentId+" does not exist");
        }
        Comment tmpComment = commentFound.get();
        reply.setComment(tmpComment);

        Reply tmpReply = replyRepo.save(reply);
        replies.add(tmpReply);
        comment.setReplies(replies);

        return tmpReply;
    }

    public Reply replyToReply(Long replyId, Reply reply) {
        List<Reply> replies = new ArrayList<Reply>();
        Reply reply1 = new Reply();
        Optional<Reply> replyFound = replyRepo.findById(replyId);

        if(!replyFound.isPresent()) {
            throw new ResourceNotFoundException("Reply with id "+replyId+" does not exist.");
        }
        Reply replyRetrieved = replyFound.get();
        Comment commentRetrieved = replyRetrieved.getComment();

        reply.setReply(replyRetrieved);
        reply.setComment(commentRetrieved);

        Reply tmpReply = replyRepo.save(reply);

        replies.add(tmpReply);
        reply1.setReplies(replies);
        commentRetrieved.setReplies(replies);

        return tmpReply;
    }
}
