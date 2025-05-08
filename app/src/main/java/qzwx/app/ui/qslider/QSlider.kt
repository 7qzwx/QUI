package qzwx.app.ui.qslider

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/**
 * 像素风格滑动条组件
 * @param value 当前值
 * @param onValueChange 值变化回调
 * @param modifier 修饰符
 * @param enabled 是否启用
 * @param valueRange 值范围
 * @param steps 步数
 * @param showLabel 是否显示值标签
 * @param labelFormat 值格式化函数
 * @param contentDescription 无障碍描述
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    showLabel: Boolean = false,
    labelFormat: (Float) -> String = { "%.0f".format(it) },
    contentDescription: String? = null
) {
    val borderColor = if (enabled) {
        MaterialTheme.colorScheme.outline
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }
    
    val primaryColor = if (enabled) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    }
    
    val backgroundColor = if (enabled) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
    }
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 显示当前值的标签
        if (showLabel) {
            Box(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .shadow(2.dp)
                    .clip(RoundedCornerShape(0.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .border(
                        1.dp,
                        borderColor
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 2.dp
                    )
            ) {
                Text(
                    text = labelFormat(value),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // 自定义滑动条
        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .semantics {
                    contentDescription?.let { this.contentDescription = it }
                }
                .padding(horizontal = 2.dp),
            enabled = enabled,
            valueRange = valueRange,
            steps = steps,
            thumb = {
                // 像素风格滑块
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .shadow(2.dp)
                        .clip(RoundedCornerShape(0.dp))
                        .background(primaryColor)
                        .border(
                            1.dp,
                            borderColor
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    val  Primarycolor = MaterialTheme.colorScheme.primary
                    // 像素十字内部线条
                    Canvas(modifier = Modifier.size(12.dp)) {
                        drawPixelCross(
                            color = Primarycolor,
                            lineWidth = size.width / 5
                        )
                    }
                }
            },
            track = { sliderPositions ->
                // 像素风格轨道 - 使用嵌套Box实现像素风格的轨道效果
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .shadow(1.dp)
                        .clip(RoundedCornerShape(0.dp))
                        .background(backgroundColor)
                        .border(
                            1.dp,
                            borderColor
                        )
                ) {
                    // 计算当前位置比例
                    val min = valueRange.start
                    val max = valueRange.endInclusive
                    val fraction = if (max - min != 0f) (value - min) / (max - min) else 0f
                    
                    // 已选择部分
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(0.dp))
                            .background(primaryColor)
                    )
                    
                    // 添加像素风格的点状标记（可选）
                    if (steps > 0) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            repeat(steps + 1) { step ->
                                // 计算当前刻度的位置比例
                                val stepFraction = step / steps.toFloat()
                                
                                Box(
                                    modifier = Modifier
                                        .size(
                                            2.dp,
                                            10.dp
                                        )
                                        .background(
                                            if (stepFraction <= fraction) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                                            else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                                        )
                                )
                            }
                        }
                    }
                }
            },
            colors = SliderDefaults.colors(
                // 设置为透明以使用自定义轨道和滑块
                thumbColor = Color.Transparent,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,
                disabledThumbColor = Color.Transparent,
                disabledActiveTrackColor = Color.Transparent,
                disabledInactiveTrackColor = Color.Transparent
            )
        )
    }
}

/**
 * 绘制像素十字标记
 */
private fun DrawScope.drawPixelCross(color: Color, lineWidth: Float) {
    // 水平线
    drawRect(
        color = color,
        topLeft = Offset(0f, size.height / 2 - lineWidth / 2),
        size = Size(size.width, lineWidth)
    )
    
    // 垂直线
    drawRect(
        color = color,
        topLeft = Offset(size.width / 2 - lineWidth / 2, 0f),
        size = Size(lineWidth, size.height)
    )
}

/**
 * 带值范围和步进的滑动条
 * @param value 当前值
 * @param onValueChange 值变化回调
 * @param modifier 修饰符
 * @param enabled 是否启用
 * @param valueRange 值范围
 * @param steps 步数
 * @param showLabel 是否显示值标签
 * @param labelFormat 值格式化函数
 * @param contentDescription 无障碍描述
 */
@Composable
fun QRangeSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    showLabel: Boolean = true,
    labelFormat: (Float) -> String = { "%.0f".format(it) },
    contentDescription: String? = null
) {
    QSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        showLabel = showLabel,
        labelFormat = labelFormat,
        contentDescription = contentDescription
    )
}

/**
 * 带刻度的像素风格滑动条
 * @param value 当前值
 * @param onValueChange 值变化回调
 * @param modifier 修饰符
 * @param enabled 是否启用
 * @param valueRange 值范围
 * @param steps 步数
 * @param showValue 是否显示当前值
 * @param showTickLabels 是否显示刻度标签
 * @param tickLabelStep 刻度标签步长
 * @param labelFormat 值格式化函数
 * @param contentDescription 无障碍描述
 */
@Composable
fun QTickSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 4,
    showValue: Boolean = false,
    showTickLabels: Boolean = true,
    tickLabelStep: Int = 1,
    labelFormat: (Float) -> String = { "%.0f".format(it) },
    contentDescription: String? = null
) {
    val borderColor = if (enabled) {
        MaterialTheme.colorScheme.outline
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }
    
    val containerColor = if (enabled) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
    }
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 当前值显示
        if (showValue) {
            Box(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .shadow(2.dp)
                    .clip(RoundedCornerShape(0.dp))
                    .background(containerColor)
                    .border(
                        1.dp,
                        borderColor
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 2.dp
                    )
            ) {
                Text(
                    text = labelFormat(value),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // 主滑动条
        QSlider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(horizontal = if (showTickLabels) 8.dp else 0.dp),
            enabled = enabled,
            valueRange = valueRange,
            steps = steps,
            contentDescription = contentDescription
        )
        
        // 刻度标记
        if (showTickLabels && steps > 0) {
            val min = valueRange.start
            val max = valueRange.endInclusive
            val range = max - min
            
            // 像素风格刻度标记区域
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            ) {
                // 刻度背景线
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(borderColor.copy(alpha = 0.3f))
                )
                
                // 刻度标记
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 0..steps step tickLabelStep) {
                        val tickValue = min + (range * i / steps)
                        val isHighlighted = value >= tickValue
                        
                        TickMark(
                            value = tickValue,
                            labelFormat = labelFormat,
                            isHighlighted = isHighlighted,
                            enabled = enabled
                        )
                    }
                }
            }
        }
    }
}

/**
 * 刻度标记
 */
@Composable
private fun TickMark(
    value: Float,
    labelFormat: (Float) -> String,
    isHighlighted: Boolean,
    enabled: Boolean
) {
    val tickColor = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        isHighlighted -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.outline
    }
    
    val textColor = when {
        !enabled -> MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        isHighlighted -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.onBackground
    }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 像素风格刻度线 - 使用矩形而非圆角
        Box(
            modifier = Modifier
                .size(
                    width = 2.dp,
                    height = 8.dp
                )
                .clip(RoundedCornerShape(0.dp))
                .background(tickColor)
                .shadow(if (isHighlighted) 1.dp else 0.dp)
        )
        
        // 刻度值
        Text(
            text = labelFormat(value),
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

/**
 * 带刻度显示的音量滑动条
 * @param value 当前值
 * @param onValueChange 值变化回调
 * @param modifier 修饰符
 * @param enabled 是否启用
 * @param contentDescription 无障碍描述
 */
@Composable
fun QVolumeSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentDescription: String = "音量控制"
) {
    QTickSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = 0f..100f,
        steps = 10,
        showValue = true,
        contentDescription = contentDescription,
        labelFormat = { "${it.roundToInt()}%" }
    )
}

/**
 * 游戏等级滑动条
 * @param value 当前等级值
 * @param onValueChange 值变化回调
 * @param modifier 修饰符
 * @param maxLevel 最大等级
 * @param enabled 是否启用
 * @param contentDescription 无障碍描述
 */
@Composable
fun QLevelSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    maxLevel: Int = 10,
    enabled: Boolean = true,
    contentDescription: String = "等级选择"
) {
    QTickSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = 1f..maxLevel.toFloat(),
        steps = maxLevel - 1,
        showValue = true,
        contentDescription = contentDescription,
        labelFormat = { "LV.${it.toInt()}" }
    )
} 