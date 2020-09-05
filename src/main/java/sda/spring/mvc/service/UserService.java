package sda.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.spring.mvc.model.User;
import sda.spring.mvc.model.dto.UserDTO;
import sda.spring.mvc.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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

    public User save(UserDTO userDTO) {
        User user = new User()
                .setName(userDTO.getName())
                .setCountry(userDTO.getCountry())
                .setEmail(userDTO.getEmail());
        return repository.save(user);
    }

    public UserDTO getById(Long id) {
        User user = repository.findById(id).get();
        return new UserDTO()
                .setId(user.getId())
                .setName(user.getName())
                .setCountry(user.getCountry())
                .setEmail(user.getEmail());
    }


    public void remove(Long id) {
        repository.deleteById(id);
    }


}
