package com.example.back_stage_application.Service;

import com.example.back_stage_application.Document.Question;
import com.example.back_stage_application.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    ////////////////////////////////////////// CRUD OF QUESTION/////////////////////////////////////////////////////////
    /////////////////////////////////////////1/METHOD TO ADD QUESTION///////////////////////////////////////////////////
    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }
    public Iterable<Question> ADDLISTOFQUESTION(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }

    ///////////////////////////////////////2/METHOD TO READ QUESTION////////////////////////////////////////////////////
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    /////////////////////////////////3/METHOD TO UPDATE QUESTION////////////////////////////////////////////////////////
    public Question updateQuestion(Question question){
        return questionRepository.save(question);
    }

    /////////////////////////////////4/METHOD TO DELETE QUESTION////////////////////////////////////////////////////////

    public void delete(String id)
    {
        questionRepository.deleteById(id);
    }

    ////////////////////////////////Methode to read question with Category//////////////////////////////////////////////
    public List<Question> getByCategory(Question question){
        String category=question.getCategory();
        return questionRepository.findByCategory(category);
    }
}

