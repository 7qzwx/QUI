package qzwx.app.ui.qlistitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun QListItemPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 标准列表项示例
                Text(
                    text = "标准列表项 - Surface变体",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                QListItem(
                    title = "个人资料",
                    subtitle = "查看和编辑您的个人信息",
                    leadingIcon = Icons.Default.Person,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ }
                )
                
                QListItem(
                    title = "消息通知",
                    subtitle = "管理您的消息和通知设置",
                    leadingIcon = Icons.Default.Email,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ }
                )
                
                QListItem(
                    title = "应用设置",
                    subtitle = "自定义应用外观和功能",
                    leadingIcon = Icons.Default.Settings,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ },
                    showDivider = false
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Primary变体列表项
                Text(
                    text = "标准列表项 - Primary变体",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                QListItem(
                    title = "推荐内容",
                    subtitle = "查看为您精选的内容推荐",
                    leadingIcon = Icons.Default.Star,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ },
                    variant = QListItemVariant.PRIMARY
                )
                
                QListItem(
                    title = "我的收藏",
                    subtitle = "管理您收藏的内容",
                    leadingIcon = Icons.Default.Favorite,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ },
                    variant = QListItemVariant.PRIMARY,
                    showDivider = false
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Secondary变体列表项
                Text(
                    text = "标准列表项 - Secondary变体",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                QListItem(
                    title = "帮助与反馈",
                    subtitle = "获取帮助或提交问题反馈",
                    leadingIcon = Icons.Default.Info,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ },
                    variant = QListItemVariant.SECONDARY,
                    showDivider = false
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // 带边框的列表项
                Text(
                    text = "带边框的列表项",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                QBorderedListItem(
                    title = "个人资料",
                    subtitle = "查看和编辑您的个人信息",
                    leadingIcon = Icons.Default.Person,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                QBorderedListItem(
                    title = "消息通知",
                    subtitle = "管理您的消息和通知设置",
                    leadingIcon = Icons.Default.Email,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ },
                    variant = QListItemVariant.PRIMARY,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                QBorderedListItem(
                    title = "应用设置",
                    subtitle = "自定义应用外观和功能",
                    leadingIcon = Icons.Default.Settings,
                    trailingIcon = Icons.Default.KeyboardArrowRight,
                    onClick = { /* 点击处理 */ },
                    variant = QListItemVariant.SECONDARY,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // 无图标列表项
                Text(
                    text = "无图标列表项",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                QListItem(
                    title = "语言设置",
                    subtitle = "选择您偏好的应用语言",
                    onClick = { /* 点击处理 */ }
                )
                
                QListItem(
                    title = "主题设置",
                    subtitle = "自定义应用主题和颜色",
                    onClick = { /* 点击处理 */ },
                    showDivider = false
                )
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
} 