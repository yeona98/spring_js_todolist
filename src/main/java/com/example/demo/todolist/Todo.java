package com.example.demo.todolist;

import com.example.demo.todolist.dto.TodoUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;
    private String author;

    private String secret;

    @Builder
    public Todo(Long id, String title, String content, String author, String secret) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.secret = secret;
    }

    public Todo update(TodoUpdateRequestDto payload) {
        this.title = payload.getTitle();
        this.content = payload.getContent();
        this.author = payload.getAuthor();

        return this;
    }
}
