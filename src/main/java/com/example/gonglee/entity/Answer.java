package com.example.gonglee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1)
    private String answerText;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private Long likes = 0L;

    @Column(nullable = false)
    private Long dislikes = 0L;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (likes == null) {
            likes = 0L;
        }
        if (dislikes == null) {
            dislikes = 0L;
        }
    }

    public void incrementLikes() {
        this.likes = (this.likes == null ? 0L : this.likes) + 1;
    }

    public void incrementDislikes() {
        this.dislikes = (this.dislikes == null ? 0L : this.dislikes) + 1;
    }

    public void decrementLikes() {
        if (this.likes != null && this.likes > 0) {
            this.likes--;
        }
    }

    public void decrementDislikes() {
        if (this.dislikes != null && this.dislikes > 0) {
            this.dislikes--;
        }
    }
}