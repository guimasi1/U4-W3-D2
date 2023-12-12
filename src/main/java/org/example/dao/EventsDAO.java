package org.example.dao;

import org.example.entities.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EventsDAO {
    private final EntityManager em;

    public EventsDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Event event) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(event);

        transaction.commit();

        System.out.println("Congratulazioni, hai creato il tuo evento con successo.");
    }

    public Event getById(long id) {

        Event found =em.find(Event.class, id);
        if(found != null) return found;
        else {
            System.out.println("Spiacenti. Elemento con id " + id + " non trovato.");
            return null;
        }
    }
    public void deleteById(long id) {
        Event eventFound = this.getById(id);
        if(eventFound != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(eventFound);
            transaction.commit();
            System.out.println("Evento con id " + id + " eliminato.");
        } else {
            System.out.println("L'elemento con ID -" + id + "- non è stato trovato");
        }
    }


}
