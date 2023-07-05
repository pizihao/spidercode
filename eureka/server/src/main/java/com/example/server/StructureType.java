package com.example.server;

import com.google.common.collect.Lists;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 结构类型
 */
public enum StructureType {

    TABLE {
        @Override
        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
            return jdbcTemplate
                    .query(InformationSql.TABLES,
                            (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(1)))
                    .stream()
                    .filter(Objects::nonNull)
                    .map(t -> jdbcTemplate.queryForObject(
                            String.format(InformationSql.SELECT_CREATE_TABLE, t.getName()),
                            (rs, rowNum) -> t.setSql(rs.getString(2) + ";"))
                    );
        }
    },

    TABLE_DATA {

        private final List<Class<?>> classes = Lists.newArrayList(String.class, Date.class, Time.class, Timestamp.class);

        @Override
        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
            List<SyncTemplate.Structure> tableNames = jdbcTemplate.query(
                    InformationSql.TABLES,
                    (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(1))
            );
            List<SyncTemplate.Structure> structures = tableNames.stream()
                    .filter(Objects::nonNull)
                    .map(s -> jdbcTemplate.queryForObject(
                            String.format(InformationSql.TABLE_COLUMN, dbName, s.getSimpleName()),
                            (rs, rowNum) -> s.setSqlFragment(rs.getString(1)))
                    )
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return structures.stream()
                    .map(s -> s.setSql(String.join(",", jdbcTemplate.query(
                            String.format(InformationSql.TABLE_SELECT, s.getSqlFragment(), dbName + "." + s.getSimpleName()),
                            (rs, rowNum) -> {
                                String values = Arrays.stream(s.getSqlFragment().split(","))
                                        .map(r -> {
                                            try {
                                                return rs.getObject(r);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        })
                                        .map(this::formatValue)
                                        .collect(Collectors.joining(","));
                                return "(" + values + ")";
                            }))))
                    .filter(Objects::nonNull)
                    .filter(s -> !StringUtils.isEmpty(s.getSql()))
                    .map(s -> s.setSql(String.format(InformationSql.TABLE_INSERT, s.getSimpleName(), s.getSqlFragment(), s.getSql())));
        }

        private String formatValue(Object value) {
            if (value == null) {
                return null;
            } else if (classes.contains(value.getClass())) {
                return "'" + value + "'";
            } else {
                return value.toString();
            }
        }
    },

    PROCEDURE {
        @Override
        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
            return jdbcTemplate.query(
                            String.format(InformationSql.PROCEDURE, dbName),
                            (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(2)))
                    .stream()
                    .filter(Objects::nonNull)
                    .map(s -> jdbcTemplate.queryForObject(
                            String.format(InformationSql.SELECT_CREATE_PROCEDURE, s.getName()),
                            (rs, rowNum) -> {
                                String body = rs.getString(3);
                                return s.setSql(body + ";");
                            })
                    );
        }
    },

    TRIGGERS {
        @Override
        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
            List<SyncTemplate.Structure> query = jdbcTemplate.query(
                    InformationSql.TRIGGERS,
                    (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(1)));
            if (CollectionUtils.isEmpty(query)) {
                return Stream.of();
            }
            return query.stream()
                    .filter(Objects::nonNull)
                    .map(s -> jdbcTemplate.queryForObject(
                            String.format(InformationSql.SELECT_CREATE_TRIGGERS, s.getName()),
                            (rs, rowNum) -> {
                                String body = rs.getString(3);
                                return s.setSql(body + ";");
                            })
                    );
        }
    },

//    VIEW {
//        @Override
//        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
//            List<SyncTemplate.Structure> query = jdbcTemplate
//                    .query(String.format(InformationSql.VIEWS, dbName),
//                            (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(1)));
//            if (CollectionUtils.isEmpty(query)) {
//                return Stream.of();
//            }
//            return query.stream()
//                    .filter(Objects::nonNull)
//                    .map(s -> jdbcTemplate.queryForObject(
//                            String.format(InformationSql.SELECT_CREATE_VIEW, s.getName()),
//                            (rs, rowNum) -> {
//                                String body = rs.getString(2);
//                                return s.setSql(body + ";");
//                            })
//                    );
//        }
//    },

    FUNCTION {
        @Override
        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
            List<SyncTemplate.Structure> query = jdbcTemplate.query(
                    String.format(InformationSql.FUNCTIONS, dbName),
                    (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(2)));
            if (CollectionUtils.isEmpty(query)) {
                return Stream.of();
            }
            return query.stream()
                    .filter(Objects::nonNull)
                    .map(s -> jdbcTemplate.queryForObject(
                            String.format(InformationSql.SELECT_CREATE_FUNCTION, s.getSimpleName()),
                            (rs, rowNum) -> {
                                String body = rs.getString(3);
                                return s.setSql(body + ";");
                            })
                    );
        }
    },

    EVENT {
        @Override
        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
            List<SyncTemplate.Structure> query = jdbcTemplate.query(
                    InformationSql.EVENTS,
                    (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(2)));
            if (CollectionUtils.isEmpty(query)) {
                return Stream.of();
            }
            return query.stream()
                    .filter(Objects::nonNull)
                    .map(s -> jdbcTemplate.queryForObject(
                            String.format(InformationSql.SELECT_CREATE_EVENT, s.getName()),
                            (rs, rowNum) -> {
                                String body = rs.getString(4);
                                return s.setSql(body + ";");
                            })
                    );
        }
    };


    public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
        throw new UnsupportedOperationException();
    }

}
