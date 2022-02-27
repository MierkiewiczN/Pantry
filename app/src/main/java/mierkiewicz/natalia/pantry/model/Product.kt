package mierkiewicz.natalia.pantry.model

data class Product(
    val id: Int = Companion.id,
    val name: String,
    val categories: List<ProductCategory>,
    val quantity: QuantityLevel,
    val importance: ImportanceLevel
) {
    companion object {
        private var id: Int = 0
            get() {
                field++
                return field
            }
    }
}

data class ProductCategory(
    val id: Int = Companion.id,
    val name: String,
    val description: String
) {
    companion object {
        var id: Int = 0
            get() {
                field++
                return field
            }
    }
}

enum class ImportanceLevel {
    LOW,
    MEDIUM,
    HIGH,
    UNKNOWN
}



enum class QuantityLevel {
    LOW,
    MEDIUM,
    HIGH,
    UNKNOWN;

    override fun toString(): String {
        return "Quantity: " + super.toString()
    }
}