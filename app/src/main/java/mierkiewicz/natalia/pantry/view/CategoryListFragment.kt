package mierkiewicz.natalia.pantry.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import mierkiewicz.natalia.pantry.R
import mierkiewicz.natalia.pantry.databinding.FragmentCategoryListBinding
import mierkiewicz.natalia.pantry.model.ProductCategory
import mierkiewicz.natalia.pantry.viewmodel.ProductViewModel


class CategoryListFragment : Fragment() {

    val productViewModel = ProductViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_category_list, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.category_list_container)
        setupRecyclerView(recyclerView)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView
    ) {
        recyclerView.adapter = CategoryRecyclerViewAdapter(productViewModel.allCategories) {
                category -> onCategoryListItemClick(category)
        }
    }

    private fun onCategoryListItemClick(category: ProductCategory) {
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
            .addToBackStack(null)
            .commit()

    }
}