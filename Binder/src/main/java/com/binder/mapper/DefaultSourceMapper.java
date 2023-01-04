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
