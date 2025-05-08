package qzwx.app.ui.qsnackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.delay
import qzwx.app.ui.qbutton.QIconButton
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QSnackbarType - 定义Snackbar的类型
 */
enum class QSnackbarType {
    INFO,      // 信息提示
    SUCCESS,   // 成功提示
    WARNING,   // 警告提示
    ERROR      // 错误提示
}

/**
 * QSnackbarPosition - 定义Snackbar显示位置
 */
enum class QSnackbarPosition {
    TOP,       // 顶部显示
    BOTTOM     // 底部显示（默认）
}

/**
 * QSnackbarHost - Snackbar宿主容器
 * 用于在布局中放置Snackbar
 */
@Composable
fun QSnackbarHost(
    hostState: QSnackbarHostState,
    modifier: Modifier = Modifier
) {
    val currentSnackbarData = hostState.currentSnackbarData
    val position = hostState.position
    
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = when (position) {
            QSnackbarPosition.TOP -> Alignment.TopCenter
            QSnackbarPosition.BOTTOM -> Alignment.BottomCenter
        }
    ) {
        currentSnackbarData?.let { snackbarData ->
            key(snackbarData.id) {
                val animation = remember { MutableTransitionState(false) }
                animation.targetState = true

                LaunchedEffect(snackbarData) {
                    if (snackbarData.duration > 0) {
                        delay(snackbarData.duration)
                        hostState.dismissCurrentSnackbar()
                    }
                }

                // 设置动画
                val density = LocalDensity.current
                val slideInFrom = with(density) { 
                    when (position) {
                        QSnackbarPosition.TOP -> (-100).dp.roundToPx()
                        QSnackbarPosition.BOTTOM -> 100.dp.roundToPx()
                    }
                }
                val slideOutTo = slideInFrom
                
                AnimatedVisibility(
                    visibleState = animation,
                    enter = slideInVertically(
                        initialOffsetY = { slideInFrom },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300)),
                    exit = slideOutVertically(
                        targetOffsetY = { slideOutTo },
                        animationSpec = tween(200)
                    ) + fadeOut(animationSpec = tween(200))
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .widthIn(max = 600.dp) // 最大宽度限制
                    ) {
                        QSnackbar(
                            message = snackbarData.message,
                            type = snackbarData.type,
                            showCloseIcon = snackbarData.duration <= 0,
                            elevation = 6.dp,
                            action = snackbarData.action,
                            onDismiss = {
                                hostState.dismissCurrentSnackbar()
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * QSnackbar - 像素风格Snackbar组件
 */
@Composable
fun QSnackbar(
    message: String,
    modifier: Modifier = Modifier,
    type: QSnackbarType = QSnackbarType.INFO,
    elevation: Dp = 4.dp,
    action: QSnackbarAction? = null,
    showCloseIcon: Boolean = true,
    borderWidth: Dp = 2.dp,
    onDismiss: (() -> Unit)? = null
) {
    // 根据类型确定颜色
    val (backgroundColor, textColor, borderColor, icon, iconTint) = when (type) {
        QSnackbarType.INFO -> {
            val bgColor = MaterialTheme.colorScheme.surface
            val txtColor = MaterialTheme.colorScheme.onSurface
            val bdrColor = MaterialTheme.colorScheme.outline
            val iconColor = MaterialTheme.colorScheme.onBackground
            Quintuple(bgColor, txtColor, bdrColor, Icons.Default.Info, iconColor)
        }
        QSnackbarType.SUCCESS -> {
            val bgColor = MaterialTheme.colorScheme.primaryContainer
            val txtColor = MaterialTheme.colorScheme.onPrimaryContainer
            val bdrColor = MaterialTheme.colorScheme.primary
            val iconColor = MaterialTheme.colorScheme.onBackground
            Quintuple(bgColor, txtColor, bdrColor, Icons.Default.Check, iconColor)
        }
        QSnackbarType.WARNING -> {
            val bgColor = MaterialTheme.colorScheme.secondaryContainer
            val txtColor = MaterialTheme.colorScheme.onSecondaryContainer
            val bdrColor = MaterialTheme.colorScheme.secondary
            val iconColor = MaterialTheme.colorScheme.onBackground
            Quintuple(bgColor, txtColor, bdrColor, Icons.Default.Warning, iconColor)
        }
        QSnackbarType.ERROR -> {
            val bgColor = MaterialTheme.colorScheme.errorContainer
            val txtColor = MaterialTheme.colorScheme.onErrorContainer
            val bdrColor = MaterialTheme.colorScheme.error
            val iconColor = MaterialTheme.colorScheme.onBackground
            Quintuple(bgColor, txtColor, bdrColor, Icons.Default.Error, iconColor)
        }
    }
    
    Box(
        modifier = modifier
            .shadow(elevation, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor, RectangleShape)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 状态图标
            Icon(
                imageVector = icon,
                contentDescription = type.name.lowercase(),
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            
            // 消息文本
            Text(
                text = message,
                color = textColor,
                fontFamily = XiangsutiFont,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            
            // 操作按钮
            if (action != null) {
                Spacer(modifier = Modifier.width(8.dp))
                QIconButton(
                    icon = action.icon,
                    onClick = action.onClick,
                    tint = iconTint,
                    contentDescription = action.contentDescription
                )
            }
            
            // 关闭按钮
            if (showCloseIcon && onDismiss != null) {
                Spacer(modifier = Modifier.width(4.dp))
                QIconButton(
                    icon = Icons.Default.Close,
                    onClick = onDismiss,
                    tint = iconTint,
                    contentDescription = "关闭"
                )
            }
        }
    }
}

/**
 * QToast - 像素风格Toast组件
 * 更小、更简洁的提示，没有交互按钮
 */
@Composable
fun QToast(
    message: String,
    modifier: Modifier = Modifier,
    type: QSnackbarType = QSnackbarType.INFO,
    elevation: Dp = 4.dp,
    borderWidth: Dp = 2.dp
) {
    // 根据类型确定颜色
    val (backgroundColor, textColor, borderColor, icon, iconTint) = when (type) {
        QSnackbarType.INFO -> {
            val bgColor = MaterialTheme.colorScheme.surface
            val txtColor = MaterialTheme.colorScheme.onSurface
            val bdrColor = MaterialTheme.colorScheme.outline
            val iconColor = MaterialTheme.colorScheme.onBackground
            Quintuple(bgColor, txtColor, bdrColor, Icons.Default.Info, iconColor)
        }
        QSnackbarType.SUCCESS -> {
            val bgColor = MaterialTheme.colorScheme.primaryContainer
            val txtColor = MaterialTheme.colorScheme.onPrimaryContainer
            val bdrColor = MaterialTheme.colorScheme.primary
            val iconColor = MaterialTheme.colorScheme.onBackground
            Quintuple(bgColor, txtColor, bdrColor, Icons.Default.Check, iconColor)
        }
        QSnackbarType.WARNING -> {
            val bgColor = MaterialTheme.colorScheme.secondaryContainer
            val txtColor = MaterialTheme.colorScheme.onSecondaryContainer
            val bdrColor = MaterialTheme.colorScheme.secondary
            val iconColor = MaterialTheme.colorScheme.onBackground
            Quintuple(bgColor, txtColor, bdrColor, Icons.Default.Warning, iconColor)
        }
        QSnackbarType.ERROR -> {
            val bgColor = MaterialTheme.colorScheme.errorContainer
            val txtColor = MaterialTheme.colorScheme.onErrorContainer
            val bdrColor = MaterialTheme.colorScheme.error
            val iconColor = MaterialTheme.colorScheme.onBackground
            Quintuple(bgColor, txtColor, bdrColor, Icons.Default.Error, iconColor)
        }
    }
    
    Box(
        modifier = modifier
            .shadow(elevation, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor, RectangleShape)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 状态图标
            Icon(
                imageVector = icon,
                contentDescription = type.name.lowercase(),
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
            
            // 消息文本
            Text(
                text = message,
                color = textColor,
                fontFamily = XiangsutiFont,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * showQToast - 显示Toast工具函数
 * 用于在屏幕中快速显示一个短暂的Toast提示
 */
@Composable
fun showQToast(
    message: String,
    type: QSnackbarType = QSnackbarType.INFO,
    position: QSnackbarPosition = QSnackbarPosition.BOTTOM,
    durationMillis: Long = 2000
) {
    // 使用remember+key确保每次显示都会重置状态
    val animationState = remember(message, type) { MutableTransitionState(false) }
    
    // 使用更安全的LaunchedEffect，避免多次触发导致异常
    LaunchedEffect(message, type) {
        try {
            // 设置为显示
            animationState.targetState = true
            
            // 等待指定时间后关闭
            delay(durationMillis)
            animationState.targetState = false
        } catch (e: Exception) {
            // 捕获可能的异常，防止应用崩溃
            println("Toast animation error: ${e.message}")
        }
    }
    
    // 只有当动画状态有效时才显示Popup
    if (animationState.currentState || animationState.targetState) {
        Popup(
            alignment = when (position) {
                QSnackbarPosition.TOP -> Alignment.TopCenter
                QSnackbarPosition.BOTTOM -> Alignment.BottomCenter
            }
        ) {
            val density = LocalDensity.current
            val slideInFrom = with(density) { 
                when (position) {
                    QSnackbarPosition.TOP -> (-50).dp.roundToPx()
                    QSnackbarPosition.BOTTOM -> 50.dp.roundToPx()
                }
            }
            
            // 使用更轻量的动画参数，减少性能消耗
            AnimatedVisibility(
                visibleState = animationState,
                enter = slideInVertically(
                    initialOffsetY = { slideInFrom },
                    animationSpec = tween(200)
                ) + fadeIn(animationSpec = tween(200)),
                exit = fadeOut(animationSpec = tween(150))
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .widthIn(max = 400.dp) // Toast的最大宽度限制
                ) {
                    QToast(
                        message = message,
                        type = type,
                        elevation = 4.dp // 减小阴影以提高性能
                    )
                }
            }
        }
    }
}

/**
 * QSnackbarHostState - 管理Snackbar状态
 */
class QSnackbarHostState {
    private val mutex = kotlinx.coroutines.sync.Mutex()
    private var currentSnackbarId = 0
    
    var currentSnackbarData by mutableStateOf<QSnackbarData?>(null)
        private set
    
    var position by mutableStateOf(QSnackbarPosition.BOTTOM)
        private set
    
    /**
     * 显示一个Snackbar
     */
    suspend fun showSnackbar(
        message: String,
        type: QSnackbarType = QSnackbarType.INFO,
        duration: Long = 4000, // 默认显示4秒
        action: QSnackbarAction? = null,
        position: QSnackbarPosition = QSnackbarPosition.BOTTOM
    ) {
        mutex.lock()
        try {
            dismissCurrentSnackbar()
            currentSnackbarId++
            this.position = position
            currentSnackbarData = QSnackbarData(
                id = currentSnackbarId,
                message = message,
                type = type,
                duration = duration,
                action = action
            )
        } finally {
            mutex.unlock()
        }
    }
    
    /**
     * 关闭当前显示的Snackbar
     */
    fun dismissCurrentSnackbar() {
        if (currentSnackbarData != null) {
            currentSnackbarData = null
        }
    }
}

/**
 * QSnackbarData - Snackbar数据类
 */
data class QSnackbarData(
    val id: Int,
    val message: String,
    val type: QSnackbarType,
    val duration: Long, // 显示时长 (毫秒)
    val action: QSnackbarAction? = null
)

/**
 * QSnackbarAction - Snackbar操作按钮数据类
 */
data class QSnackbarAction(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val contentDescription: String? = null
)

/**
 * 用于返回五个值的辅助类
 */
data class Quintuple<A, B, C, D, E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
)

/**
 * 用于返回四个值的辅助类
 */
data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) 