/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyecto_planilla.controllers;

import com.proyecto.proyecto_planilla.dominio.Detalle_deduccion;
import com.proyecto.proyecto_planilla.dominio.Detalle_pago;
import com.proyecto.proyecto_planilla.dominio.Detalle_planilla;
import com.proyecto.proyecto_planilla.dominio.Planilla;
import com.proyecto.proyecto_planilla.dominio.Vista_datos_Detalle_Planilla;
import com.proyecto.proyecto_planilla.services.IDetallePlanillaService;

import com.proyecto.proyecto_planilla.services.IPlanillaService;
import com.proyecto.proyecto_planilla.services.IVistaDetallesPlanillaService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Dany txtFechaPlanilla /crearPlanilla
 */
@Controller //con esta anotacion se le dice a la clase que sera un controlador
@Slf4j //permite ver la actividad en consola
public class PlanillaController {

    @Autowired //se agrega para poder crear las instancias cuando el servicio lo requiera
    private IPlanillaService servicePlanilla;

    @Autowired //se agrega para poder crear las instancias cuando el servicio lo requiera (VISTA)
    private IVistaDetallesPlanillaService serviceDetallesP;

    @Autowired //se agrega para poder crear las instancias cuando el servicio lo requiera
    private IDetallePlanillaService serviceDetallesPlanilla;

    @GetMapping("/abrirOrdinaria")
    public String AbrirOrdinaria(Planilla planilla) {
        return "PlanillaOrdinaria"; //aqui abre la vista para poder crear una planilla se le manda un objeto planilla vacio
    }

    @GetMapping("/abrirExtraOrdinaria")
    public String AbrirExtraOrdinaria(Planilla planilla) {
        return "planillaExtraOrdinaria"; //aqui abre la vista para poder crear una planilla se le manda un objeto planilla vacio
    }

    @PostMapping("/crearPlanilla")
    public String crearPlanilla(@Valid Planilla planilla, Errors errors, Model model) { //la planilla lo obtiene de todos los campos de texto del formulario, y lo llena para poder guardarlo en el siguiente metodo se hace de manera automatica con el validation permite verificar primero si el objeto es valido para poder guardarlo
        String mensaje;

        if (errors.hasErrors()) { //si hay errores mejor devuelvame a lo de editar cliente
            if (planilla.isTipo()) {
                return "PlanillaOrdinaria";
            } else {
                return "PlanillaExtraOrdinaria";
            }
        }

        if (!planilla.isTipo()) { //se fija si la planilla es extraordinaria para verificar si primero ya hay una planilla ordinaria, porque debe existir primero una planilla ordinaria para poder calcular la extraordinaria
            Planilla planillaO;
            planillaO = servicePlanilla.traerPlanillaOrdinaria(planilla.getFecha_planilla(), planilla.getFecha_planilla());
            if (planilla != null) {
                mensaje = "Para calcular una planilla extraordinaria primero debe de existir una planilla Ordinaria calculada";
                model.addAttribute("mensaje", mensaje);//aniade la varibale mensaje al modelo para que la vista lo obtenga
                return "PlanillaExtraOrdinaria";
            }
        }

        int resultado = servicePlanilla.crearPlanilla(planilla); //ejecuta la accion

        switch (resultado) {
            case 1:
                mensaje = "Planilla Ordinaria Calculada Con exito";
                break;
            case 2:
                mensaje = "Planilla Extraordinaria Calculada Con exito";
                break;
            case 3:
                mensaje = "Ya hay una planilla calculada para esta fecha";
                break;
            case 4:
                mensaje = "No se puede calcular del mes siguiente";
                break;
            case 5:
                mensaje = "No puede calcular planillas de periodos anteriores";
                break;
            default:
                throw new AssertionError();
        }
        model.addAttribute("mensaje", mensaje);//aniade la varibale mensaje al modelo para que la vista lo obtenga

        if (planilla.isTipo()) {
            return "PlanillaOrdinaria";
        } else {
            return "PlanillaExtraOrdinaria";
        }

    }

    @GetMapping("/abrirHistorial")
    public String AbrirHistorialP(Planilla planilla) {
        return "historialPlanillas"; //aqui abre la vista para poder crear una planilla se le manda un objeto planilla vacio
    }

    ///buscarPlanilla
    @PostMapping("/buscarPlanilla")
    public String buscarDetallesPlanilla(Model model, String fecha_planilla, String tipo) { //la planilla lo obtiene de todos los campos de texto del formulario, y lo llena para poder guardarlo en el siguiente metodo se hace de manera automatica con el validation permite verificar primero si el objeto es valido para poder guardarlo
        String mensaje, tipoTexto, mensajeEmpleados;

        List<Vista_datos_Detalle_Planilla> detalles;
        if (!fecha_planilla.equals("") && tipo != null) { //si hay errores mejor devuelvame a lo de editar cliente

            String periodo = fecha_planilla + "-01";  //obtiene la fecha y la procesa

            //java.sql.Date fecha = java.sql.Date.valueOf(periodo);//converting string into sql date 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                date = sdf.parse(periodo);
            } catch (ParseException ex) {
                Logger.getLogger(PlanillaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int mes = cal.get(Calendar.MONTH);//obtiene el mes

            mes += 1;

            if (mes == 13) {
                mes = 1;
            }

            //mesTexto = mesPlanilla(mes);
            detalles = serviceDetallesP.obtenerDetallesPlanilla(mes, cal.get(Calendar.YEAR), Integer.parseInt(tipo)); //obtiene los detalles de la planilla de acuerdo al mes, anio y tipo que se escoja

            if (detalles.size() > 0) { //si pudo recuperar detalles de la planilla entonces las manda sino muestra un mensaje
                if (tipo.equals("1")) { //manda un texto con el nombre del tipo de planilla
                    tipoTexto = "Ordinaria";
                } else {
                    tipoTexto = "ExtraOrdinaria";
                }

                model.addAttribute("detalles", detalles);
                model.addAttribute("mes", mesPlanilla(mes));
                model.addAttribute("anio", String.valueOf(cal.get(Calendar.YEAR)));
                model.addAttribute("tipo", tipoTexto);
            } else {
                mensajeEmpleados = "No hay planillas calculadas en esa fecha";
                model.addAttribute("mensajeEmpleados", mensajeEmpleados);
            }

            return "historialPlanillas";
        }

        mensaje = "Debe de especificar el periodo y el tipo de la planilla a buscar";
        model.addAttribute("mensaje", mensaje);

        return "historialPlanillas"; //redirecciona a alguna ruta del controlador o sea ir a algun controlador o al metodo
    }

    private String mesPlanilla(int mes) { //funcion que devuelve el nombre del mes
        String periodo = "";
        switch (mes) {
            case 1:
                periodo = "Enero";
                break;
            case 2:
                periodo = "Febrero";
                break;
            case 3:
                periodo = "Marzo";
                break;
            case 4:
                periodo = "Abril";
                break;
            case 5:
                periodo = "Mayo";
                break;
            case 6:
                periodo = "Junio";
                break;
            case 7:
                periodo = "Julio";
                break;
            case 8:
                periodo = "Agosto";
                break;
            case 9:
                periodo = "Setiembre";
                break;
            case 10:
                periodo = "Octubre";
                break;
            case 11:
                periodo = "Noviembre";
                break;
            case 12:
                periodo = "Diciembre";
                break;
        }

        return periodo;

    }

    @GetMapping("/verDetalles/{id_detalle_planilla}") //se le dice que va a recibir un valor el cual para el programador es el id_cliente un campo de la entidad qeu se esta utilizando
    public String detallesPlanilla(Vista_datos_Detalle_Planilla detallePlanillaVista, Model modelo) {

        Detalle_planilla detallePlanilla; //se declara una variable de tipo detalle de planilla para poder obtener toda la informacion pagos y deducciones

        detallePlanillaVista = serviceDetallesP.obtenerRegistroDetallePlanilla(detallePlanillaVista.getId_detalle_planilla()); //obtiene la informacion del detalle de la planilla nombre empleado, salario, fecha de plnailla etc. (DE LA VISTA)
        detallePlanilla = serviceDetallesPlanilla.obtenerDetallePlanilla(detallePlanillaVista.getId_detalle_planilla()); //obtengo los detalles de planilla (ENTIDAD)

        List<Detalle_pago> detallesPago = detallePlanilla.getDetalles_pago(); //debe de obtener una lista con los detalles de pago de ese detalle de planilla
        List<Detalle_deduccion> detallesDeduccion = detallePlanilla.getDetalles_deduccion();//obtiene las deducciones del detalle de planilla

        String fecha_planilla = String.valueOf(detallePlanillaVista.getAnio()) + "-" + String.valueOf(detallePlanillaVista.getMes()); //se vuelve a armar la fecha de la planilla para poder pasarselo al boton de atras por si el usuario desea volver al historial de planillas

        int mesIngreso = detallePlanillaVista.getFecha_ingreso().get(Calendar.MONTH); //descompone la fecha para mostrarla
        int anioIngreso = detallePlanillaVista.getFecha_ingreso().get(Calendar.YEAR);
        int diaIngreso = detallePlanillaVista.getFecha_ingreso().get(Calendar.DAY_OF_MONTH);

        mesIngreso += 1; //se le suma 1 al mes porque calendar empieza en 0

        if (mesIngreso == 13) {
            mesIngreso = 1;
        }

        String fecha_ingreso = diaIngreso + " / " + mesIngreso + " / " + anioIngreso;
        String tipoPlanilla;
        if (detallePlanillaVista.getTipo() == 1) {
            tipoPlanilla = "Ordinaria";
        } else {
            tipoPlanilla = "ExtraOrdinaria";
        }

        if (detallePlanillaVista != null) {
            modelo.addAttribute("id_empleado", detallePlanillaVista.getId_empleado()); //se guardar en la variable cliente del model para luego en el EDITAR CLIENTE pueda leerlo y cargar todos los datos en los textbox 
            modelo.addAttribute("nombre_empleado", detallePlanillaVista.getNombre_empleado());
            modelo.addAttribute("salario_base", detallePlanillaVista.getSalario_base());
            modelo.addAttribute("mes", mesPlanilla(detallePlanillaVista.getMes()));
            modelo.addAttribute("anio", detallePlanillaVista.getAnio());
            modelo.addAttribute("tipo", detallePlanillaVista.getTipo());
            modelo.addAttribute("fecha_ingreso", fecha_ingreso);
            modelo.addAttribute("detallesPago", detallesPago);
            modelo.addAttribute("detallesDeducciones", detallesDeduccion);
            modelo.addAttribute("fecha_planilla", fecha_planilla);
            modelo.addAttribute("tipo_planilla", tipoPlanilla);
            modelo.addAttribute("salario_bruto", detallePlanillaVista.getSalario_bruto());
            modelo.addAttribute("salario_neto", detallePlanillaVista.getSalario_neto());
            return "detallesPlanilla";
        }
        String mensaje = "No se encuentra el detalle de planilla";//muestra el mensaje y a continuacion vuelve a cargar la lista de clientes
        modelo.addAttribute("mensaje", mensaje);//aniade la varibale mensaje al modelo para que la vista lo obtenga
        return "detallesPlanilla";
    }

}
