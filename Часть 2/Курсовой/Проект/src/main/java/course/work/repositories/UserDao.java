package course.work.repositories;

import org.springframework.data.repository.CrudRepository;

import course.work.models.DAOUser;

public interface UserDao extends CrudRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
}