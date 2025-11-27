package com.example.gonglee.controller;

import com.example.gonglee.entity.Answer;
import com.example.gonglee.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/questions")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/{questionId}/answers/{answerId}/like")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> likeAnswer(
            @PathVariable Long questionId,
            @PathVariable Long answerId) {

        try {
            Answer answer = answerService.getAnswerById(answerId);

            if (answer.getDislikes() > 0) {
                answerService.decrementDislike(answerId);
            }

            answerService.incrementLike(answerId);
            answer = answerService.getAnswerById(answerId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("likes", answer.getLikes());
            response.put("dislikes", answer.getDislikes());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/{questionId}/answers/{answerId}/dislike")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> dislikeAnswer(
            @PathVariable Long questionId,
            @PathVariable Long answerId) {

        try {
            Answer answer = answerService.getAnswerById(answerId);

            if (answer.getLikes() > 0) {
                answerService.decrementLike(answerId);
            }

            answerService.incrementDislike(answerId);
            answer = answerService.getAnswerById(answerId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("likes", answer.getLikes());
            response.put("dislikes", answer.getDislikes());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}