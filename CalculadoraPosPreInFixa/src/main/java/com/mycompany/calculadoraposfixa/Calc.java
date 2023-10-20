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

    private boolean isOperador(char token) {
    return token == '+' || token == '-' || token == '*' || token == '/';
}


    
    public String converterParaPosFixa(String expressaoInfixa) {
    Stack<Character> operadores = new Stack<>();
    StringBuilder expressaoPosFixa = new StringBuilder();
    char[] tokens = expressaoInfixa.toCharArray();

    for (int i = 0; i < tokens.length; i++) {
        char token = tokens[i];
        if (token >= '0' && token <= '9') {
            while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                expressaoPosFixa.append(tokens[i]);
                i++;
            }
            i--;
            expressaoPosFixa.append(" ");
        } else if (token == '(') {
            operadores.push(token);
        } else if (token == ')') {
            while (!operadores.isEmpty() && operadores.peek() != '(') {
                expressaoPosFixa.append(operadores.pop()).append(" ");
            }
            operadores.pop();
        } else if (isOperador(token)) {
            while (!operadores.isEmpty() && hasPrecedence(token, operadores.peek())) {
                expressaoPosFixa.append(operadores.pop()).append(" ");
            }
            operadores.push(token);
        }
    }

    while (!operadores.isEmpty()) {
        expressaoPosFixa.append(operadores.pop()).append(" ");
    }

    return expressaoPosFixa.toString().trim();
}

    public String converterParaPreFixa(String expressaoInfixa) {
    StringBuilder expressaoPreFixa = new StringBuilder();
    Stack<Character> operadores = new Stack<>();
    
    StringBuilder expressaoInvertida = new StringBuilder(expressaoInfixa).reverse();
    
    char[] tokens = expressaoInvertida.toString().toCharArray();
    
    for (int i = 0; i < tokens.length; i++) {
        char token = tokens[i];
        
        if (token >= '0' && token <= '9') {
            while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                expressaoPreFixa.append(tokens[i]);
                i++;
            }
            i--;
            expressaoPreFixa.append(" ");
        } else if (token == ')') {
            operadores.push(token);
        } else if (token == '(') {
            while (!operadores.isEmpty() && operadores.peek() != ')') {
                expressaoPreFixa.append(operadores.pop()).append(" ");
            }
            operadores.pop();
        } else if (isOperador(token)) {
            while (!operadores.isEmpty() && hasPrecedence(token, operadores.peek())) {
                expressaoPreFixa.append(operadores.pop()).append(" ");
            }
            operadores.push(token);
        }
    }

    while (!operadores.isEmpty()) {
        expressaoPreFixa.append(operadores.pop()).append(" ");
    }
    
    // Inverte novamente a expressão pré-fixa para a ordem correta
    return expressaoPreFixa.reverse().toString().trim();
}
 
    public String converterPreParaInfixa(String expressaoPreFixa) {
    Stack<String> pilha = new Stack<>();
    String[] tokens = expressaoPreFixa.split(" ");

    for (int i = tokens.length - 1; i >= 0; i--) {
        String token = tokens[i];
        if (isNumero(token)) {
            pilha.push(token);
        } else if (isOperador(token)) {
            String operand1 = pilha.pop();
            String operand2 = pilha.pop();
            String resultado = "(" + operand1 + " " + token + " " + operand2 + ")";
            pilha.push(resultado);
        }
    }

    if (pilha.isEmpty()) {
        throw new IllegalArgumentException("Expressão pré-fixa vazia.");
    }

    return pilha.pop();
}

    public String converterPosParaInfixa(String expressaoPosFixa) {
    Stack<String> pilha = new Stack<>();
    String[] tokens = expressaoPosFixa.split(" ");

    for (String token : tokens) {
        if (isNumero(token)) {
            pilha.push(token);
        } else if (isOperador(token)) {
            String operand2 = pilha.pop();
            String operand1 = pilha.pop();
            String resultado = "(" + operand1 + " " + token + " " + operand2 + ")";
            pilha.push(resultado);
        }
    }

    if (pilha.isEmpty() || pilha.size() > 1) {
        throw new IllegalArgumentException("Expressão pós-fixa inválida.");
    }

    return pilha.pop();
}

    
}
