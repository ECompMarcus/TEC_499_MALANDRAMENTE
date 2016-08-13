/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.model;

import java.io.IOException;

/**
 *
 * @author Marcus José
 */
public class UnitControl {

    int pc = 0b0;
    int quantidade_instrucoes = 0;
    private final MEMORY memory;
    private DB_REG bankReg;
    private ULA ula;

    //possui instancia das demais classes
    public UnitControl() throws IOException {

        memory = new MEMORY();
        ula = new ULA();
        bankReg = new DB_REG();

    }

    private void decode() {

        while (pc < memory.getInstruNumber()) {
            String instruction = memory.getBinMemory(pc);
            String opcode = instruction.substring(0, 6);
            String op1 = instruction.substring(6, 11);
            String op2 = instruction.substring(11, 16);
            String op3 = instruction.substring(17, 22);
            String deslocamento = instruction.substring(22, 27);
            String function = instruction.substring(27, 32);
            String bit_16_31 = instruction.substring(17, 32);

            // R COM 3 OPERANDO
            // verificar demais instruções
            if (opcode.equals("000000")) {
                String result = ula.decoderULA(Integer.getInteger(function), op1, op2);
                bankReg.setValeu(Integer.getInteger(op3), Integer.getInteger(result));
                pc++;

            } else if (opcode.equals("000100")) { //beq
                boolean result = ula.decoderULA(Integer.getInteger(function), op1, op2);
                if (result) {
                    pc = Integer.parseInt(bit_16_31);
                } else {
                    pc++;
                }
            }else if (opcode.equals("000101")) {//bne
                boolean result = ula.decoderULA(Integer.getInteger(function), op1, op2);
                if (result) {
                    pc = Integer.parseInt(bit_16_31);
                } else {
                    pc++;
                }
            }else if (opcode.equals("001000")) {//addi
                String result = ula.decoderULA(Integer.getInteger(function), op2, bit_16_31);
                bankReg.setValeu(op1, result);
                pc++;
            }else if (opcode.equals("001000")) {//addi
                String result = ula.decoderULA(Integer.getInteger(function), op2, bit_16_31);
                bankReg.setValeu(op1, result);
                pc++;
            }
        }

        // switch ()
    }

    private void carregaInstrucoes(String fileName) throws IOException {
        memory.mountMemory(fileName);
    }

}
