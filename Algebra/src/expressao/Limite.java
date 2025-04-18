package expressao;

public class Limite extends Expressao{
	
	private Valor limite;
	private char variavel;	

	public Limite(char variavel,Expressao expressao) {
		super(expressao);
		this.limite = new Valor(Double.POSITIVE_INFINITY);
		this.variavel = variavel;
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		return valores.get(0).getValorDecimal(new Variavel(variavel,limite));
	}

	@Override
	public String getLatex(Variavel...variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\lim_{").append(variavel).append(" \\to ").append(limite.getLatex()).append("} ");
		sb.append(valores.get(0).getLatex(variaveis));
		return sb.toString();
	}

	@Override
	public String toString(Variavel...variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\lim_{").append(variavel).append(" \\to ").append(limite.getLatex(variaveis)).append("} ");
		sb.append(valores.get(0));
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
