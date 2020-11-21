package com.example.demo.todolist.dto;

import com.example.demo.todolist.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class TodoRegisterRequestDto {
    @NotNull
    @Size(min = 3, max = 10, message = "최소 3글자, 최대 10글자")
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String author;
    @NotNull
    private String secret;

    @Builder
    public TodoRegisterRequestDto(@NotNull @Size(min = 3, max = 10, message = "최소 3글자, 최대 10글자")
                                           String title,
                                  @NotNull String content,
                                  @NotNull String author,
                                  @NotNull String secret) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.secret = secret;
    }

    public Todo toTodoEntity() {
        return Todo.builder()
                .title(title)
                .content(content)
                .author(author)
                .secret(secret)
                .build();
    }
}
