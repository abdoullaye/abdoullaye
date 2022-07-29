package com.kebe94.springsecurityjpajwt.dao;

import com.kebe94.springsecurityjpajwt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
}
