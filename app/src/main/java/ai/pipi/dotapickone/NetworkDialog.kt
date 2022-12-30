package ai.pipi.dotapickone

import android.app.Activity
import android.app.AlertDialog
import android.widget.Toast
import ai.pipi.dotapickone.databinding.CustomNetworkBinding

class NetworkDialog(input_activity: Activity) {
    private var activity = input_activity as MainActivity
    private lateinit var alertDialog : AlertDialog
    private lateinit var binding: CustomNetworkBinding

    fun startNetworkDiaglog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        binding = CustomNetworkBinding.inflate(inflater)
        binding.retryButton.setOnClickListener {
            val networkconnect = activity.isOnline(activity)
            if(networkconnect){
                activity.initialize()
                dismissRetryDialog()
            }
            else{
                Toast.makeText(activity, "network connection failed", Toast.LENGTH_SHORT).show()
            }
        }
        binding.exitButton.setOnClickListener {
            activity.finishAndRemoveTask()
        }
        builder.setView(binding.root)
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()
    }

    fun dismissRetryDialog(){
        alertDialog.dismiss()
    }
}