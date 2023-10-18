/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadoraposfixa;
import javax.swing.JOptionPane;
/**
 *
 * @author Pc-lab
 */

public class CalculadoraPosFixa {

    public static void main(String[] args) {
        Calc calc = new Calc();
        
        String expressao = JOptionPane.showInputDialog("Digite a expressão: ");
        double resultado = calc.calcularPosFixa(expressao);
        System.out.println("Resultado da expressão pós-fixa: " + resultado);
    }
}

