package expressao;

import java.util.LinkedList;

public class Subtracao extends Expressao {
	
	public Subtracao(String... valores) {
		super(valores);
	}
	
	public Subtracao(Expressao... valores) {
		super(valores);
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		Double total = valores.get(0).getValorDecimal(variaveis);		
		for (int i=1;i<valores.size();i++) {
			total -= valores.get(i).getValorDecimal(variaveis);
		}
		return total;
	}

	@Override
	public String getLatex(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for (Expressao expressao : valores) {
			if(!primeiro)sb.append("-");
			sb.append(expressao.getLatex(variaveis));
			primeiro = false;			
		}
		return sb.toString();
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		boolean primeiro = true;
		for (Expressao expressao : valores) {
			if(!primeiro)sb.append("-");
			sb.append(expressao.toString(variaveis));
			primeiro = false;			
		}
		sb.append(")");
		return sb.toString();
	}



	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		double valor = 0d;
		LinkedList<Expressao> valores = new LinkedList<Expressao>();
		for (Expressao expressao : this.valores) {
			if(!expressao.isInteiro(variaveis)) {
				valores.add(expressao.getValor(variaveis));
			} else {
				valor -= expressao.getValorDecimal(variaveis);
			}
		}
		valores.addFirst(new Valor(valor));
		if(valores.size()==1) return valores.get(0).getValor(variaveis);
		return new Subtracao(valores.toArray(new Expressao[valores.size()]));
		
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

}
