/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * A Classe representação uma abstração em alto nivel 
 * de uma busca por instrução em arquivo de texto
 *
 * @author Marcus José e Arthur
 */
public class Simulador {

    public static HashMap<Integer, String> map = new HashMap<>();
    public static HashMap<String, String> funct_0 = new HashMap<>();
    public static HashMap<String, String> funct_Not_0 = new HashMap<>();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        int pc = 0; 
        carregaInstrucoes();
        carregaFunct();

        while (retornaInstrucao(pc) != null) {
            String instrucao = retornaInstrucao(pc);
            String opcode = instrucao.substring(0, 6);

            if (opcode.equals("000000")) {
                String funct = instrucao.substring(26, 32);
                System.out.println(funct_0.get(funct));

            } else {
                System.out.println(funct_Not_0.get(opcode));
            }
            pc++;
        }
    }

    public static String retornaInstrucao(int pc) {
        if (map.containsKey(pc)) {
            return map.get(pc);
        }
        return null;
    }

    public static void carregaInstrucoes() throws IOException {
        FileInputStream stream = null;
        stream = new FileInputStream("binario.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        int num = 0;
        while (linha != null) {
            map.put(num, linha);
            num++;
            linha = br.readLine();
        }
    }

    public static void carregaFunct() throws FileNotFoundException, IOException {
        FileInputStream stream = null;
        stream = new FileInputStream("funct.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while (linha != null) {
            String[] tokens = linha.split("[\\t - \\s]");
            if (tokens[0].equals("000000")) {
                funct_0.put(tokens[2], tokens[1]);
            } else {
                funct_Not_0.put(tokens[0], tokens[1]);
            }
            linha = br.readLine();
        }
    }
}
