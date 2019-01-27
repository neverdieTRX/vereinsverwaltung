package de.trx.veve.facade.user;

import de.trx.veve.business.UserService;
import de.trx.veve.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Restschnittstelle für alle Nutzer Funktionalitäten.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@RestController
@RequestMapping(value = "/api/user")
@Component
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gibt alle Vereinsmitglieder zurück.
     *
     * @return -
     */
    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserDTO> findAll() {
        List<User> all = this.userService.findAll();
        return all.stream()
                .map((user) -> UserDTOUtils.buildDTOFromUser(user))
                .collect(Collectors.toList());
    }

    /**
     * Gibt das Vereinsmitglied mit der übergebenen Id zurück.
     *
     * @param id -
     * @return -
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public UserDTO findById(@RequestParam("id") Long id) {
        User user = this.userService.findById(id).orElseThrow(ResourceNotFoundException::new);
        UserDTO userDTO = UserDTOUtils.buildDTOFromUser(user);
        return userDTO;
    }

    /**
     * Löscht das Vereinsmitglied mit der übergebenen ID.
     *
     * @param id -
     */
    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam("id") Long id) {
        final Optional<User> optionalUser = this.userService.findById(id);
        this.userService.delete(optionalUser.orElseThrow(ResourceNotFoundException::new));
    }


    /**
     * Aktualisiert das übergebene Vereinsmiglied.
     *
     * @param userDTO -
     */
    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
    public void update(@RequestBody UserDTO userDTO) {
        try {
            this.userService.update(UserDTOUtils.buildUserFromDTO(userDTO));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Erstellt den übergebenen Nutzer.
     *
     * @param userDTO -
     */
    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
    public void create(@RequestBody UserDTO userDTO) {
        try {
            this.userService.create(UserDTOUtils.buildUserFromDTO(userDTO));
        } catch (IllegalArgumentException e) {
            throw new EntityExistsException("Could not save user: " + userDTO.getName() + ", because he already had an ID");
        }
    }


}
