/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Tela.java
 *
 * Created on 03/03/2011, 18:28:20
 */
package Controler;

import GameModel.*;
import Auxiliar.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.*;
import java.util.logging.*;
import java.util.zip.*;

/**
 *
 * @author junio
 */
public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {
    
    private Presidente persona;
    private ArrayList<Elemento> faseAtual;
    private ControleDeJogo cj = new ControleDeJogo();
    private EmpuraDireita cel;
    private Caveira bV;
    /**
     * Creates new form tabuleiro
     * @return 
     */
    public ArrayList<Elemento> getFase(){
        return this.faseAtual;
    }
    
    public Tela() {
        Desenho.setjCenario(this);
        initComponents();
        this.addMouseListener(this); /*mouse*/
        
        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);
        
        faseAtual = new ArrayList<Elemento>(100);

        /*Cria e adiciona elementos*/
        persona = new Presidente("Scooter.png");
        persona.setPosicao(0, 7);
        this.addElemento(persona);
        
        Cunha vossaExcremencia = new Cunha("cunha.jpg");
        vossaExcremencia.setPosicao(4, 3);
        this.addElemento(vossaExcremencia);  
        
        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("bichinho.png");
        bBichinhoH.setPosicao(3, 3);
        this.addElemento(bBichinhoH);
        
        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("bichinho.png");
        bBichinhoH2.setPosicao(6, 6);
        this.addElemento(bBichinhoH2);
        
        BichinhoVaiVemHorizontal bBichinhoH3 = new BichinhoVaiVemHorizontal("bichinho.png");
        bBichinhoH3.setPosicao(2, 9);
        this.addElemento(bBichinhoH3);
        
        bV = new Caveira("caveira.png");
        bV.setPosicao(4, 2);
        this.addElemento(bV);
        
        /*Car cC = new Car("car.gif");
        cC.setPosicao(4, 0);
        this.addElemento(cC);*/
        
        cel = new EmpuraDireita("coracao.png");
        cel.setPosicao(4, 2);
        this.addElemento(cel);
     
    }
    
    public void addElemento(Elemento umElemento) {
        faseAtual.add(umElemento);
    }
    
    public void removeElemento(Elemento umElemento) {
        faseAtual.remove(umElemento);
    }
    
    public void paint(Graphics gOld) {
        Graphics g = getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);
        /*Desenha cenário*/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "scooterbackground.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                    
                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        cel.Empurrar(faseAtual, cj);
        persona.Empurrar(this.faseAtual, cj, cj.getElem(this.faseAtual, persona.getPosicao()));
        //bV.anda(faseAtual, cj);
        this.cj.desenhaTudo(faseAtual, g2);
        this.cj.processaTudo(faseAtual);
        
        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }
    
    public void go() {
        TimerTask task = new TimerTask() {
            
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            this.faseAtual.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            persona.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            persona.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            persona.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            persona.moveRight();
        }
        if (!cj.ehPosicaoValida(this.faseAtual, persona.getPosicao())) {
            
            persona.voltaAUltimaPosicao();
        }
        else{
            persona.Empurrar(this.faseAtual, cj, cj.getElem(this.faseAtual, persona.getPosicao()));
        }
        
        this.setTitle("-> Cell: " + (persona.getPosicao().getColuna()) + ", "
                + (persona.getPosicao().getLinha()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }
    
    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado
         int x = e.getX();
         int y = e.getY();
     
         this.setTitle("X: "+ x + ", Y: " + y +
         " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));
        
         this.persona.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
         */
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2015-1 - Adventures of lolo");
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("POO2015-1 - Adventures of lolo");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseReleased(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    public void mouseDragged(MouseEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    public void keyReleased(KeyEvent e) {
    }
}
