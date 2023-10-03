package uz.gita.musicplayermn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.musicplayermn.presentetion.like.LikeDirection
import uz.gita.musicplayermn.presentetion.like.LikeDirectionImpl
import uz.gita.musicplayermn.presentetion.music.MusicListDirection
import uz.gita.musicplayermn.presentetion.music.MusicListDirectionImpl
import uz.gita.musicplayermn.presentetion.play.PlayDirection
import uz.gita.musicplayermn.presentetion.play.PlayDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindMusicListDirection(impl: MusicListDirectionImpl): MusicListDirection


    @Binds
    fun bindPlayDirection(impl: PlayDirectionImpl): PlayDirection

    @Binds
    fun bindLikeDirection(impl: LikeDirectionImpl): LikeDirection
}
