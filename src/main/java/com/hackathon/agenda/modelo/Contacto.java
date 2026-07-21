package com.hackathon.agenda.modelo;
import java.util.Objects;

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    public static final String REGEX = "^(\\+\\d{1,3}\\s?)?(\\d{3}\\s?){3}$|^\\d{9}$";

    public Contacto(String nombre, String apellido, String telefono){

        if (!validarTextoIngresado(nombre)) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (!validarTextoIngresado(apellido)) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("El formato del teléfono no es válido.");
        }


        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono.trim();
    }

    public static boolean validarTextoIngresado(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.trim().matches(REGEX);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nuevoNombre) {
        if (!validarTextoIngresado(nuevoNombre)) {
            throw new IllegalArgumentException("El nuevo nombre no debe estar vacio.");
        }
        this.nombre = nuevoNombre.trim();
    }

    public void setApellido(String nuevoApellido) {
        if (!validarTextoIngresado(nuevoApellido)) {
            throw new IllegalArgumentException("El nuevo apellido no debe estar vacio.");
        }
        this.apellido = nuevoApellido.trim();
    }

    public void setTelefono(String nuevoTelefono) {
        if (!validarTelefono(nuevoTelefono)) {
            throw new IllegalArgumentException("El nuevo teléfono no cumple el formato válido.");
        }
        this.telefono = nuevoTelefono.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return nombre.equalsIgnoreCase(contacto.nombre) &&
                apellido.equalsIgnoreCase(contacto.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + telefono + ")";
    }
}