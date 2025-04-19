package expressao;

import java.util.LinkedList;

public class Adicao extends Expressao {
	
	public Adicao(String... valores) {
		super(valores);
	}
	
	public Adicao(Expressao... valores) {
		super(valores);
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		Double total = 0d;
		for (Expressao expressao : valores()) {
			total += expressao.getValorDecimal(variaveis);
		}
		return new Valor(total).getValorDecimal();
	}

	@Override
	protected void getLatex(StringBuilder sb,Variavel... variaveis) {
		boolean primeiro = true;
		for (Expressao expressao : valores()) {
			if(expressao.isDesprezivel(variaveis))continue;
			if(!primeiro)sb.append("+");
			expressao.getLatex(sb,variaveis);
			primeiro = false;			
		}
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		boolean primeiro = true;
		for (Expressao expressao : valores()) {
			if(!primeiro && !expressao.isNegativa())sb.append("+");			
			if(!expressao.isValor()) sb.append(" \\left( ");		
			sb.append(expressao.toString(variaveis));
			if(!expressao.isValor()) sb.append(" \\right) ");	
			primeiro = false;			
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValor(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		double valor = 0d;
		LinkedList<Expressao> valores = new LinkedList<Expressao>();
		for (Expressao expressao : valores()) {
			if(!expressao.isInteiro(variaveis)) {
				valores.add(expressao.getValor(variaveis));
			} else {
				valor += expressao.getValorDecimal(variaveis);
			}
		}
		valores.addFirst(new Valor(valor));
		if(valores.size()==1) return valores.get(0).getValor(variaveis);
		return new Adicao(valores.toArray(new Expressao[valores.size()]));		
		
	}

	@Override
	public Expressao copia() {
		return new Adicao(valores());
	}




}
