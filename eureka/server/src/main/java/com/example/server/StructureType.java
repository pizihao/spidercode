package com.example.server;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
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

    VIEW {
        @Override
        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
            List<SyncTemplate.Structure> query = jdbcTemplate
                    .query(String.format(InformationSql.VIEWS, dbName),
                            (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(1)));
            if (CollectionUtils.isEmpty(query)) {
                return Stream.of();
            }
            return query.stream()
                    .filter(Objects::nonNull)
                    .map(s -> jdbcTemplate.queryForObject(
                            String.format(InformationSql.SELECT_CREATE_VIEW, s.getName()),
                            (rs, rowNum) -> {
                                String body = rs.getString(2);
                                return s.setSql(body + ";");
                            })
                    );
        }
    },

    FUNCTION {
        @Override
        public Stream<SyncTemplate.Structure> getStructure(JdbcTemplate jdbcTemplate, String dbName) {
            List<SyncTemplate.Structure> query = jdbcTemplate.query(
                    String.format(InformationSql.FUNCTIONS, dbName),
                    (rs, rowNum) -> new SyncTemplate.Structure(rs.getString(1)));
            if (CollectionUtils.isEmpty(query)) {
                return Stream.of();
            }
            return query.stream()
                    .filter(Objects::nonNull)
                    .map(s -> jdbcTemplate.queryForObject(
                            String.format(InformationSql.SELECT_CREATE_FUNCTION, s.getName()),
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
