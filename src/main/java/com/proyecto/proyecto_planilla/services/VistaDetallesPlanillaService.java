/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dao.IVistaDatosDetallesP_Dao;
import com.proyecto.proyecto_planilla.dominio.Vista_datos_Detalle_Planilla;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dany
 */
@Service
public class VistaDetallesPlanillaService implements IVistaDetallesPlanillaService {

    @Autowired
    IVistaDatosDetallesP_Dao detallesPlanillaDao;

    @Override
    public List<Vista_datos_Detalle_Planilla> obtenerDetallesPlanilla(int mes, int anio, int tipo) {

        return (List<Vista_datos_Detalle_Planilla>) detallesPlanillaDao.findByMesAndAnioAndTipo(mes, anio, tipo);
    }

    @Override
    public Vista_datos_Detalle_Planilla obtenerRegistroDetallePlanilla(int detallePlanilla) {
        return (Vista_datos_Detalle_Planilla) detallesPlanillaDao.findByIdDetallePlanilla(detallePlanilla);//obtiene un registro de un detalle de planilla
    }

}
