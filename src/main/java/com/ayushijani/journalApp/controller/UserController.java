package com.ayushijani.journalApp.controller;

//controllers are the special typee of components that handles our http requests

import com.ayushijani.journalApp.entity.JournalEntry;
import com.ayushijani.journalApp.entity.User;
import com.ayushijani.journalApp.service.JournalEntryService;
import com.ayushijani.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

       @Autowired
       private UserService userService;

       @PostMapping
       public void createUser(@RequestBody User user){
           userService.saveEntry(user);
       }

       @PutMapping("/{userName}")
       public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
           User userinDb = userService.findByUserName(userName);

           if(userinDb != null)
           {
               userinDb.setUserName(user.getUserName());
               userinDb.setPassword(user.getPassword());
               userService.saveEntry(userinDb);
           }

           return new ResponseEntity<>(HttpStatus.NO_CONTENT);

       }


}

// controller --> service --> repository