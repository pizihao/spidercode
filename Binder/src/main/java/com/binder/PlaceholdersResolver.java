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

package com.binder;

import com.binder.source.SourceName;
import com.binder.special.RandomSpecial;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Handle placeholders, allowing {@code ${}} to refer to data in the current context in configuration files
 *
 * @author Create by liuwenhao on 2022/10/12 17:14
 */
public class PlaceholdersResolver {

    public static final String PREFIX = "${";

    public static final String SUFFIX = "}";

    Map<String, SourceName> map = new HashMap<>();

    Map<String, SourceName> middleMap = new HashMap<>();

    /**
     * order
     */
    List<SourceName> sourceNames;

    static Map<String, Function<String, Object>> objMap = new HashMap<>();

    static {
        objMap.put("random", new RandomSpecial(new Random()));
    }

    public PlaceholdersResolver(List<SourceName> sourceNames) {
        this.sourceNames = sourceNames;
        sourceNames.forEach(s -> {
            String qualifiedName = s.getQualifiedName();
            map.put(qualifiedName, s);
        });
    }

    /**
     * Handle placeholders
     *
     * @return new sourceName
     */
    public List<SourceName> getSourceName() {
        placeholderHandle();
        return sourceNames.stream()
                .map(s -> this.map.get(s.getQualifiedName()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Handle placeholders
     */
    private void placeholderHandle() {
        while (hasPlaceholder()) {
            this.map.forEach((s, sourceName) -> {
                SourceName handle = handle(sourceName);
                middleMap.put(s, handle);
            });
            if (middleMap.isEmpty()) {
                break;
            }
            this.map.putAll(middleMap);
            middleMap.clear();
        }
    }

    /**
     * Determine whether a placeholder exists
     *
     * @return has placeholder
     */
    private boolean hasPlaceholder() {
        for (SourceName sourceName : this.map.values()) {
            String objStr = sourceName.objToString();
            if (objStr.contains(PREFIX) && objStr.contains(SUFFIX)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check sourceName, If a placeholder exists, it is processed
     *
     * @param sourceName ole sourceName
     * @return new sourceName
     */
    private SourceName handle(SourceName sourceName) {
        String objStr = sourceName.objToString();
        if (!objStr.contains(PREFIX) || !objStr.contains(SUFFIX)) {
            return sourceName;
        }
        // the first and the last
        int leftIndex = objStr.indexOf(PREFIX);
        int rightIndex = objStr.lastIndexOf(SUFFIX);
        String s = objStr.substring(leftIndex, rightIndex);
        // universal
        SourceName name = map.get(s);
        if (name != null) {
            sourceName.setObj(name.getObj());
        }
        // special
        String prefix = s.split("\\.")[0];
        Function<String, Object> function = objMap.get(prefix);
        if (function != null) {
            sourceName.setObj(function.apply(s));
        }
        return sourceName;
    }

}
