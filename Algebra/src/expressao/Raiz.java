package expressao;

public class Raiz extends Expressao {

	public Raiz(String valor,String base) {
		this(new Valor(valor),new Valor(base));
	}
	
	public Raiz(String valor) {
		this(new Valor(valor));
	}
	
	public Raiz(Expressao valor,Expressao base) {
		super(valor,base);
	}
	
	public Raiz(Expressao valor) {
		this(valor,new Valor("2"));
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		Double valor = valor(0).getValorDecimal(variaveis);
		Double base = valor(1).getValorDecimal(variaveis);
		
		return Math.pow(valor, 1.0 / base);
	}

	@Override
	protected void getLatex(StringBuilder sb,Variavel... variaveis) {
		sb.append("	\\sqrt[");
		valor(1).getLatex(sb,variaveis);
		sb.append("]{");
		valor(0).getLatex(sb,variaveis);
		sb.append("}");
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(valor(1).toString(variaveis));
		sb.append("√");
		sb.append(valor(0).toString(variaveis));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		if(!isInteiro(variaveis)) {
			return new Raiz(valor(0).getValor(variaveis), valor(0).getValor(variaveis));
		}
		
		return super.getValor(variaveis);
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao copia() {
		return new Raiz(valor(0),valor(1));
	}

}
