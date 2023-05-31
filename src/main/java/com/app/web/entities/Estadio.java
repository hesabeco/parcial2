package com.app.web.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "estadio", schema = "public")
public class Estadio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private int capacidad;
	@OneToMany( mappedBy="estadio")
	private List<Partido> partidos;
	public String toString() {
	    return nombre; 
	}
}
