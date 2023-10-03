package uz.gita.musicplayermn.presentetion.like

import uz.gita.musicplayermn.presentetion.play.PlayScreen
import uz.gita.musicplayermn.utils.navigation.AppNavigator
import javax.inject.Inject

interface LikeDirection {
    suspend fun navigateToPlayScreen()
}

class LikeDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : LikeDirection {

    override suspend fun navigateToPlayScreen() {
        appNavigator.navigateTo(PlayScreen())
    }
}