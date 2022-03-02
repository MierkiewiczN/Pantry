package mierkiewicz.natalia.pantry.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.model.ImportanceLevel
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.QuantityLevel
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel

const val PRODUCT_ID = "product id"
open class AddProductFragment : Fragment() {

    private var productId: Int? = null

    lateinit var productViewModel: ProductViewModel

    lateinit var addProductButton: Button
    lateinit var productNameEditText: EditText
    lateinit var productQuantityRadioGroup: RadioGroup
    lateinit var productPriorityRadioGroup: RadioGroup
    lateinit var categoriesChipGroup: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        productId = arguments?.getInt(PRODUCT_ID)
        super.onCreate(savedInstanceState)
        productViewModel = ProductViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_product, container, false)
        addProductButton = view.findViewById(R.id.add_product_button)
        productNameEditText = view.findViewById(R.id.product_name_editText)
        productQuantityRadioGroup = view.findViewById(R.id.quantity_radioGroup)
        productPriorityRadioGroup = view.findViewById(R.id.priority_radioGroup)
        categoriesChipGroup = view.findViewById(R.id.categories_chip_group)
        addProductButton.setOnClickListener{ onProductAddButtonClick(view) }
        productViewModel.allCategories.forEach { c ->
            val chip = Chip(context)
            chip.isCheckable = true
            chip.text = c.name
            chip.checkedIcon = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_check_24, null)
            categoriesChipGroup.addView(chip)
         }
        return view
    }

//    private fun onCategoryAddButtonClick() {
//        val categoryName:String = categoryNameEditText.text.toString()
//        val chip = Chip(context)
//        chip.text = categoryName
//        categoriesChipGroup.addView(chip)
//    }

    private fun onProductAddButtonClick(view: View) {

        val productName = productNameEditText.text.toString()
        val quantityLevel = when(productQuantityRadioGroup.checkedRadioButtonId) {
            R.id.quantity_low -> QuantityLevel.LOW
            R.id.quantity_medium -> QuantityLevel.MEDIUM
            R.id.quantity_high -> QuantityLevel.HIGH
            else -> QuantityLevel.UNKNOWN
        }
        val priorityLevel = when(productPriorityRadioGroup.checkedRadioButtonId) {
            R.id.priority_low -> ImportanceLevel.LOW
            R.id.priority_medium -> ImportanceLevel.MEDIUM
            R.id.priority_high -> ImportanceLevel.HIGH
            else -> ImportanceLevel.UNKNOWN
        }
        val categoryNames = categoriesChipGroup.checkedChipIds.map {
                id -> view.findViewById<Chip>(id).text }

        val categories = productViewModel.allCategories.filter { c -> c.name in categoryNames }

        val product = Product(
            name = productName,
            quantity = quantityLevel,
            importance = priorityLevel,
            categories = categories
        )
        productViewModel.addProduct(product)

        val fm = parentFragmentManager
        val bundle = Bundle()
        bundle.putInt(
            PRODUCT_ID,
            product.productId
        )
        val productDetailsFragment = ProductDetailsFragment()
        productDetailsFragment.arguments = bundle

        fm.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.frameLayout, productDetailsFragment)
            .commit()

    }

    companion object {
        @JvmStatic
        fun newInstance(productId: Int) =
            AddProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(PRODUCT_ID, productId)
                }
            }
    }
}