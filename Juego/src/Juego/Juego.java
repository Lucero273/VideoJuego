package Juego;

import control.Teclado;
import graficos.Pantalla;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Juego extends Canvas implements Runnable{ //Convertimos la clase principal en un Canvas
    private static final long serialVersionUID = 1L;
    
    //Dimensiones de la ventana
    
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    
    private static volatile boolean enFuncionamiento = false;
    
    private static final String NOMBRE = "Juego";
    private static int aps = 0;
    private static int fps = 0;
    
    private static int x = 0;
    private static int y = 0;

    
    private static JFrame ventana;
    private static Thread thread;
    private static Teclado teclado;
    private static Pantalla pantalla;

    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
    
    private Juego(){
        setPreferredSize(new Dimension(ANCHO, ALTO));
        
        pantalla = new Pantalla(ANCHO, ALTO);
        
        //iniciamos "teclado"
        teclado = new Teclado();
        
        //para detectar todas las teclas que se pulsen en esta clase
        addKeyListener(teclado);
        
        //Iniciamos el objeto ventana
        
        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    //Método Main
    
     public static void main(String[] args){
         Juego juego = new Juego();
         juego.iniciar();
     }
     
     //Método inico
     
     private synchronized void iniciar(){
         enFuncionamiento = true; 
         
         thread = new Thread(this, "Graficos");
         thread.start();
     }
     
     //Método para detenr el juego
     
     private synchronized void detener(){
        
         enFuncionamiento = false;

         try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
     //Método de actualización
     
     private void actualizar(){ //la actualizaciòn funcionarà 60 veces por segundo
         teclado.actualizar();
         
         if(teclado.arriba){
             System.out.println("Arriba");
         }
         if(teclado.abajo){
             System.out.println("Abajo");
         }
         if(teclado.izquierda){
             System.out.println("Izquierda");
         }
         if(teclado.derecha){
             System.out.println("Derecha");
         }
         aps++;
     }
     
     //Método para mostar en la ventana
     
     private void mostrar(){
         
         BufferStrategy estrategia = getBufferStrategy();
         
         if (estrategia == null){
             //creamos un triple buffer - como una cola de imagenes 
             createBufferStrategy(3);
             return; 
         }
         
         pantalla.limpiar();
         pantalla.mostrar(x,y);
         
         System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
         
         //objeto graficos, es el que se encargara de dibujar lo que este dentro del Buffer estrategia
         Graphics g = estrategia.getDrawGraphics();
         g.drawImage(imagen, 0, 0, getWidth(),getHeight(),null );
         g.dispose();
         
         estrategia.show();

         fps++;
     }       
        

    @Override
    public void run() {
        
        //Temporización
        
        final int NS_POR_SEGUNDO = 1000000000;//NS=Nanosegundos 
        final byte APS_OBJETIVO = 60; //puede variar
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
         //le decimos a Java que nuestra ventana tenga el foco
        requestFocus();
        
        //mientras el funcionamiento sea verdadero se ejecutará esto
        while (enFuncionamiento){
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            while(delta >= 1){
                actualizar();
                delta--;
            }
            mostrar();
            
            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                ventana.setTitle(NOMBRE + " || APS: "+aps+ " || FPS: "+fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
                
            }
        }
    }
}
