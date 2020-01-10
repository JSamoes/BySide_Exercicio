/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byside_ex.Model;

import java.time.LocalDate;

/**
 *
 * @author joaos
 */
public class Ficheiro {

    private Diretorio diretorio;
    private String nome;
    private String dataCriacao;
    private int permissoes;
    private String conteudo;

    public Ficheiro(Diretorio d, String nome, String dataCriacao, int permissoes, String conteudo) {
        this.diretorio = d;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.permissoes = permissoes;
        this.conteudo = conteudo;
    }

    public Ficheiro(Ficheiro f, String nome) {
        this.diretorio = f.getDiretorio();
        this.conteudo = f.getConteudo();
        this.permissoes = f.getPermissoes();
        this.dataCriacao = LocalDate.now().toString();
        this.nome = nome;
    }

    /**
     * @return the diretorio
     */
    public Diretorio getDiretorio() {
        return diretorio;
    }

    /**
     * @param diretorio the diretorio to set
     */
    public void setDiretorio(Diretorio diretorio) {
        this.diretorio = diretorio;
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
     * @return the conteudo
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * @param conteudo the conteudo to set
     */
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

}
