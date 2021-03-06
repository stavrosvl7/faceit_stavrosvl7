package com.unipi.stavrosvl7.faceit.controllers;

import com.unipi.stavrosvl7.faceit.helpers.ControllerHelper;
import com.unipi.stavrosvl7.faceit.models.Country;
import com.unipi.stavrosvl7.faceit.models.UserDto;
import com.unipi.stavrosvl7.faceit.repositories.CountryRepository;
import com.unipi.stavrosvl7.faceit.services.EventStreamService;
import com.unipi.stavrosvl7.faceit.models.User;
import com.unipi.stavrosvl7.faceit.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final EventStreamService eventStreamService;
    private final ControllerHelper controllerHelper;
    private final CountryRepository countryRepository;

    public UserController(UserRepository userRepository, EventStreamService eventStreamService, ControllerHelper controllerHelper, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.eventStreamService = eventStreamService;
        this.controllerHelper = controllerHelper;
        this.countryRepository = countryRepository;
    }

    @GetMapping("/users/all")
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();

        LOGGER.info("All users fetched successfully");

        return users;
    }

    @GetMapping("/users/all/by/country/{countryName}")
    public List<User> getAllByCountryId(@PathVariable(value = "countryName") String countryName) {

        Country country = countryRepository.findByName(countryName.toLowerCase())
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));

        List<User> users = userRepository.findAllByCountry(country);

        LOGGER.info("All users from country {} fetched successfully", users.get(0).getCountry().getName());

        return users;
    }


    @PostMapping("/users/add")
    public ResponseEntity addUser(@RequestBody UserDto newUser) {

        Country country = countryRepository.findByIdAquireLock(newUser.getCountryId())
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));

        User user = controllerHelper.validateUser(newUser, country);

        if (user != null) {
            eventStreamService.produceEvent(user);

            LOGGER.info("User with email {} added successfully", user.getEmail());

            userRepository.save(user);

            return ResponseEntity.ok(HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().body("Not acceptable user fields");
    }

    @DeleteMapping("/users/remove/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable(value = "userId") Long userId) {

        User user = userRepository.findByIdAquireLock(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(user);

        LOGGER.info("User with id {} deleted successfully", userId);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping("/users/modify/{userId}")
    public ResponseEntity<?> update(@PathVariable(value = "userId") Long userId, @RequestBody UserDto sourceUser) {

        User user = controllerHelper.validateUser(sourceUser);

        if (user != null) {
            User targetUser = userRepository.findByIdAquireLock(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            BeanUtils.copyProperties(sourceUser, targetUser, "userId");

            LOGGER.info("User with id {} modified successfully", userId);

            userRepository.save(targetUser);

            return ResponseEntity.ok(HttpStatus.OK);
        }

        return ResponseEntity.badRequest().body("Not acceptable user fields");
    }
}
