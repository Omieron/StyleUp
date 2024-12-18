import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omerfarukasil.hw2.R
import com.omerfarukasil.hw2.adapter.ProductCustomRecyclerViewAdapter
import com.omerfarukasil.hw2.adapter.ProductCustomRecyclerViewAdapter.Companion
import com.omerfarukasil.hw2.db.Clothes

class SizeCustomRecyclerViewAdapter(
    private val sizeList: List<String>
) : RecyclerView.Adapter<SizeCustomRecyclerViewAdapter.SizeViewHolder>() {

    companion object {
        var selectedPosition = RecyclerView.NO_POSITION

        fun setSelectedPosition() {
            selectedPosition = RecyclerView.NO_POSITION
        }
    }

    private var recyclerItemValues = emptyList<Clothes>()

    inner class SizeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sizeText: TextView = itemView.findViewById(R.id.sizeTextView) // Custom layout id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_size_layout, parent, false)
        return SizeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val size = sizeList[position]
        holder.sizeText.text = size

        // Set selected or not
        holder.itemView.isSelected = (position == selectedPosition)

        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.size_selected_layout_background)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.size_layout_background)
        }

        // Click listener to change selected position and update background
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            // Notify changes for old and new positions
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
        }
    }


    override fun getItemCount(): Int = sizeList.size
}