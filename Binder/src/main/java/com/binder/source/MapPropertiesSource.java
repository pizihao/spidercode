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

package com.binder.source;

import cn.hutool.core.util.ArrayUtil;
import com.binder.PlaceholdersResolver;
import com.binder.element.Element;
import com.binder.mapper.DefaultSourceMapper;
import com.binder.mapper.SourceMapper;
import com.binder.util.Constants;
import com.binder.util.StringUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Configuration information parsing and processing
 */
public class MapPropertiesSource implements Source {

    /**
     * The obtained configuration information is displayed
     */
    Map<String, Object> map = new HashMap<>();

    /**
     * Placeholders and special symbols are handled by {@code $, {,}} to identify the information<br>
     * <ol>
     *     <li>Context of reference：{@code ${spring.application.name}}</li>
     * </ol>
     * A placeholder identifier will take items that are available in the current configuration, which can be nested<br>
     */
    PlaceholdersResolver placeholdersResolver;

    /**
     * Configuration items
     */
    List<SourceName> sourceNames = new LinkedList<>();

    /**
     * 映射机制
     */
    SourceMapper sourceMapper;

    /**
     * 前缀
     */
    String prefix;

    public MapPropertiesSource(Map<String, Object> map, SourceMapper sourceMapper, String prefix) {
        this.sourceMapper = sourceMapper;
        this.prefix = prefix;
        transMap(map, prefix, sourceMapper);
        buildSource();
        this.placeholdersResolver = new PlaceholdersResolver(this.sourceNames);
        this.sourceNames = placeholdersResolver.getSourceName();
    }

    public MapPropertiesSource(Map<String, Object> map, String prefix) {
        this(map, new DefaultSourceMapper(), prefix);
    }

    /**
     * The transformation extracts configuration information
     *
     * @param cumbersomeMap properties source
     * @param prefix        the prefix
     * @param sourceMapper  Map processing
     */
    private void transMap(Map<String, Object> cumbersomeMap, String prefix, SourceMapper sourceMapper) {
        cumbersomeMap.forEach((s, o) -> {
            boolean startWith = StringUtil.startWith(s, prefix);
            if (startWith) {
                String key = StringUtil.replace(s, prefix + Constants.SPOT, Constants.NULL);
                String convertKey = sourceMapper.convert(key);
                this.map.put(convertKey, o);
            }
        });
    }

    /**
     * Convert to SourceName, note the placeholder
     */
    private void buildSource() {
        this.map.forEach((s, o) -> this.sourceNames.add(extract(s, "", o)));
    }

    private SourceName extract(String s, String supplement, Object o) {
        if (!s.contains(Constants.LEFT_BRACKETS) || !s.contains(Constants.RIGHT_BRACKETS)) {
            return new SourceName(prefix + supplement, s, -1, o);
        } else {
            // the first
            int leftFirstIndex = s.indexOf(Constants.LEFT_BRACKETS);
            int rightFirstIndex = s.indexOf(Constants.RIGHT_BRACKETS);
            // the last
            int leftLastIndex = s.lastIndexOf(Constants.LEFT_BRACKETS);
            int rightLastIndex = s.lastIndexOf(Constants.RIGHT_BRACKETS);
            if (leftLastIndex == leftFirstIndex && rightLastIndex == rightFirstIndex && s.endsWith(Constants.RIGHT_BRACKETS)) {
                String elementName = s.substring(0, leftFirstIndex);
                int index;
                try {
                    index = Integer.parseInt(s.substring(leftFirstIndex + 1, rightFirstIndex));
                } catch (NumberFormatException e) {
                    index = -1;
                }
                return new SourceName(prefix + supplement, elementName, index, o);
            } else {
                String nextOne = s.substring(rightFirstIndex + 2);
                String nextSupplement = s.substring(0, rightFirstIndex + 1);
                SourceName sourceName = extract(nextOne, "." + nextSupplement, o);
                this.sourceNames.add(sourceName);
                return new SourceName(prefix + supplement, nextSupplement, -1, sourceName);
            }
        }
    }

    public static void main(String[] args) {
        Map<String, Object> a = new HashMap<>();
        a.put("spring.dynamic.thread-pool.notify-platforms[0].platform", "WECHAT");
        a.put("spring.dynamic.thread-pool.notify-platforms[0].secret-key", "ac0426a5-c712-474c-9bff-72b8b8f5caff");
        a.put("spring.dynamic.thread-pool.notify-platforms[1].platform", "DING");
        a.put("spring.dynamic.thread-pool.notify-platforms[1].secret-key", "56417ebba6a27ca352f0de77a2ae9da66d01f39610b5ee8a6033c60ef9071c55");
        a.put("spring.dynamic.thread-pool.notify-platforms[2].platform", "LARK");
        a.put("spring.dynamic.thread-pool.notify-platforms[2].secret-key", "2cbf2808-3839-4c26-a04d-fd201dd51f9e");
        a.put("spring.dynamic.thread-pool.executors[0]", "message-consume");
        a.put("spring.dynamic.thread-pool.executors[1]", "message-prudcet");

        MapPropertiesSource source = new MapPropertiesSource(a, "spring.dynamic.thread-pool");
        source.sourceNames.forEach(s -> {
            System.out.println(s.getQualifiedName());
            System.out.println(s.getElementName());
            System.out.println(s.getSimpleName());
            System.out.println("=============");
        });
        System.out.println(source.sourceNames.size());


        System.out.println(Integer.class.isPrimitive());

    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public <T> T getResult(Class<T> cls) {
        return Element.getResult(cls.getSimpleName(), sourceNames, cls);
    }

    @Override
    public List<SourceName> getSourceValue() {
        return sourceNames;
    }

}
