package io.shifu.doskiad.repository;

import io.shifu.doskiad.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
