package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entities.Resultado;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Integer>{

}
