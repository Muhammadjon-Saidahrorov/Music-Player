package uz.gita.musicplayermn.utils.navigation

import kotlinx.coroutines.flow.SharedFlow

interface NavigatorHandler {
    val navigatorState: SharedFlow<NavigationArgs>
}