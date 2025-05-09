package qzwx.app.ui.qgrid

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.XiangsutiFont

/**
 * 网格列数方式
 */
enum class QGridColumnsMode {
    FIXED,      // 固定列数
    ADAPTIVE,   // 自适应列数（根据最小宽度）
}

/**
 * 像素风格网格布局组件
 * 提供便捷的网格布局，适用于卡片、图片等内容的展示
 */
@Composable
fun QGrid(
    modifier: Modifier = Modifier,
    columnsMode: QGridColumnsMode = QGridColumnsMode.FIXED,
    columns: Int = 2,
    minColumnWidth: Dp = 150.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalSpacing: Dp = 8.dp,
    horizontalSpacing: Dp = 8.dp,
    title: String? = null,
    content: LazyGridScope.() -> Unit
) {
    Column(modifier = modifier) {
        // 标题（如果有）
        if (title != null) {
            Text(
                text = title,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        
        // 网格内容
        LazyVerticalGrid(
            columns = when (columnsMode) {
                QGridColumnsMode.FIXED -> GridCells.Fixed(columns)
                QGridColumnsMode.ADAPTIVE -> GridCells.Adaptive(minColumnWidth)
            },
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(verticalSpacing),
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true), // 确保有明确的高度约束
            content = content
        )
    }
}

/**
 * 带边框的像素风格卡片网格
 * 为整个网格添加像素风格边框和背景
 */
@Composable
fun QOutlinedGrid(
    modifier: Modifier = Modifier,
    columnsMode: QGridColumnsMode = QGridColumnsMode.FIXED,
    columns: Int = 2,
    minColumnWidth: Dp = 150.dp,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    verticalSpacing: Dp = 8.dp,
    horizontalSpacing: Dp = 8.dp,
    title: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
    borderColor: Color = MaterialTheme.colorScheme.outline,
    borderWidth: Dp = 2.dp,
    elevation: Dp = 2.dp,
    content: LazyGridScope.() -> Unit
) {
    Column(modifier = modifier) {
        // 标题（如果有）
        if (title != null) {
            Text(
                text = title,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        
        // 带边框的网格
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true) // 确保有明确的高度约束
                .shadow(elevation, RectangleShape)
                .clip(RectangleShape)
                .background(backgroundColor)
                .border(BorderStroke(borderWidth, borderColor), RectangleShape)
                .padding(8.dp)
        ) {
            LazyVerticalGrid(
                columns = when (columnsMode) {
                    QGridColumnsMode.FIXED -> GridCells.Fixed(columns)
                    QGridColumnsMode.ADAPTIVE -> GridCells.Adaptive(minColumnWidth)
                },
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(verticalSpacing),
                horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
                content = content
            )
        }
    }
}

/**
 * 像素风格网格标题项
 * 在网格中添加跨越整行的标题
 */
@Composable
fun LazyGridScope.gridHeader(
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    borderColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
    borderWidth: Dp = 2.dp,
    elevation: Dp = 2.dp
) {
    item(span = { GridItemSpan(maxLineSpan) }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .shadow(elevation, RectangleShape)
                .clip(RectangleShape)
                .background(backgroundColor)
                .border(BorderStroke(borderWidth, borderColor), RectangleShape)
                .padding(vertical = 8.dp, horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = contentColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * 像素风格网格分隔符
 * 在网格中添加跨越整行的分隔线
 */
@Composable
fun LazyGridScope.gridDivider(
    color: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
    thickness: Dp = 2.dp
) {
    item(span = { GridItemSpan(maxLineSpan) }) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(thickness)
                .background(color)
                .padding(vertical = 4.dp)
        )
    }
}

/**
 * 像素风格网格空项占位
 * 用于在网格末尾添加空白项以保持布局平衡
 */
@Composable
fun LazyGridScope.gridEmptyItems(count: Int) {
    items(count) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
        )
    }
} 