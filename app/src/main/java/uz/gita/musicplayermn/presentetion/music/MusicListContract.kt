package uz.gita.musicplayermn.presentetion.music

import android.content.Context
import org.orbitmvi.orbit.ContainerHost
import uz.gita.musicplayermn.data.model.PlayEnum

sealed interface MusicListContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Loading : UIState
        object PreparedData : UIState
    }

    sealed interface SideEffect {
        object StartMusicService : SideEffect
        data class UserAction(val playEnum: PlayEnum) : SideEffect
        object OpenPermissionDialog : SideEffect
    }

    sealed interface Intent {
        data class LoadMusics(val context: Context) : Intent
        object PlayMusic : Intent
        object OpenPlayScreen : Intent
        data class UserAction(val playEnum: PlayEnum) : Intent
        object OpenLikeScreen : Intent
        object RequestPermission : Intent
    }
}