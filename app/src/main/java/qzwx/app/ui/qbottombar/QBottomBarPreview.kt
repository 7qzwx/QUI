package qzwx.app.ui.qbottombar

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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun QBottomBarPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // 示例导航项
                val items = listOf(
                    BottomBarItem(icon = Icons.Default.Home, label = "首页"),
                    BottomBarItem(icon = Icons.Default.Favorite, label = "收藏"),
                    BottomBarItem(icon = Icons.Default.Notifications, label = "通知"),
                    BottomBarItem(icon = Icons.Default.AccountCircle, label = "我的")
                )
                
                var selectedIndex by remember { mutableStateOf(0) }
                
                // 标准底部导航 - 主色调
                Text(
                    text = "标准底部导航 - 主色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                QBottomBar(
                    items = items,
                    selectedItemIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    variant = QBottomBarVariant.PRIMARY
                )
                
                // 标准底部导航 - 次要色调
                Text(
                    text = "标准底部导航 - 次要色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QBottomBar(
                    items = items,
                    selectedItemIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    variant = QBottomBarVariant.SECONDARY
                )
                
                // 标准底部导航 - 表面色
                Text(
                    text = "标准底部导航 - 表面色",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QBottomBar(
                    items = items,
                    selectedItemIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    variant = QBottomBarVariant.SURFACE
                )
                
                // 紧凑型底部导航（仅图标）
                Text(
                    text = "紧凑型底部导航（仅图标）",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QBottomBarCompact(
                    items = items,
                    selectedItemIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    variant = QBottomBarVariant.PRIMARY
                )
                
                // 带徽章的底部导航
                Text(
                    text = "带徽章的底部导航",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                
                // 带徽章的导航项
                val badgeItems = listOf(
                    BottomBarItemWithBadge(icon = Icons.Default.Home, label = "首页", badgeCount = 0),
                    BottomBarItemWithBadge(icon = Icons.Default.Favorite, label = "收藏", badgeCount = 5),
                    BottomBarItemWithBadge(icon = Icons.Default.Notifications, label = "通知", badgeCount = 99),
                    BottomBarItemWithBadge(icon = Icons.Default.Settings, label = "设置", badgeCount = 999)
                )
                
                QBadgeBottomBar(
                    items = badgeItems,
                    selectedItemIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    variant = QBottomBarVariant.PRIMARY
                )
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
} 