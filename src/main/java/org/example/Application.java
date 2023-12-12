package org.example;

import net.bytebuddy.asm.Advice;
import org.example.dao.EventsDAO;
import org.example.entities.Event;
import org.example.entities.EventType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-D2");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        LocalDate date1 = LocalDate.parse("2024-01-01");
        EventsDAO ed = new EventsDAO(em);
        Event event1 = new Event("Concerto Pooh", date1,"" ,EventType.PUBLIC,100000);
        Event event2 = new Event("Concerto di Gigi D'Alessio", date1, "E come fai a perderlo", EventType.PUBLIC, 10);

        // ed.save(event1);
        // ed.deleteById(1);
        // ed.save(event2);
        // System.out.println(ed.getById(3));
        eventHandler(scanner, ed);

        em.close();
        emf.close();
        scanner.close();
    }

    public static void eventHandler(Scanner scanner,EventsDAO ed) {
        String answer = null;
        boolean firstTimeSave = true;
        do {
            if (firstTimeSave) {
                System.out.println("Vuoi inserire un evento? Rispondi con si o no.");
                answer = scanner.nextLine();
            }
            if (answer.equals("si")) {
                String title;
                LocalDate date;
                String description;
                EventType typeOfEvent = null;
                int maxnumberOfParticipants;
                System.out.println("Qual è il nome dell'evento?");
                title = scanner.nextLine();
                System.out.println("Che giorno sarà l'evento? Inserisci il formato yyyy-mm-dd");
                date = LocalDate.parse(scanner.nextLine());
                System.out.println("Inserisci una descrizione dell'evento");
                description = scanner.nextLine();
                System.out.println("L'evento è pubblico o privato?");
                String publicOrPrivate = scanner.nextLine();
                if (publicOrPrivate.equals("privato")) typeOfEvent = EventType.PRIVATE;
                if (publicOrPrivate.equals("pubblico")) typeOfEvent = EventType.PUBLIC;
                System.out.println("Qual è il massimo di partecipanti?");
                maxnumberOfParticipants = Integer.parseInt(scanner.nextLine());
                Event newEvent = new Event(title, date, description, typeOfEvent, maxnumberOfParticipants);
                ed.save(newEvent);
                System.out.println("Vuoi aggiungere un altro evento? Rispondi si o no.");
                answer = scanner.nextLine();
                firstTimeSave = false;
            }
        } while(answer.equals("si"));
        String answerDelete = null;
        boolean firstTimeDelete = true;
        do {
            if (firstTimeDelete) {
                System.out.println("Vuoi eliminare un evento? Rispondi si o no.");
                answerDelete = scanner.nextLine();
            }
            if (answerDelete.equals("si")) {
                System.out.println("Inserisci il numero Id dell'evento da eliminare.");
                long idToDelete = Long.parseLong(scanner.nextLine());
                ed.deleteById(idToDelete);
                System.out.println("Vuoi eliminare un altro evento? Rispondi si o no.");
                answerDelete = scanner.nextLine();
                firstTimeDelete = false;
            }
        } while(answerDelete.equals("si"));
        String readEvent = null;
        boolean firstTimeGet = true;
        do {
            if(firstTimeGet) {
                System.out.println("Vuoi leggere tutte le informazioni di un evento? Rispondi si o no.");
                readEvent = scanner.nextLine();
            }
            if (readEvent.equals("si")) {
                System.out.println("Inserisci il numero id dell'evento da visualizzare");
                long idToRead = Long.parseLong(scanner.nextLine());
                System.out.println(ed.getById(idToRead));
                System.out.println("Vuoi visualizzare un altro evento? Rispondi si o no.");
                readEvent = scanner.nextLine();
                firstTimeGet = false;
            }
        } while(readEvent.equals("si"));
        System.out.println("Event handler - quit");
    }

}
