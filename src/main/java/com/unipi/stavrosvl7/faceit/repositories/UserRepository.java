package com.unipi.stavrosvl7.faceit.repositories;

import com.unipi.stavrosvl7.faceit.models.Country;
import com.unipi.stavrosvl7.faceit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();

    List<User> findAllByCountry(Country country);

    @Query(value = "select * from web_user where id=?1 for update skip locked",nativeQuery = true)
    Optional<User> findByIdAquireLock(Long userId);

}
