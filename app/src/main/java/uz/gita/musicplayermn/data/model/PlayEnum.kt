package uz.gita.musicplayermn.data.model

enum class PlayEnum(val amount: Int)  {
    MANAGE(6), PREV(1),
    NEXT(2), UPDATE_SEEKBAR(3)
}