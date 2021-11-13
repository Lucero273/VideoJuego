package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public abstract class Cuadro { //clase que no puede instanciarse
    public int x;
    public int y;
    
    public Sprite sprite;
    
    //Coleccion de cuadros
    public static final Cuadro VACIO = new CuadroVacio(Sprite.VACIO);
    public static final Cuadro ASFALTO = new CuadroAsfalto(Sprite.ASFALTO);
    //fin de la coleccion de cuadros
    
    //Constructor
    public Cuadro(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void mostrar(int x, int y, Pantalla pantalla){
    }
    //metodo para saber si el tile a atravesar es solido
    public boolean solid(){
        return false;
    }
}
