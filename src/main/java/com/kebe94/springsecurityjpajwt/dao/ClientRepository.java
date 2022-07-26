package com.kebe94.springsecurityjpajwt.dao;

import com.kebe94.springsecurityjpajwt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
