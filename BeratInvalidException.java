
package Responsi1;
// Custom Exception untuk menangani berat barang <= 0 (Modul 3)
public class BeratInvalidException extends Exception {
    
    // Konstruktor dengan parameter pesan error
    public BeratInvalidException(String message) {
        super(message);
    }
}