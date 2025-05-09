package qzwx.app.ui.qchip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * 标签组件形状枚举
 */
enum class QChipShape {
    RECTANGLE,  // 矩形（像素风默认）
    ROUNDED,    // 圆角矩形
    PIXEL       // 像素化边缘
}

/**
 * 标签组件样式变体枚举
 */
enum class QChipVariant {
    PRIMARY,    // 主色调
    SECONDARY,  // 次要色调
    SUCCESS,    // 成功色调
    WARNING,    // 警告色调
    ERROR,      // 错误色调
    INFO,       // 信息色调
    OUTLINE,    // 边框样式
    SURFACE     // 表面样式
}

/**
 * 标签组件大小枚举
 */
enum class QChipSize {
    SMALL,      // 小尺寸
    MEDIUM,     // 中等尺寸
    LARGE       // 大尺寸
}

/**
 * 像素风格标签组件
 * 提供多种样式变体、尺寸和形状
 */
@Composable
fun QChip(
    text: String,
    modifier: Modifier = Modifier,
    variant: QChipVariant = QChipVariant.PRIMARY,
    size: QChipSize = QChipSize.MEDIUM,
    shape: QChipShape = QChipShape.RECTANGLE,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    selected: Boolean = false,
    elevation: Dp = 2.dp,
    borderWidth: Dp = 2.dp
) {
    // 设置尺寸
    val paddingHorizontal = when(size) {
        QChipSize.SMALL -> 8.dp
        QChipSize.MEDIUM -> 12.dp
        QChipSize.LARGE -> 16.dp
    }
    
    val paddingVertical = when(size) {
        QChipSize.SMALL -> 4.dp
        QChipSize.MEDIUM -> 6.dp
        QChipSize.LARGE -> 8.dp
    }
    
    val iconSize = when(size) {
        QChipSize.SMALL -> 12.dp
        QChipSize.MEDIUM -> 16.dp
        QChipSize.LARGE -> 20.dp
    }
    
    val fontSize = when(size) {
        QChipSize.SMALL -> 12.sp
        QChipSize.MEDIUM -> 14.sp
        QChipSize.LARGE -> 16.sp
    }
    
    // 设置形状
    val chipShape = when(shape) {
        QChipShape.RECTANGLE -> RectangleShape
        QChipShape.ROUNDED -> RoundedCornerShape(percent = 50)
        QChipShape.PIXEL -> RectangleShape // 像素边缘使用矩形，后面可以添加特效
    }
    
    // 设置颜色
    val (backgroundColor, contentColor, borderColor) = when(variant) {
        QChipVariant.PRIMARY -> Triple(
            if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        )
        QChipVariant.SECONDARY -> Triple(
            if (selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
            MaterialTheme.colorScheme.onSecondary,
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        )
        QChipVariant.SUCCESS -> Triple(
            if (selected) Color(0xFF388E3C) else Color(0xFF388E3C).copy(alpha = 0.8f),
            Color.White,
            Color(0xFF388E3C).copy(alpha = 0.7f)
        )
        QChipVariant.WARNING -> Triple(
            if (selected) Color(0xFFFFA000) else Color(0xFFFFA000).copy(alpha = 0.8f),
            Color.Black,
            Color(0xFFFFA000).copy(alpha = 0.7f)
        )
        QChipVariant.ERROR -> Triple(
            if (selected) Color(0xFFD32F2F) else Color(0xFFD32F2F).copy(alpha = 0.8f),
            Color.White,
            Color(0xFFD32F2F).copy(alpha = 0.7f)
        )
        QChipVariant.INFO -> Triple(
            if (selected) Color(0xFF1976D2) else Color(0xFF1976D2).copy(alpha = 0.8f),
            Color.White,
            Color(0xFF1976D2).copy(alpha = 0.7f)
        )
        QChipVariant.OUTLINE -> Triple(
            Color.Transparent,
            MaterialTheme.colorScheme.onSurface,
            MaterialTheme.colorScheme.outline
        )
        QChipVariant.SURFACE -> Triple(
            if (selected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.onSurface,
            MaterialTheme.colorScheme.outline
        )
    }
    
    val finalBackgroundColor = if (!enabled) {
        when(variant) {
            QChipVariant.OUTLINE -> Color.Transparent
            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        }
    } else {
        backgroundColor
    }
    
    val finalContentColor = if (!enabled) {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    } else {
        contentColor
    }
    
    val finalBorderColor = if (!enabled) {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
    } else {
        borderColor
    }
    
    val interactionSource = remember { MutableInteractionSource() }
    
    // 标签基础容器
    val baseModifier = modifier
        .shadow(if (enabled) elevation else 0.dp, chipShape)
        .clip(chipShape)
        .background(finalBackgroundColor)
        .border(BorderStroke(borderWidth, finalBorderColor), chipShape)
        .defaultMinSize(minHeight = 32.dp)
        .let {
            if (onClick != null && enabled) {
                it.clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = rememberRipple(color = finalContentColor.copy(alpha = 0.5f))
                )
            } else {
                it
            }
        }
        .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
    
    Row(
        modifier = baseModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 前置图标
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = finalContentColor,
                modifier = Modifier
                    .size(iconSize)
                    .padding(end = 4.dp)
            )
        }
        
        // 文本
        Text(
            text = text,
            color = finalContentColor,
            fontFamily = XiangsutiFont,
            fontWeight = FontWeight.Medium,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
        
        // 后置图标
        if (trailingIcon != null) {
            Icon(
                imageVector = trailingIcon,
                contentDescription = null,
                tint = finalContentColor,
                modifier = Modifier
                    .size(iconSize)
                    .padding(start = 4.dp)
            )
        }
    }
}

/**
 * 像素风格徽章组件
 * 通常用于计数或状态指示
 */
@Composable
fun QBadge(
    count: Int,
    modifier: Modifier = Modifier,
    variant: QChipVariant = QChipVariant.PRIMARY,
    shape: QChipShape = QChipShape.RECTANGLE,
    maxCount: Int = 99,
    icon: ImageVector? = null
) {
    val displayText = when {
        count <= 0 -> ""
        count > maxCount -> "${maxCount}+"
        else -> count.toString()
    }
    
    val chipShape = when(shape) {
        QChipShape.RECTANGLE -> RectangleShape
        QChipShape.ROUNDED -> RoundedCornerShape(percent = 50)
        QChipShape.PIXEL -> RectangleShape // 像素边缘使用矩形，后面可以添加特效
    }
    
    val (backgroundColor, contentColor, borderColor) = when(variant) {
        QChipVariant.PRIMARY -> Triple(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        )
        QChipVariant.SECONDARY -> Triple(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSecondary,
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        )
        QChipVariant.SUCCESS -> Triple(
            Color(0xFF388E3C),
            Color.White,
            Color(0xFF388E3C).copy(alpha = 0.7f)
        )
        QChipVariant.WARNING -> Triple(
            Color(0xFFFFA000),
            Color.Black,
            Color(0xFFFFA000).copy(alpha = 0.7f)
        )
        QChipVariant.ERROR -> Triple(
            Color(0xFFD32F2F),
            Color.White,
            Color(0xFFD32F2F).copy(alpha = 0.7f)
        )
        QChipVariant.INFO -> Triple(
            Color(0xFF1976D2),
            Color.White,
            Color(0xFF1976D2).copy(alpha = 0.7f)
        )
        QChipVariant.OUTLINE -> Triple(
            Color.Transparent,
            MaterialTheme.colorScheme.onSurface,
            MaterialTheme.colorScheme.outline
        )
        QChipVariant.SURFACE -> Triple(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.onSurface,
            MaterialTheme.colorScheme.outline
        )
    }
    
    Box(
        modifier = modifier
            .shadow(2.dp, chipShape)
            .clip(chipShape)
            .background(backgroundColor)
            .border(BorderStroke(2.dp, borderColor), chipShape)
            .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        if (count <= 0 && icon != null) {
            // 只显示图标
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(12.dp)
            )
        } else if (displayText.isNotEmpty()) {
            // 显示文本
            Text(
                text = displayText,
                color = contentColor,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * 方形像素风勋章/标志组件
 * 通常用于游戏成就或特殊标志
 */
@Composable
fun QPixelBadge(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    size: Dp = 32.dp,
    borderWidth: Dp = 2.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .shadow(2.dp, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(size * 0.6f)
        )
    }
} 