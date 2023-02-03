package ai.pipi.dotapickone.ui.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import ai.pipi.dotapickone.Hero
import ai.pipi.dotapickone.MainActivity
import ai.pipi.dotapickone.MainViewModel
import ai.pipi.dotapickone.databinding.FragmentHomeBinding
import ai.pipi.dotapickone.ui.dashboard.DashboardViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val hero = Hero()
    private val herolist = hero.fetchData()
    private val viewModel: MainViewModel by activityViewModels()
    private val dashviewModel: DashboardViewModel by activityViewModels()


//    private fun initrecyclerview(activityMainBinding: FragmentHomeBinding) {
        // Define a layout for RecyclerView
//        val mainActivity = requireActivity() as MainActivity
//        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//        activityMainBinding.recyclerView.layoutManager = layoutManager
//       // Initialize a new instance of RecyclerView Adapter instance
//        val adapter = Homeadapter(herolist,mainActivity.bitMap)
        // Set the adapter for RecyclerView
//        activityMainBinding.recyclerView.adapter = adapter
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SSS
        //(requireActivity() as AppCompatActivity).supportActionBar?.title = "Simple Album List"
        binding.recyclerView.layoutManager = GridLayoutManager(context,5)
        val mainActivity = requireActivity() as MainActivity


        binding.recyclerView.adapter = Homeadapter(herolist,viewModel,dashviewModel)
        //EEE // XXX Write me, action bar title,
        // recycler view layout manager, and adapter

        setblankslot()

        initslots(view)

        binding.radiant.setOnClickListener{
            viewModel.setradiantordire(true)
            dashviewModel.setradiantordire(true)
        }
        binding.dire.setOnClickListener{
            viewModel.setradiantordire(false)
            dashviewModel.setradiantordire(false)
        }
        binding.imageView.setOnClickListener {
            //binding.imageView.setImageBitmap(null)
        }
        binding.imageView2.setOnClickListener {
            binding.imageView2.setImageDrawable(null)
            //binding.imageView2.setImageBitmap(null)
            viewModel.addtorcount(2)
            dashviewModel.deletewithhero(1)
        }
        binding.imageView3.setOnClickListener {
            binding.imageView3.setImageDrawable(null)
            viewModel.addtorcount(3)
            dashviewModel.deletewithhero(2)
        }
        binding.imageView4.setOnClickListener {
            binding.imageView4.setImageDrawable(null)
            viewModel.addtorcount(4)
            dashviewModel.deletewithhero(3)
        }
        binding.imageView5.setOnClickListener {
            binding.imageView5.setImageDrawable(null)
            viewModel.addtorcount(5)
            dashviewModel.deletewithhero(4)
        }
        binding.imageView6.setOnClickListener {
            val blankimage = viewModel.getblankimage()
            binding.imageView6.setImageBitmap(blankimage)
            binding.imageView6.setBackgroundColor(Color.BLACK)
            viewModel.addtodcount(6)
            dashviewModel.deletevshero(0)
        }
        binding.imageView7.setOnClickListener {
            binding.imageView7.setImageDrawable(null)
            binding.imageView6.setBackgroundColor(Color.BLACK)
            viewModel.addtodcount(7)
            dashviewModel.deletevshero(1)
        }
        binding.imageView8.setOnClickListener {
            binding.imageView8.setImageDrawable(null)
            viewModel.addtodcount(8)
            dashviewModel.deletevshero(2)
        }
        binding.imageView9.setOnClickListener {
            binding.imageView9.setImageDrawable(null)
            viewModel.addtodcount(9)
            dashviewModel.deletevshero(3)
        }
        binding.imageView10.setOnClickListener {
            binding.imageView10.setImageDrawable(null)
            viewModel.addtodcount(10)
            dashviewModel.deletevshero(4)
        }

        viewModel.observeRtap().observe(viewLifecycleOwner){
            val heroinslot = viewModel.getheroinslots()
            if(it > 0){
                val position = heroinslot[it - 1]
                val bitmap: Bitmap?
                if(position >= 0){
                    bitmap = viewModel.getbitmapatposition(position)
                    dashviewModel.addwithhero(position,it - 1)
                    when(it){
                        2-> binding.imageView2.setImageBitmap(bitmap)
                        3-> binding.imageView3.setImageBitmap(bitmap)
                        4-> binding.imageView4.setImageBitmap(bitmap)
                        5-> binding.imageView5.setImageBitmap(bitmap)
                        else-> Log.d(javaClass.simpleName,"rcounterror_or_init:" + it.toString())
                    }
                }
            }
        }
        viewModel.observeDtap().observe(viewLifecycleOwner){
            val heroinslot = viewModel.getheroinslots()
            if(it > 0){
                val position = heroinslot[it - 1]
                val bitmap: Bitmap?
                if(position >= 0){
                    dashviewModel.addvshero(position,it - 6)
                    bitmap = viewModel.getbitmapatposition(position)
                    when(it){
                        6-> binding.imageView6.setImageBitmap(bitmap)
                        7-> binding.imageView7.setImageBitmap(bitmap)
                        8-> binding.imageView8.setImageBitmap(bitmap)
                        9-> binding.imageView9.setImageBitmap(bitmap)
                        10-> binding.imageView10.setImageBitmap(bitmap)
                        else -> Log.d(javaClass.simpleName,"dcounterror_or_init:" + it.toString())
                    }
                }

            }
        }

        viewModel.observerord().observe(viewLifecycleOwner){
            if(it){
                binding.radiant.setBackgroundColor(Color.GREEN)
                binding.dire.setBackgroundColor(Color.LTGRAY)
            }
            else{
                binding.radiant.setBackgroundColor(Color.LTGRAY)
                binding.dire.setBackgroundColor(Color.RED)
            }
        }
    }

    private fun setblankslot(){
        val resId = requireContext().resources.getIdentifier("blankimage", "drawable", requireContext().packageName)
        val currbitmap = BitmapFactory.decodeResource(resources,resId)
        Log.d(javaClass.simpleName,"blankbitmap null or not: " + (currbitmap == null).toString())
        viewModel.addblankbitmap(currbitmap)
        binding.imageView.setImageBitmap(currbitmap)
        binding.imageView6.setImageBitmap(currbitmap)
        binding.imageView.setBackgroundColor(Color.BLACK)
        binding.imageView6.setBackgroundColor(Color.BLACK)
    }

    private fun initslots(view:View){
        val heroinslot = viewModel.getheroinslots()
        Log.d(javaClass.simpleName,"heroinslot:" + heroinslot)
        for(i in 0..9){
            if(heroinslot[i] != -1){
                val position = heroinslot[i]
                val bitmap = viewModel.getbitmapatposition(position)
                val id = resources.getIdentifier("imageView" + (i+1).toString(), "id", requireContext().packageName)
                Log.d(javaClass.simpleName,"idname: imageView" + (i+1).toString())
                val imageview: ImageView = view.findViewById(id)
                imageview.setImageBitmap(bitmap)
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}