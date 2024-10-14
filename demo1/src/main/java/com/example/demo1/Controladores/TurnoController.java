/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo1.Controladores;

// TurnoController.java


import org.springframework.ui.Model;

import com.example.demo1.Repository.PacienteRepository;
import com.example.demo1.Repository.TurnoRepository;
import com.example.demo1.Model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/turnos")
public class TurnoController {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private TurnoRepository turnoRepository;

    @GetMapping("/nuevo")
    public String nuevoTurno(Model model) {
        model.addAttribute("turno", new Turno());
        model.addAttribute("pacientes", pacienteRepository.findAll());
        return "nuevo-turno";
    }

    @PostMapping("/guardar")
    public String guardarTurno(@ModelAttribute Turno turno) {
        turnoRepository.save(turno);
        return "redirect:/turnos/lista";
    }

    @GetMapping("/turnos")
    public String mostrarTurnos(Model model) {
        // Agrega lógica para llenar el modelo si es necesario
        return "turnos"; // Asegúrate de que este nombre coincida con tu archivo HTML
    }
}