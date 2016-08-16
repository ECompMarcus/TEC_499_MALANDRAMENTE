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

    public static int pc = 0b0;
    private final MEMORY memory;
    public static DB_REG bankReg;
    private final ULA ula;
    private static String instruction, opcodes, op1s, op2s, op3s, deslocamentos, functions, bit_6_31s, bit_11_31s, bit_16_31s;
    private static int opcode, op1, op2, op3, deslocamento, function, bit_6_31, bit_11_31, bit_16_31;

    //possui instancia das demais classes
    public UnitControl() throws IOException {

        memory = new MEMORY();
        ula = new ULA();
        bankReg = new DB_REG();
        bankReg.setValeu(28, memory.getInstruNumber() + 1);
        bankReg.setValeu(30, MEMORY.internalMemorySize + 1);
        bankReg.setValeu(29, MEMORY.internalMemorySize + 1);

        decode();

        for (int i = 0; i < 32; i++) {
            System.err.println("Registrador: " + i + " - " + bankReg.getRegistersValue(i));

        }

        for (int i = bankReg.getRegistersValue(28); i < bankReg.getRegistersValue(30); i++) {
            System.err.println((memory.getInternalMemory()[i]));

        }

    }

    private void decode() {

        while (pc < memory.getInstruNumber()) {
            instruction = memory.getBinMemory(pc);

            opcodes = instruction.substring(0, 6);
            opcode = Integer.parseUnsignedInt(opcodes, 2);

            op1s = instruction.substring(6, 11);
            op1 = Integer.parseUnsignedInt(op1s, 2);

            op2s = instruction.substring(11, 16);
            op2 = Integer.parseUnsignedInt(op2s, 2);

            op3s = instruction.substring(16, 21);
            op3 = Integer.parseUnsignedInt(op3s, 2);

            deslocamentos = instruction.substring(21, 26);
            deslocamento = Integer.parseUnsignedInt(deslocamentos, 2);

            functions = instruction.substring(26, 32);
            function = Integer.parseUnsignedInt(functions, 2);

            bit_16_31s = instruction.substring(16, 32);
            bit_16_31 = Integer.parseUnsignedInt(bit_16_31s, 2);

            bit_11_31s = instruction.substring(11, 32);
            bit_11_31 = Integer.parseUnsignedInt(bit_11_31s, 2);

            bit_6_31s = instruction.substring(6, 32);
            bit_6_31 = Integer.parseUnsignedInt(bit_6_31s, 2);
            executa();
        }
    }

    private void executa() {
        switch (opcode) {
            case 0b000000:
                ula.executarULA(opcode, function, op1, op2, op3, deslocamento, op1s, deslocamentos);
                break;
            case 0b000001: //ula
                //bltz, compara com zero
                if (bankReg.getRegistersValue(op1) < 0) {
                    pc += bit_16_31;
                } else {
                    pc++;
                }
                break;
            case 0b000010:
                // j
                pc = converterA2(bit_6_31);
                break;
            case 0b000011:
                //JALR
                bankReg.setValeu(31, (pc + 1)); // $ra?
                pc = converterA2(bit_6_31);
                break;
            case 0b000100:
                //beq
                if (bankReg.getRegistersValue(op1) == bankReg.getRegistersValue(op2)) { //ula
                    pc += converterA2(bit_16_31);
                } else {
                    pc++;
                }
                break;
            case 0b000101:
                //bne
                if (bankReg.getRegistersValue(op1) != bankReg.getRegistersValue(op2)) { //ula
                    pc += converterA2(bit_16_31); //mudar para endere�o relativo                        
                } else {
                    pc++;
                }
                break;
            case 0b000111:
                //bgtz
                if (bankReg.getRegistersValue(op1) > 0) { //ula
                    pc += converterA2(bit_16_31);
                } else {
                    pc++;
                }
                break;
            case 0b001000:
                //addi
                bankReg.setValeu(op2, (bankReg.getRegistersValue(op1) + converterA2((bit_16_31)))); //ula
                pc++;
                break;
            case 0b001001: {
                //addiu VERIFICAR SEM SINAL
                int result = ula.decoderULA(function, op2, (bit_16_31)); //ula               
                bankReg.setValeu(op1, result);
                pc++;
                break;
            }
            case 0b001010:
                //slti

                if (bankReg.getRegistersValue(op1) < converterA2(bit_16_31)) { //ula
                    bankReg.setValeu(op2, 1);
                } else {
                    bankReg.setValeu(op2, 1);
                }
                pc++;
                break;
            case 0b001011:
                //sltiu

                if (ula.compara(opcode, bankReg.getRegistersValue(op1), converterA2(bit_16_31)) == 0) { //ula
                    bankReg.setValeu(op2, 1);
                } else {
                    bankReg.setValeu(op2, 0);
                }
                pc++;
                break;
            case 001100:
                //andi VERIFICAR LOOP INFINITO
                bankReg.setValeu(op2, (ula.compara(opcode, bankReg.getRegistersValue(op1), converterA2(bit_16_31)))); //ula
                pc++;
                break;
            case 0b001101:
                //ori
                bankReg.setValeu(op2, ((bankReg.getRegistersValue(op1) | converterA2(bit_16_31)))); //ula
                pc++;
                break;
            case 0b001110:
                //xori
                bankReg.setValeu(op2, ula.compara(opcode, bankReg.getRegistersValue(op1), converterA2(bit_16_31))); //ula
                pc++;
                break;
            case 0b001111:
                //LUI
                bankReg.setValeu(op2, converterA2((bit_16_31)));
                pc++;
                break;
            case 0b011100:
                switch (function) {
                    case 0b000000:
                        //MADD
                        //com sinal
                        pc++;
                        break;
                    case 0b000001:
                        //MADDI
                        //sem sinal
                        pc++;
                        break;
                    case 0b000010:
                        //MUL
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) * bankReg.getRegistersValue(op2)));//sem sinal
                        pc++;
                        break;
                    case 0b000100:
                        //MSUB
                        //multiplicar (op1 * op2)-(HI concatenado LO) e colocar novamente em HI e LO
                        pc++;
                        break;
                    case 0b000101:
                        //MSUBU
                        //multiplicar (op1 * op2)-(HI concatenado LO)e colocar novamente em HI e LO
                        pc++;
                        break;
                    case 0b011100:
                        //CLZ
                        bankReg.setValeu(op3, ula.compara(opcode, bankReg.getRegistersValue(op1), 0)); //ula
                        pc++;
                        break;
                    case 0b100000:
                        //CLZO
                        bankReg.setValeu(op3, ula.compara(opcode, bankReg.getRegistersValue(op1), 0)); //ula
                        pc++;
                        break;
                    default:
                        break;
                }
                break;
            case 0b011111:
                switch (function) {
                    case 0b000000:
                        //EXT
                        if (0 >= deslocamento && deslocamento <= 32) {
                            if (0 >= (bankReg.getRegistersValue(op3) + deslocamento) && (bankReg.getRegistersValue(op3) + deslocamento) <= 32) {
                                int op = bankReg.getRegistersValue(op1);
                                String convert = Integer.toString(op);
                                String novo = convert.substring(op3, deslocamento);
                                bankReg.setValeu(op2, Integer.parseInt(novo, 2));
                            }
                        }
                        pc++;
                        break;
                    case 0b000100:
                        //INS 
                        bankReg.setValeu(op3, (Integer.bitCount((bankReg.getRegistersValue(op1)))));
                        pc++;
                        break;
                    case 0b011111: //ula
                        // SEB SEH WSBH

                        int op = bankReg.getRegistersValue(op2);
                        String convert = Integer.toString(op);
                        String novo = convert.substring(0, deslocamento);
                        for (int i = 0; i < (deslocamento - 32); i++) {
                            novo += "0";
                        }
                        //completar o restante dos bits LSB
                        bankReg.setValeu(op3, Integer.parseInt(novo, 2));
                        pc++;
                        break;
                    default:
                        break;
                }
                break;
            case 0b100000: {
                // lb
                String result = memory.getBinMemory(op1 + converterA2(bit_16_31)); // multiplica ou soma
                bankReg.setValeu(op2, Integer.parseInt(result.substring(0, 8)));
                pc++;
                break;
            }
            case 0b100001: {
                // lh
                String result = memory.getBinMemory(op1 + converterA2(bit_16_31)); // multiplica ou soma
                bankReg.setValeu(op2, Integer.parseInt(result.substring(0, 16)));
                pc++;
                break;
            }
            case 0b100011: {
                // lw
                String result = memory.getBinMemory(bankReg.getRegistersValue(op1) + converterA2(bit_16_31));
                //System.out.println(" "+ result);
                bankReg.setValeu(op2, Integer.parseUnsignedInt(result, 2));
                pc++;
                break;
            }

            case 0b100101: {
                // li
                //bankReg.setValeu(op1, converterA2((bit_16_31)));
                //bankReg.setValeu(op1, ((bankReg.getRegistersValue(op1) | converterA2(bit_16_31))));
                String result = memory.getBinMemory(converterA2(bit_11_31)); // como pegar os 32 bits?

                //String result = memory.getBinMemory(op1);
                bankReg.setValeu(op1, Integer.parseInt(result));
                pc++;
                break;
            }
            case 0b101000: {
                // sh
                int retorno = bankReg.getRegistersValue(op2);
                memory.writeMemory(converterA2(bit_16_31) + op1, Integer.parseInt(String.valueOf(retorno).substring(0, 16))); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
                pc++;
                break;
            }
            case 0b101001: {
                // sb
                int retorno = bankReg.getRegistersValue(op2);
                memory.writeMemory(converterA2(bit_16_31) + op1, Integer.parseInt(String.valueOf(retorno).substring(0, 8))); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
                pc++;
                break;
            }
            case 0b101011: {
                // sw
                int retorno = bankReg.getRegistersValue(op2);
                memory.writeMemory(converterA2(bit_16_31) + op1, retorno); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
                pc++;
                break;
            }
            default:
                break;
        }

    }

    public static int converterA2(int imm) { //fazer referencia
        int result;

        if (imm > 32767) {
            result = ~imm;
            result++;

            String j = Integer.toBinaryString(result);
            j = j.substring(16);

            result = Integer.parseInt(j, 2);
            result = ~result + 1;
            return result;
        }
        return imm;
    }

    private void carregaInstrucoes(String fileName) throws IOException {
        memory.mountMemory(fileName);
    }

}
