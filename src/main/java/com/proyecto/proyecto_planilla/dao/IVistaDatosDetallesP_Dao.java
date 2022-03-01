/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.dao;

import com.proyecto.proyecto_planilla.dominio.Vista_datos_Detalle_Planilla;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Dany
 */
public interface IVistaDatosDetallesP_Dao extends JpaRepository<Vista_datos_Detalle_Planilla, Integer> {

//    @Query(value = "select \n"
//            + "	Id_Detalle_Planilla,\n"
//            + "	Id_Planilla,\n"
//            + "	fecha_Planilla,\n"
//            + "	Id_Empleado,\n"
//            + "	nombre_Empleado,\n"
//            + "	tipo,\n"
//            + "	Salario_Base,\n"
//            + " Puesto,\n"
//            + "	Salario_Bruto,\n"
//            + "	Salario_Neto \n"
//            + " from datos_Detalle_Planilla where MONTH(fecha_Planilla) = ?1 and YEAR(fecha_Planilla) = ?1 and Tipo = ?1", nativeQuery = true
    List<Vista_datos_Detalle_Planilla> findByMesAndAnioAndTipo(int mes, int anio, int tipo);  //obtiene los datos de los detalles de planilla a partir de una vista en sql 

    @Query(value = "select Id_Detalle_Planilla,Id_Planilla,fecha_Planilla,Id_Empleado,nombre_Empleado,Fecha_Ingreso,tipo,Puesto,Salario_Base,Salario_Bruto,Salario_Neto,Fecha_Ingreso,mes,anio from Vista_datos_Detalle_Planilla where Id_Detalle_Planilla = ?1", nativeQuery = true)
    Vista_datos_Detalle_Planilla findByIdDetallePlanilla(int detallePlanilla);

}
