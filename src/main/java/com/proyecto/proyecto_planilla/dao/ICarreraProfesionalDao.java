/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.dao;

import com.proyecto.proyecto_planilla.dominio.Carrera_profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Dany
 */
public interface ICarreraProfesionalDao extends JpaRepository<Carrera_profesional, Integer> {

    @Query(value = "select Id_Carrera_P,Nombre_Certificado,Nombre_Institucion,Anio_Graduacion,Grado_Academico,nombre_Grado_Academico,Id_Empleado from TBL_CARRERA_PROFESIONAL where Id_Empleado = ?1", nativeQuery = true)
    public Iterable<Carrera_profesional> findAllByNative(int id_empleado);

    // public Iterable<Carrera_profesional> findById_Empleado(long id_empleado);//asi se construye un metodo mas especifico que uno necesite para poder encontrar alguna cosa en la base de datos
    public void deleteById(int id_carrera_p);//elimina un registro de acuerdo al id que se le pase

    @Query(value = "select Id_Empleado from TBL_CARRERA_PROFESIONAL where Id_Carrera_P = ?1", nativeQuery = true)
    public Integer findByNative(int id_carrera_p);

}
