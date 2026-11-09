package dev.taiga.crm.contact;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContactService {
    private final ContactRepository contacts;

    ContactService(ContactRepository contacts) {
        this.contacts = contacts;
    }

    public List<Contact> list(String stage, String query) {
        if (query != null && !query.isBlank())
            return contacts.findByNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(query, query);
        if (stage != null && !stage.isBlank()) return contacts.findByStage(Contact.Stage.valueOf(stage.toUpperCase()));
        return contacts.findAll();
    }

    public Contact create(ContactRequest request) {
        return contacts.save(new Contact(request.name(), request.email(), request.company(), request.stage(), null));
    }

    public Contact update(Long id, ContactRequest request) {
        Contact c = get(id);
        c.update(request.name(), request.company(), request.stage());
        return contacts.save(c);
    }

    public Contact markContacted(Long id) {
        Contact c = get(id);
        c.markContacted();
        return contacts.save(c);
    }

    public void delete(Long id) {
        contacts.delete(get(id));
    }

    private Contact get(Long id) {
        return contacts.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
    }
}
