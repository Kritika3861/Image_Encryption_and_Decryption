//Encryption and decryption of an image in JAVA
package kritika;
import javax.crypto.spec.SecretKeySpec; 
import javax.crypto.Cipher;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ImageEncryption {

	//AES value is assigned to constant variable of type string 
    private static final String ALGORITHM = "AES";
    //Transformation to be used for encryption and decryption
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    //Secret key assigned to the KEY
   private static final String KEY = "12345678123456781234567812345678";
   
   
   //ENCRYPTION PROCESS  
    public static void encryptImage(String inputImagePath, String outputImagePath) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);//convert the key into array of bytes & algorithm=AES
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);//creates a 'cipher' object for encryption and decryption 
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);//initialise teh 'Cipher' object for encryption mode with a secret key
            
            byte[] inputFileBytes = Files.readAllBytes(Paths.get(inputImagePath));//read the contents of an input image file & store them as an array of bytes
            byte[] encryptedBytes = cipher.doFinal(inputFileBytes);//performs the encryption operation on input byte array and store it in 'encryptedBytes'
            
            FileOutputStream outputStream = new FileOutputStream(outputImagePath);// Create a FileOutputStream object to write to the specified file path
            outputStream.write(encryptedBytes); // Write the byte array (encryptedBytes) to the file  
            outputStream.close();

            System.out.println("Image encrypted and saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //DECRYPTION PROCESS
    //follow the same steps but now the mode is DECRYPTION
    public static void decryptImage(String inputImagePath, String outputImagePath) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);//Decryption using the same secret key that was used for encryption

            byte[] inputFileBytes = Files.readAllBytes(Paths.get(inputImagePath));
            byte[] decryptedBytes = cipher.doFinal(inputFileBytes);

            FileOutputStream outputStream = new FileOutputStream(outputImagePath);
            outputStream.write(decryptedBytes);
            outputStream.close();

            System.out.println("Image decrypted and saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	//copy images path 
        String inputImagePath = "C:\\Users\\kriti\\OneDrive\\Pictures\\Saved Pictures\\download (4).jpeg";
        String encryptedImagePath = "C:\\Users\\kriti\\OneDrive\\Pictures\\Saved Pictures\\20230218_164303.jpg";
        String decryptedImagePath = "C:\\Users\\kriti\\OneDrive\\Pictures\\Saved Pictures\\20230218_164324.jpg";

        encryptImage(inputImagePath, encryptedImagePath);
        decryptImage(encryptedImagePath, decryptedImagePath);
    }
}
