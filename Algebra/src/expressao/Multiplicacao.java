package expressao;

import java.util.LinkedList;

public class Multiplicacao extends Expressao {
	
	public Multiplicacao(String... valores) {
		super(valores);
	}
	
	public Multiplicacao(Expressao... valores) {
		super(valores);
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		Double total = 1d;
		for (Expressao atual:valores()) {
			total *= atual.getValorDecimal(variaveis);
		}
		return total;
	}

	@Override
	protected void getLatex(StringBuilder sb,Variavel... variaveis) {
		boolean primeiro = true;
		sb.append("\\(");
		for (Expressao atual:valores()) {
			if(!primeiro)sb.append("\\cdot");
			if(!atual.isValor()) sb.append(" \\left( ");		
			atual.getLatex(sb,variaveis);
			if(!atual.isValor()) sb.append(" \\right) ");			
			primeiro = false;			
		}
		sb.append("\\)");
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		boolean primeiro = true;
		for (Expressao atual:valores()) {
			if(!primeiro)sb.append("x");
			sb.append(atual.toString(variaveis));
			primeiro = false;			
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		double valor = 1d;
		LinkedList<Expressao> valores = new LinkedList<Expressao>();
		for (Expressao atual:valores()) {
			if(!atual.isInteiro(variaveis)) {
				valores.add(atual.getValor(variaveis));
			} else {
				valor *= atual.getValorDecimal(variaveis);
			}
		}
		valores.addFirst(new Valor(valor));
		if(valores.size()==1) return valores.get(0).getValor(variaveis);
		return new Multiplicacao(valores.toArray(new Expressao[valores.size()]));
		
		
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInteiro(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Expressao copia() {
		// TODO Auto-generated method stub
		return null;
	}


}
