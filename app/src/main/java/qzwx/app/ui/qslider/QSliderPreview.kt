package qzwx.app.ui.qslider

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.AppTheme

/**
 * 基础滑动条预览
 */
@Preview(showBackground = true)
@Composable
fun QBasicSliderPreview() {
    AppTheme {
        var sliderValue by remember { mutableStateOf(0.5f) }
        
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PreviewLabel("基础滑动条")
                
                QSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it }
                )
                
                QSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    showLabel = true
                )
                
                QSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    enabled = false,
                    showLabel = true
                )
            }
        }
    }
}

/**
 * 带范围的滑动条预览
 */
@Preview(showBackground = true)
@Composable
fun QRangeSliderPreview() {
    AppTheme {
        var sliderValue by remember { mutableStateOf(75f) }
        
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PreviewLabel("带范围的滑动条")
                
                QRangeSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..100f,
                    steps = 10,
                    showLabel = true,
                    labelFormat = { "${it.toInt()}" }
                )
            }
        }
    }
}

/**
 * 带刻度的滑动条预览
 */
@Preview(showBackground = true)
@Composable
fun QTickSliderPreview() {
    AppTheme {
        var sliderValue by remember { mutableStateOf(50f) }
        
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PreviewLabel("带刻度的滑动条")
                
                QTickSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..100f,
                    steps = 5,
                    showValue = true
                )
            }
        }
    }
}

/**
 * 音量滑动条预览
 */
@Preview(showBackground = true)
@Composable
fun QVolumeSliderPreview() {
    AppTheme {
        var sliderValue by remember { mutableStateOf(60f) }
        
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PreviewLabel("音量滑动条")
                
                QVolumeSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it }
                )
            }
        }
    }
}

/**
 * 等级滑动条预览
 */
@Preview(showBackground = true)
@Composable
fun QLevelSliderPreview() {
    AppTheme {
        var sliderValue by remember { mutableStateOf(5f) }
        
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PreviewLabel("等级滑动条")
                
                QLevelSlider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    maxLevel = 20
                )
            }
        }
    }
}

/**
 * 滑动条组件集合预览
 */
@Preview(showBackground = true, heightDp = 700)
@Composable
fun AllSlidersPreview() {
    AppTheme {
        var basicSliderValue by remember { mutableStateOf(0.7f) }
        var rangeSliderValue by remember { mutableStateOf(75f) }
        var volumeSliderValue by remember { mutableStateOf(60f) }
        var levelValue by remember { mutableStateOf(5f) }
        var steppedSliderValue by remember { mutableStateOf(2f) }
        
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // 标题
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp)
                        .clip(RoundedCornerShape(0.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .border(1.dp, MaterialTheme.colorScheme.outline)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "像素风格滑动条组件",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }
                
                PreviewSection("基础滑动条") {
                    QSlider(
                        value = basicSliderValue,
                        onValueChange = { basicSliderValue = it },
                        showLabel = true
                    )
                }
                
                PixelDivider()
                
                PreviewSection("带范围的滑动条") {
                    QRangeSlider(
                        value = rangeSliderValue,
                        onValueChange = { rangeSliderValue = it },
                        valueRange = 0f..100f,
                        steps = 10
                    )
                }
                
                PixelDivider()
                
                PreviewSection("带刻度的滑动条") {
                    QTickSlider(
                        value = steppedSliderValue,
                        onValueChange = { steppedSliderValue = it },
                        valueRange = 0f..10f,
                        steps = 10,
                        showValue = true,
                        labelFormat = { "等级 ${it.toInt()}" }
                    )
                }
                
                PixelDivider()
                
                PreviewSection("音量滑动条") {
                    QVolumeSlider(
                        value = volumeSliderValue,
                        onValueChange = { volumeSliderValue = it }
                    )
                }
                
                PixelDivider()
                
                PreviewSection("游戏等级滑动条") {
                    QLevelSlider(
                        value = levelValue,
                        onValueChange = { levelValue = it },
                        maxLevel = 20
                    )
                }
                
                PixelDivider()
                
                PreviewSection("禁用状态的滑动条") {
                    QSlider(
                        value = basicSliderValue,
                        onValueChange = { basicSliderValue = it },
                        enabled = false,
                        showLabel = true
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    QTickSlider(
                        value = steppedSliderValue,
                        onValueChange = { steppedSliderValue = it },
                        valueRange = 0f..10f,
                        steps = 10,
                        showValue = true,
                        enabled = false
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

/**
 * 预览标签
 */
@Composable
private fun PreviewLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

/**
 * 预览分区
 */
@Composable
private fun PreviewSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // 像素风格标题
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .shadow(1.dp)
                .clip(RoundedCornerShape(0.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(1.dp, MaterialTheme.colorScheme.outline)
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        // 内容
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp)
        ) {
            content()
        }
    }
}

/**
 * 像素风格分隔线
 */
@Composable
private fun PixelDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(30) {
            Box(
                modifier = Modifier
                    .size(8.dp, 2.dp)
                    .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            )
        }
    }
} 