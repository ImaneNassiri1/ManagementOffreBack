package com.example.back_stage_application.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "questiondb")
public class Question implements Serializable {
    @Id
    @Field(type = FieldType.Keyword,name = "id")
    public String id;
    @Field(type = FieldType.Text,name = "client")
    public String client;
    @Field(type = FieldType.Text,name = "question")
    public String question;
    @Field(type = FieldType.Text,name = "response")
    public String response;
    @Field(type = FieldType.Text,name = "category")
    public String category;
    @Field(type = FieldType.Text,name = "comments")
    public String comments;
    @Field(type = FieldType.Date,name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date= Calendar.getInstance().getTime();


    public Question(String id,String client,String question,String response,String category,String comments) {
        this.id = id;
        this.client = client;
        this.question = question;
        this.response = response;
        this.category = category;
        this.comments = comments;
    }
}
