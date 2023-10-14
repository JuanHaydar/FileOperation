/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fileoperation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *Ejecuta diferentes operaciones con archivos, como: leer, escribir, mover, copiar, eliminar o crear archivos.
 * Se le da directamente la ruta del archivo al invocar la clase. 
 * Y si existe algun tipo de error este o mensaje de aviso, se imprime por consola.
 * @author Juan Haydar
 */
public class FileOperation {
    private String FilePath="";
    
    public FileOperation(){
    }

    public FileOperation(String FilePath){
    this.FilePath=FilePath;
    }
    
    /**
     * Recibe la ruta de acceso del archivo que desee leer, y retorna el contenido en una cadena.
     * @return String del contenido del archivo
     */
    public String leer() {
        String lineString;
        String Line="";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FilePath));
            while ((lineString = reader.readLine()) != null) {
            Line+=lineString+"\n";
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error. No se encontro el archivo a leer: "+ e.getMessage());
        } 
        return Line;
    }
    
    /**
     * Recibe la ruta de acceso del archivo que desea elejir, y escribe en el la cadena 
     * dada en el segundo parametro.Este no modifica el texto antes escrito en el archivo, si no, que añade
     * lo colocado en el parametro 'Contenido'.
     * @param Contenido Nuevo contenido añadido del archivo
     */
    public void escribir(String Contenido){
    try {
            // Abre el archivo para escritura (creará el archivo si no existe)
            BufferedWriter escritor = new BufferedWriter(new FileWriter( FilePath,true)); 

            // Escribe líneas en el archivo
            escritor.write(Contenido);
            // Cierra el BufferedWriter
            escritor.close();
            System.out.println("Contenido agregado en el archivo correctamente");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Recibe una ruta de acceso del archivo y el contenido que quiere que reescriba encima de lo que ya tiene.
     * @param Contenido Nuevo contenido del archivo
     */
    public void reescribir(String Contenido){
    try {
            // Abre el archivo para escritura (creará el archivo si no existe)
            BufferedWriter escritor = new BufferedWriter(new FileWriter( FilePath,false)); 

            // Escribe líneas en el archivo
            escritor.write(Contenido);
            // Cierra el BufferedWriter
            escritor.close();
            System.out.println("Contenido reescrito en el archivo con exito.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Mueve el archivo indicado en la primera ruta de acceso, hacia la nueva ruta y elimina el arhivo
     * en la ruta anterior. Si el nombre del archivo ya existe en esa direccion, muestra error en consola.
     * @param NewFilepath Nueva ruta y nombre del archivo
     */
    public void mover(String NewFilepath){
        File archivo = new File(FilePath);
        File destino = new File(NewFilepath);
        if(destino.exists()){
            System.out.println("Error. Este nombre de archivo ya existe en esta direccion");
        }else{
        archivo.renameTo(destino);
        archivo.delete();
        System.out.println("Archivo movido exitosamente");
        }
    }
    
    
    private static int indice(String FilePath,String caracter){
    int index=0;
    for(int i=0; i<FilePath.length();i++){
    if(FilePath.substring(i,i+1).equals(caracter)){
    index=i;
    }}
    return index+1;
    }
    
    /**
     * Copia el archivo indicado en la ruta de acceso, en el mismo directorio con el nuevo nombre indicado.
     * Si el nombre ya esta usado en la misma ruta este bota un mensaje de error.
     * @param Filename Nombre de la copia.
     */
     public void copiar(String Filename){
        try {
            Path archivoOrigen = Paths.get(FilePath);
            String NuevoNombre=FilePath.substring(0,indice(FilePath,"\\"))+Filename;
            Path archivoDestino = Paths.get(NuevoNombre);
            
            File archivo= new File(NuevoNombre);
            if(archivo.exists()){
                System.out.println("Este nombre ya existe en esta ruta");
            }else{
                Files.copy(archivoOrigen,archivoDestino);
            System.out.println("Archivo copiado exitosamente");}
        } catch (IOException e) {
            System.out.println("Error. No se encontro el archivo a copiar o ya existe en esta ruta "+ e.getMessage());
        }
        }
        
     /**
      * Elimina el archivo indicado en la ruta de acceso. Si es borrado o no correctamente muestra un mensaje.
      */
        public void eliminar(){
        try {
             File archivo = new File(FilePath);
        if (archivo.delete()) {
            System.out.println("Borrado");

        } else {
            System.out.println("No borrado");
        }
        } catch (Exception e) { 
            System.out.println("Error. Archivo a eliminar no encontrado "+ e.getMessage());
        }
        }
        
        /**
         * Crea un nuevo archivo con el nombre y en la direccion indicada.
         */
        public void Crear(){ 
        try {
            Path archivo = Paths.get(FilePath);
            Files.createFile(archivo);
            System.out.println("Archivo creado exitosamente");
        }catch(IOException e){
            System.out.println("Error. Ruta incorrecta o ya existente "+ e.getMessage());
        }}
}
