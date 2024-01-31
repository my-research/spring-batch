package com.github.dhslrl321.mybatch.domain.entity

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class TodoEntityRowMapper : RowMapper<Todo> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Todo? {
        return Todo(
            id = rs.getLong("id"),
            name = rs.getString("name"),
            status = rs.getString("status"),
            createdAt = rs.getString("created_at"),
            updatedAt = rs.getString("updated_at")
        )
    }
}
