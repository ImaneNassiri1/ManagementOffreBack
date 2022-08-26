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
@Document(indexName = "filedb")
public class File implements Serializable {
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
}