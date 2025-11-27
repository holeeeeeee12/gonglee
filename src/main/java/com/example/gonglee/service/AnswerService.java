package com.example.gonglee.service;

import com.example.gonglee.entity.Answer;
import com.example.gonglee.entity.Question;
import com.example.gonglee.repository.AnswerRepository;
import com.example.gonglee.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public Answer createAnswer(Long questionId, String answerText, String content) {
        if (!("A".equals(answerText) || "B".equals(answerText))) {
            throw new IllegalArgumentException("answerText는 'A' 또는 'B'여야 합니다");
        }

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("질문을 찾을 수 없습니다: " + questionId));

        Answer answer = Answer.builder()
                .answerText(answerText)
                .content(content)
                .question(question)
                .likes(0L)
                .dislikes(0L)
                .build();

        return answerRepository.save(answer);
    }

    public Answer getAnswerById(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("답변을 찾을 수 없습니다"));
    }

    @Transactional(readOnly = true)
    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionIdOrderByCreatedAtDesc(questionId);
    }

    public void incrementLike(Long answerId) {
        Answer answer = getAnswerById(answerId);
        answer.incrementLikes();
        answerRepository.save(answer);
    }

    public void incrementDislike(Long answerId) {
        Answer answer = getAnswerById(answerId);
        answer.incrementDislikes();
        answerRepository.save(answer);
    }

    public void decrementLike(Long answerId) {
        Answer answer = getAnswerById(answerId);
        answer.decrementLikes();
        answerRepository.save(answer);
    }

    public void decrementDislike(Long answerId) {
        Answer answer = getAnswerById(answerId);
        answer.decrementDislikes();
        answerRepository.save(answer);
    }
}