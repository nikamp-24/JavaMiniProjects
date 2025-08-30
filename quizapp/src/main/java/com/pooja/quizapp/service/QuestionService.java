package com.pooja.quizapp.service;

import com.pooja.quizapp.dao.QuestionDao;
import com.pooja.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
       catch (Exception e){
            e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        if (questionDao.existsById(id)) {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted Successfully!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Question with ID " + id + " not found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        Optional<Question> existing = questionDao.findById(question.getId());
        if (existing.isPresent()) {
            questionDao.save(question);
            return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("\"Question with ID \" + question.getId() + \" not found!\"", HttpStatus.NOT_FOUND);
    }
}
