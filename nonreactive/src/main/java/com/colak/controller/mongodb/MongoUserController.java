package com.colak.controller.mongodb;

import com.colak.model.mongodb.User;
import com.colak.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController()
@RequestMapping("mongo")
@RequiredArgsConstructor
@Slf4j
public class MongoUserController {

    private final UserService userService;


    // http://localhost:8080/mongo/findById/1
    @Transactional(readOnly = true)
    @GetMapping(path = "/findById/{userId}")
    public Optional<User> findById(@PathVariable Long userId) {
        log.info("findById is called with : {}", userId);
        return userService.findById(userId);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) {
        // Return 404
        return ResponseEntity.notFound().build();
    }
}
