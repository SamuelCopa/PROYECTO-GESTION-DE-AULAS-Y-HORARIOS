package com.emergentes.modelo;

public class Horario {

    private int id;
    private String gestion;
    private String turno;
    private String dia;
    int id_horas;
    int id_aula;
    int id_mat_par;
    int id_docente;
    int nivel;
    String nom_materia;
    String sigla;
    String paralelo;
    String ap_docente;
    String nom_docente;
    String docente;
    String mat_par;

    public Horario() {
        this.id = 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getId_horas() {
        return id_horas;
    }

    public void setId_horas(int id_horas) {
        this.id_horas = id_horas;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public int getId_mat_par() {
        return id_mat_par;
    }

    public void setId_mat_par(int id_mat_par) {
        this.id_mat_par = id_mat_par;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNom_materia() {
        return nom_materia;
    }

    public void setNom_materia(String nom_materia) {
        this.nom_materia = nom_materia;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public String getAp_docente() {
        return ap_docente;
    }

    public void setAp_docente(String ap_docente) {
        this.ap_docente = ap_docente;
    }

    public String getNom_docente() {
        return nom_docente;
    }

    public void setNom_docente(String nom_docente) {
        this.nom_docente = nom_docente;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getMat_par() {
        return mat_par;
    }

    public void setMat_par(String mat_par) {
        this.mat_par = mat_par;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }




}
