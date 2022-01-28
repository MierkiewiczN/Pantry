package mierkiewicz.natalia.pantry.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel

const val PRODUCT_NAME = "product name"

class ProductListFragment : Fragment() {

    private val productViewModel = ProductViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter =  ProductRecyclerViewAdapter (productViewModel.allProducts) { product -> onProductListItemClick(product) }
            }
        }
        return view
    }

    private fun onProductListItemClick(product: Product) {
        val intent = Intent(context, ProductDetailsActivity::class.java)
        intent.putExtra(PRODUCT_NAME, product.name)
        startActivity(intent)
    }

    companion object {
        fun newInstance(): ProductListFragment = ProductListFragment()
    }

}


