package expressao;

import java.util.Arrays;

public class SomaIndexada extends Expressao {
	
	private static final int MAXIMO = 1000;
	private char variavel;
	private int iInicial;
	private int iFinal;

	public SomaIndexada(char variavel,int iInicial,int iFinal,Expressao expressao) {
		super(expressao);
		this.variavel = variavel;
		this.iInicial = iInicial;
		this.iFinal = iFinal;
	}
	
	public SomaIndexada(char variavel,int iFinal,Expressao expressao) {
		this(variavel,0,iFinal,expressao);
	}
	
	public SomaIndexada(char variavel,Expressao expressao) {
		this(variavel,0,MAXIMO,expressao);
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		return getValorComponentes(variaveis).getValorDecimal(variaveis);
	}

	@Override
	protected void getLatex(StringBuilder sb,Variavel... variaveis) {
		sb.append("\\sum_{");
		sb.append(variavel).append('=').append(iInicial).append("}");
		sb.append("^{").append(iFinal).append("} ");
		boolean primeiro = true;
		for (Expressao expressao : valores()) {
			if(!primeiro)sb.append("+");
			expressao.getLatex(sb,variaveis);
			primeiro = false;			
		}
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("Σ").append(iInicial).append('-').append(iFinal);
		sb.append("(");
		boolean primeiro = true;
		for (Expressao expressao : valores()) {
			if(!primeiro)sb.append("+");
			sb.append(expressao.toString(variaveis));
			primeiro = false;			
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		Variavel[] variaveisNovas = Arrays.copyOf(variaveis, variaveis.length+1);		
		
		int n = iFinal-iInicial;
		Expressao[] valores = new Expressao[n];
		for (int i = 0; i < n; i++) {
			variaveisNovas[variaveis.length] = new Variavel(variavel,new Valor((iInicial+i)+""));
			valores[i] = valor(0).getValor(variaveisNovas);			
		}
		
		Expressao valor = new Adicao(valores);
		System.out.println(valor);
		return valor;
		
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao copia() {
		return new SomaIndexada(variavel, iInicial, iFinal, valor(0));
	}

}
