package br.uefs.model;

import static br.uefs.model.UnitControl.bankReg;
import static br.uefs.model.UnitControl.converterA2;

public class ULA {

    private static int HI = 0;
    private static int LO = 0;

    public ULA() {
    }

    private int ADD(int op1, int op2, int op) {
        if (op == 0) {
            return op1 + op2;
        } else {
            return op1 + (op2 * -1);
        }
    }

    private int SUB(int op1, int op2, int op) {
        if (op == 0) {
            return op1 - op2;
        } else {
            return op1 + (op2 * -1);
        }
    }

    private int AND(int op1, int op2, int op) {
        if (op == 0) {
            return op1 & op2;
        } else {
            return ~(op1 & op2);
        }
    }

    private int OR(int op1, int op2, int op) {
        if (op == 0) {
            return op1 | op2;
        } else {
            return ~(op1 | op2);
        }

    }

    private int XOR(int op1, int op2) {
        return op1 ^ op2;
    }

    private int CountZeroOrUns(int op1, int op) {
        if (op == 0) {

            return 0;
        } else {
            return 1;
        }
    }

    public int AcessoAcumulador(String Opcode, int value) {
        switch (Integer.parseInt(Opcode, 2)) {
            case 1:
                return HI;
            case 2:
                return LO;
            case 3:
                HI = value;
                break;
            case 4:
                LO = value;
                break;
        }
        return 0;
    }

    public int decoderULA(int code, int op1, int op2, int op3) {

        if (code == 0) {
            return UnitControl.bankReg.getRegistersValue(op1) << op2;
        } else if (code == 2) {

        }

        return 0;
    }

    public int decoderULA(int code, int op1, int op2, int op3, int deslocamento) {

        if (code == 4) {
            return UnitControl.bankReg.getRegistersValue(op1) << op2; // pegar valor do reg 1           
        } else if (code == 2) {

        }

        return 0;
    }

    public int decoderULA(int code, int op1, int op2) {

        if (code == 0) {
            return UnitControl.bankReg.getRegistersValue(op1) << op2;
            //SLL
        } else if (code == 2) {
            return UnitControl.bankReg.getRegistersValue(op1) >> op2;
            //SRL  e ROTR
        } else if (code == 3) {
            return UnitControl.bankReg.getRegistersValue(op1) >> op2;
            //SRA 
        } else if (code == 4) {
            return UnitControl.bankReg.getRegistersValue(op1) >> op2;
            //SLLV 
        } else if (code == 6) {
            return UnitControl.bankReg.getRegistersValue(op1) >> op2;
            //SRLV e ROTRV 
        } else if (code == 6) {
            return UnitControl.bankReg.getRegistersValue(op1) >> op2;
            //SRAV 
        }

        return 0;
    }

    public void executarULA(int opcode, int function, int op1, int op2, int op3, int deslocamento, String op1s, String deslocamentos) {

        switch (opcode) {

            case 0b000000:
                switch (function) {
                    case 0b000000:
                        //SLL
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) << deslocamento));
                        UnitControl.pc++;
                        break;
                    case 0b000010:
                        //SRL e ROTR

                        if (op1s.charAt(4) == 0) {
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op2) >> deslocamento));
                        } else {
                            bankReg.setValeu(op3, Integer.rotateRight(bankReg.getRegistersValue(op2), deslocamento));
                        }
                        UnitControl.pc++;
                        break;
                    case 0b000011:
                        //SRA
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op2) >>> deslocamento));
                        UnitControl.pc++;
                        break;
                    case 0b000100:
                        //SLLV
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) << bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        break;
                    case 0b000110:
                        if (deslocamentos.charAt(4) == 0) {//SRLV e ROTRV
                            bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) >> bankReg.getRegistersValue(op2)));
                        } else {
                            bankReg.setValeu(op3, Integer.rotateRight(bankReg.getRegistersValue(op2), bankReg.getRegistersValue(op1)));
                        }
                        UnitControl.pc++;
                        break;
                    case 0b000111:
                        //SRAV

                        bankReg.setValeu(op3, Integer.rotateLeft(bankReg.getRegistersValue(op2), bankReg.getRegistersValue(op1)));
                        UnitControl.pc++;
                        break;
                    case 0b001000:
                        //JR
                        //executa a instrução PC+1 e salta para:
                        UnitControl.pc = bankReg.getRegistersValue(op1);
                        break;
                    case 0b001001:
                        //JALR
                        bankReg.setValeu(op3, UnitControl.pc++); //ou $ra?
                        UnitControl.pc = bankReg.getRegistersValue(op1);
                        break;
                    case 0b001010:
                        //MOVZ
                        // Se o valor em GPR RT é igual a zero, então o conteúdo de GPR RS são colocados em GPR Rd.
                        if (bankReg.getRegistersValue(op2) == 0) {
                            bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                            bankReg.setValeu(op1, 0);
                            UnitControl.pc++;
                        } else {
                            UnitControl.pc++;
                        }
                        break;
                    case 0b001011:
                        //MOVN

                        // Se o valor em GPR RT diferente a zero, então o conteúdo de GPR RS são colocados em GPR Rd.
                        if (bankReg.getRegistersValue(op2) != 0) {
                            bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                            bankReg.setValeu(op1, 0);
                        } else {
                            UnitControl.pc++;
                        }
                        break;
                    case 0b001100:
                        //MOVE

                        bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                        bankReg.setValeu(op1, 0);
                        UnitControl.pc++;
                        break;
                    case 0b010000:
                        //MFHI

                        // o conteúdo de HI são colocados em rs.
                        bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                        //VERIFICAR ONDE VAI FICAR O REGISTER HI
                        UnitControl.pc++;
                        break;
                    case 0b010001: {
                        //MTHI

                        // o conteúdo de RS são colocados em HI.
                        int temp = bankReg.getRegistersValue(op3);
                        //colocar isso no HI
                        //VERIFICAR ONDE VAI FICAR O REGISTER HI
                        UnitControl.pc++;
                        break;
                    }
                    case 0b010010:
                        //MFLO

                        // o conteúdo de HI são colocados em rs.
                        bankReg.setValeu(op3, bankReg.getRegistersValue(op1));
                        //VERIFICAR ONDE VAI FICAR O REGISTER HI
                        UnitControl.pc++;
                        break;
                    case 0b010011: {
                        //MTLO

                        // o conteúdo de RS são colocados em HI.
                        int temp = bankReg.getRegistersValue(op3);
                        //colocar isso no LO
                        //VERIFICAR ONDE VAI FICAR O REGISTER HI
                        UnitControl.pc++;
                        break;
                    }
                    case 0b011000: {
                        //MULT

                        int result = bankReg.getRegistersValue(op1) * bankReg.getRegistersValue(op2);
                        //Description: (HI, LO) <- GPR[rs] x GPR[rt]
                        //VERIFICAR ONDE VAI FICAR O REGISTER HI
                        UnitControl.pc++;
                        break;
                    }
                    case 0b011001: {
                        //MULTU

                        int result = bankReg.getRegistersValue(op1) * bankReg.getRegistersValue(op2);
                        //Description: (HI, LO) <- GPR[rs] x GPR[rt]
                        //VERIFICAR ONDE VAI FICAR O REGISTER HI
                        UnitControl.pc++;
                        break;
                    }
                    case 0b011010: {
                        //DIV

                        int result = bankReg.getRegistersValue(op1) / bankReg.getRegistersValue(op2);
                        //Description: (HI, LO) <- GPR[rs] / GPR[rt]
                        //VERIFICAR ONDE VAI FICAR O REGISTER HI
                        UnitControl.pc++;
                        break;
                    }
                    case 0b011011: {
                        //DIVU

                        int result = bankReg.getRegistersValue(op1) / bankReg.getRegistersValue(op2);
                        //Description: (HI, LO) <- GPR[rs] / GPR[rt]
                        //VERIFICAR ONDE VAI FICAR O REGISTER HI
                        UnitControl.pc++;
                        break;
                    }
                    case 0b100000:
                        //ADD  E ADDU
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) + bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        break;
                    case 0b100001:
                        //ADD  E ADDU
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) + bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        break;
                    case 0b100010:
                        //SUB SUBU
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) - bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        break;
                    case 0b100011:
                        //SUB SUBU
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) - bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        break;
                    case 0b100100:
                        //AND
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) & bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        break;
                    case 0b100101:
                        //OR
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) | bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        break;
                    case 0b100110:
                        //XOR
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) ^ bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        break;
                    case 0b100111:
                        //NOR
                        bankReg.setValeu(op3, (bankReg.getRegistersValue(op1) ^ bankReg.getRegistersValue(op2)));
                        UnitControl.pc++;
                        //VERIFICAR IMPLEMENTAÇÃO
                        break;
                    case 0b101010:
                        //SLT   SLTU

                        if (bankReg.getRegistersValue(op1) < bankReg.getRegistersValue(op2)) {
                            bankReg.setValeu(op3, 1);
                        } else {
                            bankReg.setValeu(op3, 0);
                        }
                        UnitControl.pc++;
                        break;
                    case 101011:
                        //SLT   SLTU

                        if (bankReg.getRegistersValue(op1) < bankReg.getRegistersValue(op2)) {
                            bankReg.setValeu(op3, 1);
                        } else {
                            bankReg.setValeu(op3, 0);
                        }
                        UnitControl.pc++;
                        break;
                    default:
                        break;
                }
                break;
            case 0b01:
                break;
        }
    }

    public int compara(int opcode, int a, int b) {
        if (opcode == 0b001011) {
            if (a < b) {
                return 0;
            } else if (a == b) {
                return 1;
            }
            return 0;
        }else if (opcode == 0b001100){
            return (a & b);
        }else if (opcode == 0b001101){
            return (a | b);
        }else if (opcode == 0b011100){
            return (32 - Integer.bitCount(Integer.reverse(a)));
        }else if (opcode == 0b100000){
            return (Integer.bitCount(a));
        }
         
        return 10;
    }
}
