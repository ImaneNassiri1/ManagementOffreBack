package com.example.back_stage_application.Repository;

import com.example.back_stage_application.Document.Question;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuestionRepository extends ElasticsearchRepository<Question,String> {

    List<Question> findAll();
    //    @Query("{ \"query\" : { \"bool\" : { \"must\" : [ { \"query_string\" : { \"query\" : \"?\", \"fields\" : [ \"question\" ] } } ] } }}")
    @Query("{\"bool\": {\"must\": [{\"match\": {\"question\": \"?0\"}}]}}")
    List<Question> findByQuestion(String question);

    List<Question> findByCategory(String category);
}

