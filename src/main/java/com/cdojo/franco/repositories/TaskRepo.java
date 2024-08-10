package com.cdojo.franco.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdojo.franco.models.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long>{
	
	public List<Task> findAll();
	
}
