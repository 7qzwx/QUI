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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * 卡片网格item数据模型接口
 */
interface QGridItem {
    val id: Any
    val title: String
    val content: String? // 可以为空，某些卡片可能只有标题
}

/**
 * 带图标卡片网格item数据模型接口
 */
interface QImageGridItem : QGridItem {
    val imageUrl: String? // 图片URL，可以为空
}

/**
 * 卡片样式枚举
 */
enum class QCardGridStyle {
    DEFAULT,    // 默认样式卡片
    OUTLINED,   // 轮廓样式卡片
    ELEVATED,   // 高阴影样式卡片
    COLORED     // 彩色卡片
}

/**
 * 像素风格卡片网格组件
 * 为卡片项提供统一样式的网格布局
 */
@Composable
fun QCardGrid(
    items: List<QGridItem>,
    modifier: Modifier = Modifier.fillMaxHeight(),
    columnsMode: QGridColumnsMode = QGridColumnsMode.FIXED,
    columns: Int = 2,
    minColumnWidth: Dp = 150.dp,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    verticalSpacing: Dp = 8.dp,
    horizontalSpacing: Dp = 8.dp,
    title: String? = null,
    itemHeight: Dp = 120.dp,
    cardStyle: QCardGridStyle = QCardGridStyle.DEFAULT,
    onItemClick: ((QGridItem) -> Unit)? = null
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
            QGridCard(
                item = item,
                height = itemHeight,
                cardStyle = cardStyle,
                onClick = if (onItemClick != null) {
                    { onItemClick(item) }
                } else null
            )
        }
    }
}

/**
 * 带分组的像素风格卡片网格组件
 * 将卡片分组显示，每组都有自己的标题
 */
@Composable
fun QGroupedCardGrid(
    groupedItems: Map<String, List<QGridItem>>,
    modifier: Modifier = Modifier.fillMaxHeight(),
    columnsMode: QGridColumnsMode = QGridColumnsMode.FIXED,
    columns: Int = 2,
    minColumnWidth: Dp = 150.dp,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    verticalSpacing: Dp = 8.dp,
    horizontalSpacing: Dp = 8.dp,
    title: String? = null,
    itemHeight: Dp = 120.dp,
    cardStyle: QCardGridStyle = QCardGridStyle.DEFAULT,
    onItemClick: ((QGridItem) -> Unit)? = null
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
        groupedItems.forEach { (groupTitle, items) ->
            // 添加分组标题
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .shadow(2.dp, RectangleShape)
                        .clip(RectangleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)), RectangleShape)
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = groupTitle,
                        fontFamily = XiangsutiFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            // 添加该分组的卡片
            items(items = items) { item ->
                QGridCard(
                    item = item,
                    height = itemHeight,
                    cardStyle = cardStyle,
                    onClick = if (onItemClick != null) {
                        { onItemClick(item) }
                    } else null
                )
            }
            
            // 添加分隔符（如果不是最后一组）
            if (groupTitle != groupedItems.keys.last()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

/**
 * 像素风格固定尺寸卡片网格组件
 * 为卡片项提供等比例尺寸的网格布局
 */
@Composable
fun QSquareCardGrid(
    items: List<QGridItem>,
    modifier: Modifier = Modifier.fillMaxHeight(),
    columnsMode: QGridColumnsMode = QGridColumnsMode.FIXED,
    columns: Int = 3,
    minColumnWidth: Dp = 100.dp,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    verticalSpacing: Dp = 8.dp,
    horizontalSpacing: Dp = 8.dp,
    title: String? = null,
    aspectRatio: Float = 1f,
    cardStyle: QCardGridStyle = QCardGridStyle.DEFAULT,
    onItemClick: ((QGridItem) -> Unit)? = null
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
            QSquareGridCard(
                item = item,
                aspectRatio = aspectRatio,
                cardStyle = cardStyle,
                onClick = if (onItemClick != null) {
                    { onItemClick(item) }
                } else null
            )
        }
    }
}

// 辅助数据类,创建四元组
internal data class CardStyle(
    val backgroundColor: Color,
    val contentColor: Color,
    val borderColor: Color,
    val elevation: Dp
)

/**
 * 网格中的单个卡片项
 */
@Composable
fun QGridCard(
    item: QGridItem,
    height: Dp,
    cardStyle: QCardGridStyle,
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
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 标题
            Text(
                text = item.title,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = style.contentColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            
            // 内容（如果有）
            if (item.content != null) {
                Text(
                    text = item.content!!,
                    fontFamily = XiangsutiFont,
                    fontSize = 14.sp,
                    color = style.contentColor.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * 网格中的等比例卡片项
 */
@Composable
private fun QSquareGridCard(
    item: QGridItem,
    aspectRatio: Float,
    cardStyle: QCardGridStyle,
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
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
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
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 标题
            Text(
                text = item.title,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = style.contentColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = if (item.content != null) 4.dp else 0.dp)
            )
            
            // 内容（如果有且卡片不是太小）
            if (item.content != null) {
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