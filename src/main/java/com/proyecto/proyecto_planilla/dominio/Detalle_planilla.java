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
@Table(name = "TBL_DETALLE_PLANILLA")
public class Detalle_planilla implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_planilla;

    @NotEmpty(message = "Debe de especificar el puesto")
    private String puesto;

    @NotNull(message = "Debe de especificar el salario base")
    private double salario_base;

    @NotNull(message = "Debe de especificar el salario bruto")
    private double salario_bruto;

    @NotNull(message = "Debe de especificar el salario neto")
    private double salario_neto;

    @JoinColumn(name = "id_empleado")
    @ManyToOne //relacion  obligatoria
    private Empleado empleado;

    @JoinColumn(name = "id_planilla")
    @ManyToOne //relacion  obligatoria
    private Planilla planilla;

    @OneToMany(mappedBy = "detalle_planilla")
    private List<Detalle_pago> detalles_pago;

    @OneToMany(mappedBy = "detalle_planilla")
    private List<Detalle_deduccion> detalles_deduccion;

//    //constructores
//    public Detalle_planilla() {
//    }
//
//    public Detalle_planilla(int id_detalle_planilla, String puesto, double salario_base, double salario_bruto, double salario_neto, Empleado empleado, Planilla planilla, List<Detalle_pago> detalles_pago, List<Detalle_deduccion> detalles_deduccion) {
//        this.id_detalle_planilla = id_detalle_planilla;
//        this.puesto = puesto;
//        this.salario_base = salario_base;
//        this.salario_bruto = salario_bruto;
//        this.salario_neto = salario_neto;
//        this.empleado = empleado;
//        this.planilla = planilla;
//        this.detalles_pago = detalles_pago;
//        this.detalles_deduccion = detalles_deduccion;
//    }
//
//    //propiedades
//    public int getId_detalle_planilla() {
//        return id_detalle_planilla;
//    }
//
//    public void setId_detalle_planilla(int id_detalle_planilla) {
//        this.id_detalle_planilla = id_detalle_planilla;
//    }
//
//    public String getPuesto() {
//        return puesto;
//    }
//
//    public void setPuesto(String puesto) {
//        this.puesto = puesto;
//    }
//
//    public double getSalario_base() {
//        return salario_base;
//    }
//
//    public void setSalario_base(double salario_base) {
//        this.salario_base = salario_base;
//    }
//
//    public double getSalario_bruto() {
//        return salario_bruto;
//    }
//
//    public void setSalario_bruto(double salario_bruto) {
//        this.salario_bruto = salario_bruto;
//    }
//
//    public double getSalario_neto() {
//        return salario_neto;
//    }
//
//    public void setSalario_neto(double salario_neto) {
//        this.salario_neto = salario_neto;
//    }
//
//    public Empleado getEmpleado() {
//        return empleado;
//    }
//
//    public void setEmpleado(Empleado empleado) {
//        this.empleado = empleado;
//    }
//
//    public Planilla getPlanilla() {
//        return planilla;
//    }
//
//    public void setPlanilla(Planilla planilla) {
//        this.planilla = planilla;
//    }
//
//    public List<Detalle_pago> getDetalles_pago() {
//        return detalles_pago;
//    }
//
//    public void setDetalles_pago(List<Detalle_pago> detalles_pago) {
//        this.detalles_pago = detalles_pago;
//    }
//
//    public List<Detalle_deduccion> getDetalles_deduccion() {
//        return detalles_deduccion;
//    }
//
//    public void setDetalles_deduccion(List<Detalle_deduccion> detalles_deduccion) {
//        this.detalles_deduccion = detalles_deduccion;
//    }
//
//    //metodos
//    @Override
//    public String toString() {
//        return "Detalle_planilla{" + "id_detalle_planilla=" + id_detalle_planilla + ", puesto=" + puesto + ", salario_base=" + salario_base + ", salario_bruto=" + salario_bruto + ", salario_neto=" + salario_neto + ", empleado=" + empleado + ", planilla=" + planilla + ", detalles_pago=" + detalles_pago + ", detalles_deduccion=" + detalles_deduccion + '}';
//    }
}
