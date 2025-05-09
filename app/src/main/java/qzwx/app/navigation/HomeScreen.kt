package qzwx.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import qzwx.app.ui.qbutton.QPrimaryButton
import qzwx.app.ui.qtopbar.QTopBar
import qzwx.app.ui.qtopbar.QTopBarVariant
import qzwx.app.ui.qtopbar.TopBarAction
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 顶部栏
        QTopBar(
            title = "Q风格UI组件库",
            actions = listOf(
                TopBarAction(
                    icon = Icons.Default.Settings,
                    contentDescription = "设置",
                    onClick = { /* TODO */ }
                )
            ),
            variant = QTopBarVariant.SURFACE
        )

        // 组件列表
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "组件预览",
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            ComponentButton(
                text = "按钮组件 (QButton)",
                onClick = { navController.navigate(NavRoutes.BUTTON) }
            )

            ComponentButton(
                text = "卡片组件 (QCard)",
                onClick = { navController.navigate(NavRoutes.CARD) }
            )

            ComponentButton(
                text = "对话框组件 (QDialog)",
                onClick = { navController.navigate(NavRoutes.DIALOG) }
            )

            ComponentButton(
                text = "图片组件 (QImage)",
                onClick = { navController.navigate(NavRoutes.IMAGE) }
            )

            ComponentButton(
                text = "输入组件 (QInput)",
                onClick = { navController.navigate(NavRoutes.INPUT) }
            )

            ComponentButton(
                text = "列表项组件 (QListItem)",
                onClick = { navController.navigate(NavRoutes.LIST_ITEM) }
            )

            ComponentButton(
                text = "加载组件 (QLoading)",
                onClick = { navController.navigate(NavRoutes.LOADING) }
            )

            ComponentButton(
                text = "提示消息组件 (QSnackbar)",
                onClick = { navController.navigate(NavRoutes.SNACKBAR) }
            )

            ComponentButton(
                text = "标签页组件 (QTabs)",
                onClick = { navController.navigate(NavRoutes.TABS) }
            )

            ComponentButton(
                text = "顶部栏组件 (QTopBar)",
                onClick = { navController.navigate(NavRoutes.TOP_BAR) }
            )

            ComponentButton(
                text = "底部栏组件 (QBottomBar)",
                onClick = { navController.navigate(NavRoutes.BOTTOM_BAR) }
            )
            ComponentButton(
                text = "滑动组件 (QSlider)",
                onClick = { navController.navigate(NavRoutes.SLIDER) }
            )
            
            ComponentButton(
                text = "分隔符组件 (QDivider)",
                onClick = { navController.navigate(NavRoutes.DIVIDER) }
            )
            
            ComponentButton(
                text = "标签组件 (QChip)",
                onClick = { navController.navigate(NavRoutes.CHIP) }
            )
            
            ComponentButton(
                text = "卡片网格组件 (QGrid)",
                onClick = { navController.navigate(NavRoutes.GRID) }
            )
        }
    }
}

@Composable
fun ComponentButton(
    text: String,
    onClick: () -> Unit
) {
    QPrimaryButton(
        text = text,
        onClick = onClick,

        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(rememberNavController())
    }
} 