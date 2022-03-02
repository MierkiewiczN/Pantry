package mierkiewicz.natalia.pantry.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import mierkiewicz.natalia.pantry.database.AppDatabase
import mierkiewicz.natalia.pantry.model.ImportanceLevel
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.Category
import mierkiewicz.natalia.pantry.model.QuantityLevel

class ProductViewModel(context: Context): ViewModel() {


    val database: AppDatabase = AppDatabase.getDatabase(context)

    val allProducts = database.productDao().getAllProducts()

    fun productsByCategory(category: Category) =
        productsMap.values.filter { it.categories.contains(category) }

    fun productById(id: Int?) = productsMap[id]

    fun addProduct(product: Product) {
        database.productDao().insert(product)
        productsMap[product.productId] = product
    }

    fun removeProduct(id: Int) {
        productsMap.remove(id)
    }

    val allCategories = database.productCategoryDao().getAllCategories()

    fun addProductCategory(category: Category) {
        database.productCategoryDao().insert(category)
        productCategories.add(category)
    }

    fun removeProductCategory(id: Int) {
        productCategories.removeIf { c -> c.categoryId == id }
    }

    companion object {
        private val productCategories = mutableListOf(
            Category(
                name = "Category 1",
                description = "Longer description of category 1"
            ),
            Category(
                name = "Category 2",
                description = ""
            ),
            Category(
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

        val productsMap = products.associateBy { it.productId }.toMutableMap()
    }
}


