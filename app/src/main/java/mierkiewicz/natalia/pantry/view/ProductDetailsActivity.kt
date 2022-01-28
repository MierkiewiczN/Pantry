package mierkiewicz.natalia.pantry.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel


class ProductDetailsActivity : AppCompatActivity() {

    private val productViewModel = ProductViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        var productName: String? = null

        val productTitle: TextView = findViewById(R.id.product_title_textview)
        val productQuantity: TextView = findViewById(R.id.product_quantity_textview)
        val productPriority: TextView = findViewById(R.id.product_priority_textview)
        val categoriesChipGroup: ChipGroup = findViewById(R.id.categories_chip_group)

        val bundle: Bundle? = intent.extras
        bundle?.getString(PRODUCT_NAME)?.let {
            productName = it
        }

        productName?.let {
            val products = productViewModel.allProducts
            val categories = productViewModel.allCategories
            val product: Product = products.first { p -> p.name == productName }
            productTitle.text = product.name
            productPriority.text = product.importance.toString()
            productQuantity.text = product.quantity.toString()
            categories.forEach {
                val chip = Chip(this)
                chip.text = it.name
                categoriesChipGroup.addView(chip)
            }
        }
    }
}