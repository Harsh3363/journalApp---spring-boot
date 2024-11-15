package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("health-check")
    public String healthCheck(){
        return "Okay";
    }

    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody User newEntry){
        try {
            userService.saveNewUser(newEntry);
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }
}
