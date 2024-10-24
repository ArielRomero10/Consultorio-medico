package com.example.demo1.Controladores;

import com.itextpdf.io.font.constants.StandardFonts;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.demo1.Repository.TurnoRepository;
import com.example.demo1.Model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.example.demo1.Repository.TurnoRepository;
import com.example.demo1.Model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;



@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoRepository turnoRepository;

    @GetMapping("/principal")
    public String mostrarMenuPrincipal() {
        return "Principal"; // Este debe coincidir con el nombre del archivo HTML sin extensión
    }

    @GetMapping("/nuevo")
    public String nuevoTurno(Model model) {
        model.addAttribute("turno", new Turno()); // Crear un nuevo objeto Turno
        return "registro"; // Vista para crear un nuevo turno
    }

    @PostMapping("/guardar")
    public String guardarTurno(@ModelAttribute Turno turno, RedirectAttributes redirectAttributes) {
        turnoRepository.save(turno);
        redirectAttributes.addFlashAttribute("mensaje", "El turno ha sido registrado exitosamente.");
        return "redirect:/turnos/nuevo"; // Redirigir a la lista después de guardar
    }

    @GetMapping("/listar")
    public String listarTurnos(Model model) {
        List<Turno> turnos = turnoRepository.findAll(); // Obtener los turnos de la base de datos.
        model.addAttribute("turnos", turnos); // Pasar la lista de turnos al modelo.
        return "ListTurnos"; // Retornar el nombre de la vista (sin la extensión .html).
    }

    // Mostrar formulario para editar un turno
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        Turno turno = turnoRepository.findById(id).orElse(null);
        if (turno == null) {
            return "redirect:/turnos/listar"; // Redirigir si no se encuentra el turno
        }
        model.addAttribute("turno", turno);
        return "registro"; // Mismo formulario para editar
    }

    // Actualizar turno
    @PostMapping("/actualizar/{id}")
    public String actualizarTurno(@PathVariable Long id, @ModelAttribute("turno") Turno turnoActualizado) {
        Turno turnoExistente = turnoRepository.findById(id).orElse(null);
        if (turnoExistente != null) {
            turnoExistente.setNombrePaciente(turnoActualizado.getNombrePaciente());
            turnoExistente.setFecha(turnoActualizado.getFecha());
            turnoExistente.setHora(turnoActualizado.getHora());
            turnoExistente.setComentarios(turnoActualizado.getComentarios());
            turnoExistente.setObraSocial(turnoActualizado.getObraSocial());
            turnoRepository.save(turnoExistente);
        }
        return "redirect:/turnos/listar"; // Redirigir a la lista después de actualizar
    }

    // Eliminar un turno
    @GetMapping("/eliminar/{id}")
    public String eliminarTurno(@PathVariable Long id) {
        turnoRepository.deleteById(id);
        return "redirect:/turnos/listar"; // Redirigir a la lista después de eliminar
    }


    @GetMapping("/pdf/con-obra-social")
    public ResponseEntity<byte[]> descargarTurnosConObraSocialPdf() throws IOException {
        List<Turno> turnos = turnoRepository.findAll();

        // Filtrar los turnos que tienen obra social
        List<Turno> turnosConObraSocial = turnos.stream()
                .filter(turno -> turno.getObraSocial() != null && !turno.getObraSocial().isEmpty())
                .toList();

        return generarPdf(turnosConObraSocial, "turnos_con_obra_social.pdf");
    }

    @GetMapping("/pdf/sin-obra-social")
    public ResponseEntity<byte[]> descargarTurnosSinObraSocialPdf() throws IOException {
        List<Turno> turnos = turnoRepository.findAll();

        // Filtrar los turnos que no tienen obra social
        List<Turno> turnosSinObraSocial = turnos.stream()
                .filter(turno -> turno.getObraSocial() == null || turno.getObraSocial().isEmpty())
                .toList();

        return generarPdf(turnosSinObraSocial, "turnos_sin_obra_social.pdf");
    }

    private ResponseEntity<byte[]> generarPdf(List<Turno> turnos, String nombreArchivo) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(baos);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        Table tablaTurnos = new Table(6);
        tablaTurnos.addCell("ID");
        tablaTurnos.addCell("Nombre del Paciente");
        tablaTurnos.addCell("Obra Social");
        tablaTurnos.addCell("Fecha");
        tablaTurnos.addCell("Hora");
        tablaTurnos.addCell("Comentarios");

        for (Turno turno : turnos) {
            tablaTurnos.addCell(turno.getId().toString());
            tablaTurnos.addCell(turno.getNombrePaciente());
            tablaTurnos.addCell(turno.getObraSocial() != null ? turno.getObraSocial() : "Sin Obra Social");
            tablaTurnos.addCell(turno.getFecha());
            tablaTurnos.addCell(turno.getHora());
            tablaTurnos.addCell(turno.getComentarios());
        }

        document.add(tablaTurnos);
        document.close();

        byte[] pdfBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}