package de.trx.veve.facade.user;

import de.trx.veve.entity.Address;
import de.trx.veve.entity.User;
import de.trx.veve.facade.exceptions.IllegalArgumentRestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


class UserDTOUtils {

    static User buildUserFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        Address address = new Address();
        address.setNumber(userDTO.getNumber());
        address.setPlace(userDTO.getPlace());
        address.setPostalCode(userDTO.getPostalCode());
        address.setStreet(userDTO.getStreet());
        user.setAddress(address);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime;
        try {
            dateTime = LocalDate.parse(userDTO.getBirthDate(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentRestException("Could not parse date: " + userDTO.getBirthDate());
        }
        user.setBirthDate(dateTime);
        user.setIban(userDTO.getIban());
        user.setEmployment(userDTO.isEmployment());
        return user;
    }

    static UserDTO buildDTOFromUser(User user) {
        UserDTO dto = new UserDTO();
        dto.setIban(user.getIban());
        dto.setBirthDate(user.getBirthDate().toString());
        dto.setEmployment(user.isEmployment());
        dto.setNumber(user.getAddress().getNumber());
        dto.setPlace(user.getAddress().getPlace());
        dto.setPostalCode(user.getAddress().getPostalCode());
        dto.setStreet(user.getAddress().getStreet());
        dto.setName(user.getName());
        dto.setId(user.getId());
        return dto;
    }
}
