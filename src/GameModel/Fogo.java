/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameModel;

import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author junio
 */
public class Fogo extends Elemento implements Serializable{
    

    public Fogo(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bMortal = true;
    }

    @Override
    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
        if(!this.moveRight())
            Desenho.getjCenario().removeElemento(this);
    }
    
}
