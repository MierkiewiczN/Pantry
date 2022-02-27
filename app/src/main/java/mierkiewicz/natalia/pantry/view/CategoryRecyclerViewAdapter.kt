package mierkiewicz.natalia.pantry.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.model.Product
import mierkiewicz.natalia.pantry.model.ProductCategory
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel
import kotlin.coroutines.coroutineContext

class CategoryRecyclerViewAdapter(private val categories: List<ProductCategory>,
                                  private val onClick: (category: ProductCategory) -> Unit
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_category_list_item,
            parent,
            false
        )

        return CategoryViewHolder(categoryView, onClick)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        categories[position].let {
            val category = it
            holder.category = category
            holder.nameView.text = category.name
            holder.descriptionView.text = category.description

        }
    }

    override fun getItemCount(): Int = categories.size ?: run {0}

    inner class CategoryViewHolder(categoryView: View, val onClick: (category: ProductCategory) -> Unit) :
        RecyclerView.ViewHolder(categoryView) {
        val nameView: TextView = categoryView.findViewById(R.id.category_name)
        val descriptionView: TextView = categoryView.findViewById(R.id.category_description)

        var category: ProductCategory? = null

        init {
            categoryView.setOnClickListener {
                category?.let { onClick(it) }
            }
            categoryView.setOnLongClickListener {
                TODO("set long on click listener")
            }
        }
    }



}