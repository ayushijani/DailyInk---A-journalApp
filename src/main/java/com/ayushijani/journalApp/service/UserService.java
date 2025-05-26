package com.ayushijani.journalApp.service;

import com.ayushijani.journalApp.entity.JournalEntry;
import com.ayushijani.journalApp.entity.User;
import com.ayushijani.journalApp.repository.JournalEntryRepository;
import com.ayushijani.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired //dependency injection
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void saveEntry(User user){
        userRepository.save(user);
    }

    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;

        }catch (Exception e){
            log.info("we are learning logging");
            log.error("error occured for {}",user.getUserName(),e);
            log.warn("warning");
            log.debug("debug");
            log.trace("trace");
            return false;
        }
    }

    public void saveAdmin(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public List<User> getAll(){

        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){

        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){

        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}



// controller --> service --> repository