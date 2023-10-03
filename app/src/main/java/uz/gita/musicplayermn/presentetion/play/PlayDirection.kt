package uz.gita.musicplayermn.presentetion.play

import uz.gita.musicplayermn.utils.navigation.AppNavigator
import javax.inject.Inject

interface PlayDirection {
    suspend fun back()
}

class PlayDirectionImpl @Inject constructor(
    private val navigator : AppNavigator
): PlayDirection{
    override suspend fun back() {
        navigator.pop()
    }

}