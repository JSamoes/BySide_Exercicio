/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byside_ex.Model;

import java.util.ArrayList;

/**
 *
 * @author joaos
 */
public class Diretorio {

    private String caminho;
    private String nome;
    private int permissoes;
    private String dataCriacao;
    private ArrayList<Ficheiro> ficheiros;
    private ArrayList<Diretorio> diretorios;

    public Diretorio(String caminho, String nome, String dataCriacao, int permissoes) {
        this.caminho = caminho;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.permissoes = permissoes;
        this.diretorios = new ArrayList<>();
        this.ficheiros = new ArrayList<>();
    }

    /**
     * @return the dataCriacao
     */
    public String getDataCriacao() {
        return dataCriacao;
    }

    /**
     * @param dataCriacao the dataCriacao to set
     */
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCaminho() {
        return this.caminho;
    }

    public void setCaminho(String c) {
        this.caminho = c;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the permissoes
     */
    public int getPermissoes() {
        return permissoes;
    }

    /**
     * @param permissoes the permissoes to set
     */
    public void setPermissoes(int permissoes) {
        this.permissoes = permissoes;
    }

    /**
     * @return the ficheiros
     */
    public ArrayList<Ficheiro> getFicheiros() {
        return ficheiros;
    }

    /**
     * @param ficheiros the ficheiros to set
     */
    public void setFicheiros(ArrayList<Ficheiro> ficheiros) {
        this.ficheiros = ficheiros;
    }

    /**
     * @return the diretorios
     */
    public ArrayList<Diretorio> getDiretorios() {
        return diretorios;
    }

    /**
     * @param diretorios the diretorios to set
     */
    public void setDiretorios(ArrayList<Diretorio> diretorios) {
        this.diretorios = diretorios;
    }

}
