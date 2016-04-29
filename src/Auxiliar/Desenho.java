/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Auxiliar;

import Controler.Tela;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author junio
 */
public class Desenho {
    static Tela jCenario;

    public static Tela getjCenario() {
        return jCenario;
    }

    public static void setjCenario(Tela jACenario) {
        jCenario = jACenario;
    }    
    public static void desenhar(Graphics g, ImageIcon iImage, int iColuna, int iLinha){
      iImage.paintIcon(jCenario, g,iColuna * Consts.CELL_SIDE, iLinha * Consts.CELL_SIDE);
    }
}
