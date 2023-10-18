/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadoraposfixa;
import java.util.Stack;

/**
 *
 * @author Pc-lab
 */
public class Calc {
    
public static double calcularPosFixa(String expressao) {
        String[] tokens = expressao.split(" ");
        Stack<Double> pilha = new Stack<>();

        for (String token : tokens) {
            if (isNumero(token)) {
                pilha.push(Double.parseDouble(token));
            } else if (isOperador(token)) {
                double segundoNumero = pilha.pop();
                double primeiroNumero = pilha.pop();
                double resultado = realizarOperacao(primeiroNumero, segundoNumero, token);
                pilha.push(resultado);
            }
        }

        return pilha.pop();
    }

    private static boolean isNumero(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static double realizarOperacao(double a, double b, String operador) {
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Divisão por zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }
}
