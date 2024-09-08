package com.pichincha.client.repository;

import com.pichincha.client.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByStatus(Boolean status);

    Optional<Client> findByIdentificationAndStatus(String identification, Boolean status);
}
