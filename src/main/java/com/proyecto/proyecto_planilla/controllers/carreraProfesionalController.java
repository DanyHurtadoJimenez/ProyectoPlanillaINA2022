/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.controllers;

import com.proyecto.proyecto_planilla.dominio.Carrera_profesional;
import com.proyecto.proyecto_planilla.services.ICarreraProfesionalService;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Dany
 */
@Controller
@Slf4j //permite ver la actividad en consola
public class carreraProfesionalController {

    @Autowired
    ICarreraProfesionalService serviceCarreraP;

    @GetMapping("/eliminarTitulo/{id_carrera_p}") //se le dice que va a recibir un valor el cual para el programador es el id_producto un campo de la entidad qeu se esta utilizando
    public String editarProducto(Carrera_profesional titulos, Model modelo) {
        
        int id_empleado = serviceCarreraP.obtenerIdEmpleado(titulos.getId_carrera_p()); //obtiene el id del empleado para poder volver a cargar todos los datos 
        
        serviceCarreraP.eliminarTitulo(titulos.getId_carrera_p()); //realiza la operacion
        
       // System.out.println(resultado);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //formatea la fecha
//        String fecha_ingreso = sdf.format(empleado.getFecha_ingreso());
//
//        titulos.setNombre_certificado(""); //VACIA LOS CAMPOS
//        titulos.setAnio_graduacion(0);
//        titulos.setNombre_institucion("");
//
//        modelo.addAttribute("id_empleado", empleado.getId_empleado());
//        modelo.addAttribute("nombre_empleado", empleado.getNombre() + " " + empleado.getApellido1() + " " + empleado.getApellido2());
//        modelo.addAttribute("fecha_ingreso", fecha_ingreso);
//        modelo.addAttribute("titulos", titulos);
//        modelo.addAttribute("diplomas", diplomas);
//        modelo.addAttribute("mensaje", mensaje);//aniade la varibale mensaje al modelo para que la vista lo obtenga
        return "infoAcademica"; //devuelve la vista que se quiere ver
    }

}
