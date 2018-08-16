package com.company.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface GenericMapper<T> {
    T extractFromResultSet(ResultSet set) throws SQLException;

    T makeUnique(Map<Integer, T> cache, T item);
}
