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

import cn.hippo4j.common.constant.Constants;
import cn.hippo4j.common.toolkit.Assert;
import cn.hippo4j.common.toolkit.StringUtil;
import cn.hippo4j.config.springboot.starter.binder.PlaceholdersResolver;
import cn.hippo4j.config.springboot.starter.binder.mapper.DefaultSourceMapper;
import cn.hippo4j.config.springboot.starter.binder.mapper.SourceMapper;
import com.binder.PlaceholdersResolver;
import com.binder.mapper.SourceMapper;
import com.binder.util.Constants;
import com.binder.util.StringUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 配置信息的解析和处理
 *
 * @author Create by liuwenhao on 2022/10/12 16:33
 */
public class MapPropertiesSource implements Source {

    /**
     * 拉取到的配置信息
     */
    Map<String, Object> map = new HashMap<>();

    /**
     * 占位符和特殊符号的处理，通过{@code $，{，}，#}的标识的信息<br>
     * <ol>
     *     <li>调用方法：{@code ${java.util.time.LocalDateTime#now()}}，需要通过()进行标识</li>
     *     <li>调用属性：{@code ${cn.hippo4j.config.springboot.starter.binder.source.MapPropertiesSource#map}}</li>
     *     <li>引用上下文：{@code ${spring.application.name}}</li>
     * </ol>
     * 通过占位符标识时会优先取得当前配置中可以获取的项，如果没有再以方法的形式去解析<br>
     * 占位符是支持嵌套的
     */
    PlaceholdersResolver placeholdersResolver = new PlaceholdersResolver();

    /**
     * 配置项
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
    }

    public MapPropertiesSource(Map<String, Object> map, String prefix) {
        this(map, new DefaultSourceMapper(), prefix);
    }

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
