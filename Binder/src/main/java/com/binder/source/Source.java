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


import java.util.List;

/**
 * @author Create by liuwenhao on 2022/10/12 16:31
 */
public interface Source {

    /**
     * 配置源的整体前缀
     *
     * @return prefix
     */
    String getPrefix();

    /**
     * 解析后的结果<br>
     * 结果是{@link com.binder.element.Element.ElementEnum}中的定义<br>
     * 如果是基本数据类型，则会通过其包装类型处理<br>
     * cls 必须可以被以无参的形式实例化
     *
     * @param cls 解析后对应的类
     * @return obj
     * @
     */
    <T> T getResult(Class<T> cls);

    /**
     * 获取解析后的配置集合
     *
     * @return 配置集合
     */
    List<SourceName> getSourceValue();

}
