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
import java.util.LinkedList;
import java.util.List;

/**
 * Supported type elements
 *
 * @author Create by liuwenhao on 2022/10/12 16:05
 */
public interface Element {
    // order
    List<Element> elements = new LinkedList<>();

    static List<Element> getElement() {
        if (elements.isEmpty()) {
            elements.add(new SimpleElement());
            elements.add(new EnumElement());
            elements.add(new ArrayElement());
            elements.add(new CollectionElement());
            elements.add(new MapElement());
            elements.add(new ObjectElement());
        }
        return elements;
    }

    static <T> T getResult(String prefix, String elementName, List<SourceName> sourceNames, Type type) {
        T t = null;
        for (Element element : elements) {
            if (element.isSupport(type)) {
                t = element.parser(prefix, elementName, sourceNames, type);
            }
        }
        return t;
    }

    /**
     * Enumeration of element resolution
     *
     * @return ElementEnum
     */
    ElementEnum supportType();

    boolean isSupport(Type type);

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
        return (obj == null ? null : (T) obj);
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
