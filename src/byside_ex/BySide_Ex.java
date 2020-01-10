/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byside_ex;

import byside_ex.Model.Diretorio;
import byside_ex.Model.Ficheiro;
import byside_ex.Model.Sistema;
import byside_ex.Utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author joaos
 */
public class BySide_Ex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bem vindo ao seu Sistema de Ficheiros!");

        System.out.println("Pretende iniciar algum sistema já criado?\n");

        ArrayList<String> sist = Utils.importSistemas("sistemas.txt");
        for (String string : sist) {
            System.out.println("- " + string);
        }
        Sistema sis = null;
        System.out.println("\nSe pretende criar um sistema novo, digite 0.");
        String ans = scan.nextLine();
        if (ans.equals("0") || ans.equals("O")) {
            System.out.println("Insira o nome do seu novo Sistema:");
            String nomeSis = scan.nextLine();

            sis = new Sistema(nomeSis);
            String dataCriacao = LocalDate.now().toString();
            Diretorio sisD = new Diretorio(sis.getCaminho(), sis.getNome(), dataCriacao, 7);
            sis.addDiretorio(sisD);
        } else {
            if (sist.contains(ans)) {
                sis = Utils.importFile(ans + ".txt");
            } else {
                System.out.println("Opção inválida.");
                return;
            }
        }

        String dirAtual = sis.getCaminho();
        int option = Integer.MAX_VALUE;
        while (option != 0) {
            System.out.println("\nDiretório Atual: " + dirAtual);
            System.out.println("=================================");
            System.out.println("1. Criar um novo Diretório.");
            System.out.println("2. Criar um novo Ficheiro.");
            System.out.println("3. Mudar de Diretório.");
            System.out.println("4. Mover Ficheiro.");
            System.out.println("5. Apagar Diretório.");
            System.out.println("6. Apagar Ficheiro.");
            System.out.println("7. Copiar um ficheiro.");
            System.out.println("8. Adicionar conteúdo a um ficheiro.");
            System.out.println("9. Importar ficheiro.");
            System.out.println("10. Mudar Permissões Ficheiro.");
            System.out.println("11. Mudar Permissões Diretorio.");
            System.out.println("0. Sair.");
            System.out.println("A sua opção:");
            option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Insira o nome do diretório a criar.");
                    String nomeD = scan.nextLine();
                    if (sis.criarDiretorio(dirAtual, nomeD)) {
                        System.out.println("Diretório criado com sucesso.\n");
                    } else {
                        System.out.println("Erro a criar o diretório.\n");
                    }
                    break;
                case 2:
                    System.out.println("Insira o nome do ficheiro a criar.");
                    String nomeF = scan.nextLine();
                    if (!sis.verificarNomeFicheiro(dirAtual, nomeF)) {
                        sis.criarFicheiro(dirAtual, nomeF);
                        System.out.println("Ficheiro criado com sucesso.\n");
                    } else {
                        System.out.println("Nome já existente.\n");
                    }
                    break;
                case 3:
                    System.out.println("Para que diretório deseja ir? Caso queira voltar atrás, introduza '..'. ");
                    ArrayList<String> caminhos = sis.apresentaPossibilidades(dirAtual);
                    caminhos.add("..");
                    System.out.println("Digite a sua opção:");
                    String dirD = scan.nextLine();
                    if (caminhos.contains(dirD)) {
                        dirAtual = sis.mudarDiretorio(dirAtual, dirD);
                        System.out.println("Sucesso!");
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 4:
                    System.out.println("Insira o nome do Ficheiro a mover.");
                    ArrayList<String> file1 = sis.apresentaFicheiros();
                    System.out.println("Digite a sua opção:");
                    String nomeFi = scan.nextLine();
                    if (file1.contains(nomeFi)) {
                        System.out.println("Insira o nome do diretório para onde pretende mover");
                        ArrayList<String> nomesD = sis.apresentaPossibilidades();
                        String nomeDir = scan.nextLine();
                        if (nomesD.contains(nomeDir)) {
                            sis.mudarDiretorioFicheiro(nomeFi, nomeDir);
                            System.out.println("Ficheiro movido com sucesso.");
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 5:
                    System.out.println("Insira o nome do diretório a apagar.");
                    ArrayList<String> nomesD = sis.apresentaPossibilidades();
                    System.out.println("A sua opção:");
                    String apagaD = scan.nextLine();
                    if (nomesD.contains(apagaD)) {
                        if (sis.apagarDiretorio(dirAtual, apagaD)) {
                            System.out.println("Diretório apagado com sucesso.");
                        } else {
                            System.out.println("Erro!");
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 6:
                    System.out.println("Insira o nome do ficheiro a apagar.");
                    ArrayList<String> nomesF = sis.apresentaFicheiros();
                    System.out.println("A sua opção:");
                    String file = scan.nextLine();
                    if (nomesF.contains(file)) {
                        sis.apagarFicheiro(file);
                        System.out.println("Ficheiro apagado com sucesso.");
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 7:
                    System.out.println("Insira o nome do ficheiro a copiar.");
                    ArrayList<String> nomesCopiar = sis.apresentaFicheiros();
                    System.out.println("A sua opção:");
                    String fic = scan.nextLine();
                    if (nomesCopiar.contains(fic)) {
                        System.out.println("Qual o nome do novo ficheiro?");
                        String novo = scan.nextLine();
                        if (!novo.equalsIgnoreCase(fic)) {
                            sis.copiarFicheiro(novo, fic);
                            System.out.println("Ficheiro copiado com sucesso!");
                        } else {
                            System.out.println("Nome do ficheiro não pode ser igual!");
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 8:
                    System.out.println("Insira o nome do ficheiro a editar.");
                    ArrayList<String> fNome = sis.apresentaFicheiros();
                    System.out.println("A sua opção:");
                    String filenome = scan.nextLine();
                    if (fNome.contains(filenome)) {
                        Ficheiro f2 = sis.getFicheiroByNome(filenome);
                        System.out.println("Conteúdo do ficheiro:\n");
                        System.out.println(f2.getConteudo());
                        System.out.println("Conteúdo a acrescentar\n");
                        String cont = scan.nextLine();
                        sis.acrescentaConteudoFicheiro(f2, cont);
                    } else {
                        System.out.println("Opção errada.");
                    }
                    break;

                case 9:
                    System.out.println("Insira o nome do ficheiro a editar.");
                    ArrayList<String> fNom = sis.apresentaFicheiros();
                    System.out.println("A sua opção:");
                    String filenom = scan.nextLine();
                    if (fNom.contains(filenom)) {
                        System.out.println("Insira o nome do ficheiro que pretende importar");
                        System.out.println("NOTA: o ficheiro tem de se encontrar na pasta do projeto.");
                        String ficheiro = scan.nextLine();
                        String content = Utils.importFileUtilizador(sis, ficheiro);
                        if (sis.updateFicheiro(filenom, content)) {
                            System.out.println("Ficheiro importado com sucesso.");
                        } else {
                            System.out.println("ERRO");
                        }
                    }
                    break;

                case 10:
                    System.out.println("Insira o nome do ficheiro que pretende alterar permissões.");
                    ArrayList<String> fNomP = sis.apresentaFicheiros();
                    System.out.println("A sua opção:");
                    String fileno = scan.nextLine();
                    if (fNomP.contains(fileno)) {
                        Ficheiro f = sis.getFicheiroByNome(fileno);
                        System.out.println("Permissões atuais do ficheiro: " + f.getPermissoes());
                        System.out.println("0 - Sem permissões");
                        System.out.println("1 - Executar");
                        System.out.println("2 - Escrever");
                        System.out.println("3 - Executar + Escrever");
                        System.out.println("4 - Ler");
                        System.out.println("5 - Ler + Executar");
                        System.out.println("6 - Ler + Escrever");
                        System.out.println("7 - Ler + Escrever + Executar");
                        System.out.println("Insira a sua opção (número associado):");
                        String per = scan.nextLine();
                        int perm = Integer.parseInt(per);
                        if (perm >= 0 && perm < 8) {
                            sis.updatePermissoesFicheiro(f, perm);
                            System.out.println("Permissões do ficheiro " + f.getNome() + " alteradas com sucesso.");
                        } else {
                            System.out.println("Valor inválido.");
                        }
                    }
                    break;

                case 11:
                    System.out.println("Insira o nome do diretório que pretende alterar permissões.");
                    ArrayList<String> dNomP = sis.apresentaPossibilidades();
                    System.out.println("A sua opção:");
                    String dno = scan.nextLine();
                    if (dNomP.contains(dno)) {
                        Diretorio d = sis.getDiretorioByNome(dno);
                        System.out.println("Permissões atuais do diretório: " + d.getPermissoes());
                        System.out.println("0 - Sem permissões");
                        System.out.println("1 - Executar");
                        System.out.println("2 - Escrever");
                        System.out.println("3 - Executar + Escrever");
                        System.out.println("4 - Ler");
                        System.out.println("5 - Ler + Executar");
                        System.out.println("6 - Ler + Escrever");
                        System.out.println("7 - Ler + Escrever + Executar");
                        System.out.println("Insira a sua opção (número associado):");
                        String per = scan.nextLine();
                        int perm = Integer.parseInt(per);
                        if (perm >= 0 && perm < 8) {
                            sis.updatePermissoesDiretorio(d, perm);
                            System.out.println("Permissões do diretorio " + d.getNome() + " alteradas com sucesso.");
                        } else {
                            System.out.println("Valor inválido.");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Deseja guardar o seu sistema?");
                    System.out.println("A sua opção:");
                    String op = scan.nextLine();
                    if (op.equalsIgnoreCase("s") || op.equalsIgnoreCase("sim")) {
                        Utils.exportFile(sis, sis.getNome() + ".txt");
                        Utils.insertSistema(sis, "sistemas.txt");
                        System.out.println("O seu ficheiro ficou guardado como " + sis.getNome() + ".txt.");
                        System.out.println("Obrigado!");
                    } else if (op.equalsIgnoreCase("n") || op.equalsIgnoreCase("nao") || op.equalsIgnoreCase("não")) {
                        System.out.println("Obrigado!");
                    }
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    break;
            }
        }

    }

}
