package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entities.Estadio;

@Repository
public interface EstadioRepository extends JpaRepository<Estadio, Integer>{

}
