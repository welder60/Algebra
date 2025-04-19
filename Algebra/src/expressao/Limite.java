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
		return valor(0).getValorDecimal(new Variavel(variavel,limite));
	}

	@Override
	protected void getLatex(StringBuilder sb,Variavel...variaveis) {
		sb.append("\\lim_{").append(variavel).append(" \\to ");
		limite.getLatex(sb);
		sb.append("} ");
		valor(0).getLatex(sb,variaveis);
	}

	@Override
	public String toString(Variavel...variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\lim_{").append(variavel).append(" \\to ");
		limite.getLatex(sb,variaveis);
		sb.append("} ");
		sb.append(valor(0));
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

	@Override
	public Expressao copia() {
		return new Limite(variavel,valor(0));
	}
	


}
