/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dao.ICarreraProfesionalDao;
import com.proyecto.proyecto_planilla.dominio.Carrera_profesional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dany
 */
@Service
public class CarreraProfesionalService implements ICarreraProfesionalService {

    @Autowired
    ICarreraProfesionalDao carreraPDao;

    @Override
    public List<Carrera_profesional> obtenerTitulos(int id_empleado) {
        return (List<Carrera_profesional>) carreraPDao.findAllByNative(id_empleado);
    }

    @Override
    public void eliminarTitulo(int id_carrera_p) { //eliminar por id un titulo
        carreraPDao.deleteById(id_carrera_p);
    }

    @Override
    public Integer obtenerIdEmpleado(int id_carrera_p) {
        return carreraPDao.findByNative(id_carrera_p);
    }

}
