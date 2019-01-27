package de.trx.veve.business;

import de.trx.veve.entity.User;
import de.trx.veve.integration.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Service der sich um die Verwaltung aller Nutzer ist.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gibt alle Vereinsmitglieder zurück.
     *
     * @return -
     */
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    /**
     * Gib ein Vereinsmitglied mit der übergebenen Id zurück.
     *
     * @param id -
     * @return -
     */
    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }

    /**
     * Erstellt das übergebene Vereinsmitglied und presistiert es in der Datenbank.
     *
     * @param user -
     */
    public void create(User user) {
        if (!user.isNew()) {
            throw new IllegalArgumentException("Cannot create a user that is not new");
        }
        this.userRepository.save(user);
    }

    /**
     * Löscht das übergebene Vereinsmitglied.
     *
     * @param user -
     */
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    /**
     * Aktualisiert das übergebene Vereinsmitglied.
     *
     * @param user -
     */
    public void update(User user) {
        this.userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
        this.userRepository.save(user);
    }
}
