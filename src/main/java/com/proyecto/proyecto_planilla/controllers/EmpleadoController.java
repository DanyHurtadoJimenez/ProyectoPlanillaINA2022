/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.controllers;

import com.proyecto.proyecto_planilla.dominio.Carrera_profesional;
import com.proyecto.proyecto_planilla.dominio.Empleado;
import com.proyecto.proyecto_planilla.dominio.EmpleadosNoOrdinarios;
import com.proyecto.proyecto_planilla.dominio.Planilla;
import com.proyecto.proyecto_planilla.services.ICarreraProfesionalService;
import com.proyecto.proyecto_planilla.services.IEmpleadoNoOrdinarioService;
import com.proyecto.proyecto_planilla.services.IEmpleadoService;
import java.text.SimpleDateFormat;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Dany
 */
@Controller //con esta anotacion se le dice a la clase que sera un controlador
@Slf4j //permite ver la actividad en consola
public class EmpleadoController {

    @Autowired //se agrega para poder crear las instancias cuando el servicio lo requiera
    private IEmpleadoNoOrdinarioService serviceEmpleadoNoOrdinario;

    @Autowired //se agrega para poder crear las instancias cuando el servicio lo requiera
    private IEmpleadoService serviceEmpleado;

    @Autowired //se agrega para poder crear las instancias cuando el servicio lo requiera
    private ICarreraProfesionalService serviceCarreraP;

    @PostMapping("/buscarEmpleadosNoOrdinario")
    public String cargarEmpleadosNoOrdinarios(@Valid Planilla planilla, Errors errors, Model model) {
        String mensaje;
        planilla.setTipo(false);
        System.out.println(planilla.getFecha_planilla());
        List<EmpleadosNoOrdinarios> empleados;
        if (errors.hasErrors()) { //si hay errores mejor devuelvame a la planilla extraordinaria
            return "planillaExtraordinaria";
        }
        empleados = serviceEmpleadoNoOrdinario.traerExtraO(planilla.getFecha_planilla(), planilla.getFecha_planilla()); //obtiene los empleados que estan en una planilla extraOrdinaria
        if (empleados.size() == 0) {
            empleados = serviceEmpleadoNoOrdinario.traerEmpleadosNoOrdinarios(planilla.getFecha_planilla(), planilla.getFecha_planilla());
            model.addAttribute("empleados", empleados); //envia el arreglo de los empleados al html
            model.addAttribute("planilla", planilla); //envia devuelta la planilla con la fecha que se escogio
        } else {
            mensaje = "Todos los empleados ya salen en una planilla ordinaria o extraordinaria hasta la fecha suministrada";
            model.addAttribute("mensaje", mensaje);
        }

        return "planillaExtraOrdinaria";
    }

    @GetMapping("/empleados")
    public String listarEmpleados(Model modelo) {  //muestra los empleados disponibles
        List<Empleado> empleados = serviceEmpleado.listarEmpleados(false); //obtiene los empleados no borrados
        modelo.addAttribute("empleados", empleados);
        return "mantenimientoEmpleados"; //devuelve la vista que se quiere ver
    }

    @PostMapping("/buscarEmpleado")
    public String buscarCliente(String nombre, Model modelo) { //recibe el nombre del input que se le va a mandar puede recibir un httpServletRequest en el cual puede obtener todos los campos del formulario y obtenerlos a este lado

        List<Empleado> empleados = serviceEmpleado.listarEmpleados(nombre, false);
        modelo.addAttribute("empleados", empleados);
        return "mantenimientoEmpleados";
    }

    @GetMapping("/agregarEmpleado")
    public String Agregar(Empleado empleado) {
        return "editarEmpleado"; //abre el editar empleados manda un empleado vacio para poder llenarlo y guardarlo
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Empleado empleado, Carrera_profesional titulos, Errors errors, Model modelo) { //el cliente lo obtiene de todos los campos de texto del formulario, y lo llena para poder guardarlo en el siguiente metodo se hace de manera automatica con el validation permite verificar primero si el objeto es valido para poder guardarlo
        if (errors.hasErrors()) { //si hay errores mejor devuelvame a lo de editar cliente
            return "editarEmpleado";
        }
        serviceEmpleado.guardar(empleado, titulos); //ejecuta el sp mandando la entidad empleado y la entidad titulos

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //formatea la fecha
        String fecha_ingreso = sdf.format(empleado.getFecha_ingreso());

        modelo.addAttribute("id_empleado", empleado.getId_empleado());
        modelo.addAttribute("nombre_empleado", empleado.getNombre() + " " + empleado.getApellido1() + " " + empleado.getApellido2());
        modelo.addAttribute("fecha_ingreso", fecha_ingreso);
        modelo.addAttribute("titulos", titulos);

        return "infoAcademica"; //abre la ventana de informacion academica

    }

    @PostMapping("/guardarInfoAcademica")
    public String guardarInfoAcademica(Carrera_profesional titulos, String id_empleado, Model modelo) { //el cliente lo obtiene de todos los campos de texto del formulario, y lo llena para poder guardarlo en el siguiente metodo se hace de manera automatica con el validation permite verificar primero si el objeto es valido para poder guardarlo
        String mensaje;
        //if (errors.hasErrors()) { //si hay errores mejor devuelvame a lo de editar cliente
//            if (titulos.getNombre_certificado().equals("") && titulos.getNombre_institucion().equals("")) {
//                mensaje = "Debe completar los campos que componen la informacion de los titulos";
//                modelo.addAttribute("mensaje", mensaje);
//            }
//            return "infoAcademica";
        //}
        Empleado empleado = serviceEmpleado.obtenerEmpleado(Long.parseLong(id_empleado)); //obtiene la entidad cliente que pertenece a ese 
        serviceEmpleado.guardar(empleado, titulos); //ejecuta el sp mandando la entidad empleado y la entidad titulos
        List<Carrera_profesional> diplomas = serviceCarreraP.obtenerTitulos((int) empleado.getId_empleado());//se obtienen los titulos del empleado
        nombrarGrado(diplomas); //pone el nombre del gradoacademico
        
        
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
    
    
    
    

}
