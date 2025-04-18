package expressao;

import java.util.Random;

public class Variavel {	
	private static Random random = new Random();
	private char simbolo;
	private Expressao valorMinimo, valorMaximo;
	private Expressao valor;
	
	

    public Variavel(char simbolo, Expressao valorMinimo, Expressao valorMaximo) {
		super();
		this.simbolo = simbolo;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		
		aleatorio();
	}
    
    public Variavel(char simbolo) {
		this(simbolo,new Valor("0"),new Valor("999999999999999999999"));
	}
    
    public Variavel(char simbolo,Expressao valor) {
    	super();
		this.simbolo = simbolo;
		this.valor = valor;
	}
    
    public Variavel(char simbolo,Double valor) {
    	super();
		this.simbolo = simbolo;
		this.valor = new Valor(valor);
	}
    
    public char getSimbolo() {
		return simbolo;
	}
    
    public Double getValor() {
		return valor.getValorDecimal();
	}

    
    public void setValor(Expressao valor) {
		this.valor = valor;
	}
    

	public void aleatorio() {
    	Double valorMinimo = this.valorMinimo.getValorDecimal();
    	Double valorMaximo = this.valorMaximo.getValorDecimal();
    	
        if (valorMinimo.compareTo(valorMaximo) >= 0) {
            throw new IllegalArgumentException("O valor mínimo deve ser menor que o valor máximo.");
        }

        Double intervalo = valorMaximo - valorMinimo;
        Double escala = Math.max(valorMinimo, valorMaximo);

        Double fatorAleatorio = new Double(random .nextDouble());
        Double resultado = valorMinimo + (intervalo*fatorAleatorio);

        this.valor = new Valor(resultado.toString());
    }
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return simbolo+" = "+getValor();
	}
}
