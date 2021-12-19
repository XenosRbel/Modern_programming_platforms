package course.work.controllers;

import course.work.models.User;
import course.work.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/")
    List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    User findOne(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping(value = "/get_by_username")
    User getByUsername(@RequestBody User user) {
        return repository.findByUsername(user.getUsername());
    }

    @PutMapping(value = "/{id}")
    User update(@PathVariable int id, @RequestBody User user) {
        User oldUser = repository.findById(id).orElse(null);
        assert oldUser != null;
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        return repository.save(oldUser);
    }

    @DeleteMapping(value = "/{id}")
    Integer destroy(@PathVariable int id) {
        repository.deleteById(id);
        return id;
    }
}
