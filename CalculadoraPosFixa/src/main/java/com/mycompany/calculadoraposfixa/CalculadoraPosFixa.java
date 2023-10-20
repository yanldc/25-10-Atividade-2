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
        String expressao;
        double resultado;
        
        String opt = JOptionPane.showInputDialog("Escolha a calculadora: \n 1- Infixa \n 2-Pré-fixa \n 3-Pós-fixa");
        
        switch(opt){
            case "1": 
                     expressao = JOptionPane.showInputDialog("Digite a expressão: ");
                     resultado = calc.calculadoraInfixa(expressao);
                     System.out.println("Resultado da infixa: " + resultado);
                       break;
                       
            case "2":
                    expressao = JOptionPane.showInputDialog("Digite a expressão: ");
                    resultado = calc.calculadoraPreFixa(expressao);
                    System.out.println("Resultado da expressão pré-fixa: " + resultado);               
                       break;
                       
            case "3": 
                    expressao = JOptionPane.showInputDialog("Digite a expressão: ");
                    resultado = calc.calculadoraPosFixa(expressao);
                    System.out.println("Resultado da expressão pós-fixa: " + resultado);
                       break;       
                       
            default: System.out.println("Valor não econtrado");   
        }
        
    }
    
    
    
}

