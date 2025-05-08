package qzwx.app.ui.qinput

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QCheckbox - 复选框组件
 * 像素风格的复选框，使用方块和勾选图标
 */
@Composable
fun QCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    val borderColor = when {
        !enabled -> MaterialTheme.colorScheme.outline.copy(alpha = 0.38f)
        checked -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.outline
    }
    
    val backgroundColor = when {
        !enabled -> if (checked) MaterialTheme.colorScheme.primary.copy(alpha = 0.38f) else Color.Transparent
        checked -> MaterialTheme.colorScheme.primary
        else -> Color.Transparent
    }
    
    val textColor = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    
    Row(
        modifier = modifier
            .clickable(
                onClick = { onCheckedChange(!checked) },
                enabled = enabled,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 像素风格复选框 - 方形带边框
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(BorderStroke(2.dp, borderColor), RectangleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.38f),
                    modifier = Modifier.size(14.dp)
                )
            }
        }
        
        // 文本标签
        if (text != null) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f),
                color = textColor,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = XiangsutiFont,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * QSwitch - 开关组件
 * 像素风格的开关，使用滑块效果
 */
@Composable
fun QSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    val trackColor = when {
        !enabled -> if (checked) MaterialTheme.colorScheme.primary.copy(alpha = 0.38f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.38f)
        checked -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    
    val thumbColor = when {
        !enabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        checked -> MaterialTheme.colorScheme.onPrimary
        else -> MaterialTheme.colorScheme.outline
    }
    
    val trackBorderColor = when {
        !enabled -> MaterialTheme.colorScheme.outline.copy(alpha = 0.38f)
        checked -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.outline
    }
    
    val textColor = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    
    Row(
        modifier = modifier
            .clickable(
                onClick = { onCheckedChange(!checked) },
                enabled = enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 像素风格开关 - 滑动轨道和滑块
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(20.dp)
                .border(BorderStroke(2.dp, trackBorderColor), RectangleShape)
                .background(trackColor),
            contentAlignment = if (checked) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            // 滑块
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(12.dp)
                    .background(thumbColor, RectangleShape)
            )
        }
        
        // 文本标签
        if (text != null) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f),
                color = textColor,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = XiangsutiFont,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * QCheckboxGroup - 复选框组
 * 管理多个相关的复选框
 */
@Composable
fun <T> QCheckboxGroup(
    items: List<T>,
    selectedItems: Set<T>,
    onItemCheckedChange: (T, Boolean) -> Unit,
    itemText: (T) -> String,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    orientation: QCheckboxGroupOrientation = QCheckboxGroupOrientation.VERTICAL
) {
    Column(modifier = modifier) {
        // 标签文本
        if (label != null) {
            Text(
                text = label,
                modifier = Modifier.padding(bottom = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                fontFamily = XiangsutiFont
            )
        }
        
        // 复选框组布局
        when (orientation) {
            QCheckboxGroupOrientation.VERTICAL -> {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items.forEach { item ->
                        QCheckbox(
                            checked = selectedItems.contains(item),
                            onCheckedChange = { isChecked -> onItemCheckedChange(item, isChecked) },
                            enabled = enabled,
                            text = itemText(item)
                        )
                    }
                }
            }
            QCheckboxGroupOrientation.HORIZONTAL -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items.forEach { item ->
                        QCheckbox(
                            checked = selectedItems.contains(item),
                            onCheckedChange = { isChecked -> onItemCheckedChange(item, isChecked) },
                            enabled = enabled,
                            text = itemText(item),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
        
        // 错误信息
        if (isError && errorMessage != null) {
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .size(16.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    fontFamily = XiangsutiFont
                )
            }
        }
    }
}

/**
 * QCheckboxGroupOrientation - 复选框组方向
 */
enum class QCheckboxGroupOrientation {
    VERTICAL,     // 垂直排列
    HORIZONTAL    // 水平排列
} 