package com.app.web.entities;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "partido", schema = "public")
public class Partido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Timestamp fecha;
	@ManyToOne
	@JoinColumn(name="estadio_id", referencedColumnName = "id")
	private Estadio estadio; 
	public String toString() {
	    return "Estadio:"+estadio.getNombre() +" Fecha:"+fecha;
	}
}