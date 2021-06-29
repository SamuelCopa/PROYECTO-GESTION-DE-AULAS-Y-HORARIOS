package com.emergentes.modelo;

public class Usuario {

    private int id;
    private String usuario;
    private String password;
    private int id_cat_usuario;

    public Usuario() {
        this.id = 0;
        this.usuario = "";
        this.password = "";
        //this.id_cat_usuario=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_cat_usuario() {
        return id_cat_usuario;
    }

    public void setId_cat_usuario(int id_cat_usuario) {
        this.id_cat_usuario = id_cat_usuario;
    }

}
