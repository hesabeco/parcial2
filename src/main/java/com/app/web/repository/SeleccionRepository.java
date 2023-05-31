package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entities.Seleccion;
@Repository
public interface SeleccionRepository extends JpaRepository<Seleccion,Integer>{

}
