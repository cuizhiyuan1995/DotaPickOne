package ai.pipi.dotapickone

import RawLoadingdialog
import ai.pipi.dotapickone.compose.About
import ai.pipi.dotapickone.compose.Settings
import ai.pipi.dotapickone.compose.Versionlog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import apolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.exception.ApolloException
import ai.pipi.dotapickone.room.*
import ai.pipi.dotapickone.ui.dashboard.DashboardViewModel
import ai.pipi.dotapickone.ui.dashboard.ScreenDash
import ai.pipi.dotapickone.ui.home.ScreenHome
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val apolloClient = apolloClient()
    private val dashviewModel: DashboardViewModel by viewModels()
    //private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //voidspirit_icon.png



        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)



        initialize_checkfetch()
        setContent {
            LoadingDialog()
            val navController = rememberNavController()

            //filters checkboxes
            var checked1 by remember { mutableStateOf(true) }
            var checked2 by remember { mutableStateOf(true) }
            var checked3 by remember { mutableStateOf(true) }
            var checked4 by remember { mutableStateOf(true) }
            var checked5 by remember { mutableStateOf(true) }
            var showMenu by remember { mutableStateOf(false) }
            Log.d(javaClass.simpleName, "checked1 value: " + checked1)

            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        items.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        rememberVectorPainter(image = screen.icon),
                                        contentDescription = screen.route
                                    ) },
                                label = { Text(stringResource(screen.resourceId)) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                },
                topBar = {
                    TopAppBar(
                        title = { Text("PickOne") },
                        actions = {
                            // Menu button (three dots)
                            IconButton(onClick = { showMenu = !showMenu }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.filter_alt_24px),
                                    contentDescription = "Filter"
                                )
                            }

                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = {
                                    showMenu = false
                                    val selected = listOf(checked1,checked2,checked3,checked4,checked5)
                                    dashviewModel.addFilter(selected)
                                }
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = checked1, onCheckedChange = { checked1 = it })
                                        Text("Position1 - Carry")
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = checked2, onCheckedChange = { checked2 = it })
                                        Text("Position2 - Mid")
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = checked3, onCheckedChange = { checked3 = it })
                                        Text("Position3 - Offline")
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = checked4, onCheckedChange = { checked4 = it })
                                        Text("Position4 - Soft Support")
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(checked = checked5, onCheckedChange = { checked5 = it })
                                        Text("Position5 - Support")
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Button(
                                        onClick = {
                                            val selected = listOf(checked1,checked2,checked3,checked4,checked5)
                                            dashviewModel.addFilter(selected)
                                            showMenu = false
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Apply")
                                    }
                                }
                            }

                        }
                    )
                }

            ) { innerPadding ->
                NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)){
                    composable(route = "SCREEN_HOME") {
                        ScreenHome(viewModel,dashviewModel,navController)
                    }
                    composable(route = "SCREEN_DASH") {
                        ScreenDash(viewModel,dashviewModel,navController)
                    }
                    composable(route = "SCREEN_ABOUT") {
                        About(navController)
                    }
                    composable(route = "SCREEN_SETTING") {
                        Settings(navController)
                    }
                    composable(route = "SCREEN_VERSION") {
                        Versionlog(navController)
                    }
                }
            }
        }
        //setup bottomnavigation

//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        //navView.setupWithNavController(navController)
//        binding.navView.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when(destination.id){
//                R.id.navigation_home ->{
//                    binding.navView.visibility = View.VISIBLE
//                }
//                R.id.navigation_dashboard->{
//                    binding.navView.visibility = View.VISIBLE
//                }
//                R.id.navigation_notifications->{
//                    binding.navView.visibility = View.VISIBLE
//                }
//                else->{
//                    binding.navView.visibility = View.GONE
//                }
//            }
//        }

        initbitmaps()


        //initialize()



    }

    fun initialize(){
//        val loadingDialog = LoadingDialog(this)
//        loadingDialog.startLoadingDiaglog()



        lifecycleScope.launch {

            val dataprefermanager = SettingsManager(this@MainActivity)
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.BASIC_ISO_DATE
            val formatted = current.format(formatter)
            Log.d(javaClass.simpleName,"currenttime: " + formatted)

            val time: Int
            time = viewModel.getlastfetchdate()
            Log.d(javaClass.simpleName,"saved last time: " + time.toString())
            if(time != formatted.toInt()){
                try {
                    val networkcheck = isOnline(this@MainActivity)
                    if(networkcheck){
                        fetchfromapi()
                    }
                    else{
                        Log.d(javaClass.simpleName,"fetch api but no network connection")
//                                    val networkDialog = NetworkDialog(this@MainActivity)
//                                    networkDialog.startNetworkDiaglog()
                    }
                }catch (exception: ApolloException){
                    Log.d(javaClass.simpleName,"api error: " + exception)
//                                val retryDialog = RetryDialog(this@MainActivity)
//                                retryDialog.startRetryDiaglog()
                }
            }
            else{
                Log.d(javaClass.simpleName,"start dashviewmodel init ")
                dashviewModel.startinit()
            }



            lifecycleScope.launch{
                dataprefermanager.getlastFetchTime().catch { e ->
                    e.printStackTrace()
                }.collect {
                    withContext(Dispatchers.Main) {

                    }
                }
            }




//            dashviewModel.observe_finishloading().observe(this@MainActivity){
//                loadingDialog.dismissLoadingDialog()
//            }


        }
    }

    fun initialize_checkfetch(){
//        val loadingDialog = LoadingDialog(this)
//        loadingDialog.startLoadingDiaglog()

        val dataprefermanager = SettingsManager(this@MainActivity)
        val formatter = DateTimeFormatter.BASIC_ISO_DATE

        var time: Int
        lifecycleScope.launch{
            dataprefermanager.getlastFetchTime().catch { e ->
                e.printStackTrace()
            }.collect {
                withContext(Dispatchers.Main) {
                    Log.d(javaClass.simpleName,"saved last time_fetch check: " + it.toString())
                    viewModel.setlastfetchdate(it)
                }
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
            //val currbitmap = BitmapFactory.decodeResource(resources,resId)
            viewModel.addbitmap(resId)
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

        Log.d(javaClass.simpleName,"herosample: ${response.data?.heroStats?.m138.toString()}")
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


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }

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


    @Composable
    fun LoadingDialog() {
        var showDialog by remember { mutableStateOf(true) }
        var querystart by remember { mutableStateOf(false) }
        var lastfetchtime by remember { mutableStateOf(viewModel.getlastfetchdate()) }
        dashviewModel.observe_finishloading().observe(this@MainActivity){
            showDialog = !it
        }
        val initial_network = isOnline(this@MainActivity)
        dashviewModel.set_network(initial_network)
        var netDialog by remember { mutableStateOf(initial_network) }
        Log.d(javaClass.simpleName,"fisrt network check: " + netDialog.toString())
        dashviewModel.observe_network().observe(this@MainActivity){
            Log.d(javaClass.simpleName,"start network observe initialize in compose: " + it.toString())
            netDialog = it
            if(netDialog and !querystart){
                Log.d(javaClass.simpleName,"start network initialize in compose")
                querystart = true
                initialize()
            }
        }
        if (showDialog and !netDialog and (lastfetchtime == -1)) {
            AlertDialog(
                onDismissRequest = { netDialog = true},
                title = { Text("Network connection failed") },
                text = { Text("Please check your network connection and try again") },
                confirmButton = {
                    TextButton(onClick = {
                        val curr_network = isOnline(this@MainActivity)
                        dashviewModel.set_network(curr_network)
//                        if(netDialog){
//                            this.fetchfromapi()
//                        }
                    }) {
                        Text("Retry".uppercase())
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        this@MainActivity.finishAndRemoveTask()
                    }) {
                        Text("Exit".uppercase())
                    }
                },
            )
        }
        else if(showDialog and !netDialog){
            RawLoadingdialog()
            dashviewModel.startinit()
        }
        else if(showDialog){
            RawLoadingdialog()
        }
    }

    sealed class Screen(val route: String, @StringRes val resourceId: Int,val icon: ImageVector) {
        object Home : Screen("SCREEN_HOME", R.string.title_home,Icons.Default.Home)
        object Dashboard : Screen("SCREEN_DASH", R.string.title_dashboard,Icons.Default.Menu)
        object Setting : Screen("SCREEN_SETTING", R.string.title_notifications,Icons.Default.Settings)
    }

    val items = listOf(
        Screen.Home,
        Screen.Dashboard,
        Screen.Setting
    )


}


