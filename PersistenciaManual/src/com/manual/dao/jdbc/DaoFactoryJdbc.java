package com.manual.dao.jdbc;

import com.matco.administradortransacciones.dao.Transaccion;
import com.matco.administradortransacciones.dao.jdbc.TransaccionJdbc;
import com.manual.dao.AlumnoDao;
import com.manual.dao.DaoFactory;

public class DaoFactoryJdbc extends DaoFactory {

	private TransaccionJdbc transaccion;

	@Override
	public Transaccion getTransaccion() {
		return this.transaccion;
	}

	@Override
	public void setTransaccion(Transaccion conexion) {
		if (conexion instanceof TransaccionJdbc) {
			this.transaccion = (TransaccionJdbc) conexion;
		} else {
			throw new RuntimeException("Se esperaba una transaccion JDBC");
		}
	}

	@Override
	public AlumnoDao getAlumnoDao() {
		AlumnoDaoJdbc alumnoDaoJdbc = new AlumnoDaoJdbc();
		alumnoDaoJdbc.setTransaccion(this.transaccion);
		return alumnoDaoJdbc;
	}
}
