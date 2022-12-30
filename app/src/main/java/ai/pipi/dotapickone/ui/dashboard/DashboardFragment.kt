package ai.pipi.dotapickone.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ai.pipi.dotapickone.MainViewModel
import ai.pipi.dotapickone.R
import ai.pipi.dotapickone.databinding.FragmentDashboardBinding
import ai.pipi.dotapickone.room.Heroname
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val mainviewmodel: MainViewModel by activityViewModels()
    private val dashviewmodel: DashboardViewModel by activityViewModels()
    private lateinit var adapter: DashboardAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(javaClass.simpleName, "onViewCreated")
        // XXX Write me

        val rowadapter = initAdapter(binding)
        val rv = binding.sortlist
        val itemDecor = DividerItemDecoration(rv.context, LinearLayoutManager.VERTICAL)
        rv.addItemDecoration(itemDecor)
        rv.adapter = rowadapter
        rv.layoutManager = LinearLayoutManager(rv.context)

        // XXX Write me, onclick listeners and observers


        val textView: TextView = binding.textDashboard
        dashviewmodel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }



        //add sort function
        binding.heronameLinearlayout.setOnClickListener {
            Log.d(javaClass.simpleName,"heroname_clicked")
            dashviewmodel.sortInfoClick(SortColumn_value.Heroname)
            init_heroname_observer()
        }
        binding.herowinrateLinearlayout.setOnClickListener {
            dashviewmodel.sortInfoClick(SortColumn_value.Winrate)
            init_heroname_observer()
        }
        binding.HeroindexLinearlayout.setOnClickListener {
            dashviewmodel.sortInfoClick(SortColumn_value.Rate)
            init_heroname_observer()
        }


        init_sortinfo_observer()

        //scroll to top after sort
        adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                binding.sortlist.smoothScrollToPosition(0)
            }
            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                binding.sortlist.smoothScrollToPosition(0)
            }
            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                binding.sortlist.smoothScrollToPosition(0)
            }
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.sortlist.smoothScrollToPosition(0)
            }
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                binding.sortlist.smoothScrollToPosition(0)
            }
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                binding.sortlist.smoothScrollToPosition(0)
            }
        })

        // Balloon view created here
        // Balloon view created here
        val balloon = Balloon.Builder(requireContext())
            .setArrowSize(10)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setIsVisibleArrow(true)
            .setArrowPosition(0.8f)
            .setWidthRatio(0.6f)
            .setHeight(BalloonSizeSpec.WRAP)
            .setTextSize(15f)
            .setCornerRadius(4f)
            .setAlpha(0.9f)
            .setText(requireContext().getString(R.string.rate_help))
            .setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.deep_gray
                )
            )
            .setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_gray
                )
            )
            .setBalloonAnimation(BalloonAnimation.FADE)
            .setAutoDismissDuration(4000L)
            .build()


        binding.rateHelp.setOnClickListener{
            balloon.showAlignTop(it)
        }


    }

    private fun initAdapter(binding:FragmentDashboardBinding ) : DashboardAdapter {
        binding.sortlist.layoutManager = LinearLayoutManager(binding.sortlist.context)
        adapter = DashboardAdapter(mainviewmodel,dashviewmodel,requireContext())
        init_heroname_observer()
        return adapter
    }

    private fun init_heroname_observer(){
        dashviewmodel.observe_heroname().observe(viewLifecycleOwner){
            Log.d(javaClass.simpleName,"enter submitlist")
            //Log.d(javaClass.simpleName,it.toString())
            val list_removechosen = mutableListOf<Heroname>()
            for(heroname in it){
                if(!dashviewmodel.checkchosen(heroname.stratzId)){
                    list_removechosen.add(heroname)
                }
            }
            adapter.submitList(list_removechosen)
            Log.d(javaClass.simpleName,"scroll to top")

        }
    }

    private fun init_sortinfo_observer(){
        dashviewmodel.observe_sortinfo().observe(viewLifecycleOwner){
            Log.d(javaClass.simpleName,"enter observe sort")
            when(it){
                SortInfo(SortColumn_value.Heroname,true)->{
                    Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_AscName")
                    binding.heronameSort.visibility = View.VISIBLE
                    binding.heronameSort.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    binding.winrateSort.visibility = View.GONE
                    binding.rateSort.visibility = View.GONE
                }
                SortInfo(SortColumn_value.Heroname,false)->{
                    Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_DescName")
                    binding.heronameSort.visibility = View.VISIBLE
                    binding.heronameSort.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    binding.winrateSort.visibility = View.GONE
                    binding.rateSort.visibility = View.GONE
                }
                SortInfo(SortColumn_value.Winrate,true)->{
                    Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_AscWin")
                    binding.winrateSort.visibility = View.VISIBLE
                    binding.winrateSort.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    binding.heronameSort.visibility = View.GONE
                    binding.rateSort.visibility = View.GONE
                }
                SortInfo(SortColumn_value.Winrate,false)->{
                    Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_DescWin")
                    binding.winrateSort.visibility = View.VISIBLE
                    binding.winrateSort.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    binding.heronameSort.visibility = View.GONE
                    binding.rateSort.visibility = View.GONE
                }
                SortInfo(SortColumn_value.Rate,true)->{
                    Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_AscRate")
                    binding.rateSort.visibility = View.VISIBLE
                    binding.rateSort.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    binding.heronameSort.visibility = View.GONE
                    binding.winrateSort.visibility = View.GONE
                }
                SortInfo(SortColumn_value.Rate,false)->{
                    Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_DescRate")
                    binding.rateSort.visibility = View.VISIBLE
                    binding.rateSort.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    binding.heronameSort.visibility = View.GONE
                    binding.winrateSort.visibility = View.GONE
                }
                else->{
                    Log.d(javaClass.simpleName,"sorinfoclick_sortarrow_error")
                }
            }



        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}