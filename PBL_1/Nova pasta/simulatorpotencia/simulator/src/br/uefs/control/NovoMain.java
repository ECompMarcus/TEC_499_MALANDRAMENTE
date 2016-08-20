/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.control;

import br.uefs.model.ULA;
import br.uefs.model.UnitControl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcus José
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*String valor = "00000010001100101000000000100000";
            String opcode = "0b" + valor.substring(0, 6);
            String op1 = "0b" + valor.substring(6, 11);
            String op2 = "0b" + valor.substring(11, 16);
            String op3 = "0b" + valor.substring(16, 21);
            String deslocamento = "0b" + valor.substring(21, 26);
            String function = "0b" + valor.substring(26, 32);
            String bit_16_31 = "0b" + valor.substring(16, 32);
            String bit_11_31 = "0b" + valor.substring(11, 32);
            String bit_6_31 = "0b" + valor.substring(6, 32);
            
            System.out.println(bit_16_31);
            int i = Integer.parseInt(bit_16_31);
            System.out.println(String.valueOf(i));
         */

        int a = 0b01010010011011110110010001110011;

        int qnt = 0;

       
        System.out.println(Integer.bitCount(a));
        System.out.println(32 - Integer.bitCount(Integer.reverse(a)));

        //UnitControl uc = new UnitControl();
    }

}
