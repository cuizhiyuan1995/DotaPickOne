package ai.pipi.dotapickone.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import ai.pipi.dotapickone.MainViewModel
import ai.pipi.dotapickone.databinding.HeroCardBinding
import ai.pipi.dotapickone.ui.dashboard.DashboardViewModel


class Homeadapter(private val herolist: List<String>,private val viewModel: MainViewModel,private val dashviewModel: DashboardViewModel)
    : RecyclerView.Adapter<Homeadapter.VH>() {
    // Create a new, writable list that we initialize with colorList
    private var list = herolist





    // ViewHolder pattern minimizes calls to findViewById
    inner class VH(val binding: HeroCardBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            val bitmap = viewModel.getbitmapatposition(position)
            binding.IV.setImageBitmap(bitmap)
        }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = HeroCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        val holder = VH(binding)
        // XXX Write me. setOnClickListener, look at adapterPosition in Android docs
        binding.root.setOnClickListener{
            val position = holder.adapterPosition
            viewModel.imagetapped(position)
        }
        return holder
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
        // XXX Write me.
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return herolist.size
    }


    // A static function for computing luminance
    companion object {
        fun getLuminance(color: Int): Float {
            val red = Color.red(color)
            val green = Color.green(color)
            val blue = Color.blue(color)

            val hsl = FloatArray(3)
            ColorUtils.RGBToHSL(red, green, blue, hsl)
            return hsl[2]
        }
    }
}