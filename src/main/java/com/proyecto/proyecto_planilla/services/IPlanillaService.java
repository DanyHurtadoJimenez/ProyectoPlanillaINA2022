/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dominio.Planilla;
import java.util.Calendar;

/**
 *
 * @author Dany
 */
public interface IPlanillaService {

    public Integer crearPlanilla(Planilla planilla);

    public Planilla traerPlanillaOrdinaria(Calendar mes, Calendar anio);

}
