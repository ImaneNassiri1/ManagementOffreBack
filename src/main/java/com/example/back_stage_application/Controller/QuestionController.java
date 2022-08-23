package com.example.back_stage_application.Controller;

import com.example.back_stage_application.Document.Question;
import com.example.back_stage_application.Service.QuestionSearchService;
import com.example.back_stage_application.Service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionSearchService questionSearchService;

    public QuestionController(QuestionService questionService, QuestionSearchService questionSearchService) {
        this.questionService = questionService;
        this.questionSearchService = questionSearchService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions=questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }


    @PostMapping("/tests")
    public List<Question> ByAnswerQuestion(@RequestBody Question question) {
        List<Question> questions = questionSearchService.MatchQuestionAnswer(question.getQuestion()) ;
        return questions;
    }

    @PostMapping("/testss")
    public List<Question> ByQuestion(@RequestBody Question question) {
        List<Question> questions = questionSearchService.findQuestionsByQuestion(question.getQuestion()) ;
        return questions;
    }

    @PostMapping("/testsss")
    public List<Question> ByAnswer(@RequestBody Question question) {
        List<Question> questions = questionSearchService.findQuestionsByAnswer(question.getQuestion()) ;
        return questions;
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        Question newQuestion= questionService.addQuestion (question);
        return new ResponseEntity<>(newQuestion,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") String id ){
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/postdelete")
    private void deleteQuestion(@RequestBody Question questionDto)
    {
        questionService.delete(questionDto.getId());
    }

    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        Question updateQuestion = questionService.updateQuestion(question);
        return new ResponseEntity<>(updateQuestion,HttpStatus.OK);
    }

    @PostMapping("/postCategory")
    public ResponseEntity<List<Question>> getQuestionCategory(@RequestBody Question questionDto) {
        return ResponseEntity.ok(questionService.getByCategory(questionDto));
    }


}
