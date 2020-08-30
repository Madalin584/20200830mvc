package sda.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sda.spring.mvc.model.User;
import sda.spring.mvc.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User getById(Long id) {
        return repository.findById(id).get();
    }


    public void remove(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User user) {
        User userFromDB = repository.findById(id).get();
        userFromDB.setCountry(user.getCountry());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setName(user.getName());
        return repository.save(userFromDB);
    }
}
