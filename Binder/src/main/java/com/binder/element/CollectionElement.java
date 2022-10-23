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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Create by liuwenhao on 2022/10/12 16:12
 */
public class CollectionElement implements Element {
    @Override
    public ElementEnum supportType() {
        return ElementEnum.COLLECTION;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(String prefix, String name, List<SourceName> e, Type type) {
        boolean b = type instanceof ParameterizedType;
        if (!b) {
            // is not ParameterizedType, It's impossible to parse
            return null;
        }
        // group for index
        Map<Integer, List<SourceName>> map = e.stream()
                .filter(s -> s.getSimpleName().equals(name))
                .collect(Collectors.groupingBy(SourceName::getIndex));
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        List<Object> list = new LinkedList<>();
        if (typeArguments.length > 0) {
            Type typeArgument = typeArguments[0];
            Class<?> cls = (Class<?>) typeArgument;
            map.forEach((i, s) -> list.add(objectElement.parser(prefix, name, s, cls)));

        }
        return (T) list;
    }
}
