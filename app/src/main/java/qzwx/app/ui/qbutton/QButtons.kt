package qzwx.app.ui.qbutton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QPrimaryButton（主按钮） - 用于页面中最重要、最突出的操作
 * 相当于Material Design中的Button
 */
@Composable
fun QPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    // 像素风格通常有一个明显的边框和立体感
    val buttonColor = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val contentColor = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    val borderColor = if (enabled) MaterialTheme.colorScheme.primary.copy(alpha = 0.7f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
    
    // 像素风格常用矩形形状
    val shape = RectangleShape
    
    Box(
        modifier = modifier
            .shadow(4.dp, shape)
            .clip(shape)
            .background(buttonColor)
            .border(BorderStroke(2.dp, borderColor), shape)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = contentColor.copy(alpha = 0.5f))
            )
            .defaultMinSize(minWidth = 64.dp, minHeight = 48.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
        }
        
        Text(
            text = text,
            color = contentColor,
            fontFamily = XiangsutiFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * QSecondaryButton（次要按钮） - 用于次要操作或替代操作
 * 相当于Material Design中的FilledTonalButton、OutlinedButton或ElevatedButton
 */
@Composable
fun QSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: QButtonVariant = QButtonVariant.NORMAL,
    leadingIcon: ImageVector? = null
) {
    val buttonColor = when (variant) {
        QButtonVariant.NORMAL -> if (enabled) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant
        QButtonVariant.ACCENT -> if (enabled) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant
        QButtonVariant.SURFACE -> if (enabled) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant
    }
    
    val contentColor = when (variant) {
        QButtonVariant.NORMAL -> if (enabled) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        QButtonVariant.ACCENT -> if (enabled) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        QButtonVariant.SURFACE -> if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    }
    
    val borderColor = when (variant) {
        QButtonVariant.NORMAL -> if (enabled) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
        QButtonVariant.ACCENT -> if (enabled) MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
        QButtonVariant.SURFACE -> if (enabled) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
    }
    
    // 像素风格常用矩形形状
    val shape = RectangleShape
    
    Box(
        modifier = modifier
            .shadow(2.dp, shape)
            .clip(shape)
            .background(buttonColor)
            .border(BorderStroke(2.dp, borderColor), shape)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = contentColor.copy(alpha = 0.5f))
            )
            .defaultMinSize(minWidth = 64.dp, minHeight = 40.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier
                    .size(18.dp)
                    .padding(end = 8.dp)
            )
        }
        
        Text(
            text = text,
            color = contentColor,
            fontFamily = XiangsutiFont,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            letterSpacing = 0.5.sp,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * QIconButton（图标按钮） - 只有图标的按钮，用于工具栏、紧凑布局等场景
 * 相当于Material Design中的IconButton
 */
@Composable
fun QIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.primary
) {
    val backgroundColor = if (enabled) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant
    val iconColor = if (enabled) tint else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    val borderColor = if (enabled) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
    
    Box(
        modifier = modifier
            .shadow(2.dp, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(1.dp, borderColor), RectangleShape)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, color = iconColor.copy(alpha = 0.5f))
            )
            .defaultMinSize(minWidth = 40.dp, minHeight = 40.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
    }
}

/**
 * QButtonVariant（按钮变体） - 定义次要按钮的不同样式
 */
enum class QButtonVariant {
    NORMAL,   // 普通次要按钮
    ACCENT,   // 强调色次要按钮
    SURFACE   // 表面色按钮
} 