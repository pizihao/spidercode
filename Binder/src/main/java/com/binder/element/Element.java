/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.binder.element;

import com.binder.source.SourceName;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Supported type elements
 *
 * @author Create by liuwenhao on 2022/10/12 16:05
 */
public interface Element {
    Element objectElement = new ObjectElement();
    Element simpleElement = new SimpleElement();
    Element arrayElement = new ArrayElement();
    Element collectionElement = new CollectionElement();
    Element mapElement = new MapElement();
    Element enumElement = new EnumElement();

    static <T> T getResult(String prefix, String elementName, List<SourceName> sourceNames, Type type) {
        return objectElement.parser(prefix, elementName, sourceNames, type);
    }

    /**
     * Enumeration of element resolution
     *
     * @return ElementEnum
     */
    ElementEnum supportType();

    /**
     * Parsing Configuration Information
     *
     * @param name name
     * @param e    A single configuration item
     * @param type target class
     * @param <T>  The type obtained after parsing
     * @return T The parsed type
     */
    @SuppressWarnings("unchecked")
    default <T> T parser(String prefix, String name, List<SourceName> e, Type type) {
        SourceName sourceName = e.stream()
                .filter(s -> s.getFullName().equals(prefix))
                .filter(s -> s.getSimpleName().equals(name))
                .findFirst()
                .orElse(new SourceName());
        Object obj = sourceName.getObj();
        if (obj == null) {
            return null;
        }

        return (T) obj;
        /*
         Class<?> cls = (Class<?>) type;
        String s = (String) obj;
        if (cls.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(s);
        }
        if (cls.isAssignableFrom(Long.class)) {
            return (T) Long.valueOf(s);
        }
        if (cls.isAssignableFrom(Double.class)) {
            return (T) Double.valueOf(s);
        }
        if (cls.isAssignableFrom(Float.class)) {
            return (T) Float.valueOf(s);
        }
        if (cls.isAssignableFrom(Character.class)) {
            return (T) Character.valueOf(s.charAt(0));
        }
        if (cls.isAssignableFrom(Short.class)) {
            return (T) Short.valueOf(s);
        }
        if (cls.isAssignableFrom(Byte.class)) {
            return (T) Byte.valueOf(s);
        }
        if (cls.isAssignableFrom(Boolean.class)) {
            return (T) Boolean.valueOf(s);
        }
        return (T) cls.cast(obj);*/
    }

    enum ElementEnum {

        ARRAY,

        ENUM,

        COLLECTION,

        SIMPLE,

        OBJECT,

        MAP;

    }
}
