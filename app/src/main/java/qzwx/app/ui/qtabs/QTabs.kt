package qzwx.app.ui.qtabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QTabs - 标准标签页
 * 像素风格的固定标签页，适用于标签较少的场景
 */
@Composable
fun QTabs(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    variant: QTabsVariant = QTabsVariant.PRIMARY
) {
    val backgroundColor = when(variant) {
        QTabsVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QTabsVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QTabsVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QTabsVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QTabsVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QTabsVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QTabsVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QTabsVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QTabsVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RectangleShape)
            .clip(RectangleShape)
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .border(BorderStroke(2.dp, borderColor), RectangleShape)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, tab ->
                    QTab(
                        text = tab,
                        selected = index == selectedTabIndex,
                        onClick = { onTabSelected(index) },
                        modifier = Modifier.weight(1f),
                        contentColor = contentColor
                    )
                }
            }
        }
        
        // 下方指示线
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f / tabs.size)
                    .height(2.dp)
                    .background(contentColor)
                    .align(Alignment.CenterStart)
                    .padding(start = (selectedTabIndex * (1f / tabs.size) * 100).dp)
            )
        }
    }
}

/**
 * QScrollableTabs - 可滚动标签页
 * 像素风格的可滚动标签页，适用于标签较多的场景
 */
@Composable
fun QScrollableTabs(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    variant: QTabsVariant = QTabsVariant.PRIMARY
) {
    val backgroundColor = when(variant) {
        QTabsVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        QTabsVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        QTabsVariant.SURFACE -> MaterialTheme.colorScheme.surface
    }
    
    val contentColor = when(variant) {
        QTabsVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        QTabsVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        QTabsVariant.SURFACE -> MaterialTheme.colorScheme.onSurface
    }
    
    val borderColor = when(variant) {
        QTabsVariant.PRIMARY -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        QTabsVariant.SECONDARY -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
        QTabsVariant.SURFACE -> MaterialTheme.colorScheme.outline
    }
    
    val scrollState = rememberLazyListState()
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RectangleShape)
            .clip(RectangleShape)
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .border(BorderStroke(2.dp, borderColor), RectangleShape)
                .fillMaxWidth()
        ) {
            LazyRow(
                state = scrollState,
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(tabs) { index, tab ->
                    QTab(
                        text = tab,
                        selected = index == selectedTabIndex,
                        onClick = { onTabSelected(index) },
                        modifier = Modifier.widthIn(min = 80.dp),
                        contentColor = contentColor
                    )
                }
            }
        }
        
        // 指示器
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(backgroundColor)
        ) {
            Divider(
                color = contentColor,
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * QTab - 单个标签页项
 */
@Composable
private fun QTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    val tabColor = if (selected) {
        contentColor
    } else {
        contentColor.copy(alpha = 0.6f)
    }
    
    val selectedBgColor = if (selected) {
        contentColor.copy(alpha = 0.2f)
    } else {
        Color.Transparent
    }
    
    val tabModifier = if (selected) {
        modifier
            .clip(RectangleShape)
            .background(selectedBgColor)
            .border(BorderStroke(1.dp, contentColor.copy(alpha = 0.3f)), RectangleShape)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null // 移除波纹效果
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .wrapContentSize(Alignment.Center)
    } else {
        modifier
            .clip(RectangleShape)
            .background(selectedBgColor)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null // 移除波纹效果
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .wrapContentSize(Alignment.Center)
    }
    
    Text(
        text = text,
        color = tabColor,
        fontSize = if (selected) 15.sp else 14.sp,
        fontFamily = XiangsutiFont,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = tabModifier
    )
}

/**
 * QTabsVariant - 标签页样式变体
 */
enum class QTabsVariant {
    PRIMARY,   // 主色调样式
    SECONDARY, // 次要色调样式
    SURFACE    // 表面颜色样式
} 