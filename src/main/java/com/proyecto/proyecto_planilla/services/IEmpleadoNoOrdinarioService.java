/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dominio.EmpleadosNoOrdinarios;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Dany
 */
public interface IEmpleadoNoOrdinarioService {

    List<EmpleadosNoOrdinarios> traerEmpleadosNoOrdinarios(Calendar fecha, Calendar fecha2);

    List<EmpleadosNoOrdinarios> traerExtraO(Calendar fecha, Calendar fecha2);

}
