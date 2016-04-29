/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModel;

import Auxiliar.Consts;
import Controler.ControleDeJogo;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Junio
 */
public abstract class Elemento implements Serializable{

    protected ImageIcon iImage;
    protected Posicao pPosicao;
    protected boolean bTransponivel; /*Pode passar por cima?*/
    protected boolean bVuneravel; 
    protected boolean bMortal;       /*Se encostar, o Lollo morre?*/
    protected boolean bEmpuravel;
    protected boolean ehSeta;

    public boolean isbMortal() {
        return bMortal;
    }

    public boolean isehSeta(){
        return ehSeta;
    }

    protected Elemento(String sNomeImagePNG) {
        this.pPosicao = new Posicao(1, 1);
        this.bTransponivel = true;
        this.bMortal = false;
        this.bVuneravel = true;
        this.bEmpuravel = false;
        this.ehSeta = false;
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean isbVuneravel() {
        return bVuneravel;
    }
    
    public boolean isbEmpuravel(){
        return bEmpuravel;
    }

    /**
     *
     * @return
     */
    public Posicao getPosicao() {
        /*TODO: Retirar este método para que objetos externos nao possam operar
         diretamente sobre a posição do Elemento*/
        return pPosicao;
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }

    public void setbEmpuravel(boolean bEmpuravel){
        this.bEmpuravel = bEmpuravel;
    }
    
    abstract public void autoDesenho(Graphics g);

    public boolean setPosicao(int linha, int coluna) {
        return pPosicao.setPosicao(linha, coluna);
    }

    public boolean moveUp() {
        return this.pPosicao.moveUp();
    }

    public boolean moveDown() {
        return this.pPosicao.moveDown();
    }

    public boolean moveRight() {
        return this.pPosicao.moveRight();
    }

    public boolean moveLeft() {
        return this.pPosicao.moveLeft();
    }
    
    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
    
    public void Empurrar(ArrayList<Elemento> e, ControleDeJogo cj, Elemento A){
        
        if(A == null) return;
  
        if(A.isbEmpuravel()){
            if(this.pPosicao.getLinha()-this.pPosicao.getLinhaAnterior() > 0)
                A.moveDown();
            if(this.pPosicao.getLinha()-this.pPosicao.getLinhaAnterior() < 0)
                A.moveUp();
            if(this.pPosicao.getColuna()-this.pPosicao.getColunaAnterior() > 0)
                A.moveRight();
            if(this.pPosicao.getColuna()-this.pPosicao.getColunaAnterior() < 0)
                A.moveLeft();
            if (!cj.ehPosicaoValida(e, A.getPosicao())){
                A.voltaAUltimaPosicao();
                this.voltaAUltimaPosicao();
            }
        }
        
    }
}
