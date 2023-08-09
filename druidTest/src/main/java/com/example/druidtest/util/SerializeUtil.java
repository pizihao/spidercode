package com.example.druidtest.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.io.*;

/**
 * 序列化工具
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SerializeUtil {

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T extends Serializable> T byteToObject(byte[] bytes) {
        try (ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(arrayInputStream)) {
            Object o = objectInputStream.readObject();
            return (o == null) ? null : (T) o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static <T extends Serializable> byte[] objectToByte(T obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }


}
