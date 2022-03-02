package mierkiewicz.natalia.pantry.dao

import androidx.room.*
import mierkiewicz.natalia.pantry.model.Category
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.ProductWithCategories

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAllProducts(): List<Product>

    @Insert
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Transaction
    @Query("SELECT * FROM product")
    fun getProductsWithCategories(): List<ProductWithCategories>

}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAllCategories(): List<Category>

    @Insert
    fun insert(category: Category)

}