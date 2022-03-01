/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.dao;

import com.proyecto.proyecto_planilla.dominio.EmpleadosNoOrdinarios;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Dany
 */
public interface IEmpleadoN_O_Dao extends JpaRepository<EmpleadosNoOrdinarios, String> {

    @Query(value = "select \n" //obtiene los empleados que no estan en una planilla ordinaria
            + "	Identificacion,\n"
            + "	Nombre,\n"
            + "	Apellido1,\n"
            + "	Apellido2,\n"
            + "	p.Id_Puesto,\n"
            + "	p.Nombre_Puesto,\n"
            + "	p.Salario\n"
            + "from TBL_EMPLEADO e inner join TBL_PUESTO p on \n"
            + "	e.Id_Puesto = p.Id_Puesto \n"
            + " where not exists(Select 1 from TBL_DETALLE_PLANILLA dp inner join TBL_PLANILLA p on \n"
            + "				 dp.Id_Planilla = p.Id_Planilla where e.Id_Empleado = dp.Id_Empleado and Tipo = 1 and Month(fecha_Planilla) =  Month(?1) and YEAR(fecha_Planilla) = YEAR(?1))", nativeQuery = true)
    Iterable<EmpleadosNoOrdinarios> findAllNative(Calendar fecha, Calendar fecha2);

    @Query(value = " select \n"
            + "	Identificacion,\n"
            + "	Nombre,\n"
            + "	Apellido1,\n"
            + "	Apellido2,\n"
            + "	p.Id_Puesto,\n"
            + "	p.Nombre_Puesto,\n"
            + "	p.Salario,\n"
            + "	pl.fecha_Planilla \n"
            + " from TBL_EMPLEADO e inner join TBL_PUESTO p on \n"
            + "	e.Id_Puesto = p.Id_Puesto left join TBL_DETALLE_PLANILLA dp on \n"
            + "	dp.Id_Empleado = e.Id_Empleado left join TBL_PLANILLA pl on \n"
            + "	pl.Id_Planilla = dp.Id_Planilla \n"
            + " where  exists(Select 1 from TBL_DETALLE_PLANILLA dp inner join TBL_PLANILLA p on \n"
            + " dp.Id_Planilla = p.Id_Planilla \n"
            + " where e.Id_Empleado = dp.Id_Empleado and Tipo = 0 and Month(fecha_Planilla) =  Month(?1) and YEAR(fecha_Planilla) = YEAR(?1) ) ", nativeQuery = true)
    Iterable<EmpleadosNoOrdinarios> findAllExtraO(Calendar fecha, Calendar fecha2);

}
