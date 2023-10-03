package uz.gita.musicplayermn.data.model

import android.graphics.Bitmap
import android.util.Base64
import uz.gita.musicplayermn.data.sourse.local.entity.MusicEntity
import java.io.ByteArrayOutputStream

data class MusicData(
    val id: Int,
    val artist: String?,
    val title: String?,
    val data: String?,
    val duration: Long,
    val albumArt: Bitmap?
){
    fun toEntity() = MusicEntity(id, artist, title, data, duration,
        albumArt?.let { convertBitmapToByteArray(it) })

    private fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun convertBitmapToString(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        val encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT)
        return encodedString
    }
}