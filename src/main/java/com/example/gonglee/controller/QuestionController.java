package com.example.gonglee.controller;

import com.example.gonglee.entity.Question;
import com.example.gonglee.service.AnswerService;
import com.example.gonglee.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public String getQuestions(Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "questions/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("question", new Question());
        return "questions/create";
    }

    @PostMapping
    public String createQuestion(
            @RequestParam String title,
            @RequestParam String optionA,
            @RequestParam String optionB) {
        questionService.createQuestion(title, optionA, optionB);
        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    public String getQuestionDetail(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        return "questions/detail";
    }

    @PostMapping("/{id}/answers")
    public String createAnswer(
            @PathVariable Long id,
            @RequestParam String answerText,
            @RequestParam String content) {
        answerService.createAnswer(id, answerText, content);
        return "redirect:/questions/" + id;
    }

    @GetMapping("/random")
    public String getRandomQuestion(Model model) {
        Question question = questionService.getRandomQuestion();
        return "redirect:/questions/" + question.getId();
    }
}
