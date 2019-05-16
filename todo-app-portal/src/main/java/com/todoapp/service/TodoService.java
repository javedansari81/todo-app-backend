package com.todoapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todoapp.model.Todo;

@Service
public class TodoService {
	
	private static long idCount = 0;
	
	private static List<Todo> listTodos = new ArrayList<>();
	
	static {
		listTodos.add(new Todo(++idCount,"Study angular 7","Javed",new Date(),false));
		listTodos.add(new Todo(++idCount,"Play guitar","Javed",new Date(),false));
		listTodos.add(new Todo(++idCount,"Play game","Ashish",new Date(),false));
		listTodos.add(new Todo(++idCount,"Eat food","Ashish",new Date(),true));
	}
	
	public List<Todo> getListTodos(){
		return listTodos;
	}
	
	public Todo getTodo(String username,long id) {
		for (Todo todo : listTodos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
	
	public boolean updateTodo(String username,long id, Todo todo) {
		Todo t = findById(id);
		if(t != null) {
			listTodos.remove(t);
			listTodos.add(todo);
			return true;
		}
		return false;
	}
	
	public Todo createTodo(String username, Todo todo) {
		todo.setId(++idCount);
		todo.setUsername(username);
		listTodos.add(todo);
		return todo;
	}
	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		if(todo != null) {
			listTodos.remove(todo);
			return todo;
		}
		return null;
	}
	
	public Todo findById(long id) {
		for(Todo todo : listTodos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
	
	

}
