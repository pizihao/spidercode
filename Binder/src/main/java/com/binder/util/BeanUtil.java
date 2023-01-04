package com.binder.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Bean util.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtil {

    /**
     * getter for properties
     *
     * @param o              obj
     * @param propertiesName name
     * @return Method for get
     */
    public static Method getter(Class<?> o, String propertiesName) {
        if (o == null) {
            return null;
        }
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(propertiesName, o);
            return descriptor.getReadMethod();
        } catch (IntrospectionException e) {
            throw new RuntimeException("not find getter for" + propertiesName + "in" + o.getName(), e);
        }
    }

    /**
     * setter for properties
     *
     * @param o              obj
     * @param propertiesName name
     * @return Method for set
     */
    public static Method setter(Class<?> o, String propertiesName) {
        if (o == null) {
            return null;
        }
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(propertiesName, o);
            return descriptor.getWriteMethod();
        } catch (IntrospectionException e) {
            throw new RuntimeException("not find setter for" + propertiesName + "in" + o.getName(), e);
        }
    }
}
