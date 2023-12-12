package org.example.entities;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventType type;
    @Column(name = "maxNumberOfParticipants")
    private int maxNumberOfParticipants;

    // COSTRUTTORI

    public Event() {}

    public Event(String title, LocalDate date, String description, EventType type, int maxNumberOfParticipants) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.type = type;
        this.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    // GETTER

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public EventType getType() {
        return type;
    }

    public int getMaxNumberOfParticipants() {
        return maxNumberOfParticipants;
    }


    //SETTER


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setMaxNumberOfParticipants(int maxNumberOfParticipants) {
        this.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", maxNumberOfParticipants=" + maxNumberOfParticipants +
                '}';
    }
}
