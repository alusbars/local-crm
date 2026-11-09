package dev.taiga.crm.contact;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByStage(Contact.Stage stage);
    List<Contact> findByNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(String name, String company);
}
