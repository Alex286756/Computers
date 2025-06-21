package ru.kuksov.computers.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.GenerationType.SEQUENCE

/**
 * Модель данных по брендам комплектующих
 *
 * @id идентификатор
 * @name название бренда
 * @author Куксов Алексей Геннадьевич
 * @version 2.0
 */
@Entity
@Table(name="brands")
class Brand (
    /**
     * Название бренда
     */
    @Column(name = "name", unique = true, nullable = false)
    var name: String
){
    /**
     * Идентификатор бренда
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE, generator = "brand_id_seq")
    @SequenceGenerator(name = "brand_id_seq", sequenceName = "brand_id_seq", allocationSize = 1)
    val id = 0
}