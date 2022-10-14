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

package com.binder.mapper;

/**
 * @author Create by liuwenhao on 2022/10/12 17:07
 */
public class DefaultSourceMapper implements SourceMapper {

    /**
     * 默认将 - 符号和 _ 符号转化为驼峰
     *
     * @param s 转换前的key
     * @return 转换后的key，驼峰形式
     */
    @Override
    public String convert(String s) {
        // 数组的情况下去除 [] 中的数据
        return null;
    }
}
