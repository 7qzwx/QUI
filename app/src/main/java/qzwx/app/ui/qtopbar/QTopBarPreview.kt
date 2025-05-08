package qzwx.app.ui.qtopbar

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
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
fun QTopBarPreview() {
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
                // 标准顶部栏 - 主色调
                Text(
                    text = "标准顶部栏 - 主色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                QTopBar(
                    title = "主页",
                    navigationIcon = Icons.Default.Menu,
                    onNavigationClick = { /* 菜单点击 */ },
                    actions = listOf(
                        TopBarAction(
                            icon = Icons.Default.Search,
                            onClick = { /* 搜索点击 */ },
                            contentDescription = "搜索"
                        ),
                        TopBarAction(
                            icon = Icons.Default.MoreVert,
                            onClick = { /* 更多点击 */ },
                            contentDescription = "更多"
                        )
                    ),
                    variant = QTopBarVariant.PRIMARY
                )
                
                // 标准顶部栏 - 次要色调
                Text(
                    text = "标准顶部栏 - 次要色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QTopBar(
                    title = "发现",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationClick = { /* 返回点击 */ },
                    actions = listOf(
                        TopBarAction(
                            icon = Icons.Default.Share,
                            onClick = { /* 分享点击 */ },
                            contentDescription = "分享"
                        )
                    ),
                    variant = QTopBarVariant.SECONDARY
                )
                
                // 标准顶部栏 - 表面色
                Text(
                    text = "标准顶部栏 - 表面色",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QTopBar(
                    title = "设置",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationClick = { /* 返回点击 */ },
                    variant = QTopBarVariant.SURFACE
                )
                
                // 居中标题顶部栏
                Text(
                    text = "居中标题顶部栏",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QCenterTopBar(
                    title = "个人资料",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationClick = { /* 返回点击 */ },
                    actions = listOf(
                        TopBarAction(
                            icon = Icons.Default.Settings,
                            onClick = { /* 设置点击 */ },
                            contentDescription = "设置"
                        )
                    ),
                    variant = QTopBarVariant.PRIMARY
                )
                
                // 居中标题顶部栏 - 次要色调
                Text(
                    text = "居中标题顶部栏 - 次要色调",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QCenterTopBar(
                    title = "消息中心",
                    variant = QTopBarVariant.SECONDARY
                )
                
                // 居中标题顶部栏 - 表面色
                Text(
                    text = "居中标题顶部栏 - 表面色",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QCenterTopBar(
                    title = "帮助中心",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationClick = { /* 返回点击 */ },
                    actions = listOf(
                        TopBarAction(
                            icon = Icons.Default.Favorite,
                            onClick = { /* 收藏点击 */ },
                            contentDescription = "收藏"
                        )
                    ),
                    variant = QTopBarVariant.SURFACE
                )
                
                // 大标题顶部栏
                Text(
                    text = "大标题顶部栏",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QLargeTopBar(
                    title = "文章详情",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationClick = { /* 返回点击 */ },
                    actions = listOf(
                        TopBarAction(
                            icon = Icons.Default.Share,
                            onClick = { /* 分享点击 */ },
                            contentDescription = "分享"
                        ),
                        TopBarAction(
                            icon = Icons.Default.Favorite,
                            onClick = { /* 收藏点击 */ },
                            contentDescription = "收藏"
                        )
                    ),
                    variant = QTopBarVariant.PRIMARY
                )
                
                // 大标题顶部栏 - 超长标题
                Text(
                    text = "大标题顶部栏 - 超长标题",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QLargeTopBar(
                    title = "这是一个非常长的标题，用于测试标题文本截断和多行显示的效果，可以看到标题会自动换行",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationClick = { /* 返回点击 */ },
                    actions = listOf(
                        TopBarAction(
                            icon = Icons.Default.Share,
                            onClick = { /* 分享点击 */ },
                            contentDescription = "分享"
                        )
                    ),
                    variant = QTopBarVariant.SECONDARY
                )
                
                // 大标题顶部栏 - 表面色
                Text(
                    text = "大标题顶部栏 - 表面色",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QLargeTopBar(
                    title = "系统公告",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationClick = { /* 返回点击 */ },
                    variant = QTopBarVariant.SURFACE
                )
                
                // 使用预设的操作按钮
                Text(
                    text = "使用预设的操作按钮",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                QTopBar(
                    title = "使用预设按钮",
                    navigationIcon = QTopBarDefaults.BackIcon,
                    onNavigationClick = { /* 返回点击 */ },
                    actions = listOf(
                        QTopBarDefaults.MoreAction { /* 更多点击 */ }
                    ),
                    variant = QTopBarVariant.PRIMARY
                )
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
} 