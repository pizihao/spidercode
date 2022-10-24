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

import com.binder.ElementUnit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * @author Create by liuwenhao on 2022/10/12 16:06
 */
public class ArrayElement implements Element {
    @Override
    public boolean isSupport(Type type) {
        if (type instanceof ParameterizedType) {
            return false;
        }
        return ((Class<?>) type).isArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(ElementUnit elementUnit, Elements elements) {
        if (!elements.isSupport(this)) {
            return null;
        }
        Collection<Object> parser = elements.getResult(elementUnit);
        return (T) parser.toArray();
    }
}
