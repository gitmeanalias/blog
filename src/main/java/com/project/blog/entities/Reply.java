package com.project.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "replies")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reply")
    private List<Reply> replies = new ArrayList<Reply>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reply_id", nullable = true)
    private Reply reply;


    public Reply() {
        //
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @JsonIgnore
    public Comment getComment() {
        return comment;
    }

    @JsonIgnore
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Long getCommentId() {
        return comment.getId();
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @JsonIgnore
    public Reply getReply() {
        return reply;
    }

    @JsonIgnore
    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
