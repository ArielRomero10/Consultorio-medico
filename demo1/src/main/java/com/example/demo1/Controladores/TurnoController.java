/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo1.Controladores;

// TurnoController.java


import org.springframework.ui.Model;

import com.example.demo1.Repository.TurnoRepository;
import com.example.demo1.Model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoRepository turnoRepository;

    @GetMapping("/nuevo")
    public String nuevoTurno(Model model) {
        model.addAttribute("turno", new Turno()); // Crear un nuevo objeto Turno
        model.addAttribute("turnos  ", turnoRepository.findAll()); // Cargar todos los pacientes
        return "nuevo-turno"; // Vista para crear un nuevo turno
    }

    @PostMapping("/guardar")
    public String guardarTurno(@ModelAttribute Turno turno , RedirectAttributes redirectAttributes) {
        turnoRepository.save(turno);
        redirectAttributes.addFlashAttribute("mensaje", "El paciente ha sido registrado exitosamente."); // Mensaje de éxito
        return "redirect:/registro";
    }

    @GetMapping("/listar")
    public String listarTurnos(Model model) {
        List<Turno> turnos = turnoRepository.findAll(); // Método para obtener los turnos de la base de datos.
        model.addAttribute("turnos", turnos); // Pasar la lista de turnos al modelo.
        return "ListTurnos"; // Retornar el nombre de la vista (sin la extensión .html).
    }

//Desde aca comezaria a imprimir en PDF//

}
