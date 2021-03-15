package com.unipi.stavrosvl7.faceit.repositories;

import com.unipi.stavrosvl7.faceit.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "select * from dom_country where id=?1 for update skip locked", nativeQuery = true)
    Optional<Country> findByIdAquireLock(Long countryId);

}
