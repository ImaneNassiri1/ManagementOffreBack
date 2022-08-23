package com.example.back_stage_application.Service;



import com.example.back_stage_application.Document.Question;
import lombok.extern.slf4j.Slf4j;


import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class QuestionSearchService {

    private static final String QUESTION_INDEX = "questiondb";

    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public QuestionSearchService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;

    }


    // NATIVE QUERY          //Method that make us find the list of question by entering some word that exiwt there
    public List<Question> findQuestionsByAnswer(final String answer) {


        QueryBuilder queryBuilder =
                QueryBuilders
                        .matchQuery("response", answer);


        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withTrackScores(true)
                .build();


        SearchHits<Question> questionHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Question.class,
                                IndexCoordinates.of(QUESTION_INDEX));
        double score=questionHits.getSearchHit(0).getScore();

        List<Question> questionMatches = new ArrayList<Question>();
        questionHits.forEach(searchHit->{
            questionMatches.add(searchHit.getContent());
        });

        return questionMatches;
    }

    public List<Question> findQuestionsByQuestion(final String answer) {


        QueryBuilder queryBuilder =
                QueryBuilders
                        .matchQuery("question", answer);


        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withTrackScores(true)
                .build();


        SearchHits<Question> questionHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Question.class,
                                IndexCoordinates.of(QUESTION_INDEX));
        double score=questionHits.getSearchHit(0).getScore();

        List<Question> questionMatches = new ArrayList<Question>();
        questionHits.forEach(searchHit->{
            questionMatches.add(searchHit.getContent());
        });

        return questionMatches;
    }

    /////////////////////////// THE LAST METHOD BUT IT RETURN JUST THEIR SCORE//////////////////////////////////////////
    public List<Float> findQuestionsByAnswerScore(final String answer) {
        QueryBuilder queryBuilder =
                QueryBuilders
                        .matchQuery("response", answer);


        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withTrackScores(true)
                .build();


        SearchHits<Question> questionHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Question.class,
                                IndexCoordinates.of(QUESTION_INDEX));
        double score=questionHits.getSearchHit(0).getScore();

        List<Float> questionMatches = new ArrayList<Float>();
        questionHits.forEach(searchHit->{
            questionMatches.add(searchHit.getScore());
        });

        return questionMatches;
    }

    ////////////////////////////////////////////////////////////SEARCH QUESTION/////////////////////////////////////////
    public List<Question> getQuestions(String question) {
        QueryBuilder queryBuilder =
                QueryBuilders
                        .matchQuery("response", question);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Question> questionHits = elasticsearchOperations.search(searchQuery, Question.class, IndexCoordinates.of(QUESTION_INDEX));

        List<Question> questionMatches = new ArrayList<Question>();
        questionHits.forEach(searchHit->{
            questionMatches.add(searchHit.getContent());
        });
        return questionMatches;
    }
    ////////////////////////////////////GIVE US SOME SUGGGESTION////////////////////////////////////////////////////////
    public List<Question> fetchSuggestions(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("question", query+"*");


        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();


        SearchHits<Question> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        Question.class,
                        IndexCoordinates.of(QUESTION_INDEX));

        List<Question> suggestions = new ArrayList<Question>();

        searchSuggestions.getSearchHits().forEach(searchHit->{
            suggestions.add(searchHit.getContent());
        });
        return suggestions;
    }
    // String Query
    public List<Question> findByAnswerName(final String answer) {
        Query searchQuery = new StringQuery(
                "{\"match\":{\"response\":{\"query\":\""+ answer + "\"}}}\"");

        SearchHits<Question> questions = elasticsearchOperations.search(
                searchQuery,
                Question.class,
                IndexCoordinates.of(QUESTION_INDEX));
        List<Question> questionMatches = new ArrayList<Question>();
        questions.forEach(searchHit->{
            questionMatches.add(searchHit.getContent());
        });
        return questionMatches;

    }
    //Methode Match Answer with question
    public List<Question> MatchQuestionAnswer(final String query) {
        log.info("Search with query {}", query);

        // 1. Create query on multiple fields enabling fuzzy search
        QueryBuilder queryBuilder =
                QueryBuilders
                        .multiMatchQuery(query, "question", "response")
                        .fuzziness(Fuzziness.AUTO);


        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();


        // 2. Execute search
        SearchHits<Question> productHits =
                elasticsearchOperations
                        .search(searchQuery, Question.class,
                                IndexCoordinates.of(QUESTION_INDEX));


        // 3. Map searchHits to question list
        List<Question> questionsMatches = new ArrayList<Question>();
        productHits.forEach(searchHit -> {
            questionsMatches.add(searchHit.getContent());
        });
        return questionsMatches;
    }


}


