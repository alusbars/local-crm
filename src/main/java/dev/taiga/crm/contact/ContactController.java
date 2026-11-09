package dev.taiga.crm.contact;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crm")
public class ContactController {
    private final ContactService service;

    ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/contacts")
    public List<Contact> all(@RequestParam(required = false) String stage, @RequestParam(required = false) String q) {
        return service.list(stage, q);
    }

    @PostMapping("/contact/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact add(@RequestBody ContactRequest request) {
        return service.create(request);
    }

    @PostMapping("/contact/{id}/change")
    public Contact change(@PathVariable Long id, @RequestBody ContactRequest request) {
        return service.update(id, request);
    }

    @PostMapping("/contact/{id}/touched")
    public Contact touched(@PathVariable Long id) {
        return service.markContacted(id);
    }

    @PostMapping("/contact/{id}/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.delete(id);
    }
}
