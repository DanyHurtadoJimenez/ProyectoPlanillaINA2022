/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Dany
 */
@Entity
@Table(name = "TBL_CARRERA_PROFESIONAL")
public class Carrera_profesional {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrera_p;

    @NotEmpty(message = "El nombre del titulo es necesario")
    private String nombre_certificado;

    @NotEmpty(message = "El nombre de la institucion es necesario")
    private String nombre_institucion;

    @NotNull(message = "El anio es requerido")
    private int anio_graduacion;

    @NotNull(message = "El grado academico es requerido")
    private int grado_academico;

    private String nombre_Grado_Academico;

    @JoinColumn(name = "id_empleado")
    @ManyToOne(optional = false) //relacion  obligatoria
    private Empleado empleado;

    //constructor
    public Carrera_profesional() {
        this.nombre_certificado = "";
        this.nombre_institucion = "";
        this.nombre_Grado_Academico = "";
        this.anio_graduacion = 0;
        this.grado_academico = 0;
    }

    public Carrera_profesional(int id_carrera_p, String nombre_certificado, String nombre_institucion, int anio_graduacion, int grado_academico, Empleado empleado) {
        this.id_carrera_p = id_carrera_p;
        this.nombre_certificado = nombre_certificado;
        this.nombre_institucion = nombre_institucion;
        this.anio_graduacion = anio_graduacion;
        this.grado_academico = grado_academico;
        this.empleado = empleado;
    }

    //propiedades
    public int getId_carrera_p() {
        return id_carrera_p;
    }

    public void setId_carrera_p(int id_carrera_p) {
        this.id_carrera_p = id_carrera_p;
    }

    public String getNombre_certificado() {
        return nombre_certificado;
    }

    public void setNombre_certificado(String nombre_certificado) {
        this.nombre_certificado = nombre_certificado;
    }

    public String getNombre_institucion() {
        return nombre_institucion;
    }

    public void setNombre_institucion(String nombre_institucion) {
        this.nombre_institucion = nombre_institucion;
    }

    public int getAnio_graduacion() {
        return anio_graduacion;
    }

    public void setAnio_graduacion(int anio_graduacion) {
        this.anio_graduacion = anio_graduacion;
    }

    public int getGrado_academico() {
        return grado_academico;
    }

    public void setGrado_academico(int grado_academico) {
        this.grado_academico = grado_academico;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getNombre_Grado_Academico() {
        return nombre_Grado_Academico;
    }

    public void setNombre_Grado_Academico(String nombre_Grado_Academico) {
        this.nombre_Grado_Academico = nombre_Grado_Academico;
    }

    //metodos
    @Override
    public String toString() {
        return "Carrera_profesional{" + "id_carrera_p=" + id_carrera_p + ", nombre_certificado=" + nombre_certificado + ", nombre_institucion=" + nombre_institucion + ", anio_graduacion=" + anio_graduacion + ", grado_academico=" + grado_academico + ", empleado=" + empleado + '}';
    }

}
