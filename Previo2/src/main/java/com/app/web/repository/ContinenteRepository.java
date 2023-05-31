package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entities.Continente;

@Repository
public interface ContinenteRepository extends JpaRepository<Continente, Integer>{

}
