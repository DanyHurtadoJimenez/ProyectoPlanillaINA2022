/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Dany
 */
@NamedStoredProcedureQueries({ //aqui se define un procedimiento almacenado o varios se separan con comas

    @NamedStoredProcedureQuery(
            name = "SP_Insertar_Empleado", //nombre procedimiento a nivel de java
            procedureName = "SP_Insertar_Empleado", // nombre procedimiento a nivel sql
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "identificacion", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Nombre", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Apellido1", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Apellido2", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Direccion", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Telefono", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "correoE", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fechaIngreso", type = Date.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombreTitulo", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombreInstitucion", type = String.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "anioGraduacion", type = Integer.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "gradoAcademico", type = Integer.class),//se define el parametro de entrada nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "idEmpleado", type = Long.class),//se define el parametro de entrada y salida simultaneamente nombre del parametro y el tipo de dato
                @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "resultado", type = Integer.class),//se define el parametro de entrada y salida simultaneamente nombre del parametro y el tipo de dato
            }
    )
})
@Data
@Entity
@Table(name = "TBL_EMPLEADO", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"identificacion"})})

public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //es una llave que se genera automaticamente
    private long id_empleado;

    @NotEmpty(message = "La identificacion es requerida") //no admite nulos ni vacio
    private String identificacion;

    @NotEmpty(message = "El nombre es requerido")
    private String nombre;

    @NotEmpty(message = "El primer apellido es requerido")
    private String apellido1;

    private String apellido2;

    @NotEmpty(message = "La direccion es requerida")
    private String direccion;

    @NotEmpty(message = "El telefono es requerido")
    private String telefono;

    @NotEmpty(message = "El email es requerido")
    @Email(message = "Debe de incluir un formato de correo correcto")
    private String correo_electronico;

    private boolean borrado;

    @NotNull(message = "La fecha es requerida")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_ingreso;

    private int puntos_carrera_pro;

    private int grado_academico;

    private int retorno = 0;//se usa para guardar el retorno a la hora de ejecutar el procedimiento almacenado de guardar empleado

    @JoinColumn(name = "id_puesto")
    @ManyToOne(optional = true) //relacion no obligatoria
    private Puesto puesto;

    @OneToMany(mappedBy = "empleado")
    private List<Detalle_planilla> detalles_planilla;

    @OneToMany(mappedBy = "empleado")
    private List<Incapacidad> incapacidades;

    @OneToMany(mappedBy = "empleado")
    private List<Pension> pensiones;

    @OneToMany(mappedBy = "empleado")
    private List<Carrera_profesional> carrera_profesional;

}
