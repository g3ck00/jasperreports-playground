package org.example.jasperreportsplayground.service;

import org.example.jasperreportsplayground.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    //Añade datos demo a ClienteDTO
    List<ClienteDTO> clientes = List.of(
            new ClienteDTO[]{new ClienteDTO("Pedro Ruiz", "pedro@test.com"),
                    new ClienteDTO("Ana López", "ana.lopez@test.com"),
                    new ClienteDTO("Carlos Mendoza", "carlos.mendoza@test.com"),
                    new ClienteDTO("María Gómez", "maria.gomez@test.com"),
                    new ClienteDTO("Luis Herrera", "luis.herrera@test.com"),
                    new ClienteDTO("Sofía Torres", "sofia.torres@test.com"),
                    new ClienteDTO("Jorge Ramírez", "jorge.ramirez@test.com"),
                    new ClienteDTO("Valentina Castro", "valentina.castro@test.com"),
                    new ClienteDTO("Diego Morales", "diego.morales@test.com"),
                    new ClienteDTO("Camila Rojas", "camila.rojas@test.com"),
                    new ClienteDTO("Fernando Silva", "fernando.silva@test.com"),
                    new ClienteDTO("Daniela Vargas", "daniela.vargas@test.com"),
                    new ClienteDTO("Ricardo Ortiz", "ricardo.ortiz@test.com"),
                    new ClienteDTO("Gabriela Peña", "gabriela.pena@test.com"),
                    new ClienteDTO("Miguel Sánchez", "miguel.sanchez@test.com"),
                    new ClienteDTO("Laura Jiménez", "laura.jimenez@test.com"),
                    new ClienteDTO("Andrés Navarro", "andres.navarro@test.com"),
                    new ClienteDTO("Paula Vega", "paula.vega@test.com"),
                    new ClienteDTO("Roberto Cruz", "roberto.cruz@test.com"),
                    new ClienteDTO("Natalia Flores", "natalia.flores@test.com"),
                    new ClienteDTO("Eduardo Medina", "eduardo.medina@test.com"),
                    new ClienteDTO("Patricia León", "patricia.leon@test.com"),
                    new ClienteDTO("Sergio Aguilar", "sergio.aguilar@test.com"),
                    new ClienteDTO("Andrea Romero", "andrea.romero@test.com"),
                    new ClienteDTO("Hugo Paredes", "hugo.paredes@test.com"),
                    new ClienteDTO("Elena Fuentes", "elena.fuentes@test.com"),
                    new ClienteDTO("Cristian Molina", "cristian.molina@test.com"),
                    new ClienteDTO("Verónica Salas", "veronica.salas@test.com"),
                    new ClienteDTO("Óscar Núñez", "oscar.nunez@test.com"),
                    new ClienteDTO("Claudia Campos", "claudia.campos@test.com"),
                    new ClienteDTO("Iván Guerrero", "ivan.guerrero@test.com"),
                    new ClienteDTO("Mónica Cabrera", "monica.cabrera@test.com"),
                    new ClienteDTO("Raúl Espinoza", "raul.espinoza@test.com"),
                    new ClienteDTO("Carolina Bravo", "carolina.bravo@test.com"),
                    new ClienteDTO("Álvaro Castillo", "alvaro.castillo@test.com"),
                    new ClienteDTO("Silvia Acosta", "silvia.acosta@test.com"),
                    new ClienteDTO("Esteban Ríos", "esteban.rios@test.com"),
                    new ClienteDTO("Lorena Soto", "lorena.soto@test.com"),
                    new ClienteDTO("Tomás Cárdenas", "tomas.cardenas@test.com"),
                    new ClienteDTO("Juliana Márquez", "juliana.marquez@test.com"),
                    new ClienteDTO("Nicolás Valencia", "nicolas.valencia@test.com"),
                    new ClienteDTO("Beatriz Arias", "beatriz.arias@test.com"),
                    new ClienteDTO("Emilio Serrano", "emilio.serrano@test.com"),
                    new ClienteDTO("Isabel Duarte", "isabel.duarte@test.com"),
                    new ClienteDTO("Gustavo Lozano", "gustavo.lozano@test.com"),
                    new ClienteDTO("Rosa Beltrán", "rosa.beltran@test.com"),
                    new ClienteDTO("Kevin Zamora", "kevin.zamora@test.com"),
                    new ClienteDTO("Lucía Montoya", "lucia.montoya@test.com"),
                    new ClienteDTO("Mateo Delgado", "mateo.delgado@test.com"),
                    new ClienteDTO("Alejandra Reyes", "alejandra.reyes@test.com")});

    public List<ClienteDTO> readClientes() {
        return clientes;
    }
}
