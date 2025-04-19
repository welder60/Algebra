package expressao;

public class Divisao extends Expressao {
	
	public Divisao(String... valores) {
		super(valores);
	}
	
	public Divisao(Expressao... valores) {
		super(valores);
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {		
		return valor(0).getValorDecimal(variaveis)/valor(1).getValorDecimal(variaveis);
	}

	@Override
	protected void getLatex(StringBuilder sb,Variavel...variaveis) {
			sb.append("\\frac{");
			valor(0).getLatex(sb,variaveis);
			sb.append("}{");
			valor(1).getLatex(sb,variaveis);
			sb.append("}");		
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(valor(0).toString(variaveis));
		sb.append("/");
		sb.append(valor(1).toString(variaveis));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		return new Divisao(valor(0).getValor(variaveis),valor(1).getValor(variaveis));	
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao copia() {
		return new Divisao(valor(0),valor(1));
	}

	




}
