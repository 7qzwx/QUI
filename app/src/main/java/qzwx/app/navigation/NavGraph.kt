package qzwx.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import qzwx.app.ui.qbutton.QButtonPreview
import qzwx.app.ui.qcard.QCardPreview
import qzwx.app.ui.qdialog.QDialogPreviewContent
import qzwx.app.ui.qimage.QImagePreviewContent
import qzwx.app.ui.qinput.QTextInputPreview
import qzwx.app.ui.qlistitem.QListItemPreview
import qzwx.app.ui.qloading.QLoadingPreviewContent
import qzwx.app.ui.qsnackbar.QSnackbarPreviewContent
import qzwx.app.ui.qtabs.QTabsPreview
import qzwx.app.ui.qtopbar.QTopBarPreview
import qzwx.app.ui.qbottombar.QBottomBarPreview
import qzwx.app.ui.qslider.AllSlidersPreview
import qzwx.app.ui.qdivider.QDividerPreviewContent
import qzwx.app.ui.qchip.QChipPreviewContent
import qzwx.app.ui.qgrid.QGridPreviewContent

// 定义导航路由
object NavRoutes {
    const val HOME = "home"
    const val BUTTON = "button"
    const val CARD = "card"
    const val DIALOG = "dialog"
    const val IMAGE = "image"
    const val INPUT = "input"
    const val LIST_ITEM = "list_item"
    const val LOADING = "loading"
    const val SNACKBAR = "snackbar"
    const val TABS = "tabs"
    const val TOP_BAR = "top_bar"
    const val BOTTOM_BAR = "bottom_bar"
    const val SLIDER = "slider"
    const val DIVIDER = "divider"
    const val CHIP = "chip"
    const val GRID = "grid"
}

@Composable
fun NavGraph(
    navController : NavHostController,startDestination : String = NavRoutes.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.HOME) {
            HomeScreen(navController)
        }

        composable(NavRoutes.BUTTON) {
            NavigationWrapper(
                title = "按钮组件 (QButton)",
                navController = navController
            ) {
                QButtonPreview()
            }
        }

        composable(NavRoutes.CARD) {
            NavigationWrapper(
                title = "卡片组件 (QCard)",
                navController = navController
            ) {
                QCardPreview()
            }
        }

        composable(NavRoutes.DIALOG) {
            NavigationWrapper(
                title = "对话框组件 (QDialog)",
                navController = navController
            ) {
                QDialogPreviewContent()
            }
        }

        composable(NavRoutes.IMAGE) {
            NavigationWrapper(
                title = "图片组件 (QImage)",
                navController = navController
            ) {
                QImagePreviewContent()
            }
        }

        composable(NavRoutes.INPUT) {
            NavigationWrapper(
                title = "输入组件 (QInput)",
                navController = navController
            ) {
                QTextInputPreview()
            }
        }

        composable(NavRoutes.LIST_ITEM) {
            NavigationWrapper(
                title = "列表项组件 (QListItem)",
                navController = navController
            ) {
                QListItemPreview()
            }
        }

        composable(NavRoutes.LOADING) {
            NavigationWrapper(
                title = "加载组件 (QLoading)",
                navController = navController
            ) {
                QLoadingPreviewContent()
            }
        }

        composable(NavRoutes.SNACKBAR) {
            NavigationWrapper(
                title = "提示消息组件 (QSnackbar)",
                navController = navController
            ) {
                QSnackbarPreviewContent()
            }
        }

        composable(NavRoutes.TABS) {
            NavigationWrapper(
                title = "标签页组件 (QTabs)",
                navController = navController
            ) {
                QTabsPreview()
            }
        }

        composable(NavRoutes.TOP_BAR) {
            NavigationWrapper(
                title = "顶部栏组件 (QTopBar)",
                navController = navController
            ) {
                QTopBarPreview()
            }
        }

        composable(NavRoutes.BOTTOM_BAR) {
            NavigationWrapper(
                title = "底部栏组件 (QBottomBar)",
                navController = navController
            ) {
                QBottomBarPreview()
            }
        }
        composable(NavRoutes.SLIDER) {
            NavigationWrapper(
                title = "滑块组件 (QSlider)",
                navController = navController
            ) {
                AllSlidersPreview()
            }
        }

        composable(NavRoutes.DIVIDER) {
            NavigationWrapper(
                title = "分隔符组件 (QDivider)",
                navController = navController
            ) {
                QDividerPreviewContent()
            }
        }

        composable(NavRoutes.CHIP) {
            NavigationWrapper(
                title = "标签组件 (QChip)",
                navController = navController
            ) {
                QChipPreviewContent()
            }
        }

        composable(NavRoutes.GRID) {
            NavigationWrapper(
                title = "网格组件 (QGrid)",
                navController = navController
            ) {
                QGridPreviewContent()
            }
        }
    }
} 