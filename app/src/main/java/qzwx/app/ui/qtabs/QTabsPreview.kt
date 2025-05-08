package qzwx.app.ui.qtabs

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
fun QTabsPreview() {
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
                // 标准标签页示例
                val standardTabs = listOf("首页", "推荐", "关注", "热门")
                var selectedStandardTab by remember { mutableStateOf(0) }
                
                // 主色调标签页
                Text(
                    text = "标准标签页 - 主色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                QTabs(
                    tabs = standardTabs,
                    selectedTabIndex = selectedStandardTab,
                    onTabSelected = { selectedStandardTab = it },
                    variant = QTabsVariant.PRIMARY
                )
                
                // 次要色调标签页
                Text(
                    text = "标准标签页 - 次要色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QTabs(
                    tabs = standardTabs,
                    selectedTabIndex = selectedStandardTab,
                    onTabSelected = { selectedStandardTab = it },
                    variant = QTabsVariant.SECONDARY
                )
                
                // 表面色标签页
                Text(
                    text = "标准标签页 - 表面色",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QTabs(
                    tabs = standardTabs,
                    selectedTabIndex = selectedStandardTab,
                    onTabSelected = { selectedStandardTab = it },
                    variant = QTabsVariant.SURFACE
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // 可滚动标签页示例
                val scrollableTabs = listOf(
                    "全部", "游戏", "音乐", "电影", "动画", "美食", "旅游", 
                    "科技", "体育", "教育", "时尚"
                )
                var selectedScrollableTab by remember { mutableStateOf(0) }
                
                // 主色调可滚动标签页
                Text(
                    text = "可滚动标签页 - 主色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QScrollableTabs(
                    tabs = scrollableTabs,
                    selectedTabIndex = selectedScrollableTab,
                    onTabSelected = { selectedScrollableTab = it },
                    variant = QTabsVariant.PRIMARY
                )
                
                // 次要色调可滚动标签页
                Text(
                    text = "可滚动标签页 - 次要色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QScrollableTabs(
                    tabs = scrollableTabs,
                    selectedTabIndex = selectedScrollableTab,
                    onTabSelected = { selectedScrollableTab = it },
                    variant = QTabsVariant.SECONDARY
                )
                
                // 表面色可滚动标签页
                Text(
                    text = "可滚动标签页 - 表面色",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QScrollableTabs(
                    tabs = scrollableTabs,
                    selectedTabIndex = selectedScrollableTab,
                    onTabSelected = { selectedScrollableTab = it },
                    variant = QTabsVariant.SURFACE
                )
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
} 