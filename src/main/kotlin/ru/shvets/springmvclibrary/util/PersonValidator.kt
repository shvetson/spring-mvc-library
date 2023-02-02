package ru.shvets.springmvclibrary.util

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import ru.shvets.springmvcapp4.dao.PersonDao
import ru.shvets.springmvcapp4.model.Person

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  01.02.2023 23:24
 */

@Component
class PersonValidator(
    private val personDao: PersonDao
): Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return Person::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val person = target as Person
        if (personDao.findByEmail(person.email).isPresent) {
            errors.rejectValue("email", "", "Этот email уже используется")
        }
    }
}