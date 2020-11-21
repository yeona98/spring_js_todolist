package com.example.demo.todolist.mapper;

import com.example.demo.todolist.Todo;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public class TodoResponseMapper {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    @Builder
    public TodoResponseMapper(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //todoEntitytoTodoResponseMapper
    public static TodoResponseMapper of(final Todo todo) {
        return TodoResponseMapper.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .content(todo.getContent())
                .author(todo.getAuthor())
                .build();
    }
}
