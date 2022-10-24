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

import com.binder.util.Constants;
import com.binder.util.StringUtil;

/**
 *
 */
public class DefaultSourceMapper implements SourceMapper {

    /**
     * By default, the - symbol are converted to humps
     *
     * @param s key before conversion, it is  prefix + key
     * @return The transformed key, hump form
     */
    @Override
    public String convert(String s) {
        // In the case of array, the data in [] is divided, and then concatenated after conversion
        String elementName;
        String suffix;
        if (s.endsWith(Constants.RIGHT_BRACKETS)) {
            int leftIndex = s.lastIndexOf(Constants.LEFT_BRACKETS);
            elementName = s.substring(0, leftIndex);
            suffix = s.substring(leftIndex);
        } else {
            elementName = s;
            suffix = Constants.NULL;
        }
        String camelCase = StringUtil.toCamelCase(elementName, '-');
        return camelCase + suffix;
    }

    @Override
    public String reverseConvert(String s) {
        return StringUtil.toSymbolCase(s, '-');
    }


}
