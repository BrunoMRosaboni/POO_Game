/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameModel;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author junio
 */
public class Caveira extends Elemento{
    private int iContaIntervalos;
    
    public Caveira(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.iContaIntervalos = 0;
        this.bEmpuravel = true;
    }

    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, this.getPosicao().getColuna(), this.getPosicao().getLinha());
        

        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMER_FOGO){
            this.iContaIntervalos = 0;
            Fogo f = new Fogo("fire.png");
            f.setPosicao(this.getPosicao().getLinha(),this.getPosicao().getColuna()+1);
            Desenho.getjCenario().addElemento(f);
        }
    }

    public void anda(ArrayList<Elemento> e, ControleDeJogo cj){
        this.moveRight();
        if (!cj.ehPosicaoValida(e, this.getPosicao()))
            this.voltaAUltimaPosicao();
    }
}
