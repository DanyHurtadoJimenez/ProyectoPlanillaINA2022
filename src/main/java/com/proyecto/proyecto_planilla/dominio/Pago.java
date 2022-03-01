/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "TBL_PAGO")
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pago;

    @NotEmpty(message = "La descripcion del pago es requerida")
    private String descripcion_pago;

    @NotNull(message = "El monto es requerido")
    private double monto;

    private int tipo_cat;

    private boolean tipo;

    private boolean pago_anual;

    private boolean activo;

    @OneToMany(mappedBy = "pago")
    private List<Detalle_pago> detalles_pago;

}
