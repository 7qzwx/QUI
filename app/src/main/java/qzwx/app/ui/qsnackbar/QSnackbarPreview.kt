package qzwx.app.ui.qsnackbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import qzwx.app.ui.qbutton.QPrimaryButton
import qzwx.app.ui.qbutton.QSecondaryButton
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

@Preview(showBackground = true)
@Composable
fun QSnackbarPreviewLight() {
    AppTheme(darkTheme = false) {
        QSnackbarPreviewContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1A1621)
@Composable
fun QSnackbarPreviewDark() {
    AppTheme(darkTheme = true) {
        QSnackbarPreviewContent()
    }
}

@Composable
internal fun QSnackbarPreviewContent() {
    // 创建Snackbar状态管理器
    val hostState = remember { QSnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    // Toast状态与消息
    var toastData by remember { mutableStateOf<ToastData?>(null) }
    
    // 跟踪Toast显示的Job，用于取消先前的显示
    var currentToastJob by remember { mutableStateOf<Job?>(null) }
    
    // 显示Toast
    toastData?.let { data ->
        showQToast(
            message = data.message,
            type = data.type,
            position = data.position,
            durationMillis = data.duration
        )
    }
    
    // 用于安全显示Toast的函数
    fun showToast(data: ToastData) {
        // 取消之前的任务
        currentToastJob?.cancel()
        
        // 创建新任务
        currentToastJob = scope.launch {
            toastData = data
            delay(data.duration + 500) // 延迟比显示时间多一点，确保动画完成
            toastData = null
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "弹出提示预览",
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = XiangsutiFont,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Snackbar 组件展示区域
            Text(
                text = "Snackbar 组件",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = XiangsutiFont
            )
            
            // 独立Snackbar展示
            Text(
                text = "静态展示",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = XiangsutiFont
            )
            
            QSnackbar(
                message = "这是一个信息Snackbar",
                type = QSnackbarType.INFO,
                onDismiss = {}
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            QSnackbar(
                message = "操作成功完成",
                type = QSnackbarType.SUCCESS,
                onDismiss = {},
                action = QSnackbarAction(
                    icon = Icons.Default.ThumbUp,
                    onClick = {},
                    contentDescription = "点赞"
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            QSnackbar(
                message = "警告：这将删除所有数据，操作无法撤销",
                type = QSnackbarType.WARNING,
                onDismiss = {},
                action = QSnackbarAction(
                    icon = Icons.Default.Refresh,
                    onClick = {},
                    contentDescription = "重试"
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            QSnackbar(
                message = "错误：网络连接失败，请检查网络设置",
                type = QSnackbarType.ERROR,
                onDismiss = {},
                action = QSnackbarAction(
                    icon = Icons.Default.Refresh,
                    onClick = {},
                    contentDescription = "重试"
                )
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // 动态显示Snackbar
            Text(
                text = "动态显示Snackbar",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = XiangsutiFont
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QPrimaryButton(
                    text = "信息",
                    onClick = {
                        scope.launch {
                            hostState.showSnackbar(
                                message = "这是一个信息Snackbar",
                                type = QSnackbarType.INFO
                            )
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                
                QPrimaryButton(
                    text = "成功",
                    onClick = {
                        scope.launch {
                            hostState.showSnackbar(
                                message = "操作成功完成",
                                type = QSnackbarType.SUCCESS,
                                action = QSnackbarAction(
                                    icon = Icons.Default.Share,
                                    onClick = { /* 分享操作 */ }
                                )
                            )
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                
                QSecondaryButton(
                    text = "警告",
                    onClick = {
                        scope.launch {
                            hostState.showSnackbar(
                                message = "警告：这是一个警告提示",
                                type = QSnackbarType.WARNING,
                                position = QSnackbarPosition.TOP
                            )
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                
                QSecondaryButton(
                    text = "错误",
                    onClick = {
                        scope.launch {
                            hostState.showSnackbar(
                                message = "错误：操作失败，请重试",
                                type = QSnackbarType.ERROR,
                                duration = 0, // 设为0表示不自动关闭
                                action = QSnackbarAction(
                                    icon = Icons.Default.Refresh,
                                    onClick = { /* 重试操作 */ }
                                )
                            )
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // Toast 组件展示区域
            Text(
                text = "Toast 组件",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = XiangsutiFont
            )
            
            // 静态Toast展示
            Text(
                text = "静态展示",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = XiangsutiFont
            )
            
            QToast(
                message = "这是一个信息Toast",
                type = QSnackbarType.INFO
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            QToast(
                message = "操作成功完成",
                type = QSnackbarType.SUCCESS
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            QToast(
                message = "警告：这是一个警告提示",
                type = QSnackbarType.WARNING
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            QToast(
                message = "错误：操作失败",
                type = QSnackbarType.ERROR
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // 动态显示Toast
            Text(
                text = "动态显示Toast",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = XiangsutiFont
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QPrimaryButton(
                    text = "信息",
                    onClick = { 
                        showToast(
                            ToastData(
                                message = "这是一条信息提示",
                                type = QSnackbarType.INFO
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                )
                
                QPrimaryButton(
                    text = "成功",
                    onClick = { 
                        showToast(
                            ToastData(
                                message = "操作成功完成",
                                type = QSnackbarType.SUCCESS
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                )
                
                QSecondaryButton(
                    text = "警告",
                    onClick = { 
                        showToast(
                            ToastData(
                                message = "警告：这是一个警告提示",
                                type = QSnackbarType.WARNING,
                                position = QSnackbarPosition.TOP
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                )
                
                QSecondaryButton(
                    text = "错误",
                    onClick = { 
                        showToast(
                            ToastData(
                                message = "错误：操作失败",
                                type = QSnackbarType.ERROR,
                                duration = 3000
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        
        // Snackbar宿主
        QSnackbarHost(hostState = hostState)
    }
}

// 用于保存Toast显示数据的类
private data class ToastData(
    val message: String,
    val type: QSnackbarType,
    val position: QSnackbarPosition = QSnackbarPosition.BOTTOM,
    val duration: Long = 2000
) 