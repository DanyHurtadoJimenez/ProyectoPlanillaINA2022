/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dominio.Carrera_profesional;
import com.proyecto.proyecto_planilla.dominio.Empleado;
import com.proyecto.proyecto_planilla.dominio.EmpleadosNoOrdinarios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dany
 */
public interface IEmpleadoService {

    Empleado obtenerEmpleado(long idEmpleado); //obtiene una entidad empleado

    public List<Empleado> listarEmpleados(boolean borrado); //OBTIENE TODO

    public List<Empleado> listarEmpleados(String nombre, boolean borrado); //FILTA POR CONDICION

    public Empleado guardar(Empleado empleado, Carrera_profesional titulos);

    public Empleado save(Empleado empleado);

}
