package uz.gita.musicplayermn.data.sourse.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.musicplayermn.data.sourse.local.dao.MusicDao
import uz.gita.musicplayermn.data.sourse.local.entity.MusicEntity

@Database(entities = [MusicEntity::class], version = 1, exportSchema = false)
abstract class MusicDatabase : RoomDatabase() {

    abstract fun getMusicDao(): MusicDao
}