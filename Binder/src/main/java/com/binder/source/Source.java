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
 *
 */
public interface Source {

    /**
     * Configure the overall prefix of the source
     *
     * @return prefix
     */
    String getPrefix();

    /**
     * The result after parsing<br>
     * The result is defined in {@link com.binder.element.Element.ElementEnum}<br>
     * If it is a primitive data type, it is processed by its wrapper type<br>
     * cls Must be able to be instantiated without arguments
     *
     * @param cls The corresponding class after parsing
     * @return obj
     * @
     */
    <T> T getResult(Class<T> cls);

    /**
     * Gets the parsed configuration collection
     *
     * @return Configuration Collection
     */
    List<SourceName> getSourceValue();

}
