/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameModel;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;

/**
 *
 * @author junio
 */
public class Car extends Elemento{
    private int iContaIntervalos;
    
    public Car(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.iContaIntervalos = 0;
    }

    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());

        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMER_CAR){
            this.iContaIntervalos = 0;
            if(this.getPosicao().getColuna() == (Consts.RES-1))
                this.setPosicao(this.getPosicao().getLinha(),0);
            this.setPosicao(pPosicao.getLinha(),pPosicao.getColuna()+1);            
        }
    }    
}