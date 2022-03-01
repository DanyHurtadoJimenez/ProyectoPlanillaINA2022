/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Dany
 */
@Data
@Entity
@Table(name = "TBL_INCAPACIDAD")
public class Incapacidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_incapacidad;

    @NotNull(message = "La fecha de inicio es requerida")
    @Temporal((TemporalType.DATE))
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecha_inicio;

    @NotNull(message = "La fecha de fin es requerida")
    @Temporal((TemporalType.DATE))
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecha_fin;

    private int dias_pendiente_pago;

    @JoinColumn(name = "id_empleado")
    @ManyToOne(optional = false) //relacion  obligatoria
    private Empleado empleado;
    
    

}
