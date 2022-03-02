package mierkiewicz.natalia.pantry.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mierkiewicz.natalia.pantry.dao.CategoryDao
import mierkiewicz.natalia.pantry.dao.ProductDao
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.Category
import mierkiewicz.natalia.pantry.model.ProductCategoryCrossRef

@Database(entities = [Product::class, Category::class, ProductCategoryCrossRef::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun productCategoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}