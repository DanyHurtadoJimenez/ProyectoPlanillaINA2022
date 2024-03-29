/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dao.IEmpleadoDao;
import com.proyecto.proyecto_planilla.dominio.Carrera_profesional;

import com.proyecto.proyecto_planilla.dominio.Empleado;
import com.proyecto.proyecto_planilla.dominio.EmpleadosNoOrdinarios;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dany
 */
@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Override
    @Transactional(readOnly = true) //solo realiza consulta y no hace transaccion
    public List<Empleado> listarEmpleados(boolean borrado) {
        return (List<Empleado>) empleadoDao.findByBorrado(borrado);
    }

    @Override
    public List<Empleado> listarEmpleados(String nombre, boolean borrado) {
        return (List<Empleado>) empleadoDao.findByNombreContainsOrApellido1ContainsOrApellido2ContainsAndBorrado(nombre, nombre, nombre, borrado);
    }

    @Override
    @Transactional
    public Empleado guardar(Empleado empleado, Carrera_profesional titulos) {

        //Empleado empleado = new Empleado(); //se declara el empleado que se va a devolver
        HashMap resultados = empleadoDao.guardar(empleado.getIdentificacion(),
                empleado.getNombre(),
                empleado.getApellido1(),
                empleado.getApellido2(),
                empleado.getDireccion(),
                empleado.getTelefono(),
                empleado.getCorreo_electronico(),
                empleado.getFecha_ingreso(),
                titulos.getNombre_certificado(),
                titulos.getNombre_institucion(),
                titulos.getAnio_graduacion(),
                titulos.getGrado_academico(),
                empleado.getId_empleado(),
                empleado.getRetorno());

        empleado.setId_empleado((long) resultados.get("idEmpleado"));
        empleado.setRetorno((int) resultados.get("resultado"));
        return empleado;
    }

    @Override
    @Transactional
    public Empleado obtenerEmpleado(long idEmpleado) { //obtiene una entidad empleado
        return empleadoDao.findById(idEmpleado);
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

}
