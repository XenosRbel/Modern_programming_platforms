package course.work.controllers;

import com.lowagie.text.DocumentException;
import course.work.models.*;
import course.work.repositories.ProjectRepository;
import course.work.repositories.RoleRepository;
import course.work.repositories.TaskRepository;
import course.work.repositories.UserRepository;
import course.work.requests.ProjectCreate;
import course.work.services.PdfExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/")
    List<Project> findAll() {
        return repository.findAll();
    }

    @PostMapping(value = "/create")
    Project create(@RequestBody ProjectCreate project) {
        Project newProject = new Project();
        newProject.setName(project.getName());
        newProject.setDescription(project.getDescription());
        newProject.setStatus(ProjectStatus.Open);
        newProject.setStartAt(new Date());
        // Add user to the project
        User user = userRepository.findById(project.user_id).orElse(null);
        assert user != null;
        Role role = new Role();
        role.setRole(RoleType.ProjectManager);
        user.getRoles().add(role);
//        Role role = new Role();
//        role.setRole(RoleType.ProjectManager);
//        role.getUsers().add(user);
//        user.getRoles().add(role);
        newProject.getUsers().add(user);
        return repository.save(newProject);
    }

    @DeleteMapping(value = "/{id}")
    Integer destroy(@PathVariable int id) {
        repository.deleteById(id);
        return id;
    }

    @PutMapping(value = "/add_task/{id}/{user_id}")
    Project addTask(@PathVariable int id, @PathVariable int user_id, @RequestBody Task task) {
        Project project = repository.findById(id).orElse(null);
        Task newTask = new Task();
        User user = userRepository.findById(user_id).orElse(null);
        newTask.setUser(user);
        newTask.setDescription(task.getDescription());
        newTask.setTitle(task.getTitle());
        newTask.setEstimation(task.getEstimation());
        newTask.setEstimation(task.getEstimation());
        newTask.setPriority(task.getPriority());
        newTask.setStatus(TaskStatus.NotStarted);
        taskRepository.save(newTask);
        assert project != null;
        project.getTasks().add(newTask);
        return repository.save(project);
    }

    @GetMapping("/export/pdf/{before}/{after}")
    public void exportToPDF(@PathVariable String before, @PathVariable String after, HttpServletResponse response) throws DocumentException, IOException, ParseException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date beforeDate = formatter.parse(before);
        Date afterDate = formatter.parse(after);
        afterDate.setHours(23);
        List<Project> listProjects = repository.findAllByCreatedAtBetween(beforeDate, afterDate);

        PdfExporter exporter = new PdfExporter(listProjects, before, after);
        exporter.export(response);
    }

    public static Date convertDate(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        return java.util.Date
                .from(LocalDateTime.parse(date,formatter).atZone(ZoneId.systemDefault()).toInstant());
    }
}
