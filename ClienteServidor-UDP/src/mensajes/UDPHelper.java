package mensajes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
  * Clase de ayuda para la serialización. 
  * @author cllamas
  */
public class UDPHelper {
        private final ByteArrayOutputStream bos;
        private final ByteArrayInputStream  bis;
        
        public UDPHelper(byte[] entrada) {
            bos = new ByteArrayOutputStream();
            bis = new ByteArrayInputStream(entrada);
        }
        
        public byte[] aBytes(MensajeProtocolo mensaje) {     
            bos.reset();
            try (ObjectOutput out = new ObjectOutputStream(bos)) {   
                out.writeObject(mensaje);
                return bos.toByteArray();
            } catch (IOException ioe) {
                return null;
            } 
        }
        
        public MensajeProtocolo aMensaje() {
            bis.reset(); /* sólo leemos un objeto en cada datagrama */
            
            try (ObjectInput in = new ObjectInputStream(bis)) {
                return (MensajeProtocolo) in.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                return null;
            } 
        }
    }