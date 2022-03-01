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
@Table(name = "TBL_TOPE_RENTA")
public class Tope_Renta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_renta;

    @NotNull(message = "El monto minimo es requerido")
    private double monto_minimo;

    @NotNull(message = "El monto maximo es requerido")
    private double monto_maximo;

    @NotNull(message = "El porcentaje es requerido")
    private double porcentaje;

    @JoinColumn(name = "id_deduccion")
    @ManyToOne(optional = false) //relacion obligatoria
    private Deduccion deduccion;

}
