package br.uefs.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import br.uefs.debuger.Debuger;

public class MEMORY {

    private Integer size = 10000;
    private String[] internalMemory = new String[size];
    private Integer instruNumber = 0;
    static int internalMemorySize = 0;

    public MEMORY() throws IOException {
        mountMemory("binario.txt");
    }

    public void mountMemory(String name) throws IOException {
        FileInputStream stream = null;
        stream = new FileInputStream(name);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        boolean number = true;

        while (linha != null) {

            if (number) {
                instruNumber = Integer.parseInt(linha, 2);
                Debuger.sysPrinfT(instruNumber + "");
                number = false;
            } else {
                internalMemory[internalMemorySize++] = linha;
            }
            linha = br.readLine();
        }

    }

    public String getBinMemory(int i) {
        return internalMemory[i];
    }

    public int getSizeCurrent() {
        return internalMemorySize;
    }

    public Integer getInstruNumber() {
        return instruNumber;
    }

    public String[] getInternalMemory() {
        return internalMemory;
    }

    public Integer getSize() {
        return size;
    }

    public static int getInternalMemorySize() {
        return internalMemorySize;
    }

    public boolean writeMemory(int address, int value) {

        if (address >= instruNumber) {
            internalMemory[address] = Integer.toString(value);
        } else {
            return false;
        }
        return true;
    }

    public int readMemory(int address) {

        return Integer.parseInt(internalMemory[address]);
    }
}
