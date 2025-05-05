package com.ayushijani.journalApp.controller;

//controllers are the special typee of components that handles our http requests

import com.ayushijani.journalApp.entity.JournalEntry;
import com.ayushijani.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;


    //create
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    //read
    @GetMapping
    public List<JournalEntry> getAll(){

        return journalEntryService.getAll();
    }

    //read by id
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntrybyid(@PathVariable ObjectId myId){

        return journalEntryService.findById(myId).orElse(null);
    }

    //update
    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntrybyid(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){

        JournalEntry old = journalEntryService.findById(id).orElse(null);

        if(old != null){

            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }

    //delete
    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntrybyid(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

}

// controller --> service --> repository