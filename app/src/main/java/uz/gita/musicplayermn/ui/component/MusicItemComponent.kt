package uz.gita.musicplayermn.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import uz.gita.musicplayermn.data.model.MusicData
import uz.gita.musicplayermn.R


@OptIn(ExperimentalUnitApi::class)
@Composable
fun MusicItemComponent(
    musicData: MusicData,
    onClick: () -> Unit
) {
    Surface(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 4.dp)
        .clickable { onClick.invoke() }
    ) {
        Row(modifier = Modifier.wrapContentHeight()) {

            if (musicData.albumArt != null) {
                val bitmap = musicData.albumArt.asImageBitmap()
                Image(
                    bitmap,
                    contentDescription = "MusicDisk",
                    modifier = Modifier
                        .width(56.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.FillBounds
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.vinil),
                    contentDescription = "MusicDisk",
                    modifier = Modifier
                        .width(56.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = musicData.title ?: "Unknown name",
                    color = Color.Black,
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = musicData.artist ?: "Unknown artist",
                    color = Color(0XFF988E8E),
                    fontSize = TextUnit(14f, TextUnitType.Sp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MusicItemComponentPreview() {
//        val musicDate = MusicData(
//            0,
//            "My artist",
//            "Test title",
//            null,
//            10000,
//
//        )
//
//        MusicItemComponent(
//            musicData = musicDate,
//            onClick = {}
//        )
}
