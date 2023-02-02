package ru.shvets.springmvclibrary.util

import org.springframework.jdbc.core.RowMapper
import ru.shvets.springmvclibrary.model.Person
import java.sql.ResultSet

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.01.2023 23:45
 */

class BookMapper: RowMapper<Person> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Person {
        return rs.toModel()
    }

    private fun ResultSet.toModel(): Person {
        return Person(
            id = this.getInt("person_id"),
            name = this.getString("name"),
            year = this.getInt("year_of_birth"),
        )
    }
}