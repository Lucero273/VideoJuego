package mapa;

import graficos.Pantalla;

public abstract class Mapa {
    protected int ancho;
    protected int alto;
    
    protected int[] cuadros;
    
    public Mapa(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
        //Iinicializamos el array
        cuadros = new int[ancho*alto];
        //Método
        generarMapa();
    }
    public Mapa(String ruta){
        cargarMapa(ruta);
    }
    
    protected void generarMapa(){
        
    }
    private void cargarMapa(String ruta){
    }
     
    public void actualizar(){
        }
        
    public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla){
        int o = compensacionX >> 5; // /32
        int e = (compensacionX + pantalla.obtenAncho()) >>5;
        int n = compensacionY >> 5;
        int s = compensacionY + pantalla.obtenAlto() >>5;
        }

}
