/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Dany
 */
@Entity
public class Vista_datos_Detalle_Planilla implements Serializable {

    @Id
    private int id_detalle_planilla;

    private int id_planilla;

    private Date fecha_planilla;

    private int id_empleado;

    private String nombre_empleado;

    private int tipo;

    private String puesto;

    private double salario_base;

    private double salario_bruto;

    private double salario_neto;

    private int mes;

    private int anio;

    private Calendar fecha_ingreso;

    //constructor
    public Vista_datos_Detalle_Planilla() {
    }

    public Vista_datos_Detalle_Planilla(int id_detalle_planilla, int id_planilla, Date fecha_planilla, int id_empleado, String nombre_empleado, int tipo, String puesto, double salario_base, double salario_bruto, double salario_neto) {
        this.id_detalle_planilla = id_detalle_planilla;
        this.id_planilla = id_planilla;
        this.fecha_planilla = fecha_planilla;
        this.id_empleado = id_empleado;
        this.nombre_empleado = nombre_empleado;
        this.tipo = tipo;
        this.puesto = puesto;
        this.salario_base = salario_base;
        this.salario_bruto = salario_bruto;
        this.salario_neto = salario_neto;

    }

    //propiedades
    public int getId_detalle_planilla() {
        return id_detalle_planilla;
    }

    public void setId_detalle_planilla(int id_detalle_planilla) {
        this.id_detalle_planilla = id_detalle_planilla;
    }

    public int getId_planilla() {
        return id_planilla;
    }

    public void setId_planilla(int id_planilla) {
        this.id_planilla = id_planilla;
    }

    public Date getFecha_planilla() {
        return fecha_planilla;
    }

    public void setFecha_planilla(Date fecha_planilla) {
        this.fecha_planilla = fecha_planilla;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(double salario_base) {
        this.salario_base = salario_base;
    }

    public double getSalario_bruto() {
        return salario_bruto;
    }

    public void setSalario_bruto(double salario_bruto) {
        this.salario_bruto = salario_bruto;
    }

    public double getSalario_neto() {
        return salario_neto;
    }

    public void setSalario_neto(double salario_neto) {
        this.salario_neto = salario_neto;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Calendar getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Calendar fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

}
