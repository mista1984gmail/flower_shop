package com.mista.soft.io;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


import java.io.*;

@UtilityClass
@Slf4j
public class SerializationUtils {
    private static final String FILENAME = "db.txt";

    public static void serialize(Object objectToSerialize) {
        try (OutputStream out = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(objectToSerialize);
            log.info("Data was saved.");
        } catch (IOException e) {
            log.error("Data was not saved.", e);
        }
    }

    public static Object deserialize() {
        Object object = null;
        try (InputStream in = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(in)) {
            object = ois.readObject();
            log.info("Data is loaded.");
        } catch (IOException | ClassNotFoundException e) {
            log.error("Data can not be loaded.", e);
        }
        return object;
    }
}
