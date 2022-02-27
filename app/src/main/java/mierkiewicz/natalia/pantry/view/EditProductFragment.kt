package mierkiewicz.natalia.pantry.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mierkiewicz.natalia.pantry.R

class EditProductFragment : AddProductFragment() {

    private var productId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        productId = arguments?.getInt(PRODUCT_ID)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_edit_product, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(productId: Int) =
            EditProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(PRODUCT_ID, productId)
                }
            }
    }
}