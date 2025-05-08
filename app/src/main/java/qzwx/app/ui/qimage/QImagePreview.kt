package qzwx.app.ui.qimage

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

@Preview(showBackground = true)
@Composable
fun QImagePreviewLight() {
    AppTheme(darkTheme = false) {
        QImagePreviewContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1A1621)
@Composable
fun QImagePreviewDark() {
    AppTheme(darkTheme = true) {
        QImagePreviewContent()
    }
}

@Composable
fun QImagePreview() {
    QImagePreviewContent()
}

@Composable
fun QImagePreviewContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 标题
        Text(
            text = "图片组件预览",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = XiangsutiFont,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // 基础图片组件
        SectionTitle("基础图片")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 矩形图片
            QImage(
                painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
                contentDescription = "矩形图片",
                shape = QImageShape.RECTANGLE,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )
            
            // 圆角图片
            QImage(
                painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
                contentDescription = "圆角图片",
                shape = QImageShape.ROUNDED,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )
            
            // 圆形图片
            QImage(
                painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
                contentDescription = "圆形图片",
                shape = QImageShape.CIRCLE,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )
        }
        
        // 像素边缘图片
        SectionTitle("像素边缘图片")
        QImage(
            painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
            contentDescription = "像素边缘图片",
            shape = QImageShape.PIXEL,
            borderWidth = 4.dp,
            borderColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        
        // 图片面板
        SectionTitle("图片面板（带标题）")
        QImagePanel(
            painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
            contentDescription = "图片面板",
            title = "QZWX Logo",
            shape = QImageShape.ROUNDED,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        
        // 头像组件
        SectionTitle("头像组件")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 小头像
            QAvatar(
                painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
                contentDescription = "小头像",
                size = 40.dp
            )
            
            // 中头像
            QAvatar(
                painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
                contentDescription = "中头像",
                size = 64.dp,
                borderColor = MaterialTheme.colorScheme.secondary
            )
            
            // 大头像
            QAvatar(
                painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
                contentDescription = "大头像",
                size = 96.dp,
                shape = QImageShape.RECTANGLE,
                borderWidth = 4.dp,
                borderColor = MaterialTheme.colorScheme.tertiary
            )
        }
        
        // 异步加载图片（模拟）
        SectionTitle("异步加载图片")
        Text(
            text = "网络图片加载需要在实际环境中测试",
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = XiangsutiFont,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        // 加载状态
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 加载中状态
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .border(
                        width = 2.dp, 
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                DefaultImageLoadingContent()
                Text(
                    text = "加载中",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = XiangsutiFont,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
            // 错误状态
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .border(
                        width = 2.dp, 
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                DefaultImageErrorContent()
                Text(
                    text = "加载错误",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = XiangsutiFont,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        
        // 自定义样式
        SectionTitle("自定义样式")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 自定义边框
            QImage(
                painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
                contentDescription = "自定义边框",
                shape = QImageShape.RECTANGLE,
                borderWidth = 6.dp,
                borderColor = Color(0xFFFF5722),
                elevation = 8.dp,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )
            
            // 自定义背景
            QImage(
                painter = painterResource(id = qzwx.app.ui.R.drawable.qzwxlogo),
                contentDescription = "自定义背景",
                shape = QImageShape.ROUNDED,
                backgroundColor = Color(0xFF4CAF50).copy(alpha = 0.2f),
                borderColor = Color(0xFF4CAF50),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
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