package qzwx.app.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import qzwx.app.ui.qtopbar.QTopBar
import qzwx.app.ui.qtopbar.QTopBarVariant

/**
 * 导航包装器 - 用于包装已有预览页面，添加顶部导航栏和返回功能
 */
@Composable
fun NavigationWrapper(
    title: String,
    navController: NavController,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部栏
        QTopBar(
            title = title,
            navigationIcon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.navigateUp() },
            variant = QTopBarVariant.SURFACE // 使用SURFACE变体，图标颜色将是onSurface
        )
        
        // 页面内容
        content()
    }
} 