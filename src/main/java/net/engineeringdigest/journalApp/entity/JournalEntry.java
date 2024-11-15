package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "journal_entries")
@Data

public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String content;
    @NonNull
    private String title;
    private LocalDateTime date;
    private Sentiment sentiment;

    public LocalDateTime getDate() {
        return date;
    }

}
