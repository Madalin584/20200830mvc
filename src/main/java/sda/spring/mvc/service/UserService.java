package sda.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sda.spring.mvc.model.User;
import sda.spring.mvc.model.dto.UserDTO;
import sda.spring.mvc.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User each : users) {
            UserDTO userDTO = new UserDTO()
                    .setCountry(each.getCountry())
                    .setEmail(each.getEmail())
                    .setName(each.getName())
                    .setId(each.getId()
                    );
            userDTOS.add(userDTO);
        }

        return userDTOS;
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
