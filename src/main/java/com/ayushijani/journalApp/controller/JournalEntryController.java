package com.ayushijani.journalApp.controller;

//controllers are the special typee of components that handles our http requests

import com.ayushijani.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    public Map<Long, JournalEntry> journalEnries = new HashMap();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEnries.values());
    }


    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEnries.put(myEntry.getId(), myEntry);
        return true;
    }


    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntrybyid(@PathVariable Long myId){
        return journalEnries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntrybyid(@PathVariable Long myId){
        return journalEnries.remove(myId);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntrybyid(@PathVariable Long id, @RequestBody JournalEntry myEntry){
        return journalEnries.put(id,myEntry);
    }

}
