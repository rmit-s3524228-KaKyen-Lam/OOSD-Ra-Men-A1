package library;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class purpose is to create deep copy of an object, essentially cloning them without using Cloneable interface
 *
 * @author David Limantoro s3503728 on 2017/05/21.
 */
public class DeepCopier {

    /**
     * Create a deep copy of the object toCopy.
     *
     * @param toCopy object to copy
     * @return Deep copied object, as an Object object
     */
    public static Object copy(Object toCopy) {
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

    /**
     * Create a deep copy of the object array toCopy.
     *
     * @param toCopy object array to copy
     * @return Deep copied object, as an Object object
     */
    public static Object[] copyArray(Object toCopy[]) {
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
