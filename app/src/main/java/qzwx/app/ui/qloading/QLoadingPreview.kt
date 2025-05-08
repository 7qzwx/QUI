package qzwx.app.ui.qloading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

@Preview(showBackground = true)
@Composable
fun QLoadingPreviewLight() {
    AppTheme(darkTheme = false) {
        QLoadingPreviewContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1A1621)
@Composable
fun QLoadingPreviewDark() {
    AppTheme(darkTheme = true) {
        QLoadingPreviewContent()
    }
}

@Composable
fun QLoadingPreviewContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 标题
        Text(
            text = "加载组件预览",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = XiangsutiFont,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // 圆形加载动画
        SectionTitle("圆形加载动画")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            QLoading(
                style = QLoadingStyle.CIRCULAR,
                size = QLoadingSize.SMALL,
                text = "小"
            )
            
            QLoading(
                style = QLoadingStyle.CIRCULAR,
                size = QLoadingSize.MEDIUM,
                text = "中"
            )
            
            QLoading(
                style = QLoadingStyle.CIRCULAR,
                size = QLoadingSize.LARGE,
                text = "大"
            )
        }
        
        // 像素点加载动画
        SectionTitle("像素点加载动画")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            QLoading(
                style = QLoadingStyle.PIXEL,
                size = QLoadingSize.SMALL,
                color = MaterialTheme.colorScheme.primary
            )
            
            QLoading(
                style = QLoadingStyle.PIXEL,
                size = QLoadingSize.MEDIUM,
                color = MaterialTheme.colorScheme.secondary
            )
            
            QLoading(
                style = QLoadingStyle.PIXEL,
                size = QLoadingSize.LARGE,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        
        // 条形加载动画
        SectionTitle("条形加载动画")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            QLoading(
                style = QLoadingStyle.BAR,
                size = QLoadingSize.SMALL
            )
            
            QLoading(
                style = QLoadingStyle.BAR,
                size = QLoadingSize.MEDIUM
            )
            
            QLoading(
                style = QLoadingStyle.BAR,
                size = QLoadingSize.LARGE
            )
        }
        
        // 点阵加载动画
        SectionTitle("点阵加载动画")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            QLoading(
                style = QLoadingStyle.DOTS,
                size = QLoadingSize.SMALL,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )
            
            QLoading(
                style = QLoadingStyle.DOTS,
                size = QLoadingSize.MEDIUM,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )
            
            QLoading(
                style = QLoadingStyle.DOTS,
                size = QLoadingSize.LARGE,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
        
        // 进度条
        SectionTitle("确定进度的进度条")
        
        val progressValues = listOf(0.25f, 0.5f, 0.75f, 1f)
        
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            progressValues.forEach { progress ->
                QProgressBar(
                    progress = progress,
                    showPercentage = true,
                    height = 24.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        
        // 带标签的进度条
        SectionTitle("带标签的进度条")
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QProgressPanel(
                progress = 0.3f,
                label = "下载中...",
                progressColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            
            QProgressPanel(
                progress = 0.65f,
                label = "正在同步数据...",
                progressColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.fillMaxWidth()
            )
            
            QProgressPanel(
                progress = 0.9f,
                label = "即将完成...",
                progressColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        // 加载遮罩示例
        SectionTitle("加载遮罩示例")
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // 这里使用固定的isLoading值，实际应用中会是动态变化的
            QLoadingOverlay(
                isLoading = true,
                style = QLoadingStyle.CIRCULAR,
                text = "正在加载数据..."
            ) {
                // 内容区域 - 在真实场景中这里会有内容
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "内容区域",
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = XiangsutiFont
                    )
                }
            }
        }
        
        // 自定义颜色
        SectionTitle("自定义颜色")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 自定义颜色1
            QLoading(
                style = QLoadingStyle.CIRCULAR,
                size = QLoadingSize.MEDIUM,
                color = Color(0xFFFF5722),
                borderColor = Color(0xFFFF5722)
            )
            
            // 自定义颜色2
            QLoading(
                style = QLoadingStyle.PIXEL,
                size = QLoadingSize.MEDIUM,
                color = Color(0xFF4CAF50),
                backgroundColor = Color(0xFF4CAF50).copy(alpha = 0.1f)
            )
            
            // 自定义颜色3
            QLoading(
                style = QLoadingStyle.BAR,
                size = QLoadingSize.MEDIUM,
                color = Color(0xFF2196F3),
                borderColor = Color(0xFF2196F3)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontFamily = XiangsutiFont,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
} 