package com.binder.source;

import com.binder.ElementUnit;
import com.binder.PlaceholdersResolver;
import com.binder.element.Elements;
import com.binder.mapper.DefaultSourceMapper;
import com.binder.mapper.SourceMapper;
import com.binder.special.Special;
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
            return new SourceName(prefix + supplement, s, -1, o, sourceMapper);
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
            return new SourceName(prefix + supplement, elementName, index, o, sourceMapper);
        } else {
            String nextOne = s.substring(rightFirstIndex + 2);
            String nextSupplement = s.substring(0, rightFirstIndex + 1);
            SourceName sourceName = extract(nextOne, "." + nextSupplement, o);
            this.sourceNames.add(sourceName);
            return new SourceName(prefix + supplement, nextSupplement, -1, sourceName, sourceMapper);
        }
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public <T> T getResult(Class<T> cls, Elements elements) {
        ElementUnit elementUnit = new ElementUnit(prefix, cls.getSimpleName(), sourceNames, cls);
        return elements.getResult(elementUnit);
    }

    @Override
    public List<SourceName> getSourceValue() {
        return sourceNames;
    }

}
