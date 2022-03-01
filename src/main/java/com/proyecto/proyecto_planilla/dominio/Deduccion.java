/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Dany
 */
@Data
@Entity
@Table(name = "TBL_DEDUCCION")
public class Deduccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_deduccion;

    @NotEmpty(message = "La descripcion de la deduccion es requerida")
    private String descripcion_deduccion;

    @NotNull(message = "El monto es requerido")
    private double monto;

    private int tipo_cat;

    private boolean tipo;

    private boolean activo;

    @OneToMany(mappedBy = "deduccion")
    private List<Tope_Renta> topes_renta;

    @OneToMany(mappedBy = "deduccion")
    private List<Detalle_deduccion> detalles_deduccion;

}
