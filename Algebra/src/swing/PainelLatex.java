package swing;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PainelLatex extends JPanel {

    private String expressaoLatex = "";
    private float fatorEscala = 1f / 5f; // quanto menor, maior a fórmula

    public PainelLatex() {
        setBackground(Color.WHITE);
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                copiarParaAreaDeTransferencia();
//            }
//        });
    }

    public void setExpressaoLatex(String latex) {
        this.expressaoLatex = latex;
        repaint();
    }

    public void setFatorEscala(float fatorEscala) {
        this.fatorEscala = fatorEscala;
        repaint();
    }

    private void copiarParaAreaDeTransferencia() {
        if (expressaoLatex != null && !expressaoLatex.isEmpty()) {
            StringSelection selecao = new StringSelection(expressaoLatex);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selecao, null);
            JOptionPane.showMessageDialog(this, "Fórmula copiada para a área de transferência!", "Copiado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (expressaoLatex == null || expressaoLatex.isEmpty()) return;

        try {
            TeXFormula formula = new TeXFormula(expressaoLatex);

            int largura = getWidth();
            int altura = getHeight();
            float escala = Math.min(largura, altura) * fatorEscala;

            TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, escala);
            icon.setInsets(new Insets(5, 5, 5, 5));

            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = image.createGraphics();
            g2.setColor(getBackground());
            g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
            icon.paintIcon(new JLabel(), g2, 0, 0);
            g2.dispose();

            int x = (largura - icon.getIconWidth()) / 2;
            int y = (altura - icon.getIconHeight()) / 2;
            g.drawImage(image, x, y, null);

        } catch (Exception e) {
            g.setColor(Color.RED);
            g.drawString("Erro ao renderizar LaTeX", 10, 20);
            System.out.println(expressaoLatex);
        }
    }
}


