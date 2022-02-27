package mierkiewicz.natalia.pantry.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import mierkiewicz.natalia.pantry.model.ImportanceLevel
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.ProductCategory
import mierkiewicz.natalia.pantry.model.QuantityLevel
import mierkiewicz.natalia.pantry.repository.ProductRepository

class ProductViewModel(): ViewModel() {

    val allProducts = productsMap.values.toList()

    fun productsByCategory(category: ProductCategory) =
        productsMap.values.filter { it.categories.contains(category) }

    fun productById(id: Int?) = productsMap[id]

    fun addProduct(product: Product) {
        productsMap[product.id] = product
    }

    fun removeProduct(id: Int) {
        productsMap.remove(id)
    }
    val allCategories = productCategories

    fun addProductCategory(category: ProductCategory) {
        productCategories.add(category)
    }

    fun removeProductCategory(id: Int) {
        productCategories.removeIf { c -> c.id == id }
    }

    companion object {
        private val productCategories = mutableListOf(
            ProductCategory(
                name = "Category 1",
                description = "Longer description of category 1"
            ),
            ProductCategory(
                name = "Category 2",
                description = ""
            ),
            ProductCategory(
                name = "Category 3",
                description = "Short description"
            )
        )

        private val products = mutableListOf(
            Product(
                name = "Product 1",
                categories = listOf(productCategories[0]),
                quantity = QuantityLevel.MEDIUM,
                importance = ImportanceLevel.HIGH
            ),
            Product(
                name = "Product 2",
                categories = listOf(productCategories[1], productCategories[2]),
                quantity = QuantityLevel.MEDIUM,
                importance = ImportanceLevel.MEDIUM
            ),
            Product(
                name = "Product 3",
                categories = listOf(productCategories[2]),
                quantity = QuantityLevel.LOW,
                importance = ImportanceLevel.HIGH
            ),
            Product(
                name = "Product 4",
                categories = listOf(productCategories[1], productCategories[2], productCategories[0]),
                quantity = QuantityLevel.HIGH,
                importance = ImportanceLevel.LOW
            ),
            Product(
                name = "Product 5",
                categories = listOf(productCategories[0]),
                quantity = QuantityLevel.MEDIUM,
                importance = ImportanceLevel.HIGH
            ),
            Product(
                name = "Product 6",
                categories = listOf(productCategories[1], productCategories[2]),
                quantity = QuantityLevel.MEDIUM,
                importance = ImportanceLevel.MEDIUM
            ),
            Product(
                name = "Product 7",
                categories = listOf(productCategories[2]),
                quantity = QuantityLevel.LOW,
                importance = ImportanceLevel.HIGH
            ),
            Product(
                name = "Product 8",
                categories = listOf(productCategories[1], productCategories[2]),
                quantity = QuantityLevel.MEDIUM,
                importance = ImportanceLevel.MEDIUM
            ),
            Product(
                name = "Product 9",
                categories = listOf(productCategories[2]),
                quantity = QuantityLevel.LOW,
                importance = ImportanceLevel.HIGH
            ),
            Product(
                name = "Product 10",
                categories = listOf(productCategories[1], productCategories[2], productCategories[0]),
                quantity = QuantityLevel.HIGH,
                importance = ImportanceLevel.LOW
            ),
            Product(
                name = "Product 11",
                categories = listOf(productCategories[1], productCategories[2], productCategories[0]),
                quantity = QuantityLevel.HIGH,
                importance = ImportanceLevel.LOW
            ),
            Product(
                name = "Product 12",
                categories = listOf(productCategories[0]),
                quantity = QuantityLevel.MEDIUM,
                importance = ImportanceLevel.HIGH
            ),
            Product(
                name = "Product 13",
                categories = listOf(productCategories[1], productCategories[2], productCategories[0]),
                quantity = QuantityLevel.HIGH,
                importance = ImportanceLevel.LOW
            ),
            Product(
                name = "Product 14",
                categories = listOf(productCategories[1], productCategories[2], productCategories[0]),
                quantity = QuantityLevel.HIGH,
                importance = ImportanceLevel.LOW
            ),
            Product(
                name = "Product 15",
                categories = listOf(productCategories[0]),
                quantity = QuantityLevel.MEDIUM,
                importance = ImportanceLevel.HIGH
            )
        )

        val productsMap = products.associateBy { it.id }.toMutableMap()
    }
}


