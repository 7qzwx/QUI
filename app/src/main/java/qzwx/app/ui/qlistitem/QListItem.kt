package qzwx.app.ui.qlistitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QListItem - 像素风格列表项组件
 * 提供标准列表项布局，包含标题、描述文本和可选的图标
 */
@Composable
fun QListItem(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    leadingIconTint: Color = MaterialTheme.colorScheme.primary,
    trailingIconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onClick: (() -> Unit)? = null,
    variant: QListItemVariant = QListItemVariant.SURFACE,
    showDivider: Boolean = true
) {
    val backgroundColor = when(variant) {
        QListItemVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QListItemVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QListItemVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QListItemVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QListItemVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QListItemVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val interactionSource = remember { MutableInteractionSource() }
    
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null, // 移除波纹效果，保持像素风格
            onClick = onClick
        )
    } else {
        Modifier
    }
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RectangleShape)
            .background(backgroundColor)
            .then(clickableModifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 前置图标
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = leadingIconTint,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            
            // 文本内容
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = contentColor,
                    fontFamily = XiangsutiFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                if (subtitle != null) {
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Text(
                        text = subtitle,
                        color = contentColor.copy(alpha = 0.7f),
                        fontFamily = XiangsutiFont,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            
            // 尾部图标
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = trailingIconTint,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        
        // 分隔线
        if (showDivider) {
            Divider(
                color = contentColor.copy(alpha = 0.1f),
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

/**
 * QBorderedListItem - 带边框的像素风格列表项组件
 * 提供带有明显边框的列表项，更加强调像素风格
 */
@Composable
fun QBorderedListItem(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    leadingIconTint: Color = MaterialTheme.colorScheme.primary,
    trailingIconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onClick: (() -> Unit)? = null,
    variant: QListItemVariant = QListItemVariant.SURFACE,
    borderWidth: Float = 2f
) {
    val backgroundColor = when(variant) {
        QListItemVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QListItemVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QListItemVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QListItemVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QListItemVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QListItemVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QListItemVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QListItemVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QListItemVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    val interactionSource = remember { MutableInteractionSource() }
    
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null, // 移除波纹效果，保持像素风格
            onClick = onClick
        )
    } else {
        Modifier
    }
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth.dp, borderColor), RectangleShape)
            .then(clickableModifier)
            .padding(1.dp) // 防止边框被裁剪
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 前置图标
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = leadingIconTint,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            
            // 文本内容
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = contentColor,
                    fontFamily = XiangsutiFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                if (subtitle != null) {
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Text(
                        text = subtitle,
                        color = contentColor.copy(alpha = 0.7f),
                        fontFamily = XiangsutiFont,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            
            // 尾部图标
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = trailingIconTint,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

/**
 * QListItemVariant - 列表项样式变体
 */
enum class QListItemVariant {
    PRIMARY,   // 主色调样式
    SECONDARY, // 次要色调样式
    SURFACE    // 表面颜色样式
} 