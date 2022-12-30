package ai.pipi.dotapickone.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ai.pipi.dotapickone.MainViewModel
import ai.pipi.dotapickone.R
import ai.pipi.dotapickone.databinding.RowWinrateBinding
import ai.pipi.dotapickone.room.Heroname

class DashboardAdapter(private val mainViewModel: MainViewModel,private val dashviewModel: DashboardViewModel,private val context: Context)
    : ListAdapter<Heroname,DashboardAdapter.VH>(Diff()){
    inner class VH(val binding: RowWinrateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(stratzId: Int) {
            val position = dashviewModel.transferSIDtoAlpha(stratzId)
            val bitmap =
                position?.let { mainViewModel.getbitmapatposition(it) }    //position is Alphabetic position
            binding.heroicon.setImageBitmap(bitmap)
        }
    }

    class Diff : DiffUtil.ItemCallback<Heroname>(){
        override fun areItemsTheSame(
            oldItem: Heroname,
            newItem: Heroname
        ): Boolean {
            return oldItem.stratzId == newItem.stratzId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Heroname,
            newItem: Heroname
        ): Boolean {
            return oldItem.stratzId == newItem.stratzId
                    && oldItem.displayName == newItem.displayName
                    && oldItem.predicted_winrate == newItem.predicted_winrate
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = RowWinrateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = VH(binding)
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val currentItem = getItem(position)
        Log.d(javaClass.simpleName,"position: " + position)
        Log.d(javaClass.simpleName,"iddisplayname: " + currentItem.stratzId.toString() + "," + currentItem.displayName + "," + currentItem.predicted_winrate)
        val binding =holder.binding
        holder.bind(currentItem.stratzId)
        binding.stratzid.text = currentItem.stratzId.toString()
        binding.displayname.text = currentItem.displayName
        val diplaywinrate = String.format("%.2f", currentItem.predicted_winrate * 100.0) + "%"
        binding.winrate.text = diplaywinrate
        val diplayrate = String.format("%.2f", currentItem.withvs_index * 100.0) + "%"
        binding.rate.text = diplayrate

        //set progress bar
        val winratebar = (currentItem.predicted_winrate * 100).toInt()
        val ratebar = (currentItem.withvs_index * 100).toInt()

        if(winratebar >= 50){
            binding.winrateProgressBar.progressDrawable = AppCompatResources.getDrawable(context,R.drawable.progress1)
            binding.winrateProgressBar.secondaryProgress = winratebar
            binding.winrateProgressBar.progress = 50
        }
        else{
            binding.winrateProgressBar.progressDrawable = AppCompatResources.getDrawable(context,R.drawable.progress2)
            binding.winrateProgressBar.secondaryProgress = 50
            binding.winrateProgressBar.progress = winratebar
        }

        if(ratebar >= 0){
            binding.rateProgressBar.progressDrawable = AppCompatResources.getDrawable(context,R.drawable.progress1)
            binding.rateProgressBar.secondaryProgress = ratebar
            binding.rateProgressBar.progress = 0
        }
        else{
            binding.rateProgressBar.progressDrawable = AppCompatResources.getDrawable(context,R.drawable.progress2)
            binding.rateProgressBar.secondaryProgress = 0
            binding.rateProgressBar.progress = ratebar
        }
    }


}