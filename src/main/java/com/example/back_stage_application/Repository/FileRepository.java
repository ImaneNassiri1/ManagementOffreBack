package com.example.back_stage_application.Repository;

import com.example.back_stage_application.Document.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FileRepository extends ElasticsearchRepository<File,String> {
    List<File> findAll();
    List<File> findByDate(Date date);
    List<File> findByCategory(String category);






}

