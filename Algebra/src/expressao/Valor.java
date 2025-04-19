package expressao;

public class Valor extends Expressao {
	

	private Double valor;
	private char variavel;

	public Valor(String valor) {
		this.valor = new Double(valor);
	}
	
	public Valor(char variavel) {
		this.variavel = variavel;
	}

	public Valor(Double valor) {
		this.valor = valor;
	}

	@Override
	protected void getLatex(StringBuilder sb,Variavel... variaveis) {	
		if(isVariavel(variaveis)) {sb.append(variavel+"");return;}
		if(getValorDecimal(variaveis)==Double.POSITIVE_INFINITY) { sb.append("\\infty");return;}
		if(getValorDecimal(variaveis)==Double.NEGATIVE_INFINITY) { sb.append("-\\infty");return;}
		sb.append(formatarDouble(getValorDecimal(variaveis)));
	}

	@Override
	public Double getValorDecimal(Variavel... variaveis) {
		for (Variavel atual : variaveis) {
			if(atual.getSimbolo() == this.variavel) {
				return atual.getValor();
			}
		}
		if(this.valor == null) throw new ArithmeticException("Valor não informado para a variável '"+variavel+"'.");
		return arredondar(this.valor,5);
	}

	@Override
	public String toString(Variavel... variaveis) {
		if(isVariavel(variaveis)) return " "+variavel+" ";
		return formatarDouble(this.valor);
	}
	
	public static double arredondar(double valor, int casasDecimais) {
	    double fator = Math.pow(10, casasDecimais);
	    return Math.round(valor * fator) / fator;
	}
	
	public static String formatarDouble(double valor) {
		String formato = valor<0? "(%d)" : "%d";
		
	    if (valor == Math.rint(valor)) {
	        return String.format(formato,(int) valor); // remove o ".0"
	    } else {
	    	formato = valor<0? "(%f)" : "%f";
	    	return String.format(formato,valor); // mantém como está
	    }
	}

	

	@Override
	public Expressao getDerivada(Variavel... variaveis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao getValorComponentes(Variavel... variaveis) {
		return getValor(variaveis);
	}

	@Override
	public Expressao copia() {
		if(variavel>0)return new Valor(variavel);
		return new Valor(valor);
	}

	

}
