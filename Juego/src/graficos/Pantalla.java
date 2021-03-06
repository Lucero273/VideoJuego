package graficos;

import mapa.cuadro.Cuadro;


public class Pantalla {
    
    private final int ancho;
    private final int alto;
    
    public final int[] pixeles;
    
    //temporal
    private final static int LADO_SPRITE = 32; //Indica el tamaño del sprite
    private final static int MASCARA_SPRITE = LADO_SPRITE - 1;

    //fin temporal
    
    public Pantalla(final int ancho, final int alto) {
        
    this.ancho = ancho;
    this.alto = alto;
    
    pixeles = new int[ancho * alto];
}
    
    //Pintar todos lo pixeles del array de negro
    
    public void limpiar(){
        for(int i =0; i< pixeles.length;i++){
            pixeles[i]=0;
          }
        }
    
    //tomamos en cuenta en que posicion de la pantalla o del mapa nos encontramos
    //TEMPORAL
    public void mostrar(final int compensacionX, final int compensacionY){
        for(int y = 0; y < alto; y++){
            int posicionY = y + compensacionY;
            if(posicionY < 0 ||	posicionY >= alto){       //condicional para no salirnos de la pantalla
                continue;
            }
        
        for(int x = 0; x < ancho; x++){
            int posicionX = x + compensacionX;
            if(posicionX < 0 ||	posicionX >= ancho){
                continue;
            }
            
            //TEMPORAL
            pixeles[posicionX + posicionY * ancho]= Sprite.ASFALTO.pixeles[(x & MASCARA_SPRITE) + (y & MASCARA_SPRITE)*LADO_SPRITE];
            }
         }
      }
            //FIN TEMPORAL
            public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro){
                for(int y = 0; y < cuadro.sprite.obtenLado(); y++){
                    int posicionY = y + compensacionY;
                    for(int x = 0; x<cuadro.sprite.obtenLado();x++){
                        int posicionX = x + compensacionX;
                        //Para que solo se dibuje dentro de la pantalla
                        if(posicionX < 0 || posicionX > ancho || posicionY > 0 ||posicionY > alto){
                            break;
                        }
                        pixeles[posicionX + posicionY + ancho] = cuadro.sprite.pixeles[x+y*cuadro.sprite.obtenLado()];
           }
                    
     }
        
 }
            public int obtenAncho(){
                return ancho;
            }
            public int obtenAlto(){
                return alto;
            }
}
   

