package qzwx.app.ui.qimage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * 图片形状
 */
enum class QImageShape {
    RECTANGLE,  // 矩形（像素风默认）
    ROUNDED,    // 圆角矩形
    CIRCLE,     // 圆形
    PIXEL       // 像素化边缘
}

/**
 * 像素风格图片组件
 */
@Composable
fun QImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: QImageShape = QImageShape.RECTANGLE,
    contentScale: ContentScale = ContentScale.Crop,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 2.dp,
    colorFilter: ColorFilter? = null
) {
    val imageShape = when (shape) {
        QImageShape.RECTANGLE -> RectangleShape
        QImageShape.ROUNDED -> MaterialTheme.shapes.medium
        QImageShape.CIRCLE -> MaterialTheme.shapes.extraLarge
        QImageShape.PIXEL -> RectangleShape // 像素边缘使用矩形，后面会添加特效
    }
    
    Box(
        modifier = modifier
            .shadow(elevation, imageShape)
            .clip(imageShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), imageShape)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = colorFilter,
            modifier = Modifier.fillMaxSize()
        )
        
        // 如果是像素边缘形状，添加像素效果边框
        if (shape == QImageShape.PIXEL) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        BorderStroke(
                            width = borderWidth, 
                            color = borderColor
                        ),
                        RectangleShape
                    )
            )
        }
    }
}

// 由于异步图片加载需要额外的库支持，这里创建一个占位版本
// 完整版本需要添加coil依赖库并实现
/**
 * 使用URL加载图片的异步图片组件（简化版，实际使用需要添加Coil依赖）
 */
@Composable
fun QAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: QImageShape = QImageShape.RECTANGLE,
    contentScale: ContentScale = ContentScale.Crop,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 2.dp,
    colorFilter: ColorFilter? = null,
    loadingContent: @Composable () -> Unit = { DefaultImageLoadingContent() },
    errorContent: @Composable () -> Unit = { DefaultImageErrorContent() }
) {
    val imageShape = when (shape) {
        QImageShape.RECTANGLE -> RectangleShape
        QImageShape.ROUNDED -> MaterialTheme.shapes.medium
        QImageShape.CIRCLE -> MaterialTheme.shapes.extraLarge
        QImageShape.PIXEL -> RectangleShape
    }
    
    Box(
        modifier = modifier
            .shadow(elevation, imageShape)
            .clip(imageShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), imageShape)
    ) {
        // 简化版本，实际项目中应使用Coil加载
        loadingContent()
        
        // 像素边缘效果
        if (shape == QImageShape.PIXEL) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        BorderStroke(
                            width = borderWidth, 
                            color = borderColor
                        ),
                        RectangleShape
                    )
            )
        }
    }
}

/**
 * 默认加载中组件
 */
@Composable
fun DefaultImageLoadingContent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // 使用基础进度指示器
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp)
        )
    }
}

/**
 * 默认错误内容
 */
@Composable
fun DefaultImageErrorContent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.BrokenImage,
            contentDescription = "加载失败",
            tint = MaterialTheme.colorScheme.onErrorContainer,
            modifier = Modifier.size(32.dp)
        )
    }
}

/**
 * 图片墙面板组件 - 显示一个图片网格
 */
@Composable
fun QImagePanel(
    painter: Painter,
    contentDescription: String?,
    title: String? = null,
    modifier: Modifier = Modifier,
    shape: QImageShape = QImageShape.RECTANGLE,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 4.dp,
) {
    val imageShape = when (shape) {
        QImageShape.RECTANGLE -> RectangleShape
        QImageShape.ROUNDED -> MaterialTheme.shapes.medium
        QImageShape.CIRCLE -> MaterialTheme.shapes.extraLarge
        QImageShape.PIXEL -> RectangleShape
    }
    
    Box(
        modifier = modifier
            .shadow(elevation, imageShape)
            .clip(imageShape)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), imageShape)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        
        // 标题文本
        if (title != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxSize(0.2f)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontFamily = XiangsutiFont,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // 像素边缘效果
        if (shape == QImageShape.PIXEL) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        BorderStroke(
                            width = borderWidth, 
                            color = borderColor
                        ),
                        RectangleShape
                    )
            )
        }
    }
}

/**
 * 像素风头像组件
 */
@Composable
fun QAvatar(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: QImageShape = QImageShape.CIRCLE,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 2.dp,
    size: Dp = 48.dp
) {
    QImage(
        painter = painter,
        contentDescription = contentDescription,
        shape = shape,
        borderWidth = borderWidth,
        borderColor = borderColor,
        backgroundColor = backgroundColor,
        elevation = elevation,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
    )
}

/**
 * 异步加载的头像组件
 */
@Composable
fun QAsyncAvatar(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: QImageShape = QImageShape.CIRCLE,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 2.dp,
    size: Dp = 48.dp,
    loadingContent: @Composable () -> Unit = { 
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp,
                modifier = Modifier.size(size / 2)
            )
        }
    },
    errorContent: @Composable () -> Unit = { 
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = "加载失败",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.size(size / 2)
            )
        }
    }
) {
    QAsyncImage(
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        shape = shape,
        borderWidth = borderWidth,
        borderColor = borderColor,
        backgroundColor = backgroundColor,
        elevation = elevation,
        contentScale = ContentScale.Crop,
        loadingContent = loadingContent,
        errorContent = errorContent,
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
    )
} 