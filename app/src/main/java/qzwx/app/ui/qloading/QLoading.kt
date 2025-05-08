package qzwx.app.ui.qloading

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * 加载组件样式
 */
enum class QLoadingStyle {
    CIRCULAR,   // 圆形进度
    PIXEL,      // 像素点动画
    BAR,        // 条形进度条
    DOTS        // 点阵动画
}

/**
 * 加载动画大小
 */
enum class QLoadingSize {
    SMALL,      // 小尺寸
    MEDIUM,     // 中等尺寸
    LARGE       // 大尺寸
}

/**
 * 像素风格加载动画组件
 */
@Composable
fun QLoading(
    modifier: Modifier = Modifier,
    style: QLoadingStyle = QLoadingStyle.CIRCULAR,
    size: QLoadingSize = QLoadingSize.MEDIUM,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    text: String? = null
) {
    val actualSize = when (size) {
        QLoadingSize.SMALL -> 32.dp
        QLoadingSize.MEDIUM -> 48.dp
        QLoadingSize.LARGE -> 64.dp
    }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(actualSize)
        ) {
            when (style) {
                QLoadingStyle.CIRCULAR -> QCircularLoading(
                    size = actualSize,
                    color = color,
                    backgroundColor = backgroundColor,
                    borderWidth = borderWidth,
                    borderColor = borderColor
                )
                QLoadingStyle.PIXEL -> QPixelLoading(
                    size = actualSize,
                    color = color,
                    backgroundColor = backgroundColor
                )
                QLoadingStyle.BAR -> QBarLoading(
                    size = actualSize,
                    color = color,
                    backgroundColor = backgroundColor,
                    borderWidth = borderWidth,
                    borderColor = borderColor
                )
                QLoadingStyle.DOTS -> QDotsLoading(
                    size = actualSize,
                    color = color,
                    backgroundColor = backgroundColor
                )
            }
        }
        
        if (text != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = XiangsutiFont,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * 圆形进度指示器
 */
@Composable
fun QCircularLoading(
    size: Dp,
    color: Color,
    backgroundColor: Color,
    borderWidth: Dp,
    borderColor: Color
) {
    val infiniteTransition = rememberInfiniteTransition(label = "circular")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .shadow(2.dp, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape)
            .padding(4.dp)
    ) {
        Canvas(modifier = Modifier.size(size * 0.8f)) {
            // 像素风格：绘制多个小方块形成圆圈
            val numberOfSegments = 12
            val segmentSize = this.size.width / 8
            val radius = (this.size.width - segmentSize) / 2
            
            for (i in 0 until numberOfSegments) {
                val angle = (i * 360f / numberOfSegments + rotation) % 360
                val angleInRadians = Math.toRadians(angle.toDouble()).toFloat()
                
                val alpha = 0.2f + 0.8f * (1f - (i.toFloat() / numberOfSegments))
                val segmentColor = color.copy(alpha = alpha)
                
                val centerX = this.size.width / 2
                val centerY = this.size.height / 2
                
                val x = centerX + radius * kotlin.math.cos(angleInRadians)
                val y = centerY + radius * kotlin.math.sin(angleInRadians)
                
                // 绘制方块（像素风格）
                drawRect(
                    color = segmentColor,
                    topLeft = Offset(x - segmentSize / 2, y - segmentSize / 2),
                    size = Size(segmentSize, segmentSize)
                )
            }
        }
    }
}

/**
 * 像素点动画
 */
@Composable
fun QPixelLoading(
    size: Dp,
    color: Color,
    backgroundColor: Color
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pixel")
    
    // 颜色动画
    val animatedColor by infiniteTransition.animateColor(
        initialValue = color.copy(alpha = 0.3f),
        targetValue = color,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color"
    )
    
    // 脉动大小动画
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    
    Surface(
        shape = RectangleShape,
        color = backgroundColor,
        border = BorderStroke(2.dp, color),
        shadowElevation = 2.dp,
        modifier = Modifier.size(size)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val pixelSize = size.toPx() / 8
            val pixelRows = 4
            val pixelColumns = 4
            
            val startX = (this.size.width - pixelColumns * pixelSize) / 2
            val startY = (this.size.height - pixelRows * pixelSize) / 2
            
            // 绘制4x4像素点阵
            for (row in 0 until pixelRows) {
                for (col in 0 until pixelColumns) {
                    // 设置每个像素点的缩放和透明度
                    val cellScale = when {
                        (row + col) % 2 == 0 -> scale
                        else -> 1f - scale * 0.3f
                    }
                    
                    val cellColor = when {
                        (row + col) % 2 == 0 -> animatedColor
                        else -> color.copy(alpha = 0.5f)
                    }
                    
                    val cellSize = pixelSize * cellScale
                    val xOffset = startX + col * pixelSize + (pixelSize - cellSize) / 2
                    val yOffset = startY + row * pixelSize + (pixelSize - cellSize) / 2
                    
                    drawRect(
                        color = cellColor,
                        topLeft = Offset(xOffset, yOffset),
                        size = Size(cellSize, cellSize)
                    )
                }
            }
        }
    }
}

/**
 * 条形进度条
 */
@Composable
fun QBarLoading(
    size: Dp,
    color: Color,
    backgroundColor: Color,
    borderWidth: Dp,
    borderColor: Color
) {
    val infiniteTransition = rememberInfiniteTransition(label = "bar")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )
    
    Box(
        modifier = Modifier
            .size(width = size, height = size / 2)
            .shadow(2.dp, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape)
            .padding(4.dp)
    ) {
        // 进度条背景
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color.copy(alpha = 0.2f))
        )
        
        // 进度条前景
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxSize()
                .background(color)
        )
    }
}

/**
 * 点阵动画加载
 */
@Composable
fun QDotsLoading(
    size: Dp,
    color: Color,
    backgroundColor: Color
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    
    val animations = List(3) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 800,
                    delayMillis = index * 200,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ),
            label = "dot-$index"
        )
    }
    
    Row(
        horizontalArrangement = Arrangement.spacedBy(size / 8),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .size(size)
            .background(backgroundColor)
            .padding(4.dp)
    ) {
        animations.forEachIndexed { index, animation ->
            val scale = animation.value
            val dotSize = size / 4
            
            Box(
                modifier = Modifier
                    .weight(1f)
                    .size(dotSize * scale)
                    .background(color)
            )
        }
    }
}

/**
 * 带有确定进度的进度条
 */
@Composable
fun QProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = 16.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    showPercentage: Boolean = false
) {
    // 确保进度在0到1之间
    val normalizedProgress = progress.coerceIn(0f, 1f)
    
    Box(
        modifier = modifier
            .height(height)
            .shadow(2.dp, RectangleShape)
            .clip(RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), RectangleShape)
            .padding(borderWidth) // 内边距确保进度条在边框内
    ) {
        // 进度条填充部分
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(normalizedProgress)
                .background(progressColor)
        )
        
        // 如果显示百分比
        if (showPercentage) {
            Text(
                text = "${(normalizedProgress * 100).toInt()}%",
                style = MaterialTheme.typography.bodySmall,
                fontFamily = XiangsutiFont,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(2.dp)
            )
        }
    }
}

/**
 * 带有背景和标签的进度条
 */
@Composable
fun QProgressPanel(
    progress: Float,
    modifier: Modifier = Modifier,
    label: String? = null,
    height: Dp = 24.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    showPercentage: Boolean = true
) {
    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = XiangsutiFont,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        
        QProgressBar(
            progress = progress,
            height = height,
            backgroundColor = backgroundColor,
            progressColor = progressColor,
            borderWidth = borderWidth,
            borderColor = borderColor,
            showPercentage = showPercentage,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * 加载中遮罩 - 覆盖整个内容的加载指示器
 */
@Composable
fun QLoadingOverlay(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    style: QLoadingStyle = QLoadingStyle.CIRCULAR,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
    text: String? = "加载中...",
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        // 主要内容
        content()
        
        // 加载遮罩
        if (isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
            ) {
                QLoading(
                    style = style,
                    size = QLoadingSize.LARGE,
                    color = color,
                    text = text
                )
            }
        }
    }
} 