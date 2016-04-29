/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GameModel;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Junio
 */
public class BichinhoVaiVemHorizontal extends Elemento implements Serializable{

    public BichinhoVaiVemHorizontal(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    public void autoDesenho(Graphics g){
        float f = (float) Math.random();
        if(f < 0.25)
            this.getPosicao().moveDown();
        else if(f > 0.25 && f < 0.5)
            this.getPosicao().moveUp();
        else if(f > 0.5 && f < 0.75)
            this.getPosicao().moveLeft();
        else
            this.getPosicao().moveRight();

        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    }
}
