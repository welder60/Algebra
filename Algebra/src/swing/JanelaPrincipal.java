package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import expressao.Expressao;
import expressao.GeradorExpressao;
import expressao.Parser;
import expressao.Variavel;

public class JanelaPrincipal extends JFrame {
	private PainelLatex pLatex;
	private Expressao atual,selecionada;
	private Variavel x;
	private JPanel componentes;
	
	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        x = new Variavel('x', 2d);
        pLatex = new PainelLatex();
        atual = GeradorExpressao.gerar();
        selecionada = atual;
        //atual = new SomaIndexada('n',100,new Divisao(new Valor(1d),new Potencia(new Valor(4d),new Valor('n'))));
        
		JPanel expressoes = new JPanel(new GridLayout(2, 1));
        
        
		expressoes.add(pLatex);
        
        PanelExpressao input = new PanelExpressao();
        input.setListener(e -> {
            
            try {
                Expressao expressao = Parser.parse(input.getTexto());
                if(selecionada.equivalenteA(expressao)) {
                	if(selecionada == atual) {
                		atual=expressao;
                		selecionada = atual;
                	} else {
                		atual.substituir(selecionada, expressao);
                	}
                	
                	
                } else {
                	System.out.println("Expressão: " + expressao + " = " + expressao.getValorDecimal());
                    System.out.println("selecionada: " + selecionada + " = " + selecionada.getValorDecimal());
                }
                atualizar();
                
            } catch (Exception ex) {
                System.out.println("Erro ao interpretar expressão: " + ex.getMessage());
            }
        });
        
        componentes = new JPanel(new GridLayout(1, 0));
        
        
        expressoes.add(new JScrollPane(componentes));
        add(expressoes);        
        add(input,BorderLayout.SOUTH);
        setVisible(true);
        
        atualizar();
	}

	private void atualizar() {
		
		pLatex.setExpressaoLatex(atual.getLatex()+(atual.isVariavel()?"= "+atual.getValorDecimal(x):""));
		componentes.removeAll();
		for (Expressao expressao : atual.listarExpressoes()) {
			PainelLatex componente = new PainelLatex();
			componente.setExpressaoLatex(expressao.getLatex());
			componente.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					atual.desmarcar();
					expressao.marcar();
					selecionada = expressao;
					System.out.println(atual.getLatex());
					atualizar();
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
			});
			componentes.add(componente);
		}
		
		revalidate();
		repaint();
	}
}
