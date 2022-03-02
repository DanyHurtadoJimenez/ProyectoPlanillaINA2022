/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.dao;

import com.proyecto.proyecto_planilla.dominio.Empleado;
import com.proyecto.proyecto_planilla.dominio.EmpleadosNoOrdinarios;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Dany
 */
public interface IEmpleadoDao extends JpaRepository<Empleado, Long> {

    Empleado findById(long idEmpleado); //obtiene una entidad del empleado

    //este metodo permite buscar y devolver una lista con clientes que cumplan con las especificaciones como por ejemplo buscar por nombre o apellido
    public Iterable<Empleado> findByBorrado(boolean borrado);//asi se construye un metodo mas especifico que uno necesite para poder encontrar alguna cosa en la base de datos

    //este metodo permite buscar y devolver una lista con clientes que cumplan con las especificaciones como por ejemplo buscar por nombre o apellido
    public Iterable<Empleado> findByNombreContainsOrApellido1ContainsOrApellido2ContainsAndBorrado(String nombre, String apellido1, String apellido2, boolean borrado);//asi se construye un metodo mas especifico que uno necesite para poder encontrar alguna cosa en la base de datos

    @Procedure(name = "SP_Insertar_Empleado") //procedimiento almacenado para guardar un empleado en la base de datos
    public HashMap guardar(@Param("identificacion") String cedula,
            @Param("Nombre") String nombre,
            @Param("Apellido1") String apellido1,
            @Param("Apellido2") String apellido2,
            @Param("Direccion") String direccion,
            @Param("Telefono") String telefono,
            @Param("correoE") String correoE,
            @Param("fechaIngreso") Date fechaIngreso,
            @Param("nombreTitulo") String nombreTitulo,
            @Param("nombreInstitucion") String nombreInstitucion,
            @Param("anioGraduacion") int anioGraduacion,
            @Param("gradoAcademico") int gradoAcademico,
            @Param("idEmpleado") long idEmpleado,
            @Param("resultado") int resultado);

    public Empleado save(Empleado empleado); //guardar el empleado

}
