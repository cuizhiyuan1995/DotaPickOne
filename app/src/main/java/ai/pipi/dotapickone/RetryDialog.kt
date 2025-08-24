//package ai.pipi.dotapickone
//
//import android.app.Activity
//import android.app.AlertDialog
//import ai.pipi.dotapickone.databinding.CustomRetryBinding
//
//class RetryDialog(input_activity: Activity) {
//    private var activity: MainActivity = input_activity as MainActivity
//    private lateinit var alertDialog : AlertDialog
//    private lateinit var binding: CustomRetryBinding
//
//    fun startRetryDiaglog(){
//        val builder = AlertDialog.Builder(activity)
//        val inflater = activity.layoutInflater
//        binding = CustomRetryBinding.inflate(inflater)
//        binding.retryButton.setOnClickListener {
//            activity.fetchfromapi()
//            dismissRetryDialog()
//        }
//        builder.setView(binding.root)
//        builder.setCancelable(false)
//        alertDialog = builder.create()
//        alertDialog.show()
//    }
//
//    fun dismissRetryDialog(){
//        alertDialog.dismiss()
//    }
//}