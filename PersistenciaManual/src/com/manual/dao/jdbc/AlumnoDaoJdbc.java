package com.manual.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.manual.dao.AlumnoDao;
import com.manual.entity.Alumno;

import oracle.jdbc.internal.OracleTypes;

public class AlumnoDaoJdbc extends GenericDaoJdbc<Alumno, String> implements AlumnoDao {

	private static final String FUN_ADD_ALUMNO = "{? = call CAPACITACION.FUN_ADD_ALUMNO(?,?,?,?) }";
	private static final String PRO_GET_ALUMNO = "{Call CAPACITACION.PRO_GET_ALUMNO(?,?) }";
	private static final String PRO_GET_ALL_ALUMNOS = "{Call CAPACITACION.PRO_GET_ALL_ALUMNOS(?) }";
	private static final String PRO_EDIT_ALUMNO = "{Call CAPACITACION.PRO_EDIT_ALUMNO(?,?,?,?,?) }";
	private static final String PRO_DEL_ALUMNO = "{Call CAPACITACION.PRO_DEL_ALUMNO(?) }";

	@Override
	public void guardar(Alumno instancia) throws Exception {
// TODO Auto-generated method stub
	}

	@Override
	public void modificar(Alumno instancia) throws Exception {
		CallableStatement comando = getConexion().prepareCall(PRO_EDIT_ALUMNO);
		int indice = 1;

		int matricula = instancia.getMatricula();
		String nombres = instancia.getNombres();
		String apellidoPaterno = instancia.getApellidoPaterno();
		String apellidoMaterno = instancia.getApellidoMaterno();
		String modificadoPor = instancia.getModificadoPor();

		asignar(indice++, comando, matricula);
		asignar(indice++, comando, nombres);
		asignar(indice++, comando, apellidoPaterno);
		asignar(indice++, comando, apellidoMaterno);
		asignar(indice++, comando, modificadoPor);

		comando.execute();
		comando.close();
	}

	@Override
	public void eliminar(Alumno instancia) throws Exception {
		CallableStatement comando = getConexion().prepareCall(PRO_DEL_ALUMNO);
		int indice = 1;

		int matricula = instancia.getMatricula();
		asignar(indice++, comando, matricula);

		comando.execute();
		comando.close();
	}

	@Override
	public List<Alumno> obtenerTodos() throws Exception {
		CallableStatement comando = getConexion().prepareCall(PRO_GET_ALL_ALUMNOS, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		int indice = 1;

		registrarParametroSalida(indice++, comando, OracleTypes.CURSOR);
		comando.execute();
		List<Alumno> listAlumnos = new ArrayList<Alumno>();
		ResultSet resultSet = (ResultSet) comando.getObject(1);
		while (resultSet.next()) {
			Alumno alumno = new Alumno();
			int matricula = resultSet.getInt("MATRICULA");
			String nombres = resultSet.getString("NOMBRES");
			String apellidoPaterno = resultSet.getString("APELLIDOPATERNO");
			String apellidoMaterno = resultSet.getString("APELLIDOMATERNO");
			String creadoPor = resultSet.getString("CREADOPOR");
			String modificadoPor = resultSet.getString("MODIFICADOPOR");
			Date fechaHoraCreacion = resultSet.getDate("FECHAHORACAPTURA");
			Date fechaHoraModificacion = resultSet.getDate("FECHAHORAMODIFICACION");

			alumno.setMatricula(matricula);
			alumno.setNombres(nombres);
			alumno.setApellidoPaterno(apellidoPaterno);
			alumno.setApellidoMaterno(apellidoMaterno);
			alumno.setCreadoPor(creadoPor);
			alumno.setModificadoPor(modificadoPor);
			alumno.setFechaHoraCreacion(fechaHoraCreacion);
			alumno.setFechaHoraModificacion(fechaHoraModificacion);
			listAlumnos.add(alumno);
		}

		resultSet.close();
		comando.close();
		return listAlumnos;
	}

	@Override
	public Alumno obtenerPorId(String id) throws Exception {
		CallableStatement comando = getConexion().prepareCall(PRO_GET_ALUMNO, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		int indice = 1;

		registrarParametroSalida(indice++, comando, OracleTypes.CURSOR);
		asignar(indice++, comando, id);
		comando.execute();
		Alumno alumno = new Alumno();
		ResultSet resultSet = (ResultSet) comando.getObject(1);

		if (resultSet.next()) {
			int matricula = resultSet.getInt("MATRICULA");
			String nombres = resultSet.getString("NOMBRES");
			String apellidoPaterno = resultSet.getString("APELLIDOPATERNO");
			String apellidoMaterno = resultSet.getString("APELLIDOMATERNO");
			String creadoPor = resultSet.getString("CREADOPOR");
			String modificadoPor = resultSet.getString("MODIFICADOPOR");
			Date fechaHoraCreacion = resultSet.getDate("FECHAHORACAPTURA");
			Date fechaHoraModificacion = resultSet.getDate("FECHAHORAMODIFICACION");

			alumno.setMatricula(matricula);
			alumno.setNombres(nombres);
			alumno.setApellidoPaterno(apellidoPaterno);
			alumno.setApellidoMaterno(apellidoMaterno);
			alumno.setCreadoPor(creadoPor);
			alumno.setModificadoPor(modificadoPor);
			alumno.setFechaHoraCreacion(fechaHoraCreacion);
			alumno.setFechaHoraModificacion(fechaHoraModificacion);
		}
		resultSet.close();
		comando.close();
		return alumno;
	}

	@Override
	public int funAgregarAlumno(Alumno instancia) throws Exception {
		CallableStatement comando = getConexion().prepareCall(FUN_ADD_ALUMNO, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		int indice = 1;
		registrarParametroSalida(indice++, comando, OracleTypes.NUMBER);
		String nombres = instancia.getNombres();
		String apellidoPaterno = instancia.getApellidoPaterno();
		String apellidoMaterno = instancia.getApellidoMaterno();
		String creadoPor = instancia.getCreadoPor();

		asignar(indice++, comando, nombres);
		asignar(indice++, comando, apellidoPaterno);
		asignar(indice++, comando, apellidoMaterno);
		asignar(indice++, comando, creadoPor);

		comando.execute();
		int result = comando.getInt(1);
		comando.close();

		return result;
	}

}