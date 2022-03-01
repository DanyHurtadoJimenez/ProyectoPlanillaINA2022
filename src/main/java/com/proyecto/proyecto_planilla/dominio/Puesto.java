/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Dany
 */
@Data
@Entity
@Table(name = "TBL_PUESTO")
public class Puesto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_puesto;

    @NotEmpty(message = "La identificacion es requerida")
    private String nombre_puesto;

    @NotNull(message = "La categoria es requerida")
    private int tipo_categoria;

    @NotNull(message = "El salario es requerido")
    private double salario;

    private boolean borrado;

    @NotNull(message = "El grado minimo es requerido")
    private int grado_minimo;
    
    @OneToMany(mappedBy = "puesto")
    private List<Empleado> empleados;

}
