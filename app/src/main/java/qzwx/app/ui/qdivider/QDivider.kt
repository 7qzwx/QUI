package qzwx.app.ui.qdivider

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 分隔符类型
 */
enum class QDividerType {
    SOLID,      // 实线
    DOTTED,     // 点线
    DASHED,     // 虚线
    PIXEL,      // 像素点
    ZIGZAG      // 锯齿
}

/**
 * 像素风格分隔符
 * @param modifier 修饰符
 * @param type 分隔符类型
 * @param thickness 线条粗细
 * @param color 颜色
 * @param startIndent 起始缩进
 * @param endIndent 结束缩进
 */
@Composable
fun QDivider(
    modifier: Modifier = Modifier,
    type: QDividerType = QDividerType.SOLID,
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
    startIndent: Dp = 0.dp,
    endIndent: Dp = 0.dp
) {
    val effectiveModifier = modifier
        .fillMaxWidth()
        .padding(start = startIndent, end = endIndent)
        .height(thickness)
    
    when (type) {
        QDividerType.SOLID -> {
            // 实线分隔符
            Box(
                modifier = effectiveModifier
                    .background(color)
            )
        }
        QDividerType.DOTTED -> {
            // 点线分隔符
            Row(
                modifier = effectiveModifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val dotCount = 50 // 点的数量
                repeat(dotCount) {
                    Box(
                        modifier = Modifier
                            .size(thickness)
                            .background(color)
                    )
                }
            }
        }
        QDividerType.DASHED -> {
            // 虚线分隔符
            Row(
                modifier = effectiveModifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val dashCount = 20 // 虚线数量
                repeat(dashCount) {
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .height(thickness)
                            .background(color)
                    )
                }
            }
        }
        QDividerType.PIXEL -> {
            // 像素点分隔符
            Row(
                modifier = effectiveModifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val pixelCount = 30 // 像素点数量
                repeat(pixelCount) {
                    Box(
                        modifier = Modifier
                            .size(3.dp, thickness)
                            .shadow(0.5.dp)
                            .clip(RoundedCornerShape(0.dp))
                            .background(color)
                    )
                }
            }
        }
        QDividerType.ZIGZAG -> {
            // 锯齿分隔符
            Row(
                modifier = effectiveModifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val zigzagCount = 30
                repeat(zigzagCount) {
                    if (it % 2 == 0) {
                        Box(
                            modifier = Modifier
                                .width(6.dp)
                                .height(thickness * 2.5f)
                                .offset(y = (-thickness))
                                .background(color)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .width(6.dp)
                                .height(thickness * 2.5f)
                                .background(color)
                        )
                    }
                }
            }
        }
    }
}

/**
 * 带文字的分隔符
 * @param text 中间显示的文字
 * @param modifier 修饰符
 * @param type 分隔符类型
 * @param thickness 线条粗细
 * @param color 颜色
 * @param textColor 文字颜色（默认使用theme的主色）
 * @param startIndent 起始缩进
 * @param endIndent 结束缩进
 */
@Composable
fun QTextDivider(
    text: String,
    modifier: Modifier = Modifier,
    type: QDividerType = QDividerType.SOLID,
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
    textColor: Color = MaterialTheme.colorScheme.primary,
    startIndent: Dp = 16.dp,
    endIndent: Dp = 16.dp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = startIndent, end = endIndent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 左侧分隔符
        Box(
            modifier = Modifier
                .weight(1f)
                .height(thickness)
        ) {
            QDivider(
                type = type,
                thickness = thickness,
                color = color
            )
        }
        
        // 中间文字
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .shadow(1.dp)
                .clip(RoundedCornerShape(0.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, color)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
        
        // 右侧分隔符
        Box(
            modifier = Modifier
                .weight(1f)
                .height(thickness)
        ) {
            QDivider(
                type = type,
                thickness = thickness,
                color = color
            )
        }
    }
}

/**
 * 带标识的段落分隔符
 */
@Composable
fun QSectionDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    thickness: Dp = 2.dp,
    startIndent: Dp = 16.dp,
    sectionMarkWidth: Dp = 24.dp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = startIndent)
    ) {
        // 左侧标识
        Box(
            modifier = Modifier
                .width(sectionMarkWidth)
                .height(thickness)
                .background(color)
                .shadow(1.dp)
        )
        
        // 右侧细线
        Box(
            modifier = Modifier
                .weight(1f)
                .height(thickness / 2)
                .offset(y = thickness / 4)
                .background(color.copy(alpha = 0.3f))
        )
    }
}

/**
 * 像素风格装饰分隔符
 */
@Composable
fun QPixelDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
    pixelSize: Dp = 2.dp,
    startIndent: Dp = 16.dp,
    endIndent: Dp = 16.dp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = startIndent, end = endIndent, top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(40) {
            Box(
                modifier = Modifier
                    .size(pixelSize)
                    .shadow(0.5.dp)
                    .clip(RoundedCornerShape(0.dp))
                    .background(color)
            )
        }
    }
} 