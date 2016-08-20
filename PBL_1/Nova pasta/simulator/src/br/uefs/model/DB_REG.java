package br.uefs.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DB_REG {

    //[index][NOME][VALOR]
    private final String[][] registers = new String[32][2];

    DB_REG() throws IOException {
        FileInputStream stream = null;
        stream = new FileInputStream("registers.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        int i = 0;
        while (linha != null) {
            String[] tokens = linha.split("[\\t - \\s]");

            registers[i][0] = tokens[0];
            registers[i][1] = null;
            i++;
            // System.err.println(tokens[0]+ " - "+Integer.parseInt(tokens[1]));
            linha = br.readLine();
        }
    }

    public int getRegisters(String name) {
        for (int i = 0; i < registerSize(); i++) {
            if (registers[i][0].equals(name)) {
                return i;
            }
        }

        return -1;
    }

    public int getRegistersValue(int i) {
        return Integer.parseInt(registers[i][1]);
    }

    public int registerSize() {
        return registers.length;
    }

    public void setValeu(Integer registers2, Integer instruNumber) {
        registers[registers2][1] = instruNumber + "";
    }

}
