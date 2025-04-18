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
		for (Expressao expressao : valores) {
			total *= expressao.getValorDecimal(variaveis);
		}
		return total;
	}

	@Override
	public String getLatex(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		sb.append("\\(");
		for (Expressao expressao : valores) {
			if(!primeiro)sb.append("\\cdot");
			sb.append(expressao.getLatex(variaveis));
			primeiro = false;			
		}
		sb.append("\\)");
		return sb.toString();
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		boolean primeiro = true;
		for (Expressao expressao : valores) {
			if(!primeiro)sb.append("x");
			sb.append(expressao.toString(variaveis));
			primeiro = false;			
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		double valor = 1d;
		LinkedList<Expressao> valores = new LinkedList<Expressao>();
		for (Expressao expressao : this.valores) {
			if(!expressao.isInteiro(variaveis)) {
				valores.add(expressao.getValor(variaveis));
			} else {
				valor *= expressao.getValorDecimal(variaveis);
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


}
