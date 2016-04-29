/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import GameModel.GameOver;
import GameModel.Elemento;
import GameModel.Presidente;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author junio
 */
public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Elemento> e, Graphics g){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho(g);
        }
    }
    public void processaTudo(ArrayList<Elemento> e){
        if(e.isEmpty())
            return;
        Presidente dilma = (Presidente)e.get(0);
        Elemento eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(dilma.getPosicao().igual(eTemp.getPosicao()))
                if(eTemp.isbVuneravel())
                    e.remove(eTemp);
                else if(eTemp.isbMortal())
                    e.add(new GameOver("gameover.png"));
        }        
    }

    /**
     *
     * @param e
     * @param p
     * @return
     */
    public boolean ehPosicaoValida(ArrayList<Elemento> e, Posicao p){
        Elemento eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);            
            if(!eTemp.isbTransponivel() && !eTemp.isbEmpuravel()){
                if(eTemp.getPosicao().igual(p))
                    return false;
            }
            if(!eTemp.isbTransponivel() && eTemp.isbEmpuravel()){
                if(eTemp.getPosicao().igual(p))
                    return true;
            }
        }   
        return true;
    }
    
    public Elemento getElem(ArrayList<Elemento> e, Posicao p){
        Elemento eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);            
            if(!eTemp.isbTransponivel() && !eTemp.isbEmpuravel()){
                if(eTemp.getPosicao().igual(p))
                    return null;
            }
            if(!eTemp.isbTransponivel() && eTemp.isbEmpuravel()){
                if(eTemp.getPosicao().igual(p))
                    return eTemp;
            }
        }
        return null;
    }
}
