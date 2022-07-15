package com.traxcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traxcrm.entities.Billing;
import com.traxcrm.entities.Lead;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
	
}
