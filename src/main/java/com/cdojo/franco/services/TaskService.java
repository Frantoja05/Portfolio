package com.cdojo.franco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdojo.franco.models.Task;
import com.cdojo.franco.repositories.TaskRepo;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepo tr;
	
	public List<Task> allTasks(){
		return tr.findAll();
	}

	public Task saveTask(Task task) {
		return tr.save(task);
	}
	
	public Task findTask(Long id) {
		return tr.findById(id).orElse(null);
	}
	
	public void delete(Long id) {
		tr.deleteById(id);
	}
	
}
