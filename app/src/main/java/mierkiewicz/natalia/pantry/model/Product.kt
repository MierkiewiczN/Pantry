package mierkiewicz.natalia.pantry.model

import androidx.room.*

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) val productId: Int=0,
    val name: String,
    @Ignore val categories: List<Category> = emptyList(),
    val quantity: QuantityLevel,
    val importance: ImportanceLevel
) {
    constructor(productId: Int, name: String, quantity: QuantityLevel, importance: ImportanceLevel) : this(productId, name, emptyList(), quantity, importance)
    //@Ignore constructor(name: String, quantity: QuantityLevel, importance: ImportanceLevel) : this(0, name, emptyList(), quantity, importance)
    //@Ignore constructor(name: String,categories: List<Category>, quantity: QuantityLevel, importance: ImportanceLevel) : this(0, name, categories, quantity, importance)

    companion object {
        private var id: Int = 0
            get() {
                field++
                return field
            }
    }
}

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val categoryId: Int = Companion.id,
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

@Entity(primaryKeys = ["productId", "categoryId"])
data class ProductCategoryCrossRef(
    val productId: Int,
    val categoryId: Int
)

data class ProductWithCategories(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "productId",
        entityColumn = "categoryId",
        associateBy = Junction(ProductCategoryCrossRef::class)
    )
    val categories: List<Category>
)
