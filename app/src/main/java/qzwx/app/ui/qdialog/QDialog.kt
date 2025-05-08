package qzwx.app.ui.qdialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import qzwx.app.ui.qbutton.QButtonVariant
import qzwx.app.ui.qbutton.QPrimaryButton
import qzwx.app.ui.qbutton.QSecondaryButton
import qzwx.app.ui.qinput.QTextInput
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QDialog - 像素风格基础对话框组件
 */
@Composable
fun QDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    title: String,
    variant: QDialogVariant = QDialogVariant.SURFACE,
    dismissOnClickOutside: Boolean = true,
    dismissOnBackPress: Boolean = true,
    elevation: Dp = 8.dp,
    borderWidth: Dp = 2.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            val backgroundColor = when(variant) {
                QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.primaryContainer
                QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.secondaryContainer
                QDialogVariant.SURFACE -> MaterialTheme.colorScheme.surface
            }
            
            val contentColor = when(variant) {
                QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimaryContainer
                QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondaryContainer
                QDialogVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
            }
            
            val headerBackgroundColor = when(variant) {
                QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.primary
                QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
                QDialogVariant.SURFACE -> MaterialTheme.colorScheme.surfaceVariant
            }
            
            val headerContentColor = when(variant) {
                QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
                QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
                QDialogVariant.SURFACE -> MaterialTheme.colorScheme.onSurfaceVariant
            }
            
            val borderColor = when(variant) {
                QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                QDialogVariant.SURFACE -> MaterialTheme.colorScheme.outline
            }
            
            Box(
                modifier = Modifier
                    .shadow(elevation, RectangleShape)
                    .clip(RectangleShape)
                    .background(backgroundColor)
                    .border(BorderStroke(borderWidth, borderColor), RectangleShape)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 标题区域
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(headerBackgroundColor)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = headerContentColor,
                            fontFamily = XiangsutiFont,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                    
                    // 内容区域
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        content = content
                    )
                }
            }
        }
    }
}

/**
 * QConfirmDialog - 像素风格确认对话框
 * 提供标题、消息、确认和取消按钮
 */
@Composable
fun QConfirmDialog(
    visible: Boolean,
    title: String,
    message: String,
    confirmText: String = "确认",
    cancelText: String = "取消",
    variant: QDialogVariant = QDialogVariant.SURFACE,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    QDialog(
        visible = visible,
        onDismiss = onDismiss,
        title = title,
        variant = variant
    ) {
        Text(
            text = message,
            fontFamily = XiangsutiFont,
            style = MaterialTheme.typography.bodyLarge,
            color = when(variant) {
                QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimaryContainer
                QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondaryContainer
                QDialogVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
        
        // 统一按钮样式的布局
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 确保所有按钮都有相同的大小和高度
            Box(modifier = Modifier.weight(1f)) {
                QSecondaryButton(
                    text = cancelText,
                    onClick = onDismiss,
                    variant = QButtonVariant.NORMAL,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                )
            }
            
            Box(modifier = Modifier.weight(1f)) {
                QPrimaryButton(
                    text = confirmText,
                    onClick = onConfirm,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                )
            }
        }
    }
}

/**
 * QInputDialog - 像素风格输入对话框
 * 提供标题、消息、输入框、确认和取消按钮
 */
@Composable
fun QInputDialog(
    visible: Boolean,
    title: String,
    message: String? = null,
    placeholder: String = "",
    initialValue: String = "",
    confirmText: String = "确认",
    cancelText: String = "取消",
    variant: QDialogVariant = QDialogVariant.SURFACE,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var inputValue by remember { mutableStateOf(initialValue) }
    
    QDialog(
        visible = visible,
        onDismiss = onDismiss,
        title = title,
        variant = variant
    ) {
        // 显示可选消息
        if (message != null) {
            Text(
                text = message,
                fontFamily = XiangsutiFont,
                style = MaterialTheme.typography.bodyLarge,
                color = when(variant) {
                    QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimaryContainer
                    QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondaryContainer
                    QDialogVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
        
        // 美化输入框样式
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .shadow(4.dp, RectangleShape)
                .clip(RectangleShape)
                .background(
                    when(variant) {
                        QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                        QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f)
                        QDialogVariant.SURFACE -> MaterialTheme.colorScheme.surface
                    }
                )
                .border(
                    BorderStroke(
                        2.dp,
                        when(variant) {
                            QDialogVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                            QDialogVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                            QDialogVariant.SURFACE -> MaterialTheme.colorScheme.outline
                        }
                    ),
                    RectangleShape
                )
                .padding(2.dp)
        ) {
            QTextInput(
                value = inputValue,
                onValueChange = { inputValue = it },
                placeholder = placeholder,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
        
        // 统一按钮样式的布局
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 确保所有按钮都有相同的大小和高度
            Box(modifier = Modifier.weight(1f)) {
                QSecondaryButton(
                    text = cancelText,
                    onClick = onDismiss,
                    variant = QButtonVariant.NORMAL,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                )
            }
            
            Box(modifier = Modifier.weight(1f)) {
                QPrimaryButton(
                    text = confirmText,
                    onClick = { onConfirm(inputValue) },
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                )
            }
        }
    }
}

/**
 * QDialogVariant - 对话框样式变体
 */
enum class QDialogVariant {
    PRIMARY,   // 主色调样式
    SECONDARY, // 次要色调样式
    SURFACE    // 表面颜色样式
} 