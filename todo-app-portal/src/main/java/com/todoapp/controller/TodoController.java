package com.todoapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todoapp.model.Todo;
import com.todoapp.service.TodoService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/users")
public class TodoController {
	
	@Autowired
	TodoService todoService;

	@GetMapping(value="/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		System.out.println("Username :"+username);
		return todoService.getListTodos();
	}
	
	@GetMapping(value="/{username}/todos/{todoId}")
	public Todo getTodo(@PathVariable("username") String username,@PathVariable("todoId") long id) {
		System.out.println("Username :"+username+",Id "+id);
		return todoService.getTodo(username, id);
	}
	
	@PutMapping("/{username}/todos/{todoId}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable("username") String username,@PathVariable("todoId") long id,
			@RequestBody Todo todo) {
		boolean flag = todoService.updateTodo(username, id, todo);
		if(flag == true) {
			return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		}
		return new ResponseEntity<Todo>(todo,HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/{username}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable("username") String username,@RequestBody Todo todo) {
		Todo createdTodo = todoService.createTodo(username, todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(createdTodo.getId())
		.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{username}/todos/{todoId}")
	public ResponseEntity<Void> DeleteTodo(
			@PathVariable("username") String username,@PathVariable("todoId") long id) {
		Todo todo = todoService.deleteById(id); 
		if(todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
