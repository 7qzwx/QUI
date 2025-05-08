package qzwx.app.ui.qbottombar

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QBottomBar - 底部导航栏
 * 像素风格的底部导航栏，支持图标和文本标签
 */
@Composable
fun QBottomBar(
    items: List<BottomBarItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    showLabels: Boolean = true,
    variant: QBottomBarVariant = QBottomBarVariant.PRIMARY
) {
    val backgroundColor = when(variant) {
        QBottomBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QBottomBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QBottomBarVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QBottomBarVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QBottomBarVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QBottomBarVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QBottomBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QBottomBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QBottomBarVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                4.dp,
                RectangleShape
            )
            .clip(RectangleShape),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .border(
                    BorderStroke(
                        2.dp,
                        borderColor
                    ),
                    RectangleShape
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    QBottomBarItem(
                        item = item,
                        isSelected = index == selectedItemIndex,
                        onItemClick = { onItemSelected(index) },
                        showLabel = showLabels,
                        contentColor = contentColor
                    )
                }
            }
        }
    }
}

/**
 * QBottomBarCompact - 紧凑型底部导航栏
 * 只显示图标的紧凑型底部导航栏
 */
@Composable
fun QBottomBarCompact(
    items: List<BottomBarItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    variant: QBottomBarVariant = QBottomBarVariant.PRIMARY
) {
    QBottomBar(
        items = items,
        selectedItemIndex = selectedItemIndex,
        onItemSelected = onItemSelected,
        showLabels = false,
        variant = variant,
        modifier = modifier
    )
}

/**
 * QBottomBarItem - 底部导航栏项目
 * 单个导航项目，包含图标和可选的文本标签
 */
@Composable
private fun QBottomBarItem(
    item: BottomBarItem,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    showLabel: Boolean,
    contentColor: Color
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    val itemColor = if (isSelected) {
        contentColor
    } else {
        contentColor.copy(alpha = 0.6f)
    }
    
    val selectedBgColor = if (isSelected) {
        contentColor.copy(alpha = 0.2f)
    } else {
        Color.Transparent
    }
    
    // 增加选中项的样式效果
    val itemModifier = if (isSelected) {
        Modifier
            .clip(RectangleShape)
            .background(selectedBgColor)
            .border(
                BorderStroke(
                    1.dp,
                    contentColor.copy(alpha = 0.3f)
                ),
                RectangleShape
            )
            .clickable(
                onClick = onItemClick,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null // 移除波纹效果
            )
            .padding(
                horizontal = 12.dp,
                vertical = if (showLabel) 8.dp else 12.dp
            )
    } else {
        Modifier
            .clip(RectangleShape)
            .background(selectedBgColor)
            .clickable(
                onClick = onItemClick,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null // 移除波纹效果
            )
            .padding(
                horizontal = 12.dp,
                vertical = if (showLabel) 8.dp else 12.dp
            )
    }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = itemModifier
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = itemColor,
            // 增大选中项的图标
            modifier = Modifier.size(if (isSelected) 28.dp else 24.dp)
        )
        
        if (showLabel) {
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = item.label,
                color = itemColor,
                fontSize = if (isSelected) 13.sp else 12.sp,
                fontFamily = XiangsutiFont,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

/**
 * QBadgeBottomBar - 带徽章的底部导航栏
 * 支持在导航项上显示徽章，用于提示未读消息等
 */
@Composable
fun QBadgeBottomBar(
    items: List<BottomBarItemWithBadge>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    variant: QBottomBarVariant = QBottomBarVariant.PRIMARY
) {
    val backgroundColor = when(variant) {
        QBottomBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QBottomBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QBottomBarVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QBottomBarVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QBottomBarVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QBottomBarVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QBottomBarVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QBottomBarVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QBottomBarVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                4.dp,
                RectangleShape
            )
            .clip(RectangleShape),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .border(
                    BorderStroke(
                        2.dp,
                        borderColor
                    ),
                    RectangleShape
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    QBottomBarItemWithBadge(
                        item = item,
                        isSelected = index == selectedItemIndex,
                        onItemClick = { onItemSelected(index) },
                        contentColor = contentColor
                    )
                }
            }
        }
    }
}

/**
 * QBottomBarItemWithBadge - 带徽章的底部导航栏项目
 * 单个导航项目，包含图标、文本标签和徽章
 */
@Composable
private fun QBottomBarItemWithBadge(
    item: BottomBarItemWithBadge,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    contentColor: Color
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isDarkTheme = isSystemInDarkTheme()
    
    val itemColor = if (isSelected) {
        contentColor
    } else {
        contentColor.copy(alpha = 0.6f)
    }
    
    val selectedBgColor = if (isSelected) {
        contentColor.copy(alpha = 0.2f)
    } else {
        Color.Transparent
    }
    
    // 增加选中项的样式效果
    val itemModifier = if (isSelected) {
        Modifier
            .clip(RectangleShape)
            .background(selectedBgColor)
            .border(
                BorderStroke(
                    1.dp,
                    contentColor.copy(alpha = 0.3f)
                ),
                RectangleShape
            )
            .clickable(
                onClick = onItemClick,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null // 移除波纹效果
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    } else {
        Modifier
            .clip(RectangleShape)
            .background(selectedBgColor)
            .clickable(
                onClick = onItemClick,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null // 移除波纹效果
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    }
    
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = itemModifier
        ) {
            Box {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    tint = itemColor,
                    // 增大选中项的图标
                    modifier = Modifier.size(if (isSelected) 28.dp else 24.dp)
                )
                
                // 显示徽章
                if (item.badgeCount > 0) {
                    val badgeText = if (item.badgeCount > 99) "99+" else item.badgeCount.toString()
                    
                    // 徽章背景色和文字颜色根据深色/浅色模式调整
                    val badgeBgColor = MaterialTheme.colorScheme.error
                    val badgeTextColor = if (isDarkTheme) {
                        Color.Black
                    } else {
                        Color.White
                    }
                    
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(
                                x = 6.dp,
                                y = (-4).dp
                            )
                            .background(
                                color = badgeBgColor,
                                shape = CircleShape
                            )
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    badgeBgColor
                                ),
                                shape = CircleShape
                            )
                            .padding(
                                horizontal = 4.dp,
                                vertical = 2.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = badgeText,
                            color = badgeTextColor,
                            fontSize = 9.sp,
                            fontFamily = XiangsutiFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = item.label,
                color = itemColor,
                fontSize = if (isSelected) 13.sp else 12.sp,
                fontFamily = XiangsutiFont,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

/**
 * BottomBarItem - 底部导航栏项目数据类
 */
data class BottomBarItem(
    val icon: ImageVector,
    val label: String
)

/**
 * BottomBarItemWithBadge - 带徽章的底部导航栏项目数据类
 */
data class BottomBarItemWithBadge(
    val icon: ImageVector,
    val label: String,
    val badgeCount: Int = 0
)

/**
 * QBottomBarVariant - 底部导航栏样式变体
 */
enum class QBottomBarVariant {
    PRIMARY,   // 主色调样式
    SECONDARY, // 次要色调样式
    SURFACE    // 表面颜色样式
} 