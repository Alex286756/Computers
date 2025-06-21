package ru.kuksov.computers.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kuksov.computers.model.Brand
import java.util.Optional

/**
 * Интерфейс работы с записями о брендах
 * @author Куксов Алексей Геннадьевич
 * @version 2.0
 */
@Repository
interface BrandRepository: CrudRepository<Brand, Int> {

    fun findByName(name: String): Optional<Brand>
}