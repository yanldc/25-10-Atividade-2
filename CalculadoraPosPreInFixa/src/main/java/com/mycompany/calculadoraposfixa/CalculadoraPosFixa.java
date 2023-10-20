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
        String expressaoPosFixa;
        String expressaoPreFixa;
        String expressaoInfixa;
        
        String opt = JOptionPane.showInputDialog("Escolha a calculadora: \n 1= Infixa \n 2= Pré-fixa \n 3= Pós-fixa");
        
        switch(opt){
            case "1": 
                    expressao = JOptionPane.showInputDialog("Digite a expressão infixa: ");
                    resultado= calc.calculadoraInfixa(expressao);
    
                    expressaoPosFixa = calc.converterParaPosFixa(expressao);
                    expressaoPreFixa = calc.converterParaPreFixa(expressao);

                    System.out.println("Expressão digitada (infixa): " + expressao);
                    System.out.println("Expressão pós-fixa: " + expressaoPosFixa);
                    System.out.println("Expressão pré-fixa: " + expressaoPreFixa);
                    System.out.println("Resultado da expressão infixa: " + resultado);
                    break;
                       
            case "2":
                    expressao = JOptionPane.showInputDialog("Digite a expressão pré-fixa: ");
                    resultado = calc.calculadoraPreFixa(expressao);
                    
                    expressaoPosFixa = calc.converterParaPosFixa(expressao);
                    expressaoInfixa = calc.converterPreParaInfixa ( expressao);
                    
                    System.out.println("Expressão digitada (pré-fixa): " + expressao);
                    System.out.println("Expressão infixa: " + expressaoInfixa);
                    System.out.println("Expressão pós-fixa: " + expressaoPosFixa);
                    System.out.println("Resultado da expressão pré-fixa: " + resultado);               
                    break;
                       
            case "3": 
                    expressao = JOptionPane.showInputDialog("Digite a expressão pós-fixa: ");
                    resultado = calc.calculadoraPosFixa(expressao);
                    
                    expressaoPreFixa = calc.converterParaPreFixa(expressao);
                    expressaoInfixa = calc.converterPosParaInfixa ( expressao);
                    
                    System.out.println("Expressão digita (pós-fixa): " + expressao);
                    System.out.println("Expressão infixa: " + expressaoInfixa);
                    System.out.println("Expressão pré-fixa: " + expressaoPreFixa);
                    System.out.println("Resultado da expressão pós-fixa: " + resultado);
                    break;       
                       
            default: System.out.println("Valor não econtrado");   
        }
        
    }
    
    
    
}

