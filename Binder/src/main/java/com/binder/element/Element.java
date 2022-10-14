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

import com.binder.source.Source;

import java.util.ArrayList;
import java.util.List;

/**
 * 支持的类型元素
 *
 * @author Create by liuwenhao on 2022/10/12 16:05
 */
public interface Element {

    /**
     * 元素解析的枚举
     *
     * @return ElementEnum
     */
    default ElementEnum supportType() {
        return null;
    }

    /**
     * 支持的元素类型
     *
     * @return List<Class < ?>>
     */
    default List<Class<?>> supportClasses() {
        return new ArrayList<>();
    }

    /**
     * 解析配置信息
     *
     * @param e   单个的配置项
     * @param <T> 解析后获得的类型
     * @return T 解析后的类型
     */
    default <T> T parser(Source e) {
        return null;
    }

    enum ElementEnum {

        STRING,

        ARRAY,

        PRIMITIVE,

        COLLECTION,

        OBJECT,

        MAP;

    }
}
