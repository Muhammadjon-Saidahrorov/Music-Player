package uz.gita.musicplayermn.presentetion.like

import android.content.Intent
import android.os.Build
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.*
import uz.gita.musicplayermn.MainActivity
import uz.gita.musicplayermn.R
import uz.gita.musicplayermn.data.model.CommandEnum
import uz.gita.musicplayermn.data.model.CursorEnum
import uz.gita.musicplayermn.service.MusicService
import uz.gita.musicplayermn.ui.component.LoadingComponent
import uz.gita.musicplayermn.ui.component.MusicItemComponent
import uz.gita.musicplayermn.utils.MyEventBus
import uz.gita.musicplayermn.utils.base.getMusicDataByPosition
import uz.gita.musicplayermn.utils.navigation.AppScreen

class LikeScreen : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val activity = LocalContext.current as MainActivity
        val viewModel: LikeContract.ViewModel = getViewModel<LikeViewModel>()
        val uiState = viewModel.collectAsState()

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                LikeContract.SideEffect.StartMusicService -> {
                    MyEventBus.currentCursorEnum = CursorEnum.SAVED
                    val intent = Intent(activity, MusicService::class.java)
                    intent.putExtra("COMMAND", CommandEnum.PLAY)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        activity.startForegroundService(intent)
                    } else activity.startService(intent)
                }
            }
        }

        LifecycleEffect(
            onStarted = { viewModel.onEventDispatcher(LikeContract.Intent.CheckMusicExistence) }
        )

        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(topBar = { TopBar() }) {
                PlayListScreenContent(
                    uiState = uiState,
                    eventListener = viewModel::onEventDispatcher,
                    modifier = Modifier.padding(it)
                )
            }
        }
    }
}

@Composable
fun PlayListScreenContent(
    uiState: State<LikeContract.UIState>,
    eventListener: (LikeContract.Intent) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize()) {
        when (uiState.value) {

            LikeContract.UIState.IsExistMusic -> {
                LoadingComponent()
                eventListener.invoke(LikeContract.Intent.LoadMusics)
            }

            LikeContract.UIState.Loading -> {
                eventListener.invoke(LikeContract.Intent.CheckMusicExistence)
            }

            is LikeContract.UIState.PreparedData -> {
                if (MyEventBus.roomCursor!!.count == 0) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(180.dp),
                            painter = painterResource(id = R.drawable.empty),
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(text = "Empty !", fontSize = 18.sp, color = Color(0xFF4C4C4C))
                    }
                } else {
                    LazyColumn {

                        for (pos in 0 until MyEventBus.roomCursor!!.count) {
                            item {
                                MusicItemComponent(
                                    musicData = MyEventBus.roomCursor!!.getMusicDataByPosition(pos),
                                    onClick = {
                                        MyEventBus.roomPos = pos
                                        eventListener.invoke(LikeContract.Intent.PlayMusic)
                                        eventListener.invoke(LikeContract.Intent.OpenPlayScreen)
                                    }
                                )
                            }
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(56.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Color.Black,
            fontSize = 22.sp,
            text = "Favorites",
            fontWeight = FontWeight.Bold
        )
    }
}