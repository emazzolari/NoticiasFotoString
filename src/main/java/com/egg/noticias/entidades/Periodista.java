package com.egg.noticias.entidades;

import javax.persistence.Entity;

@Entity
public class Periodista extends Usuario {
	
	private Integer cantidadNoticias[];
	private Integer sueldoMensual;
	public Periodista() {
	
	}
	public Integer[] getCantidadNoticias() {
		return cantidadNoticias;
	}
	public void setCantidadNoticias(Integer[] cantidadNoticias) {
		this.cantidadNoticias = cantidadNoticias;
	}
	public Integer getSueldoMensual() {
		return sueldoMensual;
	}
	public void setSueldoMensual(Integer sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}
	
	
	
	

}
