package dev.taiga.crm.contact;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String company;
    @Enumerated(EnumType.STRING)
    private Stage stage;
    private LocalDate lastContactedOn;

    protected Contact() {
    }

    public Contact(String name, String email, String company, Stage stage, LocalDate lastContactedOn) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.stage = stage;
        this.lastContactedOn = lastContactedOn;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public Stage getStage() {
        return stage;
    }

    public LocalDate getLastContactedOn() {
        return lastContactedOn;
    }

    public void update(String name, String company, Stage stage) {
        this.name = name;
        this.company = company;
        this.stage = stage;
    }

    public void markContacted() {
        this.lastContactedOn = LocalDate.now();
    }

    public enum Stage {LEAD, ACTIVE, DORMANT, ARCHIVED}
}
