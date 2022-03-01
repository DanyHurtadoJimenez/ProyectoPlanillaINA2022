/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *
 * @author Dany
 */
@Data
@Entity
@Table(name = "TBL_PLANILLA")
public class Planilla implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_planilla;

    @NotNull(message = "La fecha es requerida")
    @Temporal((TemporalType.DATE))
    @DateTimeFormat(pattern = "yyyy-MM-dd")  //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecha_planilla;

    @NotNull(message = "El tipo de planilla es requerido")
    private boolean tipo = true;//verificar

    private boolean estado;

    private boolean condicion_pago;

    @OneToMany(mappedBy = "planilla")
    private List<Detalle_planilla> detalles_planilla;

}
