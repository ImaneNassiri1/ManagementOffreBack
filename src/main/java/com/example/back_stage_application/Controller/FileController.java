package com.example.back_stage_application.Controller;

import com.example.back_stage_application.Document.File;
import com.example.back_stage_application.Service.FileService;
import com.example.back_stage_application.Service.QuestionSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    FileService fileService;
    QuestionSearchService questionSearchService;

    public FileController(FileService fileService,QuestionSearchService questionSearchService) {
        this.fileService = fileService;
        this.questionSearchService=questionSearchService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<File>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    @PutMapping("/update/file")
    public ResponseEntity<File> updateQuestion(@RequestBody File file){
        File updateFile = fileService.updateFile(file);
        return new ResponseEntity<>(updateFile, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<File> addQuestion(@RequestBody File file){
        File newQuestion= fileService.addQuestion (file);
        return new ResponseEntity<>(newQuestion,HttpStatus.CREATED);
    }

    @PostMapping("/testss")
    public List<File> ByQuestion(@RequestBody File file) {
        List<File> files = questionSearchService.findQuestionsByQuestionfile(file.getQuestion()) ;
        return files;
    }



}
