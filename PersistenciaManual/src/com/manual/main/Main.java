package com.manual.main;

import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import com.manual.entity.Alumno;
import com.manual.facade.AlumnoFacade;

public class Main {
    private static final String log4j = "log4j_manual.properties";

    public static void main(String[] args) {
        PropertyConfigurator.configure(log4j);
        String archivoConfiguracion = "admintx_manual_escritorio.properties";

        try {
            AlumnoFacade alumnoFacade = new AlumnoFacade(archivoConfiguracion);
            List<Alumno> alumnos = alumnoFacade.obtenerTodosAlumnos();
            for (Alumno alumno : alumnos) {
                System.out.println(alumno.getMatricula() + " - " + alumno.getNombres() + " " +
                                   alumno.getApellidoPaterno() + " " + alumno.getApellidoMaterno());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
