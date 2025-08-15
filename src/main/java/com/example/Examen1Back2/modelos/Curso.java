package com.example.Examen1Back2.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/*
Representa la entidad Curso en el sistema.
Esta clase está mapeada a la tabla "curso" en la base de datos.
*/
@Entity
@Table(name = "curso")
public class Curso {

    /*
     Identificador único del curso.
     Generado automáticamente con la estrategia de identidad.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    /*
     Relación muchos a uno con la entidad Docente.
     Un curso es impartido por un docente.
     La relación se establece mediante la columna "fk_docente".
     Se utiliza JsonBackReference para evitar problemas de recursión en la serialización JSON.
    */

    @ManyToOne
    @JoinColumn(name = "fk_docente", referencedColumnName = "id")
    @JsonBackReference(value = "docente-curso")
    private Docente docente;

    // Constructor por defecto y constructor con parámetros para inicializar el curso.

    public Curso() {
    }

    public Curso(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters para acceder y modificar los atributos del curso.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}
