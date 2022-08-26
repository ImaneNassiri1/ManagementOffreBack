package com.example.back_stage_application.Controller;

import com.example.back_stage_application.Document.File;
import com.example.back_stage_application.Document.Question;
import com.example.back_stage_application.Service.FileService;
import com.example.back_stage_application.Service.QuestionSearchService;
import com.example.back_stage_application.Service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.Record;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final FileService fileService;
    private final QuestionSearchService questionSearchService;

    public QuestionController(QuestionService questionService, FileService fileService, QuestionSearchService questionSearchService) {
        this.questionService = questionService;
        this.fileService = fileService;
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

    @PostMapping ("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception{
        List<Question> questions = new ArrayList<>();
        InputStream inputStream =file.getInputStream();
        String originalName=file.getOriginalFilename();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Question question = new Question();
            question.setId(record.getString("id"));
            question.setClient(record.getString("client"));
            question.setQuestion(record.getString("question"));
            question.setResponse(record.getString("response"));
            question.setCategory(record.getString("category"));
            question.setComments(record.getString("comments"));
            questions.add(question);
        });
        questionService.saves(questions);
        return new ResponseEntity<String>(originalName,HttpStatus.OK);

    }

    @PostMapping ("/upload/file")
    public ResponseEntity<String> upl(@RequestParam("file") MultipartFile file) throws Exception{
        List<File> files = new ArrayList<>();
        InputStream inputStream =file.getInputStream();
        String originalName=file.getOriginalFilename();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            File filess = new File();
            filess.setId(record.getString("id"));
            filess.setClient(record.getString("client"));
            filess.setQuestion(record.getString("question"));
            filess.setResponse(record.getString("response"));
            filess.setCategory(record.getString("category"));
            filess.setComments(record.getString("comments"));
            files.add(filess);
        });
        fileService.saves(files);
        return new ResponseEntity<String>(originalName,HttpStatus.OK);

    }


}
