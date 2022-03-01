/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dominio.Vista_datos_Detalle_Planilla;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Dany
 */
public interface IVistaDetallesPlanillaService {

    List<Vista_datos_Detalle_Planilla> obtenerDetallesPlanilla(int mes, int anio, int tipo);

    Vista_datos_Detalle_Planilla obtenerRegistroDetallePlanilla(int detallePlanilla);

}
