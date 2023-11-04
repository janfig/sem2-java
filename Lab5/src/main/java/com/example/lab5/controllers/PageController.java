package com.example.lab5.controllers;

import com.example.lab5.entities.Task;
import com.example.lab5.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8081/taskList/generate
// http://localhost:8081/taskList
// http://localhost:8081/delete/2
// http://localhost:8081/cost/between/1300/1700
// http://localhost:8081/cost/lessthan/1300/1700
@Controller
@AllArgsConstructor
public class PageController {
    private final TaskRepository taskRepository;

    @GetMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @GetMapping("/taskList")
    @ResponseBody
    public String taskList() {
        StringBuilder response = new StringBuilder();
        for (var task : taskRepository.findAll()) {
            response.append(task).append("<br>");
        }
        return response.toString();
    }

    @GetMapping("/taskList/generate")
    @ResponseBody
    public String taskListGenerate() {
        StringBuilder response = new StringBuilder();
        response.append("Tasks generated: <br>");

        for (var task : taskRepository.findAll()) {
            response.append(task).append("<br>");
        }

        double cost = 1000;
        boolean done = false;
        for (int i = 1; i <= 10; i++) {
            var newTask = new Task();
            newTask.setName("Task " + i);
            newTask.setDescription("Description " + i);
            newTask.setCost(cost += 200.5);
            newTask.setDone(done = !done);
            taskRepository.save(newTask);
            response.append(newTask).append("<br>");
        }

        return response.toString();
    }

    @GetMapping("/delete/{taskId}")
    @ResponseBody
    public String deleteTask(@PathVariable Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return "Task with ID " + taskId + " has been deleted.";
        } else {
            return "Task with ID " + taskId + " not found.";
        }
    }

    @GetMapping("/done/{done}")
    @ResponseBody
    public String findTasksByDone(@PathVariable boolean done) {
        List<Task> tasks = taskRepository.findByDone(done);
        var response = new StringBuilder();
        for (var task : tasks) {
            response.append(task).append("<br>");
        }
        return response.toString();
    }

    @GetMapping("/cost/lessthan/{cost}")
    @ResponseBody
    public String findTasksByCostLessThan(@PathVariable double cost) {
        List<Task> tasks = taskRepository.findByCostLessThan(cost);
        var response = new StringBuilder();
        for (var task : tasks) {
            response.append(task).append("<br>");
        }
        return response.toString();
    }

    @GetMapping("/cost/between/{minCost}/{maxCost}")
    @ResponseBody
    public String findTasksByCostBetween(
            @PathVariable double minCost,
            @PathVariable double maxCost
    ) {
        List<Task> tasks = taskRepository.findByCostBetween(minCost, maxCost);
        var response = new StringBuilder();
        for (var task : tasks) {
            response.append(task).append("<br>");
        }
        return response.toString();
    }
}