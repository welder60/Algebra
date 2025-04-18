package swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import expressao.Divisao;
import expressao.Expressao;
import expressao.GeradorExpressao;
import expressao.Limite;
import expressao.Parser;
import expressao.Potencia;
import expressao.Soma;
import expressao.SomaIndexada;
import expressao.Subtracao;
import expressao.Valor;
import expressao.Variavel;

public class JanelaPrincipal extends JFrame {
	private PainelLatex pLatex;
	private Expressao atual;
	private Variavel x;
	
	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        x = new Variavel('x', 2d);
        pLatex = new PainelLatex();
        //atual = GeradorExpressao.gerar();
        atual = new SomaIndexada('n',100,new Divisao(new Valor(1d),new Potencia(new Valor(4d),new Valor('n'))));
		System.out.println(atual);
        atualizar();
        
        
        add(pLatex);
        
        PanelExpressao input = new PanelExpressao();
        input.setListener(e -> {
            
            try {
                Expressao expressao = Parser.parse(input.getTexto());
                if(atual.equivalenteA(expressao)) atual = expressao;
                atualizar();
                //System.out.println("Expressão: " + expressao + " = " + expressao.getValorDecimal());
                //System.out.println("Atual: " + atual + " = " + atual.getValorDecimal());
            } catch (Exception ex) {
                System.out.println("Erro ao interpretar expressão: " + ex.getMessage());
            }
        });
        
        add(input,BorderLayout.SOUTH);
        setVisible(true);
	}

	private void atualizar() {
		pLatex.setExpressaoLatex(atual.getLatex()+" = "+atual.getLatex(x)+" = "+atual.getValor(x).getValorDecimal(x));
	}
}
