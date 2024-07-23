package com.manual.dao;

import com.manual.entity.Alumno;

public interface AlumnoDao extends GenericDao <Alumno, String> {

/**
*
* Agrega un alumno
* @param Alumno
* @throws Exception cuando el metodo no esta implementado
*
**/

int funAgregarAlumno(Alumno instancia) throws Exception;
}

