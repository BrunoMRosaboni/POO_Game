/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import Auxiliar.Desenho;
import Controler.ControleDeJogo;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author druidic
 */
public class EmpuraDireita extends Elemento{

    public EmpuraDireita(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bVuneravel = false;
    }
    

    @Override
    public void autoDesenho(Graphics g) {
        Desenho.desenhar(g, this.iImage, pPosicao.getColuna(), pPosicao.getLinha());
    }
    

    public void Empurrar(ArrayList<Elemento> e, ControleDeJogo cj){
        Elemento eTemp;
        for(int i = 0; i < e.size(); i++)
        {
            eTemp = e.get(i);
            if(eTemp == this) continue;
            if(eTemp.pPosicao.getColuna() == this.pPosicao.getColuna() && eTemp.pPosicao.getLinha() == this.pPosicao.getLinha())
            {
                    eTemp.moveRight();
                if (!cj.ehPosicaoValida(e, eTemp.getPosicao()))
                    eTemp.voltaAUltimaPosicao();
            }
                
        }
    }
}