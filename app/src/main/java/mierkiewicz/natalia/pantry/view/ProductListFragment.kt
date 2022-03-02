package mierkiewicz.natalia.pantry.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.Category
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel

const val PRODUCT_NAME = "product name"

class ProductListFragment : Fragment() {

    lateinit var productViewModel: ProductViewModel
    private var category: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        productViewModel = ProductViewModel(requireContext())
        val products = category?.let { productViewModel.productsByCategory(it) }
            ?: productViewModel.allProducts

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter =  ProductRecyclerViewAdapter (products) { product -> onProductListItemClick(product) }
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()


    }

    private fun onProductListItemClick(product: Product) {

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
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(category: Category? = null): ProductListFragment = with (category) {
            val productsListFragment = ProductListFragment()
            productsListFragment.category = category
            return productsListFragment
        }
    }

}


