package br.uefs.model;

import br.uefs.debuger.Debuger;

public class UnidadeControle {

    public Registradores banco_registradores = new Registradores();
    public UlaOP operador_ula = new UlaOP();
    public Memoria memoria = new Memoria();

    int size_sinais = 9;
    int size_op_ula = 8;
    int[] sinais = new int[size_sinais];
    int[] UlaOP = new int[size_op_ula];

    public void decoder(String decoder) {
        Debuger.sysPrinfT(decoder);

        String token_0_5, token_6_10, token_11_15, token_16_20, token_21_25, token_26_31, token_16_31, token_11_31;
        int int_token_0_5;

        token_0_5 = decoder.substring(0, 6);
        int_token_0_5 = Integer.parseUnsignedInt(token_0_5, 2);
        token_6_10 = decoder.substring(6, 11);	// int_token_6_10 = Integer.parseInt(token_6_10,2);
        token_11_15 = decoder.substring(11, 16);	// int_token_11_15 = Integer.parseInt(token_11_15,2);
        token_16_20 = decoder.substring(16, 21);	// int_token_16_20 = Integer.parseInt(token_16_20,2);
        token_21_25 = decoder.substring(21, 26);	// int_token_21_25 = Integer.parseInt(token_21_25,2);
        token_26_31 = decoder.substring(26, 32);	// int_token_26_31 = Integer.parseInt(token_26_31,2);
        token_16_31 = decoder.substring(16, 32);
        token_11_31 = decoder.substring(11, 31);

        int funcao = Integer.parseUnsignedInt(token_26_31, 2),
                RA = Integer.parseUnsignedInt(token_6_10, 2),
                RB = Integer.parseUnsignedInt(token_11_15, 2),
                RD = Integer.parseUnsignedInt(token_16_20, 2),
                SFA = Integer.parseUnsignedInt(token_21_25, 2);

        if (int_token_0_5 == 0b000000) {

            // PARA MULTIPLICA��O E DIVIS�O QUE USA O HI e o LO
            if ((funcao == 0b011010 || funcao == 0b011011 || funcao == 0b011000 || funcao == 0b011001)) {

                int[] resultado = operador_ula.operacao_accumulador(int_token_0_5, funcao, banco_registradores.getValor(RA), banco_registradores.getValor(RB));
                banco_registradores.setHi(resultado[0]);
                banco_registradores.setLo(resultado[1]);
            } //MFHI
            else if (funcao == 0b010000) {
                banco_registradores.setValor(RD, banco_registradores.getHi());
            } //MFLO
            else if (funcao == 0b010010) {
                banco_registradores.setValor(RD, banco_registradores.getLo());
            } //MTHI
            else if (funcao == 0b010001) {
                banco_registradores.setHi(banco_registradores.getValor(RD));
            } //MTLO
            else if (funcao == 0b010011) {
                banco_registradores.setLo(banco_registradores.getValor(RD));
            } else if ((funcao == 0b000100 || funcao == 0b000111 || funcao == 0b000110)) {

            } else if ((funcao == 0b000000 || funcao == 0b000011 || funcao == 0b000010)) {

            } // JR
            else if (funcao == 0b001001 && RD == 0b00000) {

            } // JALR
            else if (funcao == 0b001001 && RD != 0b00000) {

            }

        } // JAL
        else if (int_token_0_5 == 0b000011) {

        } // J
        else if (int_token_0_5 == 0b000010) {
            int ENDERECO = Integer.parseUnsignedInt(decoder.substring(6, 32), 2);

        } else if (int_token_0_5 == 0b000001 && (RB == 0b00001 || RB == 0b00000)) {
            //operador_ula.operacao_salto2();
        } // FUNCOES COM  ACUMULADOR
        else if (int_token_0_5 == 0b011100) {

            if ((funcao == 0b000000 || funcao == 0b000001)) {
                int[] resultado = operador_ula.operacao_accumulador(int_token_0_5, funcao, banco_registradores.getValor(RA), banco_registradores.getValor(RB));
                banco_registradores.setHi(banco_registradores.getHi() + resultado[0]);
                banco_registradores.setLo(banco_registradores.getLo() + resultado[1]);
            }
        } // FUNCOES COM  ACUMULADOR
        else if (int_token_0_5 == 0b011100) {

            if ((funcao == 0b000100 || funcao == 0b000101)) {
                int[] resultado = operador_ula.operacao_accumulador(int_token_0_5, funcao, banco_registradores.getValor(RA), banco_registradores.getValor(RB));
                banco_registradores.setHi(banco_registradores.getHi() - resultado[0]);
                banco_registradores.setLo(banco_registradores.getLo() - resultado[1]);
            }
        } // XORI					SLTI
        else if (int_token_0_5 == 0b001110 || int_token_0_5 == 0b001010
                //SLTIU						ADDIU
                || int_token_0_5 == 0b001011 || int_token_0_5 == 0b001001
                // ANDI						ORI
                || int_token_0_5 == 0b001100 || int_token_0_5 == 0b001101) {
            RA = Integer.parseInt(token_6_10, 2);
            RB = Integer.parseInt(token_11_15, 2);

            int OPERANDO_CONST = Integer.parseUnsignedInt(decoder.substring(16, 32), 2);

            int resultado = operador_ula.operation_I(int_token_0_5, RA, OPERANDO_CONST);
            banco_registradores.setValor(RB, resultado);
        } else if (int_token_0_5 == 0b100000) {
            // lb
            String result = memoria.ler_memoria_32B(Integer.parseInt(token_6_10) + Integer.parseInt(token_16_31)); // multiplica ou soma
            banco_registradores.setValor(RB, Integer.parseInt(result.substring(0, 8)));
            banco_registradores.setPc((banco_registradores.getPc() + 1));
        } else if (int_token_0_5 == 0b100001) {
            // lh
            String result = memoria.ler_memoria_32B(Integer.parseInt(token_6_10) + Integer.parseInt(token_16_31)); // multiplica ou soma
            banco_registradores.setValor(RB, Integer.parseInt(result.substring(0, 16)));
            banco_registradores.setPc((banco_registradores.getPc() + 1));
        } else if (int_token_0_5 == 0b100011) {
            // lw

            String result = memoria.ler_memoria_32B(banco_registradores.getValor(RD) + Integer.parseInt(token_16_31)); // multiplica ou soma
            banco_registradores.setValor(RB, Integer.parseInt(result.substring(0, 16), 2));
            banco_registradores.setPc((banco_registradores.getPc() + 1));

        } else if (int_token_0_5 == 0b100101) {
            // li
            //bankReg.setValeu(op1, converterA2((bit_16_31)));
            //bankReg.setValeu(op1, ((bankReg.getRegistersValue(op1) | converterA2(bit_16_31))));
            String result = memoria.ler_memoria_32B(Integer.parseInt(token_11_31)); // como pegar os 32 bits?

            //String result = memory.getBinMemory(op1);
            banco_registradores.setValor(RA, Integer.parseInt(result));
            banco_registradores.setPc((banco_registradores.getPc() + 1));
        } else if (int_token_0_5 == 0b101000) {
            // sh
            int retorno = banco_registradores.getValor(RB);
            memoria.escrever_memoria_32B(Integer.parseInt(token_16_31) + RA, String.valueOf(retorno).substring(0, 16)); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
            banco_registradores.setPc((banco_registradores.getPc() + 1));
        } else if (int_token_0_5 == 0b101001) {
            // sb
            int retorno = banco_registradores.getValor(RB);
            memoria.escrever_memoria_32B((Integer.parseInt(token_16_31)) + RA, (String.valueOf(retorno).substring(0, 8))); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
            banco_registradores.setPc((banco_registradores.getPc() + 1));
        } else if (int_token_0_5 == 0b101011) {
            // sw

            int retorno = banco_registradores.getValor(RB);
            memoria.escrever_memoria_32B((Integer.parseInt(token_16_31)) + RA, (String.valueOf(retorno))); // escreve o byte da parte mais baixa do registrador dado para a posição da RAM                
            banco_registradores.setPc((banco_registradores.getPc() + 1));

        } else { // TIPO I
            RA = Integer.parseInt(token_6_10, 2);
            RB = Integer.parseInt(token_11_15, 2);
            int OPERANDO_CONST = Integer.parseUnsignedInt(decoder.substring(16, 32), 2);
        }

    }

    private int complementoA2(int constante) {
        int resultado;

        resultado = ~constante;
        resultado++;

        String sub = Integer.toBinaryString(resultado);
        sub = sub.substring(16);

        resultado = Integer.parseInt(sub, 2);
        resultado = ~resultado;
        resultado++;
        return resultado;
    }

}
