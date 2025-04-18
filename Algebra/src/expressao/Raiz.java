package expressao;

public class Raiz extends Expressao {

	public Raiz(String valor,String base) {
		this(new Valor(valor),new Valor(base));
	}
	
	public Raiz(String valor) {
		this(new Valor(valor));
	}
	
	public Raiz(Expressao valor,Expressao base) {
		super(valor);
		parametros.add(base);
	}
	
	public Raiz(Expressao valor) {
		this(valor,new Valor("2"));
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		Double valor = valores.get(0).getValorDecimal(variaveis);
		Double base = parametros.get(0).getValorDecimal(variaveis);
		
		return Math.pow(valor, 1.0 / base);
	}

	@Override
	public String getLatex(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("	\\sqrt[").append(parametros.get(0).getLatex(variaveis)).append("]{").append(valores.get(0).getLatex(variaveis)).append("}");
		return sb.toString();
	}

	@Override
	public String toString(Variavel... variaveis) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(parametros.get(0).toString(variaveis));
		sb.append("√");
		sb.append(valores.get(0).toString(variaveis));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		if(!isInteiro(variaveis)) {
			return new Raiz(valores.get(0).getValor(variaveis), parametros.get(0).getValor(variaveis));
		}
		
		return super.getValor(variaveis);
	}

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

}
