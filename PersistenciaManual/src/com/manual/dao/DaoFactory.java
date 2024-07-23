package com.manual.dao;

import com.matco.administradortransacciones.dao.Transaccion;
import com.manual.dao.jdbc.DaoFactoryJdbc;

public abstract class DaoFactory {

	public static DaoFactoryJdbc crearFabrica(TipoFabrica tipo) {
		switch (tipo) {
		case JDBC:
			return new DaoFactoryJdbc();
		default:
			throw new RuntimeException("No se encontro la implementacion de la fabrica: " + tipo);
		}
	}

	/**
	 * Obtiene la transaccion que se realiza en el datasource
	 *
	 * @return transaccion - La transaccion que se realiza en el datasource
	 */
	public abstract Transaccion getTransaccion();

	/**
	 * Asigna la transaccion que se realiza en el datasource
	 *
	 * @return transaccion
	 */
	public abstract void setTransaccion(Transaccion conexion);

	/**
	 * Declaracion de metodo para obtener un objeto Alumno
	 *
	 * @return {@link Alumnos}
	 */
	public abstract AlumnoDao getAlumnoDao();
}