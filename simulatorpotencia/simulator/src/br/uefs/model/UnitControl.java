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

    private int pc = 0b0;
    private final MEMORY memory;
    public static DB_REG bankReg;
    private final ULA ula;

    //possui instancia das demais classes
    public UnitControl() throws IOException {

        memory = new MEMORY();
        ula = new ULA();
        bankReg = new DB_REG();
        decode();

    }

    private void decode() {

        while (pc < memory.getInstruNumber()) {

            String instruction = memory.getBinMemory(pc);

            String opcodes = instruction.substring(0, 6);
            int opcode = Integer.parseUnsignedInt(opcodes, 2);

            String op1s = instruction.substring(6, 11);
            int op1 = Integer.parseUnsignedInt(op1s, 2);

            String op2s = instruction.substring(11, 16);
            int op2 = Integer.parseUnsignedInt(op2s, 2);

            String op3s = instruction.substring(16, 21);
            int op3 = Integer.parseUnsignedInt(op3s, 2);

            String deslocamentos = instruction.substring(21, 26);
            int deslocamento = Integer.parseUnsignedInt(deslocamentos, 2);

            String functions = instruction.substring(26, 32);
            int function = Integer.parseUnsignedInt(functions, 2);

            String bit_16_31s = instruction.substring(16, 32);
            int bit_16_31 = Integer.parseUnsignedInt(bit_16_31s, 2);

            String bit_11_31s = instruction.substring(11, 32);
            int bit_11_31 = Integer.parseUnsignedInt(bit_11_31s, 2);

            String bit_6_31s = instruction.substring(6, 32);
            int bit_6_31 = Integer.parseUnsignedInt(bit_6_31s, 2);

            // R COM 3 OPERANDO
            // verificar demais instruções
            switch (opcode) {
                case 0b000000:
                    switch (function) {
                        case 0b000000:
                            //SLL
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) << deslocamento));
                            pc++;
                            break;
                        case 0b000010:
                            //SRL e ROTR

                            if (op1s.charAt(4) == 0) {
                                bankReg.setValeu(op3, (bankReg.getRegistersValue(op2) >> deslocamento));
                            } else {
                                bankReg.setValeu(op3, Integer.rotateRight(bankReg.getRegistersValue(op2), deslocamento));
                            }
                            pc++;
                            break;
                        case 0b000011:
                            //SRA
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op2) >>> deslocamento));
                            pc++;
                            break;
                        case 0b000100:
                            //SLLV
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) << bankReg.getRegistersValue(op2)));
                            pc++;
                            break;
                        case 0b000110:
                            if (deslocamentos.charAt(4) == 0) {//SRLV e ROTRV
                                bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) >> bankReg.getRegistersValue(op2)));
                            } else {
                                bankReg.setValeu(op3, Integer.rotateRight(bankReg.getRegistersValue(op2), bankReg.getRegistersValue(op1)));
                            }
                            pc++;
                            break;
                        case 0b000111:
                            //SRAV

                            bankReg.setValeu(op3, Integer.rotateLeft(bankReg.getRegistersValue(op2), bankReg.getRegistersValue(op1)));
                            pc++;
                            break;
                        case 0b001000:
                            //JR
                            //executa a instrução PC+1 e salta para:
                            pc += bankReg.getRegistersValue(op1);
                            break;
                        case 0b001001:
                            //JALR

                            bankReg.setValeu(op3, pc++); //ou $ra?
                            pc = bankReg.getRegistersValue(op1);
                            break;
                        case 0b001010:
                            //MOVZ
                            // Se o valor em GPR RT é igual a zero, então o conteúdo de GPR RS são colocados em GPR Rd.
                            if (bankReg.getRegistersValue(op2) == 0) {
                                bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                                bankReg.setValeu(op1, 0);
                                pc++;
                            } else {
                                pc++;
                            }
                            break;
                        case 0b001011:
                            //MOVN

                            // Se o valor em GPR RT diferente a zero, então o conteúdo de GPR RS são colocados em GPR Rd.
                            if (bankReg.getRegistersValue(op2) != 0) {
                                bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                                bankReg.setValeu(op1, 0);
                            } else {
                                pc++;
                            }
                            break;
                        case 0b001100:
                            //MOVE

                            bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                            bankReg.setValeu(op1, 0);
                            pc++;
                            break;
                        case 0b010000:
                            //MFHI

                            // o conteúdo de HI são colocados em rs.
                            bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                            //VERIFICAR ONDE VAI FICAR O REGISTER HI
                            pc++;
                            break;
                        case 0b010001: {
                            //MTHI

                            // o conteúdo de RS são colocados em HI.
                            int temp = bankReg.getRegistersValue(op3);
                            //colocar isso no HI
                            //VERIFICAR ONDE VAI FICAR O REGISTER HI
                            pc++;
                            break;
                        }
                        case 0b010010:
                            //MFLO

                            // o conteúdo de HI são colocados em rs.
                            bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                            //VERIFICAR ONDE VAI FICAR O REGISTER HI
                            pc++;
                            break;
                        case 0b010011: {
                            //MTLO

                            // o conteúdo de RS são colocados em HI.
                            int temp = bankReg.getRegistersValue(op3);
                            //colocar isso no LO
                            //VERIFICAR ONDE VAI FICAR O REGISTER HI
                            pc++;
                            break;
                        }
                        case 0b011000: {
                            //MULT

                            int result = bankReg.getRegistersValue(op1) * bankReg.getRegistersValue(op2);
                            //Description: (HI, LO) <- GPR[rs] x GPR[rt]
                            //VERIFICAR ONDE VAI FICAR O REGISTER HI
                            pc++;
                            break;
                        }
                        case 0b011001: {
                            //MULTU

                            int result = bankReg.getRegistersValue(op1) * bankReg.getRegistersValue(op2);
                            //Description: (HI, LO) <- GPR[rs] x GPR[rt]
                            //VERIFICAR ONDE VAI FICAR O REGISTER HI
                            pc++;
                            break;
                        }
                        case 0b011010: {
                            //DIV

                            int result = bankReg.getRegistersValue(op1) / bankReg.getRegistersValue(op2);
                            //Description: (HI, LO) <- GPR[rs] / GPR[rt]
                            //VERIFICAR ONDE VAI FICAR O REGISTER HI
                            pc++;
                            break;
                        }
                        case 0b011011: {
                            //DIVU

                            int result = bankReg.getRegistersValue(op1) / bankReg.getRegistersValue(op2);
                            //Description: (HI, LO) <- GPR[rs] / GPR[rt]
                            //VERIFICAR ONDE VAI FICAR O REGISTER HI
                            pc++;
                            break;
                        }
                        case 0b100000:
                        case 0b100001:
                            //ADD  E ADDU
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) + bankReg.getRegistersValue(op2)));
                            pc++;
                            break;
                        case 0b100010:
                        case 0b100011:
                            //SUB SUBU
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) - bankReg.getRegistersValue(op2)));
                            pc++;
                            break;
                        case 0b100100:
                            //AND
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) & bankReg.getRegistersValue(op2)));
                            pc++;
                            break;
                        case 0b100101:
                            //OR
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) | bankReg.getRegistersValue(op2)));
                            pc++;
                            break;
                        case 0b100110:
                            //XOR
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) ^ bankReg.getRegistersValue(op2)));
                            pc++;
                            break;
                        case 0b100111:
                            //NOR
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) ^ bankReg.getRegistersValue(op2)));
                            pc++;
                            //VERIFICAR IMPLEMENTAÇÃO
                            break;
                        case 0b101010:
                        case 101011:
                            //SLT   SLTU

                            if (bankReg.getRegistersValue(op1) < bankReg.getRegistersValue(op2)) {
                                bankReg.setValeu(op3, 1);
                            } else {
                                bankReg.setValeu(op3, 0);
                            }
                            pc++;
                            break;
                        default:
                            break;
                    }
                    break;
                case 0b000001:
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

                    bankReg.setValeu(31, (pc+1)); // $ra?
                    pc = converterA2(bit_6_31);
                    break;
                case 0b000100:
                    //beq
                    if (bankReg.getRegistersValue(op1) == bankReg.getRegistersValue(op2)) {
                        pc += converterA2(bit_16_31);
                    } else {
                        pc++;
                    }
                    break;
                case 0b000101:
                    //bne
                    if (bankReg.getRegistersValue(op1) != bankReg.getRegistersValue(op2)) {                                                
                        pc += converterA2(bit_16_31); //mudar para endere�o relativo                        
                    } else {
                        pc++;
                    }
                    break;
                case 0b000111:
                    //bgtz
                    if (bankReg.getRegistersValue(op1) > 0) {
                        pc += converterA2(bit_16_31);
                    } else {
                        pc++;
                    }
                    break;
                case 0b001000:
                    //addi
                    bankReg.setValeu(op2, (bankReg.getRegistersValue(op1) + converterA2((bit_16_31))));
                    pc++;
                    break;
                case 0b001001: {
                    //addiu VERIFICAR SEM SINAL
                    int result = ula.decoderULA(function, op2, (bit_16_31));
                    bankReg.setValeu(op1, result);
                    pc++;
                    break;
                }
                case 0b001010:
                    //slti

                    if (bankReg.getRegistersValue(op1) < converterA2(bit_16_31)) {
                        bankReg.setValeu(op2, 1);
                    } else {
                        bankReg.setValeu(op2, 1);
                    }
                    pc++;
                    break;
                case 0b001011:
                    //sltiu
                    if (bankReg.getRegistersValue(op1) < converterA2(bit_16_31)) {
                        bankReg.setValeu(op2, 1);
                    } else {
                        bankReg.setValeu(op2, 0);
                    }
                    pc++;
                    break;
                case 001100:
                    //andi VERIFICAR LOOP INFINITO
                    bankReg.setValeu(op2, ((bankReg.getRegistersValue(op1) & converterA2(bit_16_31))));
                    pc++;
                    break;
                case 0b001101:
                    //ori
                    bankReg.setValeu(op2, ((bankReg.getRegistersValue(op1) | converterA2(bit_16_31))));
                    pc++;
                    break;
                case 0b001110:
                    //xori
                    bankReg.setValeu(op2, ((bankReg.getRegistersValue(op1) ^ converterA2(bit_16_31))));
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
                            bankReg.setValeu(op3, (32 - Integer.bitCount(Integer.reverse(bankReg.getRegistersValue(op1)))));
                            pc++;
                            break;
                        case 0b100000:
                            //CLZO
                            bankReg.setValeu(op3, (Integer.bitCount((bankReg.getRegistersValue(op1)))));
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
                            //INS FAZER
                            bankReg.setValeu(op3, (Integer.bitCount((bankReg.getRegistersValue(op1)))));
                            pc++;
                            break;
                        case 0b011111:
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
                    String result = memory.getBinMemory(op1 + converterA2(bit_16_31)); // multiplica ou soma
                    bankReg.setValeu(op2, Integer.parseInt(result));
                    pc++;
                    break;
                }
                case 0b100101: {
                    // li
                    String result = memory.getBinMemory(bit_11_31); // como pegar os 32 bits?
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
    }

    private int converterA2(int imm) { //colocar depois o tratamento para negativos
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
