/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import Auxiliar.Desenho;
import java.awt.Graphics;

/**
 *
 * @author aula
 */
public class Cunha extends Elemento{
    boolean b;
    public Cunha(String sNomeImagePNG) {
        super(sNomeImagePNG);
        b = false;
        this.bVuneravel = false;
        this.bTransponivel = false;
    }

    @Override
    public void autoDesenho(Graphics g) {
        /*float f = (float) Math.random();
        if(f < 0.25)
            this.getPosicao().moveDown();
        else if(f > 0.25 && f < 0.5)
            this.getPosicao().moveUp();
        else if(f > 0.5 && f < 0.75)
            this.getPosicao().moveLeft();
        else
            this.getPosicao().moveRight();*/

        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());

    }
    
}
