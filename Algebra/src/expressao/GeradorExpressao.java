package expressao;

import java.util.Random;

public class GeradorExpressao {
	public static Expressao gerar() {
		Expressao atual = gerar(gerar(gerar(valores(2,1,5)),gerar(valores(2,1,5))),gerar(gerar(valores(2,1,5)),gerar(valores(2,1,5))));
		return atual;
	}
	
	public static Expressao gerar(Expressao... expressoes) {
		Random r = new Random();
		switch(r.nextInt(6)) {
		case 0:
			return new Soma(expressoes);
		case 1:
			return new Subtracao(expressoes);
		case 2:
			return new Multiplicacao(expressoes);
		case 3:
			return new Divisao(expressoes);
		case 4:
			return new Raiz(expressoes[0]);
		case 5:
			return new Potencia(expressoes[0]);
				
		}
		
		return new Soma(expressoes);
	}
	
	public static Valor[] valores(int n,int min,int max){
		Valor[] valores = new Valor[n];
		for (int i = 0; i < n; i++) {
			valores[i] = valor(min,max);
		}
		return valores;		
	}
	
	public static Valor valor(int min,int max){
		Random r = new Random();
		int valor = (r.nextBoolean()?1:-1)*r.nextInt(max-min)+max;
		if(r.nextInt(5)==0) return new Valor('x');
		return new Valor(valor+"");		
	}
}
