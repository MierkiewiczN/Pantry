package mierkiewicz.natalia.pantry.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.databinding.FragmentProductListItemBinding
import mierkiewicz.natalia.pantry.model.Product


class ProductRecyclerViewAdapter(
    private val products: List<Product>?,
    private val onClick: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val productView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_product_list_item,
            parent,
            false
        )

        return ProductViewHolder(productView, onClick)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        products?.get(position)?.let {
            val product = it
            holder.product = product
            holder.nameView.text = product.name
            holder.quantityView.text = product.quantity.toString()
        }
    }

    override fun getItemCount(): Int = products?.size ?: run {0}

    inner class ProductViewHolder(productView: View, val onClick: (product: Product) -> Unit) :
        RecyclerView.ViewHolder(productView) {
        val nameView: TextView = productView.findViewById(R.id.product_name)
        val quantityView: TextView = productView.findViewById(R.id.product_quantity)
        var product: Product? = null

        init {
            productView.setOnClickListener {
                product?.let { onClick(it) }
            }
        }
    }

}