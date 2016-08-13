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
            int opcode = Integer.parseInt(instruction.substring(0, 6));
            int op1 = Integer.parseInt(instruction.substring(6, 11));
            int op2 = Integer.parseInt(instruction.substring(11, 16));
            int op3 = Integer.parseInt(instruction.substring(16, 21));
            int deslocamento = Integer.parseInt(instruction.substring(21, 26));
            int function = Integer.parseInt(instruction.substring(26, 32));
            int bit_16_31 = Integer.parseInt(instruction.substring(16, 32));
            int bit_11_31 = Integer.parseInt(instruction.substring(11, 32));
            int bit_6_31 = Integer.parseInt(instruction.substring(6, 32));

            // R COM 3 OPERANDO
            // verificar demais instruções
            if (opcode == 0) {
                int result = ula.decoderULA(function, op1, op2);
                bankReg.setValeu(op3, result);
                pc++;

            } else if (opcode == 0b000100) { //beq
                int result = ula.decoderULA(function, op1, op2);
                if (result == 1) {
                    pc += bit_16_31;
                } else {
                    pc++;
                }
            } else if (opcode == 0b000101) {//bne
                int result = ula.decoderULA(function, op1, op2);
                if (result == 1) {
                    pc += bit_16_31;
                } else {
                    pc++;
                }
            } else if (opcode == 0b001000) {//addi
                int result = ula.decoderULA(function, op2, bit_16_31);
                bankReg.setValeu(op1, result);
                pc++;
            } else if (opcode == 0b001001) {//addiu
                int result = ula.decoderULA(function, op2, bit_16_31);
                bankReg.setValeu(op1, result);
                pc++;
            } else if (opcode == 0b001010) {//slti
                int result = ula.decoderULA(function, op1, bit_16_31
                );
                bankReg.setValeu(op2, result);
                pc++;
            } else if (opcode == 0b001011) {//sltiu
                int result = ula.decoderULA(function, op1, bit_16_31);
                bankReg.setValeu(op2, result);
                pc++;
            } else if (opcode == 001100) {//andi
                int result = ula.decoderULA(function, op1, bit_16_31);
                bankReg.setValeu(op2, result);
                pc++;
            } else if (opcode == 0b001101) {//ori
                int result = ula.decoderULA(function, op2, bit_16_31
                );
                bankReg.setValeu(op1, result);
                pc++;
            } else if (opcode == 001110) {//xori
                int result = ula.decoderULA(function, op1, bit_16_31);
                bankReg.setValeu(op2, result);
                pc++;
            } else if (opcode == 0b000001) {//bltz, compara com zero
                int result = ula.decoderULA(function, op1, 0b0);
                if (result == 1) {
                    pc += bit_16_31;
                } else {
                    pc++;
                }
            } else if (opcode == 0b000111) {//bltz, compara com zero
                int result = ula.decoderULA(function, op1, 0b0);
                if (result == 1) {
                    pc += bit_16_31;
                } else {
                    pc++;
                }
            } else if (opcode == 0b001111) {//xori
                int result = ula.decoderULA(function, op2, bit_16_31
                );
                bankReg.setValeu(op2, result);
                pc++;
            } else if (opcode == 0b100000) {// lb
                String result = memory.getBinMemory(op1 + bit_16_31); // multiplica ou soma
                bankReg.setValeu(op2, Integer.parseInt(result));
                pc++;
            } else if (opcode == 0b100001) {// lh
                String result = memory.getBinMemory(op1 + bit_16_31); // multiplica ou soma
                bankReg.setValeu(op2, Integer.parseInt(result));
                pc++;
            } else if (opcode == 0b100011) {// lw
                String result = memory.getBinMemory(op1 + bit_16_31); // multiplica ou soma
                bankReg.setValeu(op2, Integer.parseInt(result));
                pc++;
            } else if (opcode == 0b100101) {// li
                String result = memory.getBinMemory(bit_11_31); // como pegar os 32 bits?
                bankReg.setValeu(op1, Integer.parseInt(result));
                pc++;
            } else if (opcode == 0b101000) {// sh
                int retorno = bankReg.getRegistersValue(op2);
                memory.writeMemory(bit_16_31 + op1, retorno); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
                pc++;
            } else if (opcode == 0b101001) {// sb
                int retorno = bankReg.getRegistersValue(op2);
                memory.writeMemory(bit_16_31 + op1, retorno); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
                pc++;
            } else if (opcode == 0b101011) {// sw
                int retorno = bankReg.getRegistersValue(op2);
                memory.writeMemory(bit_16_31 + op1, retorno); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
                pc++;
            } else if (opcode == 0b000010) {// j                        
                pc = bit_6_31;
            } else if (opcode == 0b000011) {// jal                        
                bankReg.setValeu(31, pc);
                pc = bit_6_31;
            } else if (opcode == 0b011111) {// ext   e ins                    

                if (function == 0b000000) {
                    //implementar
                } else {
                    //implementar
                }
                pc++;
            } else if (opcode == 0b011100) {// mul                   
                int retorno = ula.decoderULA(function, op1, op2);
                bankReg.setValeu(op3, retorno);
                pc++;
            } else if (opcode == 0b011100) {// clz e clo                  

                int retorno = ula.decoderULA(function, op1, op2);
                bankReg.setValeu(op3, retorno);
                pc++;
            } else if (opcode == 0b011100) {// madd e msub                    

                int retorno = ula.decoderULA(function, op1, op2);
                bankReg.setValeu(op3, retorno);
                pc++;
            } else if (opcode == 0b011111) {// SEB    seh wsbh              

                //Sign-Extend Byte
            } else if (opcode == 0b011111) {// SEB    seh wsbh              

                //Sign-Extend Byte
            }

        }
    }

    private void carregaInstrucoes(String fileName) throws IOException {
        memory.mountMemory(fileName);
    }

}
