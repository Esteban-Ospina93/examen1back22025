package com.example.Examen1Back2.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
/*
Representa la entidad Docente en el sistema.
Esta clase está mapeada a la tabla "docente" en la base de datos.
*/
@Entity
@Table(name = "docente")
public class Docente {
    /*
     Identificador único del docente.
     Generado automáticamente con la estrategia de identidad.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String especialidad;
    /*
     Relación uno a muchos con la entidad Curso.
     Un docente puede impartir varios cursos.
     La relación se establece mediante la columna "fk_docente" en la tabla Curso.
     Se utiliza JsonManagedReference para manejar la serialización JSON de manera adecuada.
    */
    @OneToMany(mappedBy = "docente")
    @JsonManagedReference(value = "docente-curso")
    private List<Curso> cursos;
    /*
     Relación uno a uno con la entidad Usuario.
     Un docente está asociado a un usuario.
     La relación se establece mediante la columna "fk_usuario" en la tabla Usuario.
     Se utiliza JsonManagedReference para manejar la serialización JSON de manera adecuada.
    */
    @OneToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario")
    @JsonManagedReference(value = "docente-usuario")
    private Usuario usuario;

    // Constructor por defecto y constructor con parámetros para inicializar el docente.
    public Docente() {
    }

    public Docente(Integer id, String especialidad, List<Curso> cursos, Usuario usuario) {
        this.id = id;
        this.especialidad = especialidad;
        this.cursos = cursos;
        this.usuario = usuario;
    }

    // Getters y setters para acceder y modificar los atributos del docente.
    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}