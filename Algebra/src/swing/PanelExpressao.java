package swing;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;

// Classe principal com a UI
public class PanelExpressao extends JPanel {

    private JTextField campoExpressao;
    private JButton botaoConfirmar;
    private String texto;
	private ActionListener listener;

    public PanelExpressao() {
        this.listener = listener;
		setLayout(new BorderLayout());

        campoExpressao = new JTextField();
        botaoConfirmar = new JButton("Confirmar");

        add(campoExpressao, BorderLayout.CENTER);
        add(botaoConfirmar, BorderLayout.EAST);
        
        campoExpressao.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				texto = campoExpressao.getText();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				texto = campoExpressao.getText();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				texto = campoExpressao.getText();
			}
		});
        
    }
    
    public void setListener(ActionListener listener) {
    	botaoConfirmar.addActionListener(listener);
	}
    
    public String getTexto() {
		return texto;
	}

   
}

