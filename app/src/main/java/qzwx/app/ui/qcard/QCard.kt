package qzwx.app.ui.qcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QCard - 像素风格卡片组件
 * 提供标准卡片布局，包含可选的标题
 */
@Composable
fun QCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    variant: QCardVariant = QCardVariant.SURFACE,
    elevation: Dp = 4.dp,
    borderWidth: Dp = 2.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val backgroundColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    Box(
        modifier = modifier
            .shadow(elevation, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (title != null) {
                Text(
                    text = title,
                    color = contentColor,
                    fontFamily = XiangsutiFont,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            content()
        }
    }
}

/**
 * QOutlinedCard - 像素风格轮廓卡片组件
 * 提供仅有边框的卡片布局，背景为透明色
 */
@Composable
fun QOutlinedCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    variant: QCardVariant = QCardVariant.SURFACE,
    borderWidth: Dp = 2.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val contentColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    val backgroundColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
    
    Box(
        modifier = modifier
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (title != null) {
                Text(
                    text = title,
                    color = contentColor,
                    fontFamily = XiangsutiFont,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            content()
        }
    }
}

/**
 * QHeaderCard - 像素风格带头部区域的卡片组件
 * 提供卡片上部有特殊背景色的布局
 */
@Composable
fun QHeaderCard(
    modifier: Modifier = Modifier,
    headerContent: @Composable BoxScope.() -> Unit,
    variant: QCardVariant = QCardVariant.SURFACE,
    elevation: Dp = 4.dp,
    borderWidth: Dp = 2.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val backgroundColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.primaryContainer
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.secondaryContainer
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val headerBackgroundColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.surfaceVariant
    }
    
    val contentColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimaryContainer
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondaryContainer
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val headerContentColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    
    val borderColor = when(variant) {
        QCardVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QCardVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QCardVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    Box(
        modifier = modifier
            .shadow(elevation, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // 头部区域
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(headerBackgroundColor)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                headerContent()
            }
            
            // 内容区域
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                content()
            }
        }
    }
}

/**
 * QCardVariant - 卡片样式变体
 */
enum class QCardVariant {
    PRIMARY,   // 主色调样式
    SECONDARY, // 次要色调样式
    SURFACE    // 表面颜色样式
} 