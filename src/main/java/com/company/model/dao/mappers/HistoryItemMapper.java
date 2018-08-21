package com.company.model.dao.mappers;

import com.company.model.entities.HistoryItem;
import com.company.model.entities.interfaces.Tracker;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.Map;

public class HistoryItemMapper
        implements GenericMapper<HistoryItem> {

    @Override
    public HistoryItem extractFromResultSet(ResultSet set) throws SQLException {
        return extractWithSpecifiedReferences(set, getTracker(set));
    }

    public HistoryItem extractWithSpecifiedReferences(ResultSet set, Tracker tracker) throws SQLException {
        int id = set.getInt("finished_activity_id");
        String title = set.getString("title");
        int spentTime = set.getInt("spent_time");
        Date date = set.getDate("finish_date");
        LocalDate localDate = date.toLocalDate();
        return new HistoryItem(id, tracker, title, spentTime, localDate);
    }

    private Tracker getTracker(ResultSet set) throws SQLException {
        TrackerUserMapper mapper = new TrackerUserMapper();
        return mapper.extractFromResultSet(set);
    }

    @Override
    public HistoryItem makeUnique(Map<Integer, HistoryItem> cache, HistoryItem item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
