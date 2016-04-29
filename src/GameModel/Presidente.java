/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GameModel;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Junio
 */
public class Presidente extends Elemento implements Serializable{
    public Presidente(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    public void autoDesenho(Graphics g){
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    }

    
}
