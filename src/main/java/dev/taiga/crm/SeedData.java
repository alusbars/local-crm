package dev.taiga.crm;

import dev.taiga.crm.contact.Contact;
import dev.taiga.crm.contact.ContactRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SeedData {
    @Bean CommandLineRunner seed(ContactRepository contacts) {
        return args -> { if (contacts.count() == 0) {
            contacts.save(new Contact("Maya Chen", "maya@northstar.example", "Northstar Labs", Contact.Stage.ACTIVE, LocalDate.now().minusDays(6)));
            contacts.save(new Contact("Owen Brooks", "owen@atlas.example", "Atlas Works", Contact.Stage.LEAD, null));
            contacts.save(new Contact("Priya Nair", "priya@terracotta.example", "Terracotta", Contact.Stage.DORMANT, LocalDate.now().minusDays(90)));
        }};
    }
}
