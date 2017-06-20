package com.mycompany.marketsurveys.repositories;

import com.mycompany.marketsurveys.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository  extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);

}
