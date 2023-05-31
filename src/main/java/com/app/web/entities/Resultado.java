package com.app.web.entities;

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
@Table(name = "resultado", schema = "public")
public class Resultado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="partido_id", referencedColumnName = "id")
	private Partido partido; 
	@ManyToOne
	@JoinColumn(name="seleccion_id", referencedColumnName = "id")
	private Seleccion seleccion; 
	
	private Integer goles;
	private Integer amarillas;
	private Integer rojas;
	public String toString() {
	    return "id:"+id;
	}
}