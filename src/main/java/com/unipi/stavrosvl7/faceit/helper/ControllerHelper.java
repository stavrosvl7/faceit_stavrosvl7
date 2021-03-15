package com.unipi.stavrosvl7.faceit.helper;

import com.unipi.stavrosvl7.faceit.models.Country;
import com.unipi.stavrosvl7.faceit.models.User;
import com.unipi.stavrosvl7.faceit.models.UserDto;
import com.unipi.stavrosvl7.faceit.repositories.CountryRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import static com.unipi.stavrosvl7.faceit.Utilities.Utilities.isValidEmailAddress;

@Component
public class ControllerHelper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CountryRepository countryRepository;

    public ControllerHelper(BCryptPasswordEncoder bCryptPasswordEncoder, CountryRepository countryRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.countryRepository = countryRepository;
    }

    public User validateUser(UserDto newUser, Country country) {
        return createUser(newUser, country);
    }

    public User validateUser(UserDto newUser) {

        Country country = countryRepository.findByIdAquireLock(newUser.getCountryId())
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));

        return createUser(newUser, country);
    }

    private User createUser(UserDto newUser, Country country) {
        User user = new User(newUser.getFirstName(), newUser.getLastName(), newUser.getNickName(), newUser.getPassword(), newUser.getEmail(), country);

        if (user.getEmail() != null && user.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (isValidEmailAddress(user.getEmail())) {
                return user;
            }
            ;
        }
        return null;
    }
}
