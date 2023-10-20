/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadoraposfixa;
import java.util.Stack;
import java.util.StringJoiner;

/**
 *
 * @author Pc-lab
 */
public class Calc {
    
    public static double calculadoraInfixa(String expression) {
        expression = expression.replaceAll("\\s", "");
        char[] tokens = expression.toCharArray();
        
        Stack<Character> operatorStack = new Stack<>();
        Stack<Double> valueStack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];
            if (token >= '0' && token <= '9') {
                StringBuilder number = new StringBuilder();
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    number.append(tokens[i]);
                    i++;
                }
                i--; 
                valueStack.push(Double.parseDouble(number.toString()));
            } else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    double result = performOperation(operatorStack.pop(), valueStack.pop(), valueStack.pop());
                    valueStack.push(result);
                }
                operatorStack.pop(); 
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek())) {
                    double result = performOperation(operatorStack.pop(), valueStack.pop(), valueStack.pop());
                    valueStack.push(result);
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            double result = performOperation(operatorStack.pop(), valueStack.pop(), valueStack.pop());
            valueStack.push(result);
        }

        return valueStack.pop();
    }

    private static boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op2 == '+' || op2 == '-') && (op1 == '*' || op1 == '/');
    }

    private static double performOperation(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Divisão por zero não é permitida.");
                }
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        String infixExpression = "3 + (4 * 2) / (1 - 5)";
        double result = calculadoraInfixa(infixExpression);
        System.out.println("Resultado: " + result);
    }

    
    
    
    public static double calculadoraPosFixa(String expressao) {
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
    
  
    
    
    public static double calculadoraPreFixa(String expressao) {
        Stack<Double> pilha = new Stack<>();

        String[] tokens = expressao.split(" ");

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            
            if (isNumero2(token)) {
                pilha.push(Double.parseDouble(token));
            } else {
                double operand2 = pilha.pop();
                double operand1 = pilha.pop();
                double resultado = aplicarOperador(token, operand1, operand2);
                pilha.push(resultado);
            }
        }
        return pilha.pop();
    }

    private static boolean isNumero2(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double aplicarOperador(String operador, double operand1, double operand2) {
        switch (operador) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }

    public static void main2(String[] args) {
        String expressao = "+ 5 * 3 4"; 
        double resultado = calculadoraPreFixa(expressao);
        System.out.println("Resultado: " + resultado);
    }


    
}
