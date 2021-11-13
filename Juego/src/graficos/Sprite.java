package graficos;


public class Sprite {
    private final int lado;
    
    private int x;
    private int y;
    
    public int[] pixeles;
    private HojaSprites hoja;
    
    //Coleccion de Sprites
    public static final Sprite VACIO = new Sprite(64,0);
    public static final Sprite ASFALTO = new Sprite(64,2,0,HojaSprites.madera );
    
    //Fin de la coleccion
    
    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja){
        this.lado=lado;
        
        pixeles = new int[lado*lado];
        
        this.x = columna*lado;
        this.y = fila*lado;
        this.hoja = hoja;
        
        //Recorremos el sprite
        for(int y = 0; y < lado; y++){
            for(int x = 0; x < lado; x++){
                pixeles[x + y * lado] = hoja.pixeles[(x+this.x)+(y+this.y)* hoja.obtenAncho()];
            }
        }

    }
    
    public Sprite(final int lado, final int color){
        this.lado = lado;
        pixeles = new int[lado*lado];
        
        for(int i = 0; i < pixeles.length; i++){
            pixeles[i] = color;
        }
    }
    
    public int obtenLado(){
        return lado;
    }
}
