package com.example.Examen1Back2.modelos;

import com.example.Examen1Back2.utils.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/*
 * Representa la entidad Usuario en el sistema.
 * Esta clase está mapeada a la tabla "usuarios" en la base de datos.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
    /*
      Identificador único del usuario.
      Generado automáticamente con la estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    /*
      Nombre del usuario.
      No puede ser nulo y tiene una longitud máxima de 100 caracteres.
     */
    @Column(nullable = false, length = 100)
    private String nombre;

    /*
      Correo electrónico del usuario.
      Debe ser único en la base de datos y no puede ser nulo.
      Tiene una longitud máxima de 100 caracteres.
     */
    @Column(name = "correo_electronico", unique = true)
    private String correoElectronico;
    private String contraseña;
    private String telefono;

    /*
      Tipo de usuario, que puede ser ADMINISTRADOR, DOCENTE o ESTUDIANTE.
      Se almacena como una cadena en la base de datos.
     */
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    //Estableciendo la relacion uno a uno con la tabla docente
    @OneToOne(mappedBy = "usuario")
    @JsonBackReference(value = "docente-usuario")
    private Docente docente;

    // Constructor por defecto y constructor con parámetros para inicializar el usuario.
    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String correoElectronico, String contraseña, String telefono, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters y setters para acceder y modificar los atributos del usuario.
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}