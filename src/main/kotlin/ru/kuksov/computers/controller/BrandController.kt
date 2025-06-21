package ru.kuksov.computers.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kuksov.computers.model.Brand
import ru.kuksov.computers.model.BrandRequest
import ru.kuksov.computers.returnEmptyResponseEntity
import ru.kuksov.computers.returnResponseEntityWithBody
import ru.kuksov.computers.service.BrandService

@RestController
@RequestMapping("/computers-api/v2/brands")
class BrandController(
    val brandService: BrandService
) {

    @GetMapping
    fun findAllBrands(): ResponseEntity<List<Brand>> =
        returnResponseEntityWithBody(this.brandService.findAllBrands(), HttpStatus.OK)

    @GetMapping("/{id:\\d+}")
    fun findBrandById(@PathVariable id: Int): ResponseEntity<Brand> {
        val brand = this.brandService.findBrandById(id)
        return if (brand.isPresent) {
            returnResponseEntityWithBody(brand.get(), HttpStatus.OK)
        } else {
            returnEmptyResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/name={name}")
    fun findBrandByName(@PathVariable name: String): ResponseEntity<Brand> {
        val brand = this.brandService.findBrandByName(name)
        return if (brand.isPresent) {
            returnResponseEntityWithBody(brand.get(), HttpStatus.OK)
        } else {
            returnEmptyResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun addBrand(@RequestBody request: BrandRequest): ResponseEntity<Brand> {
        if (this.brandService.findBrandByName(request.name).isPresent) {
            return returnEmptyResponseEntity(HttpStatus.CONFLICT)
        }
        return returnResponseEntityWithBody(this.brandService.addBrand(request.name),
            HttpStatus.CREATED)
    }

    @PatchMapping("/{id:\\d+}")
    fun editBrand(@PathVariable id: Int,
                  @RequestBody request: BrandRequest): ResponseEntity<Brand> {
        val brandById = this.brandService.findBrandById(id)
        if (brandById.isEmpty)
            return returnEmptyResponseEntity(HttpStatus.NOT_FOUND)
        val brandByName = this.brandService.findBrandByName(request.name)
        if (brandByName.isPresent && brandByName.get().id != id)
            return returnEmptyResponseEntity(HttpStatus.CONFLICT)
        val updatedBrand = this.brandService.updateBrand(brandById.get(), request.name)
        return returnResponseEntityWithBody(updatedBrand, HttpStatus.OK)
    }

    @DeleteMapping("/{id:\\d+}")
    fun deleteBrandById(@PathVariable id: Int): ResponseEntity<String> {
        try {
            this.brandService.deleteBrandById(id)
        } catch (e: Exception) {
            return returnEmptyResponseEntity(HttpStatus.NOT_MODIFIED)
        }
        return returnResponseEntityWithBody("Brand $id deleted", HttpStatus.OK)
    }

    @DeleteMapping
    fun deleteAllBrands(): ResponseEntity<String> {
        try {
            this.brandService.deleteAllBrands()
        } catch (e: Exception) {
            return returnEmptyResponseEntity(HttpStatus.NOT_MODIFIED)
        }
        return returnResponseEntityWithBody("All brands deleted", HttpStatus.OK)
    }
}