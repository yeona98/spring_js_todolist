package com.example.demo.todolist;

import com.example.demo.todolist.dto.TodoRegisterRequestDto;
import com.example.demo.todolist.dto.TodoUpdateRequestDto;
import com.example.demo.todolist.mapper.TodoResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TodoListController {
    private final TodoListService todoListService;

    @GetMapping("todolist")
    public List<TodoResponseMapper> findAll() {
        return todoListService.findAll();
    }

//    @PostMapping(value = "todolist",
//            produces="application/json",
//            consumes = "application/json")
//    public Long register(@RequestBody @Valid final TodoRegisterReguestDto payload) {
//        return todoListService.register(payload);
//    }

    @PostMapping(value = "todolist",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<Long> register(@RequestBody @Valid final TodoRegisterRequestDto payload) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoListService.register(payload));
    }

//    @PutMapping(value    = "todolist/{id}",
//            produces = "application/json",
//            consumes = "application/json" )
//    public Long update(@PathVariable("id") final Long id,
//                                       @RequestBody @Valid final TodoUpdateRequestDto payload) {
//        return todoListService.update(id, payload);
//    }

    @PutMapping( value    = "todolist/{id}",
            produces = "application/json",
            consumes = "application/json" )
    public ResponseEntity<Long> update(@PathVariable("id") final Long id,
                                       @RequestBody @Valid final TodoUpdateRequestDto payload) {
        return ResponseEntity.ok(todoListService.update(id, payload));
    }

    @DeleteMapping("todolist/{id}")
    public void delete(@PathVariable("id") final Long id) {
        todoListService.delete(id);
    }
}
