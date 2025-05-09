package qzwx.app.ui.qchip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

/**
 * 所有标签组件的预览
 */
@Composable
fun QChipPreviewContent() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 标题
        Text(
            text = "标签组件 (QChip)",
            fontFamily = XiangsutiFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )
        
        // 1. 基础标签
        ChipSection(title = "基础标签") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QChip(text = "基础标签")
                QChip(text = "可点击", onClick = {})
                QChip(text = "不可用", enabled = false)
                QChip(text = "已选择", selected = true)
            }
        }
        
        // 2. 带图标的标签
        ChipSection(title = "带图标的标签") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QChip(
                    text = "前置图标",
                    leadingIcon = Icons.Default.Star
                )
                QChip(
                    text = "后置图标",
                    trailingIcon = Icons.Default.Close
                )
                QChip(
                    text = "双侧图标",
                    leadingIcon = Icons.Default.Favorite,
                    trailingIcon = Icons.Default.Done
                )
            }
        }
        
        // 3. 不同颜色变体
        ChipSection(title = "颜色变体") {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QChip(text = "主色", variant = QChipVariant.PRIMARY)
                    QChip(text = "次色", variant = QChipVariant.SECONDARY)
                    QChip(text = "成功", variant = QChipVariant.SUCCESS, leadingIcon = Icons.Default.Check)
                    QChip(text = "警告", variant = QChipVariant.WARNING, leadingIcon = Icons.Default.Warning)
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QChip(text = "错误", variant = QChipVariant.ERROR, leadingIcon = Icons.Default.Error)
                    QChip(text = "信息", variant = QChipVariant.INFO, leadingIcon = Icons.Default.Info)
                    QChip(text = "边框", variant = QChipVariant.OUTLINE)
                    QChip(text = "表面", variant = QChipVariant.SURFACE)
                }
            }
        }
        
        // 4. 交互式标签
        ChipSection(title = "交互式标签") {
            var selected by remember { mutableStateOf(false) }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                QChip(
                    text = if (selected) "已选择" else "未选择",
                    selected = selected,
                    onClick = { selected = !selected },
                    leadingIcon = if (selected) Icons.Default.Check else null
                )
                
                Text(
                    text = "点击标签切换状态",
                    fontFamily = XiangsutiFont,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
        
        // 5. 不同尺寸
        ChipSection(title = "尺寸变体") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                QChip(text = "小标签", size = QChipSize.SMALL)
                QChip(text = "中标签", size = QChipSize.MEDIUM)
                QChip(text = "大标签", size = QChipSize.LARGE)
            }
        }
        
        // 6. 不同形状
        ChipSection(title = "形状变体") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QChip(text = "矩形", shape = QChipShape.RECTANGLE)
                QChip(text = "圆角", shape = QChipShape.ROUNDED)
                QChip(text = "像素边缘", shape = QChipShape.PIXEL)
            }
        }
        
        // 7. 徽章组件
        ChipSection(title = "徽章 (QBadge)") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QBadge(count = 5)
                QBadge(count = 25, variant = QChipVariant.SECONDARY)
                QBadge(count = 100, variant = QChipVariant.ERROR)
                QBadge(count = 0, icon = Icons.Default.Notifications, variant = QChipVariant.INFO)
            }
        }
        
        // 8. 像素风格徽章
        ChipSection(title = "像素风勋章 (QPixelBadge)") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QPixelBadge(icon = Icons.Default.Star)
                QPixelBadge(
                    icon = Icons.Default.Favorite,
                    backgroundColor = MaterialTheme.colorScheme.secondary
                )
                QPixelBadge(
                    icon = Icons.Default.Add,
                    backgroundColor = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

/**
 * 组件预览区域
 */
@Composable
private fun ChipSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            fontFamily = XiangsutiFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    MaterialTheme.shapes.small
                )
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            content()
        }
    }
}

/**
 * 标签组件预览
 */
@Preview(showBackground = true)
@Composable
fun AllChipsPreview() {
    AppTheme {
        QChipPreviewContent()
    }
} 