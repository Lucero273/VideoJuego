package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{

    private final static int numeroTeclas = 120;
    //hacemos un arreglo de booleanos
    private final boolean[] teclas = new boolean[numeroTeclas];
    
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    
    public void actualizar(){ 
        //la variable arriba va a equivaler a pulsar la tecla W
        arriba = teclas[KeyEvent.VK_W];
        
        //la variable abajo va a equivaler a pulsar la tecla S
        abajo = teclas[KeyEvent.VK_S];
        
        //la variable izquierda equivale a pulsar la tecla A
        izquierda = teclas[KeyEvent.VK_A];
        
        //la variable derechabequivale a pulsar la tecla D
        derecha = teclas[KeyEvent.VK_D];

    }
    
    @Override //pulsar y soltar la tecla
    public void keyTyped(KeyEvent ke) {
        

    }

    
    @Override //tecla pulsada
    public void keyPressed(KeyEvent ke) {
        teclas[ke.getKeyCode()] = true;

    }

    
    @Override //tecla soltada
    public void keyReleased(KeyEvent ke) {
        teclas[ke.getKeyCode()] = false;

        
    }
    
}
