package uz.gita.musicplayermn.presentetion.music

import uz.gita.musicplayermn.presentetion.like.LikeScreen
import uz.gita.musicplayermn.presentetion.play.PlayScreen
import uz.gita.musicplayermn.utils.navigation.AppNavigator
import javax.inject.Inject

interface MusicListDirection {
    suspend fun navigationToPlayScreen()
    suspend fun navigationToLikeScreen()
}

class MusicListDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    MusicListDirection {
    override suspend fun navigationToPlayScreen() {
        navigator.navigateTo(PlayScreen())
    }

    override suspend fun navigationToLikeScreen() {
        navigator.navigateTo(LikeScreen())
    }

}