package expressao;

import java.util.LinkedList;
import java.util.List;

public abstract class Expressao {
	protected boolean marcada;
	protected static final int ESCALA = 50;
	
	private List<Expressao> valores;
	
	protected Expressao() {
		this.valores = new LinkedList<Expressao>();
	}	
	
	public Expressao(String... valores) {
		this();
		for (String atual : valores) {
			this.valores.add(new Valor(atual));
		}
	}
	
	public Expressao(Expressao... valores) {
		this();
		for (Expressao atual : valores) {
			this.valores.add(atual.copia());
		}
	}
	
	
	
	public abstract Double getValorDecimal(Variavel...variaveis);
	protected abstract void getLatex(StringBuilder sb,Variavel...variaveis);
	public abstract String toString(Variavel...variaveis);
	public abstract Expressao copia();
	
	public String getLatex(Variavel...variaveis) {
		StringBuilder sb = new StringBuilder();
		if(marcada) {
			sb.append(" \\textcolor{red}{ ");
		}
		
		getLatex(sb, variaveis);
		
		if(marcada) {
			sb.append(" } ");
		}
		
		return sb.toString();
	}
	
	public Expressao getValor(Variavel...variaveis) {
		if (isInteiro(variaveis)) return new Valor(getValorDecimal(variaveis));
		return getValorComponentes(variaveis);
	}
	
	public boolean isDesprezivel(Variavel...variaveis) {
		try {
			return Math.abs(getValorDecimal(variaveis))<1e-5;
		} catch(ArithmeticException e) {
			return false;
		}
		
	}
	
	public abstract Expressao getValorComponentes(Variavel...variaveis);
	
	public abstract Expressao getDerivada(Variavel...variaveis);
	
	
	public boolean isInteiro(Variavel...variaveis) {
		return Math.abs(getValorDecimal(variaveis)%1d)<1e-9;
	}
	
	public boolean isVariavel(Variavel...variaveis) {
		try {
			getValorDecimal(variaveis);
			return false;
		} catch(ArithmeticException e) {
			//e.printStackTrace();
			return true;
		}
	}
	
	public void substituir(Expressao anterior,Expressao nova) {
		int substituir = -1;
		for (int i = 0; i < valores.size(); i++) {
			Expressao expressao = valores.get(i);
			if(expressao == anterior) {
				substituir = i;
				break;
			}
		}
		
		if(substituir>=0) {
			valores.set(substituir, nova);
			return;
		}
		
		for (Expressao expressao : valores) {
			expressao.substituir(anterior, nova);
		}
	}
	
	
	//public abstract Expressao derivada();
	//public abstract Expressao limite();
	public boolean equivalenteA(Expressao b,Variavel... variaveis) {
		return Math.abs(getValorDecimal(variaveis) - b.getValorDecimal(variaveis))<1e-9;		
	}
	
	protected Expressao valor(int i) {
		return valores.get(i);
	}
	
	protected Expressao[] valores() {
		return valores.toArray(new Expressao[valores.size()]);
	}
	
	public List<Expressao> listarExpressoes() {
		List<Expressao> lista = new LinkedList<Expressao>();
		for (Expressao expressao : valores) {
			lista.addAll(expressao.listarExpressoes());
		}
		if (!isValor()) {
	        lista.add(this);
	    }
		return lista;
	}
	
	public void marcar() {
		marcada = true;
	}
	
	public void desmarcar() {
		marcada = false;
		for (Expressao expressao : valores) {
			expressao.desmarcar();
		}
	}
	
	protected boolean isValor() {
		return this instanceof Valor;
	}
	
	
	
	
	@Override
	public String toString() {
		return toString(new Variavel[0]);
	}

	public boolean isNegativa(Variavel... variaveis) {
		try {
			return getValorDecimal(variaveis)<0d;
		} catch(ArithmeticException e) {
			return false;
		}
	}
	
	

}
