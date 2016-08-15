package br.uefs.model;

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
}
