/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byside_ex.Utils;

import byside_ex.Model.Diretorio;
import byside_ex.Model.Ficheiro;
import byside_ex.Model.Sistema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author joaos
 */
public class Utils {

    public static Sistema importFile(String filename) {
        ArrayList<Diretorio> dirEmEspera = new ArrayList<>();
        try (Scanner lerFicheiro = new Scanner(new File(filename), "utf-8")) {
            Sistema sis = null;
            while (lerFicheiro.hasNextLine()) {
                String linha = lerFicheiro.nextLine();
                String[] aux = linha.split(":");
                if (aux[0].equalsIgnoreCase("s")) {
                    sis = new Sistema(aux[1]);

                    String dataCriacao = LocalDate.now().toString();
                    Diretorio sisD = new Diretorio(sis.getCaminho(), sis.getNome(), dataCriacao, 7);
                    sis.addDiretorio(sisD);
                } else if (aux[0].equalsIgnoreCase("d")) {
                    String[] infoD = aux[1].split(";");

                    String caminhoD = infoD[0];

                    int auxL = caminhoD.split("/").length;
                    String nome = caminhoD.split("/")[auxL - 1];
                    String dataCriacao = infoD[1];

                    int permissoes = Integer.parseInt(infoD[2]);

                    Diretorio d = new Diretorio(caminhoD, nome, dataCriacao, permissoes);

                    if (caminhoD.split("/").length > 3) {
                        ArrayList<Diretorio> getD = sis.getDiretorios();

                        for (Diretorio diretorio : getD) {
                            if (diretorio.getNome().equals(caminhoD.split("/")[caminhoD.split("/").length - 2])) {
                                ArrayList<Diretorio> getD1 = diretorio.getDiretorios();
                                getD1.add(d);
                                diretorio.setDiretorios(getD1);
                            }

                        }

                    }
                    if (sis != null && !(d.equals(null))) {
                        sis.addDiretorio(d);
                    }
                } else if (aux[0].equalsIgnoreCase("f")) {
                    String[] infoF = aux[1].split(";");

                    String caminhoF = infoF[0];

                    String dataCriacao = infoF[1];

                    int auxL = caminhoF.split("/").length - 1;
                    String nome = caminhoF.split("/")[auxL];

                    Diretorio d = null;
                    if (caminhoF.split("/").length > 2) {
                        d = sis.getDiretorioByNome(caminhoF.split("/")[caminhoF.split("/").length - 2]);
                    }
                    int permissoes = Integer.parseInt(infoF[2]);
                    String conteudo = infoF[3];
                    Ficheiro f = null;
                    if (d != null) {
                        f = new Ficheiro(d, nome, dataCriacao, permissoes, conteudo);
                    }
                    if (sis != null) {
                        sis.addFicheiro(f);
                    }

                }
            }
            return sis;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static String importFileUtilizador(Sistema s, String filename) {
        try (Scanner lerFicheiro = new Scanner(new File(filename), "utf-8")) {
            String conteudo = "";
            while (lerFicheiro.hasNext()) {
                String linha = lerFicheiro.nextLine();
                conteudo += linha;
            }
            return conteudo;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean exportFile(Sistema s, String filename) {
        try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
            writer.println("s:" + s.getNome());
            int cont = 0;
            for (Diretorio diretorio : s.getDiretorios()) {
                if (cont != 0) {
                    writer.println("d:" + diretorio.getCaminho() + ";" + diretorio.getDataCriacao() + ";" + diretorio.getPermissoes());
                }
                cont++;
            }
            for (Ficheiro ficheiro : s.getFicheiros()) {
                writer.println("f:" + ficheiro.getDiretorio().getCaminho() + "/" + ficheiro.getNome() + ";" + ficheiro.getDataCriacao() + ";" + ficheiro.getPermissoes() + ";" + ficheiro.getConteudo());
            }
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public static boolean insertSistema(Sistema s, String filename) {
        ArrayList<String> sis = Utils.importSistemas("sistemas.txt");

        try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
            for (String si : sis) {
                writer.println(si);
            }
            if (!sis.contains(s.getNome())) {
                writer.println(s.getNome());
            }

            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<String> importSistemas(String filename) {
        try (Scanner lerFicheiro = new Scanner(new File(filename), "utf-8")) {
            ArrayList<String> sistemas = new ArrayList<>();
            while (lerFicheiro.hasNextLine()) {
                sistemas.add(lerFicheiro.nextLine());
            }
            return sistemas;
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return null;
    }
}
