/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Dany
 */
@Data
@Entity
@Table(name = "TBL_DETALLE_DEDUCCION")
public class Detalle_deduccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_deducciones;

    @NotNull(message = "El monto es requerido")
    private double monto;

    @JoinColumn(name = "id_detalle_planilla")
    @ManyToOne(optional = false) //relacion obligatoria
    private Detalle_planilla detalle_planilla;

    @JoinColumn(name = "id_deduccion")
    @ManyToOne(optional = false) //relacion obligatoria
    private Deduccion deduccion;

}
