package com.manual.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.matco.administradortransacciones.dao.Transaccion;
import com.matco.administradortransacciones.dao.TransaccionFactory;
import com.manual.dao.AlumnoDao;
import com.manual.dao.DaoFactory;
import com.manual.entity.Alumno;

public class AlumnoFacade {

	private static final Logger LOG = Logger.getLogger(AlumnoFacade.class);
	private DaoFactory daoFactory;
	private TransaccionFactory transaccionFactory;
	private String tabla = "ALUMNOS";

	public AlumnoFacade(String archivoConfig) {
		daoFactory = FactorySingleton.getDaoFactory();
		transaccionFactory = FactorySingleton.getTransaccionFactory(archivoConfig);
	}

	public int guardarAlumno(Alumno instancia) throws Exception {
		Transaccion tx = transaccionFactory.getTransaccion();
		try {
			tx.iniciar();
			daoFactory.setTransaccion(tx);
			AlumnoDao alumnoDao = daoFactory.getAlumnoDao();
			int dato = alumnoDao.funAgregarAlumno(instancia);
			tx.finalizar();
			return dato;
		} catch (Exception e) {
			String mensajeError = "No se pudo guardar el registro: " + tabla;
			LOG.warn(mensajeError, e);
			try {
				LOG.warn("Intentando hacer rollback al obtener el registro: " + tabla);
				tx.rollback();
			} catch (Exception ex) {
				LOG.error("Fallo al hacer rollback " + tabla, ex);
			}
			throw new Exception(mensajeError);
		}
	}

	public Alumno obtenerAlumnoPorMatricula(int matricula) throws Exception {
		Alumno alumno = new Alumno();
		Transaccion tx = transaccionFactory.getTransaccion();
		try {
			tx.iniciar();
			AlumnoDao alumnoDao = daoFactory.getAlumnoDao();
			alumno = alumnoDao.obtenerPorId(String.valueOf(matricula));
			tx.finalizar();
		} catch (Exception e) {
			String mensajeError = "No se pudo obtener el registro: " + tabla;
			LOG.warn(mensajeError, e);
			try {
				LOG.warn("Intentando hacer rollback al obtener el registro: " + tabla);
				tx.rollback();
			} catch (Exception ex) {
				LOG.error("Fallo al hacer rollback " + tabla, ex);
			}
			throw new Exception(mensajeError);
		}
		return alumno;
	}

	public void modificarAlumno(Alumno alumno) throws Exception {
		Transaccion tx = transaccionFactory.getTransaccion();
		try {
			tx.iniciar();
			daoFactory.setTransaccion(tx);
			AlumnoDao alumnoDao = daoFactory.getAlumnoDao();
			alumnoDao.modificar(alumno);
			tx.finalizar();
		} catch (Exception e) {
			String mensajeError = "No se pudo modificar el registro: " + tabla;
			LOG.warn(mensajeError, e);
			try {
				LOG.warn("Intentando hacer rollback al obtener el registro: " + tabla);
				tx.rollback();
			} catch (Exception ex) {
				LOG.error("Fallo al hacer rollback " + tabla, ex);
			}
			throw new Exception(mensajeError);
		}
	}

	public void eliminarAlumno(Alumno alumno) throws Exception {
		Transaccion tx = transaccionFactory.getTransaccion();
		try {
			tx.iniciar();
			daoFactory.setTransaccion(tx);
			AlumnoDao alumnoDao = daoFactory.getAlumnoDao();
			alumnoDao.eliminar(alumno);
			tx.finalizar();
		} catch (Exception e) {
			String mensajeError = "No se pudo eliminar el registro: " + tabla;
			LOG.warn(mensajeError, e);
			try {
				LOG.warn("Intentando hacer rollback al obtener el registro: " + tabla);
				tx.rollback();
			} catch (Exception ex) {
				LOG.error("Fallo al hacer rollback " + tabla, ex);
			}
			throw new Exception(mensajeError);
		}
	}

	public List<Alumno> obtenerTodosAlumnos() throws Exception {
		List<Alumno> listAlumnos = new ArrayList<Alumno>();
		Transaccion tx = transaccionFactory.getTransaccion();
		try {
			tx.iniciar();
			daoFactory.setTransaccion(tx);
			AlumnoDao alumnoDao = daoFactory.getAlumnoDao();
			listAlumnos = alumnoDao.obtenerTodos();
			tx.finalizar();
		} catch (Exception e) {
			String mensajeError = "No se pudo obtener todos los registro: " + tabla;
			LOG.warn(mensajeError, e);
			try {
				LOG.warn("Intentando hacer rollback al obtener el registro: " + tabla);
				tx.rollback();
			} catch (Exception ex) {
				LOG.error("Fallo al hacer rollback " + tabla, ex);
			}
			throw new Exception(mensajeError);
		}
		return listAlumnos;
	}
}