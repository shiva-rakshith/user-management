package org.rakshith.RESTAPIController;

import org.rakshith.UserActions.MysqlUserActions.MysqlUserActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTAPIController {

    @Autowired
    private MysqlUserActions execute;

    @PostMapping("/createUserDetails")
    public String createUserDetails(@RequestParam String phoneNumber, @RequestParam String name, @RequestParam int age, @RequestParam String location){
        return execute.createUser(phoneNumber,name,age,location);
    }

    @PutMapping("/updateUserDetails")
    public String updateUserDetails(@RequestParam String phoneNumber,@RequestParam String name,@RequestParam int age,@RequestParam String location){
        return execute.updateUser(phoneNumber,name,age,location);
    }

    @GetMapping("/showUserDetails")
    public String showUserDetails(@RequestParam String phoneNumber){
        return execute.showUser(phoneNumber);
    }

    @DeleteMapping("/deleteUserDetails")
    public String deleteUserDetails(@RequestParam String phoneNumber){
        return execute.deleteUser(phoneNumber);
    }


}
