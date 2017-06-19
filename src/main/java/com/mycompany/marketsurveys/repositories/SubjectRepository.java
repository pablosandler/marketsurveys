package com.mycompany.marketsurveys.repositories;

import com.mycompany.marketsurveys.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
