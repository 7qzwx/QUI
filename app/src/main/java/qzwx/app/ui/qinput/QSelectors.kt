package qzwx.app.ui.qinput

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.PopupProperties
import qzwx.app.ui.qbutton.QPrimaryButton
import qzwx.app.ui.qbutton.QSecondaryButton
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QDropdown - 下拉选择器
 * 像素风格的下拉选择器组件
 */
@Composable
fun <T> QDropdown(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    itemText: (T) -> String,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String = "请选择",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    maxHeight: Int = 5,
) {
    var expanded by remember { mutableStateOf(false) }
    
    val backgroundColor = if (enabled) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
    val textColor = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    val borderColor = when {
        isError -> MaterialTheme.colorScheme.error
        enabled -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.38f)
    }
    
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
        
        // 选择器主体
        Box(
            modifier = Modifier
                .shadow(2.dp, RectangleShape)
                .clip(RectangleShape)
                .background(backgroundColor, RectangleShape)
                .border(BorderStroke(2.dp, borderColor), RectangleShape)
                .clickable(
                    onClick = { if (enabled) expanded = true },
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                )
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 显示选中的项或占位符
                Text(
                    text = selectedItem?.let { itemText(it) } ?: placeholder,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    color = if (selectedItem != null) textColor else textColor.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = XiangsutiFont,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                // 下拉箭头图标
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = if (expanded) "收起" else "展开",
                    tint = textColor.copy(alpha = 0.6f)
                )
            }
            
            // 下拉菜单
            if (items.isNotEmpty()) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .border(
                            BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                            RectangleShape
                        ),
                    properties = PopupProperties(
                        focusable = true,
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                ) {
                    items.forEach { item ->
                        val isSelected = selectedItem == item
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = itemText(item),
                                    fontFamily = XiangsutiFont,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            },
                            onClick = {
                                onItemSelected(item)
                                expanded = false
                            },
                            modifier = Modifier.background(
                                if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                                else Color.Transparent
                            )
                        )
                        if (item != items.last()) {
                            Divider(
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
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
 * QRadioGroup - 单选按钮组
 * 像素风格的单选按钮组组件
 */
@Composable
fun <T> QRadioGroup(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    itemText: (T) -> String,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    orientation: QRadioGroupOrientation = QRadioGroupOrientation.VERTICAL
) {
    val textColor = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    
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
        
        // 单选按钮组
        when (orientation) {
            QRadioGroupOrientation.VERTICAL -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    items.forEachIndexed { index, item ->
                        QRadioItem(
                            text = itemText(item),
                            selected = selectedItem == item,
                            onSelect = { onItemSelected(item) },
                            enabled = enabled
                        )
                        
                        if (index < items.size - 1) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
            QRadioGroupOrientation.HORIZONTAL -> {
                Row(modifier = Modifier.fillMaxWidth()) {
                    items.forEachIndexed { index, item ->
                        QRadioItem(
                            text = itemText(item),
                            selected = selectedItem == item,
                            onSelect = { onItemSelected(item) },
                            enabled = enabled,
                            modifier = Modifier.weight(1f)
                        )
                        
                        if (index < items.size - 1) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
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
 * QRadioItem - 单选按钮项
 * 像素风格的单个单选按钮组件
 */
@Composable
private fun QRadioItem(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val textColor = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onSelect,
                enabled = enabled,
                role = Role.RadioButton
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 自定义像素风格单选按钮
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(
                    BorderStroke(2.dp, 
                        if (enabled) MaterialTheme.colorScheme.outline 
                        else MaterialTheme.colorScheme.outline.copy(alpha = 0.38f)
                    ),
                    RectangleShape
                )
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            if (enabled) MaterialTheme.colorScheme.primary 
                            else MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
                            RectangleShape
                        )
                )
            }
        }
        
        // 文本
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f),
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = XiangsutiFont
        )
    }
}

/**
 * QRadioGroupOrientation - 单选按钮组方向
 */
enum class QRadioGroupOrientation {
    VERTICAL,     // 垂直排列
    HORIZONTAL    // 水平排列
} 