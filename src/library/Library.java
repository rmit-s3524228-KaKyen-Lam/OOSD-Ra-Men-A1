package library;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author David Limantoro s3503728 on 2017/05/21.
 */
public class Library {

    public static Object deepCopy(Object toCopy) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(toCopy);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            Object deepCopiedTarget = new ObjectInputStream(bais).readObject();
            return deepCopiedTarget;
        } catch (Exception e) {
            return null;
        }
    }

    public static Object[] deepCopyArray(Object toCopy[]) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(toCopy);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            Object[] deepCopiedTarget = (Object[]) new ObjectInputStream(bais).readObject();
            return deepCopiedTarget;
        } catch (Exception e) {
            return null;
        }
    }
}
