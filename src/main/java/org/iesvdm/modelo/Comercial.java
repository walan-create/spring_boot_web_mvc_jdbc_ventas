package org.iesvdm.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comercial {

//	nombre - obligatorio, con una longitud máxima de 30
//	apellido1- obligatorio, con una longitud máxima de 30
//	apellido2 - opcional

	private int id;

	@Size(max = 30, message = "{error.nombre.size.max}")
	private String nombre;

	@Size(max = 30, message = "{error.apellido1.size.max}")
	private String apellido1;
	private String apellido2;

	@DecimalMin(value="0.276", message="{error.comision.min}")
	@DecimalMax(value="0.946", message="{error.comision.max}")
	private BigDecimal comision;


}
