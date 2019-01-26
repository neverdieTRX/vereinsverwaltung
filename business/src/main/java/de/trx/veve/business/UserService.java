package de.trx.veve.business;

import de.trx.veve.entity.User;
import de.trx.veve.integration.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }

    public void create(User user) {
        this.userRepository.save(user);
    }

    public void delete(User user) {
        this.userRepository.delete(user);
    }

    public void update(User user) {
        this.userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
        this.userRepository.save(user);
    }
}
