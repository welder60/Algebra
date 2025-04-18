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
		return valores.get(0).getValorDecimal(variaveis)/valores.get(1).getValorDecimal(variaveis);
	}

	@Override
	public String getLatex(Variavel...variaveis) {
		StringBuilder sb = new StringBuilder();
			sb.append("\\frac{").append(valores.get(0).getLatex(variaveis)).append("}{").append(valores.get(1).getLatex(variaveis)).append("}");
		return sb.toString();
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(valores.get(0).toString(variaveis));
		sb.append("/");
		sb.append(valores.get(1).toString(variaveis));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		return new Divisao(valores.get(0).getValor(variaveis),valores.get(1).getValor(variaveis));	
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	




}
