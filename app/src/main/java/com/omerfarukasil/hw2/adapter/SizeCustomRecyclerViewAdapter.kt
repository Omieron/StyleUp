import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omerfarukasil.hw2.R

class SizeCustomRecyclerViewAdapter(
    private val sizeList: List<String>
) : RecyclerView.Adapter<SizeCustomRecyclerViewAdapter.SizeViewHolder>() {

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
    }

    override fun getItemCount(): Int = sizeList.size
}