package expressao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Expressao {
	protected static final int ESCALA = 50;
	
	protected List<Expressao> valores;
	protected List<Expressao> parametros;
	
	protected Expressao() {
		this.valores = new LinkedList<Expressao>();
		this.parametros = new LinkedList<Expressao>();
	}	
	
	public Expressao(String... valores) {
		this();
		for (String atual : valores) {
			this.valores.add(new Valor(atual));
		}
	}
	
	public Expressao(Expressao... valores) {
		this();
		this.valores.addAll(Arrays.asList(valores));
	}
	
	public abstract Double getValorDecimal(Variavel...variaveis);
	public abstract String getLatex(Variavel...variaveis);
	public abstract String toString(Variavel...variaveis);
	
	public Expressao getValor(Variavel...variaveis) {
		if (isInteiro(variaveis)) return new Valor(getValorDecimal(variaveis));
		return getValorComponentes(variaveis);
	}
	
	public boolean isDesprezivel(Variavel...variaveis) {
		return Math.abs(getValorDecimal(variaveis))<1e-5;
	}
	
	public abstract Expressao getValorComponentes(Variavel...variaveis);
	
	public abstract Expressao getDerivada(Variavel...variaveis);
	
	
	public boolean isInteiro(Variavel...variaveis) {
		return Math.abs(getValorDecimal(variaveis)%1d)<1e-9;
	}
	
	public boolean isVariavel(Variavel...variaveis) {
		try {
			getValorDecimal(variaveis);
			return false;
		} catch(ArithmeticException e) {
			//e.printStackTrace();
			return true;
		}
	}
	
	
	//public abstract Expressao derivada();
	//public abstract Expressao limite();
	public boolean equivalenteA(Expressao b,Variavel... variaveis) {
		return Math.abs(getValorDecimal(variaveis) - b.getValorDecimal(variaveis))<1e-9;		
	}
	
	@Override
	public String toString() {
		return toString(new Variavel[0]);
	}
	
	

}
