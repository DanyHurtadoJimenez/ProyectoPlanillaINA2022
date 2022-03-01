/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dao.IDetallesPlanillaDao;
import com.proyecto.proyecto_planilla.dominio.Detalle_planilla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Progra
 */
@Service
public class DetallesPlanillaService implements IDetallePlanillaService {

    @Autowired
    IDetallesPlanillaDao detallesPDao;

    @Override
    @Transactional
    public Detalle_planilla obtenerDetallePlanilla(int detallePlanilla) { //obtiene una entidad detalle de planilla
        return detallesPDao.findById(detallePlanilla);
    }

}
