package com.cdojo.franco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.cdojo.franco.models.Priority;
import com.cdojo.franco.models.Task;
import com.cdojo.franco.models.User;
import com.cdojo.franco.services.TaskService;
import com.cdojo.franco.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TasksController {
	
	@Autowired
	private TaskService ts;
	
	@Autowired
	private UserService us;
	
	@GetMapping("/tasks")
	public String tasks(HttpSession session, Model model) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		if(userTemp == null) {
			return "redirect:/";
		}
		
		model.addAttribute("tasks", ts.allTasks());
		
		return "tasks.jsp";
	}
	
	@GetMapping("/tasks/new")
	public String taskNew(HttpSession session,
							 @ModelAttribute("task")Task task,
							 Model model) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		if(userTemp == null) {
			return "redirect:/";
		}	
		
		List<User> assignees = us.allUsers();
		model.addAttribute("assignees", assignees);
		model.addAttribute("priorities", Priority.Priorities);
		
		return "new.jsp";
	}
	
	@PostMapping("/tasks/create")
	public String taskCreate(@Valid @ModelAttribute("task")Task task,
								BindingResult result,
								HttpSession session,
								Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
		if(userTemp == null) {
			return "redirect:/";
		}
		
		List<User> assignees = us.allUsers();
		model.addAttribute("assignees", assignees);
		model.addAttribute("priorities", Priority.Priorities);
		
		if(result.hasErrors()) {
			model.addAttribute("assignees", assignees);
			model.addAttribute("priorities", Priority.Priorities);
			return "new.jsp";
		} else {
			ts.saveTask(task);
			return "redirect:/tasks";
		}
	}
	
	@GetMapping("/tasks/{taskId}")
	public String task(@PathVariable("taskId") Long taskId,
						HttpSession session,
						Model model) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		if(userTemp == null) {
			return "redirect:/";
		}
		
		User myUser = us.findUser(userTemp.getId());
		model.addAttribute("user", myUser);
		
		Task task = ts.findTask(taskId);
		model.addAttribute("task", task);
		
		return "task.jsp";
	}
	
	@GetMapping("/tasks/{taskId}/edit")
	public String editTask(@PathVariable("taskId") Long taskId,
						HttpSession session,
						Model model) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		if(userTemp == null) {
			return "redirect:/";
		}
		
		Task task = ts.findTask(taskId);
		
		if(userTemp.getId() != task.getCreator().getId()) {
			return "redirect:/tasks";
		}
		
		List<User> assignees = us.allUsers();
		model.addAttribute("assignees", assignees);
		model.addAttribute("priorities", Priority.Priorities);
		model.addAttribute("task", task);
		
		return "edit.jsp";
	}
	
	@PutMapping("/tasks/update")
	public String taskUpdate(@Valid @ModelAttribute("task")Task task,
								BindingResult result,
								HttpSession session,
								Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
		if(userTemp == null) {
			return "redirect:/";
		}
		
		if(result.hasErrors()) {
			List<User> assignees = us.allUsers();
			model.addAttribute("assignees", assignees);
			model.addAttribute("priorities", Priority.Priorities);
			return "edit.jsp";
		} else {
			ts.saveTask(task);
			return "redirect:/tasks";
		}
	}
	
	@DeleteMapping("/tasks/{taskId}/delete")
	public String taskDel(@PathVariable("taskId") Long taskId,
							HttpSession session) {
		User userTemp = (User) session.getAttribute("userInSession");
		if(userTemp == null) {
			return "redirect:/";
		}
		
		Task task = ts.findTask(taskId);
		
		if(userTemp.getId() != task.getCreator().getId()) {
			return "redirect:/tasks";
		}
		
		ts.delete(taskId);
		return "redirect:/tasks";
	}
	
}
