package com.example.gonglee.repository;

import com.example.gonglee.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionIdOrderByCreatedAtDesc(Long questionId);

    long countByQuestionId(Long questionId);
}