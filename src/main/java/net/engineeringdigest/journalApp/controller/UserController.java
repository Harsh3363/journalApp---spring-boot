    package net.engineeringdigest.journalApp.controller;

    import net.engineeringdigest.journalApp.api.response.WeatherResponse;
    import net.engineeringdigest.journalApp.entity.User;
    import net.engineeringdigest.journalApp.repository.UserRepository;
    import net.engineeringdigest.journalApp.service.UserService;
    import net.engineeringdigest.journalApp.service.WeatherService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/user")
    public class UserController {

        @Autowired
        UserService userService;

        @Autowired
        UserRepository userRepository;

        @Autowired
        WeatherService weatherService;
    //    @GetMapping
    //    public  ResponseEntity<?> getAll(){
    //        List<User> allUser = userService.getAll();
    //        if(allUser!=null && !allUser.isEmpty()){
    //            return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    //        }
    //        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //    }



            @PutMapping()
            public ResponseEntity<?> updateUser(@RequestBody User user){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userName = authentication.getName();
                User userToUpdate = userService.findByUserName(userName);
                    userToUpdate.setUserName(user.getUserName());
                    userToUpdate.setPassword(user.getPassword());
                    userService.saveNewUser(userToUpdate);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);


            }

            @DeleteMapping()
            public ResponseEntity<?> deleteUser(@RequestBody User user){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userName = authentication.getName();
               userRepository.deleteByUserName(userName);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            @GetMapping()
            public ResponseEntity<?> greeting(){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                WeatherResponse Weatherresponse = weatherService.getWeather("Delhi");
                String greeting = "";
                if(greeting!=null){
                    greeting=" ,weather feels like: "+Weatherresponse.getCurrent().getFeelslike();
                }
                return new ResponseEntity<>("Hi "+authentication.getName()+greeting,HttpStatus.OK);
            }



    }
