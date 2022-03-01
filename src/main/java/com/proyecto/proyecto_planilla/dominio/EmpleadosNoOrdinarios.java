/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Dany
 */
@Entity

public class EmpleadosNoOrdinarios implements Serializable {

    //atributos
    @Id
    private String identificacion;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private int id_puesto;

    private String nombre_puesto;

    private double salario;

//    @NotNull(message = "La fecha es requerida")
//    @Temporal((TemporalType.DATE))
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Calendar fecha_ingreso;
//
//    private double salario;
//
//    private String nombre_puesto;
//
//    private int tipo_categoria;
    //constructores
    public EmpleadosNoOrdinarios() {
    }

    public EmpleadosNoOrdinarios(String identificacion, String nombre, String apellido1, String apellido2, int id_puesto, String nombre_puesto, double salario) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.id_puesto = id_puesto;
        this.nombre_puesto = nombre_puesto;
        this.salario = salario;
    }

    //propiedades
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getNombre_puesto() {
        return nombre_puesto;
    }

    public void setNombre_puesto(String nombre_puesto) {
        this.nombre_puesto = nombre_puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
