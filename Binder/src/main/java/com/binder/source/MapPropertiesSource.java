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

import com.binder.PlaceholdersResolver;
import com.binder.mapper.DefaultSourceMapper;
import com.binder.mapper.SourceMapper;
import com.binder.util.Constants;
import com.binder.util.StringUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
                this.map.put(key, convertKey);
            }
        });
    }

    /**
     * Convert to SourceName, note the placeholder
     */
    private void buildSource() {
        this.map.forEach((s, o) -> {
            if (s.contains(Constants.LEFT_BRACKETS) && s.contains(Constants.RIGHT_BRACKETS)) {
                int leftIndex = s.indexOf(Constants.LEFT_BRACKETS);
                int rightIndex = s.lastIndexOf(Constants.RIGHT_BRACKETS);
                String elementName = s.substring(0, leftIndex);
                int index;
                try {
                    index = Integer.parseInt(s.substring(leftIndex + 1, rightIndex));
                } catch (NumberFormatException e) {
                    index = -1;
                }
                SourceName sourceName = new SourceName(prefix, elementName, index, o);
                sourceNames.add(sourceName);
            } else {
                SourceName sourceName = new SourceName(prefix, s, -1, o);
                sourceNames.add(sourceName);
            }
        });
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public <T> T getResult(Class<T> cls) {

        return null;
    }

    @Override
    public List<SourceName> getSourceValue() {
        return sourceNames;
    }
}
