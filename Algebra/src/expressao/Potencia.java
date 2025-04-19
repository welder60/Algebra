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
		super(base,expoente);
	}
	
	public Potencia(Expressao valor) {
		this(valor,new Valor("2"));
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		Double base = valor(0).getValorDecimal(variaveis);
		Double expoente = valor(1).getValorDecimal(variaveis);
		
		if(expoente==Double.POSITIVE_INFINITY) {
			if(Math.abs(base)<1) return 0d;
			if(base<0) return Double.NaN;
			return Double.POSITIVE_INFINITY;
		}
		
        return Math.pow(base, expoente);
	}

	@Override
	protected void getLatex(StringBuilder sb,Variavel... variaveis) {
		if(!valor(0).isValor()) sb.append(" \\left( ");	
		valor(0).getLatex(sb,variaveis);
		if(!valor(0).isValor()) sb.append(" \\right) ");
		sb.append("^{");			
		valor(1).getLatex(sb,variaveis);		
		sb.append("}");
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(valor(0).toString(variaveis));
		sb.append("^");
		sb.append(valor(1).toString(variaveis));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		return new Potencia(valor(0).getValor(variaveis), valor(1).getValor(variaveis));
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao copia() {
		return new Potencia(valor(0),valor(1));
	}

}
