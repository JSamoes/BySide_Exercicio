/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byside_ex.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author joaos
 */
public class Sistema {

    public String nomeSistema;
    public String caminho;
    public ArrayList<Diretorio> diretorios;
    public ArrayList<Ficheiro> ficheiros;

    public Sistema(String nome) {
        this.nomeSistema = nome;
        this.caminho = "/" + nome;
        this.diretorios = new ArrayList<>();
        this.ficheiros = new ArrayList<>();
    }

    public String getCaminho() {
        return this.caminho;
    }

    public String getNome() {
        return this.nomeSistema;
    }

    //============ DIRETORIOS =========================
    public boolean criarDiretorio(String dirAtual, String nome) {

        String[] dir = dirAtual.split("/");
        String caminhoD = null;
        if (dir.length > 2) {
            String dirPai = dir[dir.length - 1];
            for (Diretorio dire : this.diretorios) {
                if (dire.getNome().equals(dirPai)) {
                    caminhoD = dire.getCaminho() + "/" + nome;
                }
            }
        } else {
            caminhoD = this.caminho + "/" + nome;
        }
        String dataCriacao = LocalDate.now().toString();
        int permissoes = 6;
        if (caminhoD != null) {
            Diretorio d = new Diretorio(caminhoD, nome, dataCriacao, permissoes);
            addDiretorio(d);
            return true;
        } else {
            return false;
        }
    }

    public void addDiretorio(Diretorio d) {
        this.diretorios.add(d);
    }

    public Diretorio getDiretorioByNome(String nome) {
        for (Diretorio diretorio : this.diretorios) {
            if (diretorio.getNome().equals(nome)) {
                return diretorio;
            }
        }
        return null;
    }

    public ArrayList<Diretorio> getDiretorios() {
        return this.diretorios;
    }

    public ArrayList<String> apresentaPossibilidades(String dirAtual) {
        ArrayList<String> caminhos = new ArrayList<>();
        int cont = 1;
        for (Diretorio diretorio : this.diretorios) {
            if (diretorio.getCaminho().contains(dirAtual)) {
                System.out.println(cont + "- " + diretorio.getCaminho());
                caminhos.add(diretorio.getCaminho());
                cont++;
            }
        }
        return caminhos;
    }

    public ArrayList<String> apresentaPossibilidades() {
        ArrayList<String> nome = new ArrayList<>();
        int cont = 1;
        for (Diretorio diretorio : this.diretorios) {
            System.out.println(cont + "- " + diretorio.getNome());
            nome.add(diretorio.getNome());
            cont++;
        }
        return nome;
    }

    public String mudarDiretorio(String dirAtual, String dir) {
        String dirnova = "";
        if (dir.equals("..")) {
            if (dirAtual.equals(this.getCaminho())) {
                return dirAtual;
            } else {
                String[] aux = dirAtual.split("/");
                for (int i = 1; i < aux.length - 1; i++) {
                    dirnova += "/" + aux[i];
                }
                if (!dirnova.equals("")) {
                    return dirnova;
                }
            }
        } else {
            return dir;
        }
        return null;
    }

    public boolean apagarDiretorio(String dirAtual, String dir) {
        Diretorio d = this.getDiretorioByNome(dir);
        int per = d.getPermissoes();
        if (per != 4) {
            if (d.getFicheiros().isEmpty() || d.getDiretorios().isEmpty()) {
                return this.diretorios.remove(d);
            } else {
                System.out.println("O diretório tem de estar vazio!");
                return false;
            }
        } else {
            System.out.println("Sem permissões.");
            return false;
        }
    }

    public void updatePermissoesDiretorio(Diretorio d, int perm) {
        for (Diretorio diretorio : diretorios) {
            if (diretorio.equals(d)) {
                diretorio.setPermissoes(perm);
                break;
            }
        }
    }

    //=================FICHEIROS============================
    public ArrayList<Ficheiro> getFicheiros() {
        return this.ficheiros;
    }

    public void addFicheiro(Ficheiro f) {
        if (this.ficheiros.contains(f)) {
            return;
        }
        this.ficheiros.add(f);
        Diretorio n = f.getDiretorio();
        if (n != null) {
            ArrayList<Ficheiro> fic = n.getFicheiros();
            fic.add(f);
            n.setFicheiros(fic);
        }
    }

    public void criarFicheiro(String dirAtual, String nome) {
        String dir = dirAtual.split("/")[dirAtual.split("/").length - 1];
        Diretorio d = this.getDiretorioByNome(dir);
        String dataCriacao = LocalDate.now().toString();
        int permissoes = 6;
        Ficheiro f = new Ficheiro(d, nome, dataCriacao, permissoes, "");
        addFicheiro(f);
    }

    public boolean verificarNomeFicheiro(String dirAtual, String nome) {
        for (Diretorio diretorio : diretorios) {
            if (dirAtual.equals(diretorio.getCaminho())) {
                for (Ficheiro ficheiro : diretorio.getFicheiros()) {
                    if (ficheiro.getNome().equals(nome)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<String> apresentaFicheiros() {
        ArrayList<String> nomeF = new ArrayList<>();
        int cont = 1;
        for (Ficheiro ficheiro : ficheiros) {
            System.out.println(cont + "- " + ficheiro.getNome());
            nomeF.add(ficheiro.getNome());
            cont++;
        }
        return nomeF;
    }

    public Ficheiro getFicheiroByNome(String nome) {
        for (Ficheiro ficheiro : ficheiros) {
            if (ficheiro.getNome().equals(nome)) {
                return ficheiro;
            }
        }
        return null;
    }

    public boolean apagarFicheiro(String file) {
        Ficheiro f = this.getFicheiroByNome(file);
        if (f.getPermissoes() != 4) {
            return this.ficheiros.remove(f);
        }
        return false;
    }

    public Ficheiro copiarFicheiro(String nome, String nomeACopiar) {
        Ficheiro f = this.getFicheiroByNome(nomeACopiar);
        Ficheiro novo = new Ficheiro(f, nome);
        this.addFicheiro(novo);
        return novo;
    }

    public void acrescentaConteudoFicheiro(Ficheiro f, String cont) {
        for (Ficheiro ficheiro : this.ficheiros) {
            if (ficheiro.equals(f)) {
                String conteudoAntigo = ficheiro.getConteudo();
                conteudoAntigo += cont;
                ficheiro.setConteudo(conteudoAntigo);
                break;
            }
        }
    }

    public boolean updateFicheiro(String file, String cont) {
        Ficheiro f = this.getFicheiroByNome(file);
        f.setConteudo(cont);
        return true;
    }

    public void mudarDiretorioFicheiro(String filename, String directory) {
        Ficheiro f = this.getFicheiroByNome(filename);
        f.getDiretorio().getFicheiros().remove(f);
        Diretorio d = this.getDiretorioByNome(directory);
        f.setDiretorio(d);

    }

    public void updatePermissoesFicheiro(Ficheiro f, int perm) {
        for (Ficheiro ficheiro : this.ficheiros) {
            if (ficheiro.equals(f)) {
                ficheiro.setPermissoes(perm);
                break;
            }
        }
    }
}
