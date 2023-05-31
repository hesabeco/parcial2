package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entities.Partido;
@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

}
