package com.manual.main;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.manual.entity.Alumno;
import com.manual.facade.AlumnoFacade;

public class Main {
	private static final String log4j = "log4j_manual.properties";

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure(log4j);
		String archivoConfiguracion = "admintx_manual_escritorio.properties";

		//Código para listarlos todos
		AlumnoFacade alumnoFacade = new AlumnoFacade(archivoConfiguracion);
		List<Alumno> alumnos = alumnoFacade.obtenerTodosAlumnos();
		int contador = 0;
		for (Alumno alumnoo : alumnos) {
			contador++;
			System.out.println(alumnoo.getMatricula() + " - " + alumnoo.getNombres() + " "
					+ alumnoo.getApellidoPaterno() + " " + alumnoo.getApellidoMaterno());
		}
		System.out.println("Hay " + contador + " alumnos");

		//Código para agregar un alumno
		Alumno alumno = new Alumno();
		alumno.setNombres("Juan");
		alumno.setApellidoPaterno("Rodriguez");
		alumno.setApellidoMaterno("Cantoral");
		alumno.setCreadoPor("Usuario1");
		int matricula = alumnoFacade.guardarAlumno(alumno);
		System.out.println("Se guardó el alumno con la matricula: " + matricula);

		alumnos = alumnoFacade.obtenerTodosAlumnos();
		contador = 0;
		for (Alumno alumnoo : alumnos) {
			contador++;
			System.out.println(alumnoo.getMatricula() + " - " + alumnoo.getNombres() + " "
					+ alumnoo.getApellidoPaterno() + " " + alumnoo.getApellidoMaterno());
		}
		System.out.println("Hay " + contador + " alumnos");

		//Código para consultar un alumno

		alumno = alumnoFacade.obtenerAlumnoPorMatricula(matricula);
		System.out.println(alumno.getMatricula() + " - " + alumno.getNombres() + " " + alumno.getApellidoPaterno() + " "
				+ alumno.getApellidoMaterno());

		//Código para modificar un alumno
		alumno.setNombres("Mario");
		alumno.setApellidoPaterno("Lopez");
		alumno.setApellidoMaterno("Hernandez");
		alumnoFacade.modificarAlumno(alumno);
		alumno = alumnoFacade.obtenerAlumnoPorMatricula(matricula);
		System.out.println(alumno.getMatricula() + " - " + alumno.getNombres() + " " + alumno.getApellidoPaterno() + " "
				+ alumno.getApellidoMaterno());

		//Código para eliminar un alumno
		alumnoFacade.eliminarAlumno(alumno);
		System.out.println("Se ha eliminado a " + alumno.getMatricula() + " - " + alumno.getNombres() + " "
				+ alumno.getApellidoPaterno() + " " + alumno.getApellidoMaterno());
		alumnos = alumnoFacade.obtenerTodosAlumnos();
		contador = 0;
		for (Alumno alumnoo : alumnos) {
			contador++;
			System.out.println(alumnoo.getMatricula() + " - " + alumnoo.getNombres() + " "
					+ alumnoo.getApellidoPaterno() + " " + alumnoo.getApellidoMaterno());
		}
		System.out.println("Hay " + contador + " alumnos");
	}

}