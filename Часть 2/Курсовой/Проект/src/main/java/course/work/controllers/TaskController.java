package course.work.controllers;

import course.work.models.Note;
import course.work.models.Task;
import course.work.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository repository;

    @GetMapping(value = "/")
    List<Task> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    Task findOne(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }


    @PutMapping(value = "/{id}")
    Task update(@PathVariable int id, @RequestBody Task task) {
        Task oldTask = repository.findById(id).orElse(null);
        assert oldTask != null;
        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setEstimation(task.getEstimation());
        oldTask.setWorkTime(task.getWorkTime());
        oldTask.setPriority(task.getPriority());
        Note note = new Note();
        if (task.getNoteText() != null) {
            note.setText(task.getNoteText());
            oldTask.getNotes().add(note);
        }
        oldTask.setStatus(task.getStatus());
        return repository.save(oldTask);
    }

    @DeleteMapping(value = "/{id}")
    Integer destroy(@PathVariable int id) {
        repository.deleteById(id);
        return id;
    }
}
