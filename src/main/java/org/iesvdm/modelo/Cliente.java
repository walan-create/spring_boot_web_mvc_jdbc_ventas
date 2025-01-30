package org.iesvdm.modelo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//La anotación @Data de lombok proporcionará el código de:
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

//	nombre - obligatorio, con una longitud máxima de 30
//	apellido1- obligatorio, con una longitud máxima de 30
//	apellido2 - opcional
//	ciudad - obligatorio, con longitud máxima de 50
//	categoria - con un rango de valores de 100 a 1000 extremos incluidos

	private int id;

	@Size(max = 30, message = "Nombre como máximo de {max} caracteres.")
	private String nombre;

	@NotBlank
	@Size(max = 30, message = "Nombre como máximo de {max} caracteres.")
	private String apellido1;

	@Size(max = 30, message = "Nombre como máximo de {max} caracteres.")
	private String apellido2;

	@NotBlank
	@Size(max = 50, message = "Ciudad como máximo de {max} caracteres.")
	private String ciudad;

	@Min(value = 100, message = "Categoría mínima de {value}.")
	@Max(value = 1000, message = "Categoría máxima de {value}.")
	private int categoria;
	
}
