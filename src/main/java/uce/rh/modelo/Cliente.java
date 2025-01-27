package uce.rh.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//Creacion de las tablas
@Entity
//Anotacion para la generacion de datos GET y SET
@Data
//Constructor Vario
@NoArgsConstructor
//Constructor con todos los argumentos
@AllArgsConstructor
//Mandar a imprimir el objeto empleado
@ToString

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCliente;
    String nombre;
    String cedula;
    String direccion;
    String celular;
}

