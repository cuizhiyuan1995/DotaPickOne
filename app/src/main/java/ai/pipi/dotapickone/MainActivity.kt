package ai.pipi.dotapickone

import android.content.Context
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import apolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.exception.ApolloException
import ai.pipi.dotapickone.databinding.ActivityMainBinding
import ai.pipi.dotapickone.room.*
import ai.pipi.dotapickone.ui.dashboard.DashboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val apolloClient = apolloClient()
    private val dashviewModel: DashboardViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //voidspirit_icon.png



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup bottomnavigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.navigation_home ->{
                    binding.navView.visibility = View.VISIBLE
                }
                R.id.navigation_dashboard->{
                    binding.navView.visibility = View.VISIBLE
                }
                R.id.navigation_notifications->{
                    binding.navView.visibility = View.VISIBLE
                }
                else->{
                    binding.navView.visibility = View.GONE
                }
            }
        }

        initbitmaps()

        val networkcheck = isOnline(this)
        if(networkcheck){
            initialize()
        }
        else{
            val networkDialog = NetworkDialog(this)
            networkDialog.startNetworkDiaglog()
        }

    }

    fun initialize(){
        val loadingDialog = LoadingDialog(this)
        loadingDialog.startLoadingDiaglog()



        lifecycleScope.launch {

            val dataprefermanager = SettingsManager(this@MainActivity)
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.BASIC_ISO_DATE
            val formatted = current.format(formatter)
            Log.d(javaClass.simpleName,"currenttime: " + formatted)

            var time: Int
            lifecycleScope.launch{
                dataprefermanager.getlastFetchTime().catch { e ->
                    e.printStackTrace()
                }.collect {
                    withContext(Dispatchers.Main) {
                        time = it
                        Log.d(javaClass.simpleName,"saved last time: " + time.toString())
                        if(time != formatted.toInt()){
                            try {
                                fetchfromapi()
                            }catch (exception: ApolloException){
                                Log.d(javaClass.simpleName,"api error: " + exception)
                                val retryDialog = RetryDialog(this@MainActivity)
                                retryDialog.startRetryDiaglog()
                            }
                        }
                        else{
                            Log.d(javaClass.simpleName,"start dashviewmodel init ")
                            dashviewModel.startinit()
                        }
                    }
                }
            }




            dashviewModel.observe_finishloading().observe(this@MainActivity){
                loadingDialog.dismissLoadingDialog()
            }


        }
    }

    private fun initbitmaps(){
        val hero = Hero()
        val herolist = hero.fetchData()
        for (name in herolist){
            val temp = name.lowercase() + "_icon"
            Log.d(javaClass.simpleName,"name is:" + temp)
            val resId = this.resources.getIdentifier(temp, "drawable", this.packageName)
            val currbitmap = BitmapFactory.decodeResource(resources,resId)
            viewModel.addbitmap(currbitmap)
        }
    }

    fun fetchfromapi() = lifecycleScope.launch{
        //database create
        val db = AppDatabase.getDatabase(applicationContext)
        val heronameDao = db.heronameDao()
        val herowinrateDao = db.herowinrateDao()
        val herowithDao = db.herowithDao()
        val herovsDao = db.herovsDao()


        Log.d(javaClass.simpleName, "query start")

        val response: ApolloResponse<DotaAppQuery.Data>
        val response1: ApolloResponse<DotaVsQueryoneQuery.Data>
        val response2: ApolloResponse<DotaVsQuerytwoQuery.Data>
        val response3: ApolloResponse<DotaVsQuerythreeQuery.Data>
        val response4: ApolloResponse<DotaVsQueryfourQuery.Data>
        val response5: ApolloResponse<DotaVsQueryfiveQuery.Data>



        response = apolloClient.query(
            DotaAppQuery(
                matchLimit = Optional.present(0)
            )
        ).fetchPolicy(
            FetchPolicy.NetworkFirst).execute()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        response1 = apolloClient.query(DotaVsQueryoneQuery(matchLimit = Optional.present(0))).fetchPolicy(
            FetchPolicy.NetworkFirst).execute()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        response2 = apolloClient.query(DotaVsQuerytwoQuery(matchLimit = Optional.present(0))).fetchPolicy(
            FetchPolicy.NetworkFirst).execute()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        response3 = apolloClient.query(DotaVsQuerythreeQuery(matchLimit = Optional.present(0))).fetchPolicy(
            FetchPolicy.NetworkFirst).execute()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        response4 = apolloClient.query(DotaVsQueryfourQuery(matchLimit = Optional.present(0))).fetchPolicy(
            FetchPolicy.NetworkFirst).execute()
        withContext(Dispatchers.IO) {
            Thread.sleep(1_000)
        }
        response5 = apolloClient.query(DotaVsQueryfiveQuery(matchLimit = Optional.present(0))).fetchPolicy(
            FetchPolicy.NetworkFirst).execute()




        val heronamelist = response.data?.constants?.toHeronames()
        val herowinratelist = response.data?.heroStats?.toHerowinrates()

        Log.d(javaClass.simpleName,"herosample: ${response.data?.heroStats?.m137.toString()}")
        Log.d(javaClass.simpleName,"herosample: ${response1.data?.heroStats?.m23.toString()}")
        Log.d(javaClass.simpleName,"herosample: ${response2.data?.heroStats?.m47.toString()}")
        Log.d(javaClass.simpleName,"herosample: ${response3.data?.heroStats?.m69.toString()}")
        Log.d(javaClass.simpleName,"herosample: ${response4.data?.heroStats?.m92.toString()}")
        Log.d(javaClass.simpleName,"herosample: ${response5.data?.heroStats?.m119.toString()}")

        Log.d(javaClass.simpleName, "Query Success ${response.data}")
        Log.d(javaClass.simpleName, "heroname: ${response.data?.constants?.heroes}")

        if (heronamelist != null) {
            Log.d(javaClass.simpleName,"startinsertindatabase")
            heronameDao.insertAll(*heronamelist.heronames.toTypedArray())
        }

        if (herowinratelist != null) {
            herowinrateDao.insertAll(*herowinratelist.herowinrates.toTypedArray())
        }


        //insert data to herowith room table
        var herowithlist = response1.data?.heroStats?.toHerowiths()
        if(herowithlist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herowithDao.insertAll(*herowithlist.herowiths.toTypedArray())
        }
        herowithlist = response2.data?.heroStats?.toHerowiths()
        if(herowithlist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herowithDao.insertAll(*herowithlist.herowiths.toTypedArray())
        }
        herowithlist = response3.data?.heroStats?.toHerowiths()
        if(herowithlist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herowithDao.insertAll(*herowithlist.herowiths.toTypedArray())
        }
        herowithlist = response4.data?.heroStats?.toHerowiths()
        if(herowithlist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herowithDao.insertAll(*herowithlist.herowiths.toTypedArray())
        }
        herowithlist = response5.data?.heroStats?.toHerowiths()
        if(herowithlist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herowithDao.insertAll(*herowithlist.herowiths.toTypedArray())
        }
        herowithlist = response.data?.heroStats?.toHerowiths()
        if(herowithlist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herowithDao.insertAll(*herowithlist.herowiths.toTypedArray())
        }

        //insert data to herovs room table
        var herovslist = response1.data?.heroStats?.toHerovss()
        if(herovslist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herovsDao.insertAll(*herovslist.herovss.toTypedArray())
        }
        herovslist = response2.data?.heroStats?.toHerovss()
        if(herovslist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herovsDao.insertAll(*herovslist.herovss.toTypedArray())
        }
        herovslist = response3.data?.heroStats?.toHerovss()
        if(herovslist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herovsDao.insertAll(*herovslist.herovss.toTypedArray())
        }
        herovslist = response4.data?.heroStats?.toHerovss()
        if(herovslist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herovsDao.insertAll(*herovslist.herovss.toTypedArray())
        }
        herovslist = response5.data?.heroStats?.toHerovss()
        if(herovslist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herovsDao.insertAll(*herovslist.herovss.toTypedArray())
        }
        herovslist = response.data?.heroStats?.toHerovss()
        if(herovslist != null){
            Log.d(javaClass.simpleName,"startinsert with indatabase")
            herovsDao.insertAll(*herovslist.herovss.toTypedArray())
        }


        val dataprefermanager = SettingsManager(this@MainActivity)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.BASIC_ISO_DATE
        val formatted = current.format(formatter)
        Log.d(javaClass.simpleName,"currenttime: " + formatted)
        Log.d(javaClass.simpleName,"start savelastfetchtime ")
        dataprefermanager.saveLastFetchTime(formatted.toInt())

        Log.d(javaClass.simpleName,"start dashviewmodel init ")
        dashviewModel.startinit()

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }


}