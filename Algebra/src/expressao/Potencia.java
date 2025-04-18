package expressao;

public class Potencia extends Expressao {

	public Potencia(String valor,String expoente) {
		this(new Valor(valor),new Valor(expoente));
	}
	
	public Potencia(char variavel,int expoente) {
		this(new Valor(variavel),new Valor(expoente+""));
	}
	
	public Potencia(String valor) {
		this(new Valor(valor));
	}
	
	public Potencia(Expressao base,Expressao expoente) {
		super(base);
		parametros.add(expoente);
	}
	
	public Potencia(Expressao valor) {
		this(valor,new Valor("2"));
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		Double base = valores.get(0).getValorDecimal(variaveis);
		Double expoente = parametros.get(0).getValorDecimal(variaveis);
		
		if(expoente==Double.POSITIVE_INFINITY) {
			if(Math.abs(base)<1) return 0d;
			if(base<0) return Double.NaN;
			return Double.POSITIVE_INFINITY;
		}
		
        return Math.pow(base, expoente);
	}

	@Override
	public String getLatex(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append(valores.get(0).getLatex(variaveis)).append("^{").append(parametros.get(0).getLatex(variaveis)).append("}");
		return sb.toString();
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(valores.get(0).toString(variaveis));
		sb.append("^");
		sb.append(parametros.get(0).toString(variaveis));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		return new Potencia(valores.get(0).getValor(variaveis), parametros.get(0).getValor(variaveis));
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

}
