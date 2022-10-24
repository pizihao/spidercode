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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Create by liuwenhao on 2022/10/12 16:21
 */
public class SimpleElement implements Element {

    List<Class<?>> simple = new LinkedList<>();

    public SimpleElement() {
        simple.add(Integer.class);
        simple.add(Float.class);
        simple.add(Character.class);
        simple.add(Long.class);
        simple.add(Double.class);
        simple.add(Short.class);
        simple.add(Boolean.class);
        simple.add(Byte.class);
        simple.add(String.class);
    }

    @Override
    public ElementEnum supportType() {
        return ElementEnum.SIMPLE;
    }

    @Override
    public boolean isSupport(Type type) {
        if (type instanceof ParameterizedType) {
            return false;
        }
        Class<?> cls = (Class<?>) type;
        return isSimple(cls);
    }

    private boolean isSimple(Class<?> cls) {
        for (Class<?> sim : simple) {
            if (sim.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }
}
