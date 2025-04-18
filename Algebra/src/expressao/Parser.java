package expressao;

import java.util.Stack;

public class Parser {

    public static Expressao parse(String texto) {
        return parseExpressao(texto.replace(" ", ""));
    }

    private static Expressao parseExpressao(String str) {
        if (str.startsWith("(") && str.endsWith(")")) {
            str = str.substring(1, str.length() - 1); // remove os parênteses externos
        }

        int nivel = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') nivel++;
            else if (c == ')') nivel--;

            if (nivel == 0) {
            	
            	switch(c) {
            	case '+':
            		return new Soma(parseExpressao(str.substring(0, i)), parseExpressao(str.substring(i + 1)));
            	case '-':
            		return new Subtracao(parseExpressao(str.substring(0, i)), parseExpressao(str.substring(i + 1)));
            	case '*':
            		return new Multiplicacao(parseExpressao(str.substring(0, i)), parseExpressao(str.substring(i + 1)));
            	case '/':
            		return new Divisao(parseExpressao(str.substring(0, i)), parseExpressao(str.substring(i + 1)));
            	case 'r':
            		return new Raiz(parseExpressao(str.substring(0, i)), parseExpressao(str.substring(i + 1)));
            	}
                
            }
        }

        int posPotencia = str.indexOf('^');
        if (posPotencia != -1) {
            return new Potencia(parseExpressao(str.substring(0, posPotencia)), parseExpressao(str.substring(posPotencia + 1)));
        }

        return new Valor(str);
    }

}
