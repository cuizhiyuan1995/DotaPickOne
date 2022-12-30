package ai.pipi.dotapickone

import android.app.Activity
import android.app.AlertDialog
import ai.pipi.dotapickone.databinding.CustomLoadingBinding


class LoadingDialog(input_activity: Activity) {
    private var activity = input_activity
    private lateinit var alertDialog : AlertDialog
    private lateinit var binding: CustomLoadingBinding

    fun startLoadingDiaglog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        binding = CustomLoadingBinding.inflate(inflater)
        builder.setView(binding.root)
        //builder.setView(inflater.inflate(R.layout.custom_loading,null))
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()
    }

    fun dismissLoadingDialog(){
        alertDialog.dismiss()
    }


}