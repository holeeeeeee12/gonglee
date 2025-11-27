package com.example.gonglee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "questions")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String title;
    
    @Column(nullable = false, length = 200)
    private String optionA;
    
    @Column(nullable = false, length = 200)
    private String optionB;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();

    private int countA;
    private int countB;

    public long getCountA() {
        return answers.stream()
                .filter(a -> "A".equals(a.getAnswerText()))
                .count();
    }
    
    public long getCountB() {
        return answers.stream()
                .filter(a -> "B".equals(a.getAnswerText()))
                .count();
    }
    
    public long getTotalCount() {
        return answers.size();
    }
    
    public double getPercentageA() {
        if (getTotalCount() == 0) return 0;
        return (double) getCountA() / getTotalCount() * 100;
    }
    
    public double getPercentageB() {
        if (getTotalCount() == 0) return 0;
        return (double) getCountB() / getTotalCount() * 100;
    }
}
