/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo1.Controladores;

import com.example.demo1.Model.Usuario;
import com.example.demo1.Repository.UsuarioRepository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método GET para mostrar el formulario de login
    @GetMapping("/login")
    public String login() {
        return "Login";  // Devuelve la vista del formulario de login
    }

    // Método POST para procesar el formulario de login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Buscamos el usuario en la base de datos usando el nombre de usuario
        Usuario usuario = usuarioRepository.findByUsername(username);
        // Validamos si el usuario existe y la contraseña es correcta
        if (usuario != null && usuario.getPassword().equals(password)) {
            // Redirigimos al usuario a una vista específica según su id
            if (usuario.getId() == 34) {
                return "doctor";}

                if (usuario.getId() == 35) {
                    return "ObraS";

            } else {
                return "Principal";
            }
        } else {
            // Si son incorrectos, mostramos un mensaje de error
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";  // Volvemos a mostrar el formulario de login
        }
    }

    // Método GET para mostrar la lista de turnos
    @GetMapping("/registro")
    public String mostrarLista() {
        return "registro";  // Devuelve la vista registro.html
    }
}
