/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.controllers;

import com.proyecto.proyecto_planilla.dao.ICarreraProfesionalDao;
import com.proyecto.proyecto_planilla.dominio.Carrera_profesional;
import com.proyecto.proyecto_planilla.dominio.Empleado;
import com.proyecto.proyecto_planilla.services.ICarreraProfesionalService;
import com.proyecto.proyecto_planilla.services.IEmpleadoService;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Dany
 */
@Controller
@Slf4j //permite ver la actividad en consola
public class carreraProfesionalController {

    @Autowired
    ICarreraProfesionalService serviceCarreraP;

    @Autowired //se agrega para poder crear las instancias cuando el servicio lo requiera
    private IEmpleadoService serviceEmpleado;

    @PostMapping("/guardarInfoAcademica")
    public String guardarInfoAcademica(Carrera_profesional titulos, String id_empleado, Model modelo) { //el cliente lo obtiene de todos los campos de texto del formulario, y lo llena para poder guardarlo en el siguiente metodo se hace de manera automatica con el validation permite verificar primero si el objeto es valido para poder guardarlo

        Empleado empleado = serviceEmpleado.obtenerEmpleado(Long.parseLong(id_empleado)); //obtiene la entidad cliente que pertenece a ese titulo
        titulos.setEmpleado(empleado); //le setea la entidad
        Carrera_profesional tituloGenerado = serviceCarreraP.guardarTitulo(titulos);//guarda los titulos

        Carrera_profesional titulo = new Carrera_profesional();
        List<Carrera_profesional> diplomas = serviceCarreraP.obtenerTitulos((int) empleado.getId_empleado());//se obtienen los titulos del empleado
        nombrarGrado(diplomas); //pone el nombre del gradoacademico

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //formatea la fecha
        String fecha_ingreso = sdf.format(empleado.getFecha_ingreso());

        modelo.addAttribute("id_empleado", empleado.getId_empleado());
        modelo.addAttribute("nombre_empleado", empleado.getNombre() + " " + empleado.getApellido1() + " " + empleado.getApellido2());
        modelo.addAttribute("fecha_ingreso", fecha_ingreso);
        modelo.addAttribute("titulos", titulo);
        modelo.addAttribute("diplomas", diplomas);

        return "infoAcademica"; //abre la ventana de informacion academica

    }

    private void nombrarGrado(List<Carrera_profesional> titulos) {

        for (int i = 0; i < titulos.size(); i++) {

            switch (titulos.get(i).getGrado_academico()) {
                case 1:
                    titulos.get(i).setNombre_Grado_Academico("Técnico");
                    break;
                case 2:
                    titulos.get(i).setNombre_Grado_Academico("Diplomado");
                    break;
                case 3:
                    titulos.get(i).setNombre_Grado_Academico("Bachillerato");
                    break;
                case 4:
                    titulos.get(i).setNombre_Grado_Academico("Licenciatura");
                    break;
                case 5:
                    titulos.get(i).setNombre_Grado_Academico("Posgrado");
                    break;
                default:
                    throw new AssertionError();
            }

        }

    }

    @GetMapping("/eliminarTitulo/{id_carrera_p}") //se le dice que va a recibir un valor el cual para el programador es el id_producto un campo de la entidad qeu se esta utilizando
    public String editarProducto(Carrera_profesional titulos, Model modelo) {

        int id_empleado = serviceCarreraP.obtenerIdEmpleado(titulos.getId_carrera_p()); //obtiene el id del empleado para poder volver a cargar todos los datos 

        serviceCarreraP.eliminarTitulo(titulos.getId_carrera_p()); //realiza la operacion de eliminacion del titulo
        String mensaje = "Título eliminado";

        Empleado empleado = serviceEmpleado.obtenerEmpleado(id_empleado); //obtiene el empleado entidad
        List<Carrera_profesional> diplomas = empleado.getCarrera_profesional(); //carga los diplomas para mostrar

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //formatea la fecha
        String fecha_ingreso = sdf.format(empleado.getFecha_ingreso());

        titulos.setNombre_certificado(""); //VACIA LOS CAMPOS
        titulos.setAnio_graduacion(0);
        titulos.setNombre_institucion("");

        modelo.addAttribute("id_empleado", empleado.getId_empleado());
        modelo.addAttribute("nombre_empleado", empleado.getNombre() + " " + empleado.getApellido1() + " " + empleado.getApellido2());
        modelo.addAttribute("fecha_ingreso", fecha_ingreso);
        modelo.addAttribute("titulos", titulos);
        modelo.addAttribute("diplomas", diplomas);
        modelo.addAttribute("mensaje", mensaje);//aniade la varibale mensaje al modelo para que la vista lo obtenga
        return "infoAcademica"; //devuelve la vista que se quiere ver
    }

}
