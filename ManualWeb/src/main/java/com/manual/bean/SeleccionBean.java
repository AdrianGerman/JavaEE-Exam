package com.manual.bean;

import java.util.List;
import com.manual.entity.Alumno;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SuppressWarnings("deprecation")
@ManagedBean(name = "seleccionBean")
@SessionScoped
public class SeleccionBean {

private List<Alumno> alumnosListFiltrada;

public List<Alumno> getAlumnosListFiltrada() {
return alumnosListFiltrada;
}

public void setAlumnosListFiltrada(List<Alumno> alumnosListFiltrada) {
this.alumnosListFiltrada = alumnosListFiltrada;
}
}


