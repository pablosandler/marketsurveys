package com.mycompany.marketsurveys.repositories;
import com.mycompany.marketsurveys.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
