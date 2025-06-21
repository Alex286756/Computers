package ru.kuksov.computers.service

import org.springframework.stereotype.Service
import ru.kuksov.computers.model.Brand
import ru.kuksov.computers.repository.BrandRepository
import java.util.*

/**
 * Класс содержит всю логику, касающуюся работы с брендами
 * @author Куксов Алексей Геннадьевич
 * @version 2.0
 */
@Service
class BrandService (
    val brandRepository: BrandRepository
){
    fun findAllBrands(): List<Brand> =
        this.brandRepository.findAll().toList()

    fun findBrandById(id: Int): Optional<Brand> =
        this.brandRepository.findById(id)

    fun findBrandByName(name: String): Optional<Brand> =
        this.brandRepository.findByName(name)

    fun addBrand(name: String): Brand =
        this.brandRepository.save(Brand(name))

    fun updateBrand(brand: Brand, name: String): Brand {
        brand.name = name
        return this.brandRepository.save(brand)
    }

    fun deleteBrandById(id: Int) =
        this.brandRepository.deleteById(id)

    fun deleteAllBrands() =
        this.brandRepository.deleteAll()

}