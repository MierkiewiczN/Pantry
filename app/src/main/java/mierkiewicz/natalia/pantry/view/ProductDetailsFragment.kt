package mierkiewicz.natalia.pantry.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.Category
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel


class ProductDetailsFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private  var productId: Int? = null
    lateinit var toolbarLayout: CollapsingToolbarLayout
    lateinit var productQuantity: TextView
    lateinit var productPriority: TextView
    lateinit var categoriesChipGroup: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productId = arguments?.getInt(PRODUCT_ID)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)

        productViewModel = ProductViewModel(requireContext())
        toolbarLayout = view.findViewById(R.id.toolbar_layout)
        productQuantity = view.findViewById(R.id.product_quantity_textview)
        productPriority = view.findViewById(R.id.product_priority_textview)
        categoriesChipGroup = view.findViewById(R.id.categories_chip_group)

        productId?.let {
            val products = productViewModel.allProducts
            val product: Product = products.first { p -> p.productId == productId }
            val categories = product.categories
            toolbarLayout.title = product.name
            productPriority.text = product.importance.toString()
            productQuantity.text = product.quantity.toString()
            categories.forEach { c ->
                val chip = Chip(context)
                chip.text = c.name
                chip.setOnClickListener {
                    onCategoryChipClick(c)
                }
                categoriesChipGroup.addView(chip)
            }
        }
        return view
    }
    private fun onCategoryChipClick(category:Category) {
        val fm = parentFragmentManager
        val bundle = Bundle()
        bundle.putString(
            ARG_ITEM_ID,
            category.name
        )
        val itemDetailFragment = CategoryDetailsFragment()
        itemDetailFragment.arguments = bundle

        fm.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.frameLayout, itemDetailFragment)
            .addToBackStack("")
            .commit()
    }
}