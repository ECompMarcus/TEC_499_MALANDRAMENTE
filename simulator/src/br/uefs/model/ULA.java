package br.uefs.model;

public class ULA {

    private static int HI = 0;
    private static int LO = 0;

    public ULA() {
    }

    public int ADD(int op1, int op2, int op) {
        if (op == 0) {
            return op1 + op2;
        } else {
            return op1 + (op2 * -1);
        }
    }

    public int AND(int op1, int op2, int op) {
        if (op == 0) {
            return op1 & op2;
        } else {
            return ~(op1 & op2);
        }
    }

    public int OR(int op1, int op2, int op) {
        if (op == 0) {
            return op1 | op2;
        } else {
            return ~(op1 | op2);
        }

    }

    public int XOR(int op1, int op2) {
        return op1 ^ op2;
    }

    public int CountZeroOrUns(int op1, int op) {
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

    public void decoderULA(int code) {
        // ACUMULADOR
        if (code == 0b010000 || code == 0b010010 || code == 0b010001 || code == 0b0100110) {

        } // ADDI,ADDIU,ADD,ADDU,MOVE
        else if (code == 0b001000 || code == 0b001001 || code == 0b100000 || code == 0b100001 || code == 0b001010) {

        } //SUB, SUBU
        else if (code == 0b100010 || code == 0b100011) {

        } // AND , ANDI
        else if (code == 0b100100 || code == 0b001100) {

        } // OR , ORI
        else if (code == 0b100101 || code == 0b001100) {

        } // NOR	
        else if (code == 0b100111) {

        } // XOR , XORI
        else if (code == 0b100110 || code == 0b001110) {

        } //BLTZ BGTZ
        else if (code == 0b000001 || code == 0b000111) {

        } // LUI
        else if (code == 0b001111) {

        } //SLTI, SLTIU
        else if (code == 0b001010 || code == 0b001011) {

        } // BEQ BNE
        else if (code == 0b000100 || code == 0b000101) {

        } // SLL, SLLV
        else if (code == 0b000000 || code == 0b000100) {

        } // SRA , SRAV
        else if (code == 0b000011 || code == 0b000111) {

        } //SRL, SRLV
        else if (code == 0b000110 || code == 0b000010) {

        } // STL, STLU
        else if (code == 0b101010 || code == 0b101011) {

        } // MOVZ, MOVN
        else if (code == 0b001011 || code == 0b001010) {

        } // JR
        else if (code == 0b001000) {

        } //multu / mult
        else if (code == 0b011001 || code == 0b011000) {

        } // div divu
        else if (code == 0b011010 || code == 0b011011) {

        } //
        else if (code == 0b001001) {

        } // JAL
        else if (code == 0b000011) {

        } //LOAD BYTE
        else if (code == 0b100000) {

        }

    }

}
