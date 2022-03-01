/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.proyecto_planilla.dao;

import com.proyecto.proyecto_planilla.dominio.EmpleadosNoOrdinarios;
import com.proyecto.proyecto_planilla.dominio.Planilla;
import java.sql.Date;
import java.util.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dany
 */
public interface IPlanillaDao extends JpaRepository<Planilla, Integer> {

    //String apanie = "HOLA";
    //procedimiento almacenado
    @Transactional
    @Procedure(name = "SP_INICIAR_PAGOS_DEDUCCIONES", outputParameterName = "resultado")
    public Integer SP_INICIAR_PAGOS_DEDUCCIONES(@Param("tipoPlanilla") boolean tipoPlanilla, @Param("fechaPlanilla") Calendar fechaPlanilla);

    
    @Query(value = "SELECT Id_Planilla,fecha_Planilla,Tipo,Estado,Condicion_Pago FROM TBL_PLANILLA where Tipo = 1 and MONTH(fecha_Planilla) = MONTH(?1) and YEAR(fecha_Planilla) = YEAR(?1)", nativeQuery = true) //obtiene una planilla ordinaria
    Planilla findPlanillaByMonthAndYear(Calendar mes, Calendar anio);

}
