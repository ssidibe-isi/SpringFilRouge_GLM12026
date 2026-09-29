package com.groupeisi.HelloSpring.repositories;

import com.groupeisi.HelloSpring.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit,Long> {
}
