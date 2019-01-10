package com.wirecardtechincalchallenge.payment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wirecardtechincalchallenge.payment.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
