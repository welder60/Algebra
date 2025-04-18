package expressao;

import java.util.LinkedList;
import java.util.function.Predicate;

public class Soma extends Expressao {
	
	public Soma(String... valores) {
		super(valores);
	}
	
	public Soma(Expressao... valores) {
		super(valores);
		this.valores.removeIf(new Predicate<Expressao>() {

			@Override
			public boolean test(Expressao t) {
				return t.isDesprezivel();
			}
		}) ;
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		Double total = 0d;
		for (Expressao expressao : valores) {
			total += expressao.getValorDecimal(variaveis);
		}
		return new Valor(total).getValorDecimal();
	}

	@Override
	public String getLatex(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for (Expressao expressao : valores) {
			if(expressao.isDesprezivel(variaveis))continue;
			if(!primeiro)sb.append("+");
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
			if(expressao.isDesprezivel(variaveis))continue;
			//if(!primeiro)sb.append("+");
			//sb.append(expressao.toString(variaveis));
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
		for (Expressao expressao : this.valores) {
			if(!expressao.isInteiro(variaveis)) {
				valores.add(expressao.getValor(variaveis));
			} else {
				valor += expressao.getValorDecimal(variaveis);
			}
		}
		valores.addFirst(new Valor(valor));
		if(valores.size()==1) return valores.get(0).getValor(variaveis);
		return new Soma(valores.toArray(new Expressao[valores.size()]));		
		
	}




}
