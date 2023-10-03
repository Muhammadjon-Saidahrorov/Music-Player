package uz.gita.musicplayermn

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.musicplayermn.presentetion.music.MusicListScreen
import uz.gita.musicplayermn.utils.navigation.NavigatorHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigatorHandler: NavigatorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = resources.getColor(R.color.black)

        setContent {
                Navigator(screen = MusicListScreen()) { navigator ->
                    LaunchedEffect(navigator) {
                        navigatorHandler.navigatorState
                            .onEach { it.invoke(navigator) }
                            .launchIn(lifecycleScope)
                    }
                    CurrentScreen()
                }

        }
    }

}
