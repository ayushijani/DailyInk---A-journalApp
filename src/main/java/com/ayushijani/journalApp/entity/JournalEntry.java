package com.ayushijani.journalApp.entity;


import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

//this is a POJO class (plain old java object)
@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;

    private LocalDateTime date;

    private String title;

    private String content;
}
