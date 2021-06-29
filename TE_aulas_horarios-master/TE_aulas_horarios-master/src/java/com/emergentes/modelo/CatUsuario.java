
package com.emergentes.modelo;


public class CatUsuario {
    
        private int id;
    private String descripcion;

    public CatUsuario() {
        this.id = 0;
        this.descripcion = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
