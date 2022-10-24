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
import com.binder.element.Element;
import com.binder.mapper.DefaultSourceMapper;
import com.binder.mapper.SourceMapper;
import com.binder.special.Special;
import com.binder.tes.BootstrapConfigProperties;
import com.binder.util.Constants;
import com.binder.util.StringUtil;

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
     * map for key
     */
    SourceMapper sourceMapper;

    /**
     * prefix
     */
    String prefix;

    public MapPropertiesSource(Map<String, Object> map, SourceMapper sourceMapper, String prefix, List<Special<?>> specials) {
        this.sourceMapper = sourceMapper;
        this.prefix = prefix;
        transMap(map, prefix, sourceMapper);
        buildSource();
        this.placeholdersResolver = new PlaceholdersResolver(this.sourceNames, specials);
        this.sourceNames = placeholdersResolver.getSourceName();
    }

    public MapPropertiesSource(Map<String, Object> map, SourceMapper sourceMapper, String prefix) {
        this(map, sourceMapper, prefix, null);
    }

    public MapPropertiesSource(Map<String, Object> map, String prefix) {
        this(map, new DefaultSourceMapper(), prefix, null);
    }


    public MapPropertiesSource(Map<String, Object> map, String prefix, List<Special<?>> specials) {
        this(map, new DefaultSourceMapper(), prefix, specials);
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
        if (s.contains(Constants.SPOT)) {
            int index = s.indexOf(Constants.SPOT);
            supplement = Constants.SPOT + s.substring(0, index);
            s = s.substring(index + 1);
        }
        if (!s.contains(Constants.LEFT_BRACKETS) || !s.contains(Constants.RIGHT_BRACKETS)) {
            return new SourceName(prefix + supplement, s, -1, o);
        }
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

    public static void main(String[] args) {
        Map<String, Object> a = new HashMap<>();
        a.put("spring.application.name", "threadPool");
        a.put("spring.dynamic.thread-pool.active-alarm", 80);
        a.put("spring.dynamic.thread-pool.alarm", false);
        a.put("spring.dynamic.thread-pool.alarm-interval", 8);
        a.put("spring.dynamic.thread-pool.banner", true);
        a.put("spring.dynamic.thread-pool.capacity-alarm", 80);
        a.put("spring.dynamic.thread-pool.check-state-interval", 3000);
        a.put("spring.dynamic.thread-pool.collect", true);
        a.put("spring.dynamic.thread-pool.config-file-type", "yaml");
        a.put("spring.dynamic.thread-pool.enable", false);
        a.put("spring.dynamic.thread-pool.executors[0].active-alarm", 80);
        a.put("spring.dynamic.thread-pool.executors[0].alarm", true);
        a.put("spring.dynamic.thread-pool.executors[0].allow-core-thread-time-out", true);
        a.put("spring.dynamic.thread-pool.executors[0].blocking-queue", "LinkedBlockingQueue");
        a.put("spring.dynamic.thread-pool.executors[0].capacity-alarm", 80);
        a.put("spring.dynamic.thread-pool.executors[0].core-pool-size", 1);
        a.put("spring.dynamic.thread-pool.executors[0].execute-time-out", 1000);
        a.put("spring.dynamic.thread-pool.executors[0].keep-alive-time", 1024);
        a.put("spring.dynamic.thread-pool.executors[0].maximum-pool-size", 1);
        a.put("spring.dynamic.thread-pool.executors[0].queue-capacity", 1);
        a.put("spring.dynamic.thread-pool.executors[0].rejected-handler", "AbortPolicy");
        a.put("spring.dynamic.thread-pool.executors[0].thread-name-prefix", "message-consume");
        a.put("spring.dynamic.thread-pool.executors[0].thread-pool-id", "message-consume");
        a.put("spring.dynamic.thread-pool.executors[1].active-alarm", 80);
        a.put("spring.dynamic.thread-pool.executors[1].alarm", true);
        a.put("spring.dynamic.thread-pool.executors[1].allow-core-thread-time-out", true);
        a.put("spring.dynamic.thread-pool.executors[1].blocking-queue", "LinkedBlockingQueue");
        a.put("spring.dynamic.thread-pool.executors[1].capacity-alarm", 80);
        a.put("spring.dynamic.thread-pool.executors[1].core-pool-size", 3);
        a.put("spring.dynamic.thread-pool.executors[1].execute-time-out", 1000);
        a.put("spring.dynamic.thread-pool.executors[1].keep-alive-time", 1024);
        a.put("spring.dynamic.thread-pool.executors[1].maximum-pool-size", 10);
        a.put("spring.dynamic.thread-pool.executors[1].nodes", "192.168.137.1:58866");
        a.put("spring.dynamic.thread-pool.executors[1].queue-capacity", 1);
        a.put("spring.dynamic.thread-pool.executors[1].rejected-handler", "AbortPolicy");
        a.put("spring.dynamic.thread-pool.executors[1].thread-name-prefix", "message-produce");
        a.put("spring.dynamic.thread-pool.executors[1].thread-pool-id", "message-produce");
        a.put("spring.dynamic.thread-pool.nacos.data-id", "AbortPolicy");
        a.put("spring.dynamic.thread-pool.nacos.group", "DEFAULT_GROUP");
        a.put("spring.dynamic.thread-pool.notify-platforms[0].platform", "WECHAT");
        a.put("spring.dynamic.thread-pool.notify-platforms[0].token", "xxx");
        a.put("spring.dynamic.thread-pool.notify-platforms[1].platform", "DING");
        a.put("spring.dynamic.thread-pool.notify-platforms[1].secret", "xxx");
        a.put("spring.dynamic.thread-pool.notify-platforms[1].token", "xxx");
        a.put("spring.dynamic.thread-pool.notify-platforms[2].platform", "LARK");
        a.put("spring.dynamic.thread-pool.notify-platforms[2].token", "xxx");
        a.put("spring.dynamic.thread-pool.tomcat.core-pool-size", .120);
        a.put("spring.dynamic.thread-pool.tomcat.enable", true);
        a.put("spring.dynamic.thread-pool.tomcat.keep-alive-time", 1007);
        a.put("spring.dynamic.thread-pool.tomcat.maximum-pool-size", 200);
        a.put("spring.dynamic.thread-pool.tomcat.nodes", "*:*");

        Source source = new MapPropertiesSource(a, "spring.dynamic.thread-pool");
        source.getSourceValue().forEach(s -> {
            if (s.getIndex() != -1) {
                System.out.println(s.getQualifiedName());
            }
        });

        BootstrapConfigProperties result = source.getResult(BootstrapConfigProperties.class);
        System.out.println(result);

    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public <T> T getResult(Class<T> cls) {
        return Element.getResult(prefix, cls.getSimpleName(), sourceNames, cls);
    }

    @Override
    public List<SourceName> getSourceValue() {
        return sourceNames;
    }

}
