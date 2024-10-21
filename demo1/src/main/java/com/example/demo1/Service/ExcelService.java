package com.example.demo1.Service;

import com.example.demo1.Model.Turno;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


import java.io.OutputStream;
import java.util.List;

@Service
public class ExcelService {

    public void exportTurnosToExcel(List<Turno> turnos, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Turnos");

        // Crear encabezado
        createHeader(sheet); // Llamar a la función que ya has creado

        // Llenar datos
        int rowNum = 1;
        for (Turno turno : turnos) {
            Row row = sheet.createRow(rowNum++);
            fillTurnoData(row, turno); // Usar la función que ya has creado para llenar datos
        }

        // Escribir el archivo en el ByteArrayOutputStream
        workbook.write(outputStream);
        workbook.close();
    }

    private void createHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Paciente", "Fecha", "Hora", "Comentarios", "Obra Social", "Nombre", "Apellido"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
    }

    private void fillTurnoData(Row row, Turno turno) {
        row.createCell(0).setCellValue(turno.getId());
        row.createCell(1).setCellValue(turno.getPaciente());
        row.createCell(2).setCellValue(turno.getFecha().toString()); // Si es Date, usar toString()
        row.createCell(3).setCellValue(turno.getHora().toString());   // Si es Date, usar toString()
        row.createCell(4).setCellValue(turno.getComentarios());
        row.createCell(5).setCellValue(turno.getObraSocial());
        row.createCell(6).setCellValue(turno.getNombrePaciente());
        row.createCell(7).setCellValue(turno.getApellidoPaciente());
    }
}

