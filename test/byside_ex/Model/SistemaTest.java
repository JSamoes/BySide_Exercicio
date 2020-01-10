/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byside_ex.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaos
 */
public class SistemaTest {

    public SistemaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of criarDiretorio method, of class Sistema.
     */
    @Test
    public void testCriarDiretorio() {
        System.out.println("criarDiretorio");

        String dirAtual = "/Sistema";
        String nome = "Sistema";
        Sistema instance = new Sistema(nome);
        boolean expResult = true;
        boolean result = instance.criarDiretorio(dirAtual, nome);
        assertEquals(expResult, result);
    }

    /**
     * Test of addDiretorio method, of class Sistema.
     */
    @Test
    public void testAddDiretorio() {
        System.out.println("addDiretorio");
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        instance.addDiretorio(d);
        int expResult = 1;
        assertEquals(expResult, instance.getDiretorios().size());
    }

    /**
     * Test of getDiretorioByNome method, of class Sistema.
     */
    @Test
    public void testGetDiretorioByNome() {
        System.out.println("getDiretorioByNome");
        String nome = "Sistema";
        Sistema instance = new Sistema("Sistema");
        Diretorio expResult = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        instance.addDiretorio(expResult);
        Diretorio result = instance.getDiretorioByNome(nome);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiretorios method, of class Sistema.
     */
    @Test
    public void testGetDiretorios() {
        System.out.println("getDiretorios");
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        instance.addDiretorio(d);
        ArrayList<Diretorio> expResult = new ArrayList<>();
        expResult.add(d);
        ArrayList<Diretorio> result = instance.getDiretorios();
        assertEquals(expResult, result);
    }

    /**
     * Test of apresentaPossibilidades method, of class Sistema.
     */
    @Test
    public void testApresentaPossibilidades_String() {
        System.out.println("apresentaPossibilidades");
        String dirAtual = "/Sistema";
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        instance.addDiretorio(d);
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(d.getCaminho());
        ArrayList<String> result = instance.apresentaPossibilidades(dirAtual);
        assertEquals(expResult, result);
    }

    /**
     * Test of apresentaPossibilidades method, of class Sistema.
     */
    @Test
    public void testApresentaPossibilidades_0args() {
        System.out.println("apresentaPossibilidades");
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        instance.addDiretorio(d);
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(d.getNome());
        ArrayList<String> result = instance.apresentaPossibilidades();
        assertEquals(expResult, result);
    }

    /**
     * Test of mudarDiretorio method, of class Sistema.
     */
    @Test
    public void testMudarDiretorio() {
        System.out.println("mudarDiretorio");
        String dirAtual = "/Sistema";
        String dir = "/Sistema/nome";
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Diretorio d1 = new Diretorio(instance.getCaminho() + "/nome", "nome", LocalDate.now().toString(), 7);
        instance.addDiretorio(d);
        instance.addDiretorio(d1);
        String expResult = "/Sistema/nome";
        String result = instance.mudarDiretorio(dirAtual, dir);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFicheiros method, of class Sistema.
     */
    @Test
    public void testGetFicheiros() {
        System.out.println("getFicheiros");
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Ficheiro f = new Ficheiro(d, "ficheiro", LocalDate.now().toString(), 7, "conteudo");
        instance.addDiretorio(d);
        instance.addFicheiro(f);
        ArrayList<Ficheiro> expResult = new ArrayList<>();
        expResult.add(f);
        ArrayList<Ficheiro> result = instance.getFicheiros();
        assertEquals(expResult, result);
    }

    /**
     * Test of addFicheiro method, of class Sistema.
     */
    @Test
    public void testAddFicheiro() {
        System.out.println("addFicheiro");
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Ficheiro f = new Ficheiro(d, "ficheiro", LocalDate.now().toString(), 7, "conteudo");
        int expResult = instance.getFicheiros().size();
        instance.addDiretorio(d);
        instance.addFicheiro(f);
        int result = instance.getFicheiros().size();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of criarFicheiro method, of class Sistema.
     */
    @Test
    public void testCriarFicheiro() {
        System.out.println("criarFicheiro");
        String dirAtual = "/Sistema";
        String nome = "novo";
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Ficheiro f = new Ficheiro(d, "ficheiro", LocalDate.now().toString(), 7, "conteudo");
        instance.addDiretorio(d);
        instance.addFicheiro(f);
        int sizeB = instance.getFicheiros().size();
        instance.criarFicheiro(dirAtual, nome);
        int sizeA = instance.getFicheiros().size();
        assertNotEquals(sizeB, sizeA);
    }

    /**
     * Test of verificarNomeFicheiro method, of class Sistema.
     */
    @Test
    public void testVerificarNomeFicheiro() {
        System.out.println("verificarNomeFicheiro");
        String dirAtual = "/Sistema";
        String nome = "ficheiro";
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Ficheiro f = new Ficheiro(d, "ficheiro", LocalDate.now().toString(), 7, "conteudo");
        instance.addDiretorio(d);
        instance.addFicheiro(f);
        boolean expResult = true;
        boolean result = instance.verificarNomeFicheiro(dirAtual, nome);
        assertEquals(expResult, result);
    }

    /**
     * Test of apresentaFicheiros method, of class Sistema.
     */
    @Test
    public void testApresentaFicheiros() {
        System.out.println("apresentaFicheiros");
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Ficheiro f = new Ficheiro(d, "ficheiro", LocalDate.now().toString(), 7, "conteudo");
        instance.addDiretorio(d);
        instance.addFicheiro(f);
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(f.getNome());
        ArrayList<String> result = instance.apresentaFicheiros();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFicheiroByNome method, of class Sistema.
     */
    @Test
    public void testGetFicheiroByNome() {
        System.out.println("getFicheiroByNome");
        String nome = "ficheiro";
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Ficheiro expResult = new Ficheiro(d, "ficheiro", LocalDate.now().toString(), 7, "conteudo");
        instance.addDiretorio(d);
        instance.addFicheiro(expResult);
        Ficheiro result = instance.getFicheiroByNome(nome);
        assertEquals(expResult, result);
    }

    /**
     * Test of apagarFicheiro method, of class Sistema.
     */
    @Test
    public void testApagarFicheiro() {
        System.out.println("apagarFicheiro");
        String file = "ficheiro";
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Ficheiro f = new Ficheiro(d, "ficheiro", LocalDate.now().toString(), 7, "conteudo");
        instance.addDiretorio(d);
        instance.addFicheiro(f);
        int sizeB = instance.getFicheiros().size();
        System.out.println("SIZEB");
        System.out.println(sizeB);
        instance.apagarFicheiro(file);
        int sizeA = instance.getFicheiros().size();
        System.out.println("SIZEA");
        System.out.println(sizeA);
        assertNotEquals(sizeB, sizeA);
    }

    /**
     * Test of copiarFicheiro method, of class Sistema.
     */
    @Test
    public void testCopiarFicheiro() {
        System.out.println("copiarFicheiro");
        String nomeACopiar = "nome";
        String nome = "nome1";
        Sistema instance = new Sistema("Sistema");
        Diretorio d = new Diretorio(instance.getCaminho(), instance.getNome(), LocalDate.now().toString(), 7);
        Ficheiro f = new Ficheiro(d, nome, LocalDate.now().toString(), 7, "conteudo");
        Ficheiro f1 = new Ficheiro(d, nomeACopiar, LocalDate.now().toString(), 7, "conteudo");
        instance.addFicheiro(f);
        instance.addFicheiro(f1);
        instance.copiarFicheiro(nome, nomeACopiar);
        boolean expResult = false;
        if (f.getDiretorio().equals(f1.getDiretorio())) {
            if (f.getConteudo().equals(f1.getConteudo())) {
                if (f.getPermissoes() == f1.getPermissoes()) {
                    expResult = true;
                }
            }
        }
        assertTrue(expResult);
    }

}
