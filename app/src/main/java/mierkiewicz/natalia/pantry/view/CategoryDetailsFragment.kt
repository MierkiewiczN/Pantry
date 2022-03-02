package mierkiewicz.natalia.pantry.view

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.model.ProductCategory
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel

const val ARG_ITEM_ID = "item_id"

class CategoryDetailsFragment : Fragment() {

    val productViewModel = ProductViewModel()
    private var category: ProductCategory? = null

    lateinit var itemDetailTextView: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null
    private var buttonAddProduct: FloatingActionButton? = null


    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text
            category = productViewModel.allCategories.first { c -> c.name == dragData}
            updateContent()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                category = productViewModel.allCategories.first { c -> c.name == it.getString(ARG_ITEM_ID)}
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_category_details, container, false)

        toolbarLayout = view.findViewById(R.id.toolbar_layout)
        itemDetailTextView = view.findViewById(R.id.item_detail)
        buttonAddProduct = view.findViewById(R.id.add_product_fab)
        updateContent()
        buttonAddProduct?.setOnClickListener{ onFabClick()}
        view.setOnDragListener(dragListener)

        return view
    }

    private fun updateContent() {
        toolbarLayout?.title = category?.name
        itemDetailTextView.text = category?.description
        val productListFragment: ProductListFragment = ProductListFragment.newInstance(category)
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.category_products_frame_layout, productListFragment)
            .commit()
    }

    private fun onFabClick() {
        val fm = parentFragmentManager

        val addProductFragment = AddProductFragment()

        fm.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.frameLayout, addProductFragment)
            .addToBackStack(null)
            .commit()

    }

}