package mierkiewicz.natalia.pantry.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.database.AppDatabase
import mierkiewicz.natalia.pantry.model.Category
import mierkiewicz.natalia.pantry.model.ImportanceLevel
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.QuantityLevel
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val vm = ProductViewModel(application)
        products.forEach { p -> vm.addProduct(p) }
        productCategories.forEach { c -> vm.addProductCategory(c) }

        val productListFragment: ProductListFragment = ProductListFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, productListFragment)
            .commit()
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.recipes_menu_item -> replaceFragment(ProductListFragment.newInstance())
                R.id.products_menu_item -> replaceFragment(productListFragment)
                R.id.categories_menu_item -> replaceFragment(CategoryListFragment())
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) = supportFragmentManager.commit {
        setReorderingAllowed(true)
        replace(R.id.frameLayout, fragment)
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