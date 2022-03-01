/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dao.IEmpleadoDao;
import com.proyecto.proyecto_planilla.dao.IEmpleadoN_O_Dao;
import com.proyecto.proyecto_planilla.dominio.EmpleadosNoOrdinarios;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dany
 */
@Service
public class EmpleadoNoOrdinarioService implements IEmpleadoNoOrdinarioService {

    @Autowired
    IEmpleadoN_O_Dao empleadoN_O_Dao;

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadosNoOrdinarios> traerEmpleadosNoOrdinarios(Calendar fecha, Calendar fecha2) { //obtiene todos los empleados que no estan en una planilla Ordinaria

        return (List<EmpleadosNoOrdinarios>) empleadoN_O_Dao.findAllNative(fecha, fecha2);

    }

    @Override
    public List<EmpleadosNoOrdinarios> traerExtraO(Calendar fecha, Calendar fecha2) {
        return (List<EmpleadosNoOrdinarios>) empleadoN_O_Dao.findAllExtraO(fecha, fecha2);
    }

}
