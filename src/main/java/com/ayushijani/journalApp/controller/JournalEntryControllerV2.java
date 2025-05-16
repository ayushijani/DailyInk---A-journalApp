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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //read
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //read by id
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntrybyid(@PathVariable ObjectId myId){

        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //update
    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntrybyid(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String userName){

        JournalEntry old = journalEntryService.findById(id).orElse(null);

        if(old != null){

            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //delete
    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntrybyid(@PathVariable ObjectId myId, @PathVariable String userName){
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

// controller --> service --> repository