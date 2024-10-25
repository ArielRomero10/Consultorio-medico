# Proyecto de Gestión de Turnos Médicos

Este es un sistema de gestión de turnos médicos desarrollado para digitalizar el proceso de atención en un consultorio médico. El objetivo principal es facilitar la gestión de turnos, mejorando la experiencia tanto para el personal médico como para los pacientes.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal utilizado para desarrollar la lógica del sistema.
- **Spring Boot**: Framework utilizado para simplificar el desarrollo y la implementación de aplicaciones web basadas en Java.
- **H2 Database**: Base de datos en memoria utilizada para almacenar la información de los usuarios y turnos.
- **HTML/CSS/JavaScript**: Tecnologías utilizadas para crear la interfaz de usuario.
- **Maven**: Herramienta de gestión de proyectos y dependencias.

## Funcionalidades

- **Gestión de Usuarios**: Permite el registro y autenticación de médicos y pacientes.
- **Reservas de Turnos**: Facilita a los pacientes la reserva de turnos disponibles.
- **Visualización de Turnos**: Los médicos pueden visualizar y gestionar sus turnos asignados.
- **Recursos Estáticos**: Integración de archivos HTML, CSS y JavaScript para una interfaz atractiva y funcional.

## Estructura del Proyecto

La estructura del proyecto se organiza en las siguientes capas:

- **Controladores**: Manejan las peticiones del usuario y dirigen la lógica de la aplicación.
  - `LoginController`
  - `TurnoController`
  
- **Repositorios**: Interactúan con la base de datos para almacenar y recuperar datos.
  - `UsuarioRepository`
  - `TurnoRepository`
  
- **Modelo**: Define las entidades que representan los datos en el sistema.
  - `Usuario`
  - `Turno`
  
- **Base de Datos**: Se utiliza H2 para almacenar los datos de los usuarios y los turnos.



## Cómo Ejecutar el Proyecto

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/tu_usuario/nombre_del_repositorio.git
