/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dominio.Carrera_profesional;
import java.util.List;

/**
 *
 * @author Dany
 */
public interface ICarreraProfesionalService {

    List<Carrera_profesional> obtenerTitulos(int id_empleado);

    void eliminarTitulo(int id_carrera_p);

    Integer obtenerIdEmpleado(int id_carrera_p);

    public Carrera_profesional guardarTitulo(Carrera_profesional titulo);

}
