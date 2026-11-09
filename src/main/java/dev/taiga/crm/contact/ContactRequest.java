package dev.taiga.crm.contact;

public record ContactRequest(String name, String email, String company, Contact.Stage stage) { }
