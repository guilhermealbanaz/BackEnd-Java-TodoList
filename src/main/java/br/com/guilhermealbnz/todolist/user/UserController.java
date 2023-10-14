package br.com.guilhermealbnz.todolist.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var hasUserWithThisName = this.userRepository.findByName(userModel.getName());

        if (hasUserWithThisName == null) {
            var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

            userModel.setPassword(passwordHashred);
            this.userRepository.save(userModel);

            Map<String, Object> response = new HashMap<>();

            response.put("message", "User created successfully");
            response.put("user", userModel);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        Map<String, Object> responseError = new HashMap<>();

        responseError.put("message", "User created successfully");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
}
