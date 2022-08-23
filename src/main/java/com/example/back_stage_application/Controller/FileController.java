package com.example.back_stage_application.Controller;

import com.example.back_stage_application.Document.File;
import com.example.back_stage_application.Document.Question;
import com.example.back_stage_application.Service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<File>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }


}
