package mierkiewicz.natalia.pantry.model

data class Product(
    val name: String,
    val categories: List<ProductCategory>,
    val quantity: QuantityLevel,
    val importance: ImportanceLevel
)

data class ProductCategory(
    val name: String,
    val description: String
)

enum class ImportanceLevel {
    LOW,
    MEDIUM,
    HIGH
}

enum class QuantityLevel {
    LOW,
    MEDIUM,
    HIGH;

    override fun toString(): String {
        return "Quantity: " + super.toString()
    }
}