package com.java.spring.kafka.api.controller;

import com.java.spring.kafka.api.model.Book;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {


    List<String> messages = new ArrayList<>();

    Book bookFromTopic = null;


    @GetMapping("/consumeStringMessage")
    public List<String> consumeMsg() {
        return messages;
    }


    @GetMapping("/consumeJsonMessage")
    public Book consumeJsonMessage() {
        return bookFromTopic;
    }


    @KafkaListener(groupId = "bookGrp1", topics = "book-topic", containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMsgFromTopic(String data) {
        messages.add(data);
        return messages;
    }


    @KafkaListener(groupId = "bookGrp2", topics = "book-topic", containerFactory = "bookKafkaListenerContainerFactory")
    public Book getAllBooksFromTopic(Book book) {
        bookFromTopic=book;
        return book;
    }


}
