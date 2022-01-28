package mierkiewicz.natalia.pantry.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import mierkiewicz.natalia.pantry.model.ImportanceLevel
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.ProductCategory
import mierkiewicz.natalia.pantry.model.QuantityLevel
import mierkiewicz.natalia.pantry.repository.ProductRepository

class ProductViewModel(): ViewModel() {

    val allProducts = products
//    liveData {
//        emit(products)
//    }

    val allCategories = productCategories
//    liveData {
//        emit(productCategories)
//    }
}

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
    )
)