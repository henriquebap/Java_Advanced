package br.com.fiap.apimypass.apimypass.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.apimypass.apimypass.User.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        return userRepository.save(user);
    }

}