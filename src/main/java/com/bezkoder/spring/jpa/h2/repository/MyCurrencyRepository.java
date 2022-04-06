package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCurrencyRepository extends JpaRepository<Tutorial, Long> {
}
