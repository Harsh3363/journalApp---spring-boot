package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser() {
        List<User> allUser = userService.getAll();
        if (!allUser.isEmpty() && allUser != null) {
            return new ResponseEntity<>(allUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        userService.createAdmin(user);
        return new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }

}
