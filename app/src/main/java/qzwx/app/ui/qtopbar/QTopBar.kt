package qzwx.app.ui.qtopbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import qzwx.app.ui.qbutton.QIconButton
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QTopBar - 顶部导航栏
 * 提供应用顶部导航功能，包含标题、导航按钮和操作按钮
 * 相当于Material Design中的TopAppBar
 */
@Composable
fun QTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    actions: List<TopBarAction> = emptyList(),
    variant: QTopBarVariant = QTopBarVariant.PRIMARY,
) {
    val backgroundColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(2.dp, borderColor), RectangleShape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 导航图标
            if (navigationIcon != null && onNavigationClick != null) {
                QIconButton(
                    icon = navigationIcon,
                    onClick = onNavigationClick,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "导航"
                )
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }
            
            // 标题
            Text(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                color = contentColor,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            // 右侧操作按钮
            Row {
                actions.forEach { action ->
                    QIconButton(
                        icon = action.icon,
                        onClick = action.onClick,
                        tint = contentColor,
                        contentDescription = action.contentDescription
                    )
                }
                
                // 如果没有操作按钮，添加一个空间占位
                if (actions.isEmpty()) {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        }
    }
}

/**
 * QCenterTopBar - 居中标题的顶部导航栏
 * 相当于Material Design中的CenterAlignedTopAppBar
 */
@Composable
fun QCenterTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    actions: List<TopBarAction> = emptyList(),
    variant: QTopBarVariant = QTopBarVariant.PRIMARY,
) {
    val backgroundColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(2.dp, borderColor), RectangleShape)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            // 标题居中
            Text(
                text = title,
                color = contentColor,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 48.dp) // 留出两侧按钮的空间
            )
            
            // 左侧导航图标
            if (navigationIcon != null && onNavigationClick != null) {
                Box(
                    modifier = Modifier.align(Alignment.CenterStart),
                    contentAlignment = Alignment.Center
                ) {
                    QIconButton(
                        icon = navigationIcon,
                        onClick = onNavigationClick,
                        tint = contentColor,
                        contentDescription = "导航"
                    )
                }
            }
            
            // 右侧操作按钮
            if (actions.isNotEmpty()) {
                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    actions.forEach { action ->
                        QIconButton(
                            icon = action.icon,
                            onClick = action.onClick,
                            tint = contentColor,
                            contentDescription = action.contentDescription
                        )
                    }
                }
            }
        }
    }
}

/**
 * QLargeTopBar - 大标题顶部导航栏
 * 相当于Material Design中的LargeTopAppBar，提供更大的标题显示
 */
@Composable
fun QLargeTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    actions: List<TopBarAction> = emptyList(),
    variant: QTopBarVariant = QTopBarVariant.PRIMARY,
) {
    val backgroundColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QTopBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QTopBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QTopBarVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RectangleShape)
            .background(backgroundColor)
            .border(BorderStroke(2.dp, borderColor), RectangleShape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp)
        ) {
            // 顶部操作按钮行
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 导航图标
                if (navigationIcon != null && onNavigationClick != null) {
                    QIconButton(
                        icon = navigationIcon,
                        onClick = onNavigationClick,
                        tint = contentColor,
                        contentDescription = "导航"
                    )
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                // 右侧操作按钮
                actions.forEach { action ->
                    QIconButton(
                        icon = action.icon,
                        onClick = action.onClick,
                        tint = contentColor,
                        contentDescription = action.contentDescription
                    )
                }
            }
            
            // 大标题
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
                color = contentColor,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * TopBarAction - 顶部栏操作按钮数据类
 */
data class TopBarAction(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val contentDescription: String
)

/**
 * QTopBarVariant - 顶部栏样式变体
 */
enum class QTopBarVariant {
    PRIMARY,   // 主色调样式
    SECONDARY, // 次要色调样式
    SURFACE    // 表面颜色样式
}

/**
 * 预设的常用TopBar操作按钮和图标
 */
object QTopBarDefaults {
    val BackIcon = Icons.Default.ArrowBack
    val MenuIcon = Icons.Default.Menu
    val MoreIcon = Icons.Default.MoreVert
    
    @Composable
    fun BackAction(onClick: () -> Unit) = TopBarAction(
        icon = BackIcon,
        onClick = onClick,
        contentDescription = "返回"
    )
    
    @Composable
    fun MenuAction(onClick: () -> Unit) = TopBarAction(
        icon = MenuIcon,
        onClick = onClick,
        contentDescription = "菜单"
    )
    
    @Composable
    fun MoreAction(onClick: () -> Unit) = TopBarAction(
        icon = MoreIcon,
        onClick = onClick,
        contentDescription = "更多"
    )
} 