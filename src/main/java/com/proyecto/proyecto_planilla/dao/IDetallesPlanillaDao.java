/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.proyecto_planilla.dao;

import com.proyecto.proyecto_planilla.dominio.*;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Progra
 */
public interface IDetallesPlanillaDao extends JpaRepository<Detalle_planilla, Integer> {

    Detalle_planilla findById(int detallePlanilla); //obtiene una entidad del detalle de planilla

}
