package qzwx.app.ui.qgrid

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import qzwx.app.ui.theme.XiangsutiFont

/**
 * 带图片的像素风格卡片网格组件
 * 为带图片的卡片项提供统一样式的网格布局
 */
@Composable
fun QImageCardGrid(
    items: List<QImageGridItem>,
    modifier: Modifier = Modifier.fillMaxHeight(),
    columnsMode: QGridColumnsMode = QGridColumnsMode.FIXED,
    columns: Int = 2,
    minColumnWidth: Dp = 150.dp,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    verticalSpacing: Dp = 8.dp,
    horizontalSpacing: Dp = 8.dp,
    title: String? = null,
    cardStyle: QCardGridStyle = QCardGridStyle.DEFAULT,
    imageAspectRatio: Float = 16f / 9f, // 默认16:9
    showTitle: Boolean = true,
    showContent: Boolean = true,
    onItemClick: ((QImageGridItem) -> Unit)? = null
) {
    QGrid(
        modifier = modifier,
        columnsMode = columnsMode,
        columns = columns,
        minColumnWidth = minColumnWidth,
        contentPadding = contentPadding,
        verticalSpacing = verticalSpacing,
        horizontalSpacing = horizontalSpacing,
        title = title,
    ) {
        items(items = items) { item ->
            QImageGridCard(
                item = item,
                cardStyle = cardStyle,
                imageAspectRatio = imageAspectRatio,
                showTitle = showTitle,
                showContent = showContent,
                onClick = if (onItemClick != null) {
                    { onItemClick(item) }
                } else null
            )
        }
    }
}

/**
 * 照片墙样式网格组件
 * 为图片提供等比例的网格布局，适合展示相册
 */
@Composable
fun QPhotoGrid(
    items: List<QImageGridItem>,
    modifier: Modifier = Modifier.fillMaxHeight(),
    columnsMode: QGridColumnsMode = QGridColumnsMode.FIXED,
    columns: Int = 3,
    minColumnWidth: Dp = 100.dp,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    verticalSpacing: Dp = 4.dp,
    horizontalSpacing: Dp = 4.dp,
    title: String? = null,
    aspectRatio: Float = 1f, // 默认正方形
    showTitles: Boolean = false,
    onItemClick: ((QImageGridItem) -> Unit)? = null
) {
    QGrid(
        modifier = modifier,
        columnsMode = columnsMode,
        columns = columns,
        minColumnWidth = minColumnWidth,
        contentPadding = contentPadding,
        verticalSpacing = verticalSpacing,
        horizontalSpacing = horizontalSpacing,
        title = title,
    ) {
        items(items = items) { item ->
            QPhotoGridItem(
                item = item,
                aspectRatio = aspectRatio,
                showTitle = showTitles,
                onClick = if (onItemClick != null) {
                    { onItemClick(item) }
                } else null
            )
        }
    }
}

/**
 * 带图片的网格卡片项
 */
@Composable
private fun QImageGridCard(
    item: QImageGridItem,
    cardStyle: QCardGridStyle,
    imageAspectRatio: Float = 16f / 9f,
    showTitle: Boolean = true,
    showContent: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    val style = when (cardStyle) {
        QCardGridStyle.DEFAULT -> CardStyle(
            backgroundColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            borderColor = MaterialTheme.colorScheme.outline,
            elevation = 2.dp
        )
        QCardGridStyle.OUTLINED -> CardStyle(
            backgroundColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onSurface,
            borderColor = MaterialTheme.colorScheme.primary,
            elevation = 0.dp
        )
        QCardGridStyle.ELEVATED -> CardStyle(
            backgroundColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            borderColor = MaterialTheme.colorScheme.outline,
            elevation = 4.dp
        )
        QCardGridStyle.COLORED -> CardStyle(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
            elevation = 2.dp
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(style.elevation, RectangleShape)
            .clip(RectangleShape)
            .background(style.backgroundColor)
            .border(BorderStroke(2.dp, style.borderColor), RectangleShape)
            .let {
                if (onClick != null) {
                    it.clickable(
                        onClick = onClick,
                        role = Role.Button,
                        interactionSource = interactionSource,
                        indication = rememberRipple(color = style.contentColor.copy(alpha = 0.5f))
                    )
                } else {
                    it
                }
            }
            .padding(bottom = if (showTitle || showContent) 8.dp else 0.dp)
    ) {
        // 图片区域
        if (item.imageUrl != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(imageAspectRatio)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        
        // 文本内容区域
        if (showTitle || showContent) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = if (item.imageUrl != null) 8.dp else 0.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 标题
                if (showTitle) {
                    Text(
                        text = item.title,
                        fontFamily = XiangsutiFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = style.contentColor,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = if (showContent && item.content != null) 4.dp else 0.dp)
                    )
                }
                
                // 内容（如果有且需要显示）
                if (showContent && item.content != null) {
                    Text(
                        text = item.content!!,
                        fontFamily = XiangsutiFont,
                        fontSize = 12.sp,
                        color = style.contentColor.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

/**
 * 照片墙中的网格项
 */
@Composable
private fun QPhotoGridItem(
    item: QImageGridItem,
    aspectRatio: Float = 1f,
    showTitle: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .shadow(2.dp, RectangleShape)
            .clip(RectangleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.outline), RectangleShape)
            .let {
                if (onClick != null) {
                    it.clickable(
                        onClick = onClick,
                        role = Role.Button,
                        interactionSource = interactionSource,
                        indication = rememberRipple(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                    )
                } else {
                    it
                }
            },
        contentAlignment = Alignment.Center
    ) {
        // 加载图片
        if (item.imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        // 标题覆盖在图片底部（如果需要显示）
        if (showTitle) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
                    .padding(4.dp)
            ) {
                Text(
                    text = item.title,
                    fontFamily = XiangsutiFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
} 