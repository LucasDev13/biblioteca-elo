package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers;

import br.com.elotech.biblioteca_elo.application.validations.EmailValidator;
import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.UserUseCase;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserUseCase userUseCase;

    @Autowired
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequest userRequest) {
        if(EmailValidator.isValidEmail(userRequest.email())) {
            UserResponse novoUsuario = userUseCase.createUser(userRequest);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUser(@Valid @PathVariable String id) {
        UserResponse usuarioDTO = userUseCase.findUserById(id);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listUsers() {
        List<UserResponse> usuarios = userUseCase.listUsers();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @PathVariable String id, @RequestBody UserRequest userRequest) {
        UserResponse usuarioAtualizado = userUseCase.updateUser(id, userRequest);
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable String id) {
        userUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
