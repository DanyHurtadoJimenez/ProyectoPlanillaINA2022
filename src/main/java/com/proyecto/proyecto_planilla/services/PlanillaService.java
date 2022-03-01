package com.proyecto.proyecto_planilla.services;

import com.proyecto.proyecto_planilla.dao.IPlanillaDao;
import com.proyecto.proyecto_planilla.dominio.Planilla;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dany
 */
@Service //indica que esta clase es un servicio
public class PlanillaService implements IPlanillaService {

    @Autowired
    private IPlanillaDao planillaDao;//variable para conectarse con el acceso a datos

    @Override
    @Transactional
    public Integer crearPlanilla(Planilla planilla) {

        return planillaDao.SP_INICIAR_PAGOS_DEDUCCIONES(planilla.isTipo(), planilla.getFecha_planilla());

    }

    @Override
    public Planilla traerPlanillaOrdinaria(Calendar mes, Calendar anio) { //obtiene una planilla por mes y anio
        return planillaDao.findPlanillaByMonthAndYear(mes, anio);
    }

}
