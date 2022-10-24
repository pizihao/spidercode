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
import com.binder.source.SourceName;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Create by liuwenhao on 2022/10/12 16:12
 */
public class MapElement implements Element {

    @Override
    public boolean isSupport(Type type) {
        if (type instanceof ParameterizedType) {
            Class<?> cls = (Class<?>) ((ParameterizedType) type).getRawType();
            return Map.class.isAssignableFrom(cls);
        } else {
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parser(ElementUnit elementUnit, Elements elements) {
        if (elements.isSupport(this)) {
            return null;
        }
        boolean b = elementUnit.getType() instanceof ParameterizedType;
        List<SourceName> e = elementUnit.getSourceNames();
        String prefix = elementUnit.getPrefix();
        if (!b) {
            // is not ParameterizedType, It's impossible to parse
            return null;
        }
        return (T) e.stream()
                .filter(s -> s.getPrefix().equals(prefix))
                .collect(Collectors.toMap(
                        SourceName::getOriginalName,
                        SourceName::getObj,
                        (f, l) -> f
                ));
    }
}
