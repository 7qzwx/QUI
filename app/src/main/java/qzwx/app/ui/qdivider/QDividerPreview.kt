package qzwx.app.ui.qdivider

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.AppTheme

/**
 * 基础分隔符预览
 */
@Preview(showBackground = true)
@Composable
fun QBasicDividerPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                PreviewLabel("实线分隔符")
                QDivider(type = QDividerType.SOLID)
                
                PreviewLabel("点线分隔符")
                QDivider(type = QDividerType.DOTTED)
                
                PreviewLabel("虚线分隔符")
                QDivider(type = QDividerType.DASHED)
                
                PreviewLabel("像素点分隔符")
                QDivider(type = QDividerType.PIXEL)
                
                PreviewLabel("锯齿分隔符")
                QDivider(type = QDividerType.ZIGZAG)
            }
        }
    }
}

/**
 * 带文字分隔符预览
 */
@Preview(showBackground = true)
@Composable
fun QTextDividerPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                QTextDivider(
                    text = "实线分隔符",
                    type = QDividerType.SOLID
                )
                
                QTextDivider(
                    text = "点线分隔符",
                    type = QDividerType.DOTTED
                )
                
                QTextDivider(
                    text = "虚线分隔符",
                    type = QDividerType.DASHED
                )
                
                QTextDivider(
                    text = "像素点分隔符",
                    type = QDividerType.PIXEL
                )
                
                QTextDivider(
                    text = "锯齿分隔符",
                    type = QDividerType.ZIGZAG
                )
            }
        }
    }
}

/**
 * 段落分隔符预览
 */
@Preview(showBackground = true)
@Composable
fun QSectionDividerPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                PreviewLabel("段落分隔符")
                
                QSectionDivider()
                
                QSectionDivider(
                    color = MaterialTheme.colorScheme.secondary,
                    thickness = 3.dp,
                    sectionMarkWidth = 32.dp
                )
                
                QSectionDivider(
                    color = MaterialTheme.colorScheme.tertiary,
                    thickness = 4.dp,
                    sectionMarkWidth = 16.dp
                )
            }
        }
    }
}

/**
 * 像素装饰分隔符预览
 */
@Preview(showBackground = true)
@Composable
fun QPixelDividerPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                PreviewLabel("像素装饰分隔符")
                
                QPixelDivider()
                
                QPixelDivider(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    pixelSize = 3.dp
                )
                
                QPixelDivider(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
                    pixelSize = 4.dp
                )
            }
        }
    }
}

/**
 * 分隔符综合预览
 */
@Preview(showBackground = true, heightDp = 700)
@Composable
fun QDividerPreviewContent() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(32.dp)
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
                        text = "像素风格分隔符组件",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }
                
                // 基础分隔符
                PreviewSection("基础分隔符") {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // 实线
                        PreviewItem("实线分隔符") {
                            QDivider(type = QDividerType.SOLID)
                        }
                        
                        // 点线
                        PreviewItem("点线分隔符") {
                            QDivider(type = QDividerType.DOTTED)
                        }
                        
                        // 虚线
                        PreviewItem("虚线分隔符") {
                            QDivider(type = QDividerType.DASHED)
                        }
                        
                        // 像素点
                        PreviewItem("像素点分隔符") {
                            QDivider(type = QDividerType.PIXEL)
                        }
                        
                        // 锯齿
                        PreviewItem("锯齿分隔符") {
                            QDivider(type = QDividerType.ZIGZAG)
                        }
                    }
                }
                
                QPixelDivider()
                
                // 带文字分隔符
                PreviewSection("带文字分隔符") {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        QTextDivider(
                            text = "第一章",
                            type = QDividerType.SOLID
                        )
                        
                        QTextDivider(
                            text = "第二章",
                            type = QDividerType.DOTTED,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                        )
                        
                        QTextDivider(
                            text = "第三章",
                            type = QDividerType.PIXEL,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                            textColor = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                
                QPixelDivider(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                )
                
                // 段落分隔符
                PreviewSection("段落分隔符") {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        QSectionDivider()
                        
                        Text(
                            text = "这是一段示例文本，用于演示段落分隔符的效果。通过添加明显的分隔标记，可以让内容更加清晰。",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        
                        QSectionDivider(
                            color = MaterialTheme.colorScheme.secondary
                        )
                        
                        Text(
                            text = "这是第二段示例文本，使用了不同颜色的段落分隔符，可以用于区分不同的内容类型或重要程度。",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                
                QPixelDivider(
                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                    pixelSize = 3.dp
                )
                
                // 自定义样式
                PreviewSection("自定义样式分隔符") {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // 加粗分隔符
                        PreviewItem("加粗分隔符") {
                            QDivider(
                                type = QDividerType.SOLID,
                                thickness = 3.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        
                        // 带缩进的分隔符
                        PreviewItem("带缩进的分隔符") {
                            QDivider(
                                type = QDividerType.DASHED,
                                startIndent = 32.dp,
                                endIndent = 32.dp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        
                        // 彩色像素分隔符
                        PreviewItem("彩色像素分隔符") {
                            QPixelDivider(
                                color = MaterialTheme.colorScheme.tertiary,
                                pixelSize = 4.dp
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
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
                .padding(bottom = 16.dp)
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
                .padding(horizontal = 4.dp)
        ) {
            content()
        }
    }
}

/**
 * 预览项
 */
@Composable
private fun PreviewItem(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        
        content()
    }
} 