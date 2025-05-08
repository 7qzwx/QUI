package qzwx.app.ui.qdialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.qbutton.QButtonVariant
import qzwx.app.ui.qbutton.QPrimaryButton
import qzwx.app.ui.qbutton.QSecondaryButton
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

@Preview(showBackground = true)
@Composable
fun QDialogPreviewLight() {
    AppTheme(darkTheme = false) {
        QDialogPreviewContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1A1621)
@Composable
fun QDialogPreviewDark() {
    AppTheme(darkTheme = true) {
        QDialogPreviewContent()
    }
}

@Composable
internal fun QDialogPreviewContent() {
    var showBasicDialog by remember { mutableStateOf(false) }
    var showSurfaceDialog by remember { mutableStateOf(false) }
    var showPrimaryDialog by remember { mutableStateOf(false) }
    var showSecondaryDialog by remember { mutableStateOf(false) }
    
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showConfirmPrimaryDialog by remember { mutableStateOf(false) }
    var showConfirmSecondaryDialog by remember { mutableStateOf(false) }
    
    var showInputDialog by remember { mutableStateOf(false) }
    var showInputPrimaryDialog by remember { mutableStateOf(false) }
    var showInputSecondaryDialog by remember { mutableStateOf(false) }
    
    // 基础对话框示例
    if (showBasicDialog) {
        QDialog(
            visible = true,
            onDismiss = { showBasicDialog = false },
            title = "基础对话框"
        ) {
            Text(
                text = "这是一个基础对话框示例，可以自定义内容区域。",
                fontFamily = XiangsutiFont,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                QPrimaryButton(
                    text = "关闭",
                    onClick = { showBasicDialog = false }
                )
            }
        }
    }
    
    // Surface样式对话框
    if (showSurfaceDialog) {
        QDialog(
            visible = true,
            onDismiss = { showSurfaceDialog = false },
            title = "Surface样式",
            variant = QDialogVariant.SURFACE
        ) {
            Text(
                text = "这是使用Surface样式的对话框示例。",
                fontFamily = XiangsutiFont,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                QPrimaryButton(
                    text = "关闭",
                    onClick = { showSurfaceDialog = false }
                )
            }
        }
    }
    
    // Primary样式对话框
    if (showPrimaryDialog) {
        QDialog(
            visible = true,
            onDismiss = { showPrimaryDialog = false },
            title = "Primary样式",
            variant = QDialogVariant.PRIMARY
        ) {
            Text(
                text = "这是使用Primary样式的对话框示例。",
                fontFamily = XiangsutiFont,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                QPrimaryButton(
                    text = "关闭",
                    onClick = { showPrimaryDialog = false }
                )
            }
        }
    }
    
    // Secondary样式对话框
    if (showSecondaryDialog) {
        QDialog(
            visible = true,
            onDismiss = { showSecondaryDialog = false },
            title = "Secondary样式",
            variant = QDialogVariant.SECONDARY
        ) {
            Text(
                text = "这是使用Secondary样式的对话框示例。",
                fontFamily = XiangsutiFont,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                QPrimaryButton(
                    text = "关闭",
                    onClick = { showSecondaryDialog = false }
                )
            }
        }
    }
    
    // 确认对话框示例
    if (showConfirmDialog) {
        QConfirmDialog(
            visible = true,
            title = "确认操作",
            message = "您确定要执行此操作吗？此操作无法撤销。",
            onConfirm = { showConfirmDialog = false },
            onDismiss = { showConfirmDialog = false }
        )
    }
    
    // Primary样式确认对话框
    if (showConfirmPrimaryDialog) {
        QConfirmDialog(
            visible = true,
            title = "确认操作",
            message = "您确定要执行此操作吗？此操作无法撤销。",
            variant = QDialogVariant.PRIMARY,
            onConfirm = { showConfirmPrimaryDialog = false },
            onDismiss = { showConfirmPrimaryDialog = false }
        )
    }
    
    // Secondary样式确认对话框
    if (showConfirmSecondaryDialog) {
        QConfirmDialog(
            visible = true,
            title = "确认操作",
            message = "您确定要执行此操作吗？此操作无法撤销。",
            variant = QDialogVariant.SECONDARY,
            onConfirm = { showConfirmSecondaryDialog = false },
            onDismiss = { showConfirmSecondaryDialog = false }
        )
    }
    
    // 输入对话框示例
    if (showInputDialog) {
        QInputDialog(
            visible = true,
            title = "请输入",
            message = "请输入您的用户名：",
            placeholder = "用户名",
            onConfirm = { showInputDialog = false },
            onDismiss = { showInputDialog = false }
        )
    }
    
    // Primary样式输入对话框
    if (showInputPrimaryDialog) {
        QInputDialog(
            visible = true,
            title = "请输入",
            message = "请输入您的用户名：",
            placeholder = "用户名",
            variant = QDialogVariant.PRIMARY,
            onConfirm = { showInputPrimaryDialog = false },
            onDismiss = { showInputPrimaryDialog = false }
        )
    }
    
    // Secondary样式输入对话框
    if (showInputSecondaryDialog) {
        QInputDialog(
            visible = true,
            title = "请输入",
            message = "请输入您的用户名：",
            placeholder = "用户名",
            variant = QDialogVariant.SECONDARY,
            onConfirm = { showInputSecondaryDialog = false },
            onDismiss = { showInputSecondaryDialog = false }
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "对话框预览",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = XiangsutiFont,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "基础对话框",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = XiangsutiFont
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QPrimaryButton(
                text = "默认样式",
                onClick = { showBasicDialog = true },
                modifier = Modifier.weight(1f)
            )
            
            QSecondaryButton(
                text = "Surface",
                onClick = { showSurfaceDialog = true },
                variant = QButtonVariant.SURFACE,
                modifier = Modifier.weight(1f)
            )
            
            QPrimaryButton(
                text = "Primary",
                onClick = { showPrimaryDialog = true },
                modifier = Modifier.weight(1f)
            )
            
            QSecondaryButton(
                text = "Secondary",
                onClick = { showSecondaryDialog = true },
                variant = QButtonVariant.ACCENT,
                modifier = Modifier.weight(1f)
            )
        }
        
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        
        Text(
            text = "确认对话框",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = XiangsutiFont
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QPrimaryButton(
                text = "默认样式",
                onClick = { showConfirmDialog = true },
                modifier = Modifier.weight(1f)
            )
            
            QPrimaryButton(
                text = "Primary",
                onClick = { showConfirmPrimaryDialog = true },
                modifier = Modifier.weight(1f)
            )
            
            QSecondaryButton(
                text = "Secondary",
                onClick = { showConfirmSecondaryDialog = true },
                variant = QButtonVariant.ACCENT,
                modifier = Modifier.weight(1f)
            )
        }
        
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        
        Text(
            text = "输入对话框",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = XiangsutiFont
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QPrimaryButton(
                text = "默认样式",
                onClick = { showInputDialog = true },
                modifier = Modifier.weight(1f)
            )
            
            QPrimaryButton(
                text = "Primary",
                onClick = { showInputPrimaryDialog = true },
                modifier = Modifier.weight(1f)
            )
            
            QSecondaryButton(
                text = "Secondary",
                onClick = { showInputSecondaryDialog = true },
                variant = QButtonVariant.ACCENT,
                modifier = Modifier.weight(1f)
            )
        }
    }
} 