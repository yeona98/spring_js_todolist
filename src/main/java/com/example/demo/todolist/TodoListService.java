package com.example.demo.todolist;

import com.example.demo.todolist.dto.TodoRegisterRequestDto;
import com.example.demo.todolist.dto.TodoUpdateRequestDto;
import com.example.demo.todolist.exception.ResourceNotFoundException;
import com.example.demo.todolist.mapper.TodoResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoListService {
    private final TodoRepository todoRepository;

    @Transactional
    public Long register(TodoRegisterRequestDto payload) {
        return todoRepository.save(payload.toTodoEntity()).getId();
    }

    @Transactional
    public List<TodoResponseMapper> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(TodoResponseMapper::of)//  todo -> TodoResponseMapper.of(todo)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, TodoUpdateRequestDto payload) {
        if (id != payload.getId()) {
            //Error Process
        }
        return findById(id)
                .update(payload)
                .getId();
    }

    @Transactional
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    private Todo findById(final Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
    }
}
