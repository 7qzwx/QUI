package qzwx.app.ui.qgrid

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

/**
 * 用于预览的网格项数据
 */
private data class SampleGridItem(
    override val id: Int,
    override val title: String,
    override val content: String?
) : QGridItem

/**
 * 用于预览的带图片网格项数据
 */
private data class SampleImageGridItem(
    override val id: Int,
    override val title: String,
    override val content: String?,
    override val imageUrl: String?
) : QImageGridItem

/**
 * QGrid组件预览
 */
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun QGridPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            QGridPreviewContent()
        }
    }
}

/**
 * 生成示例数据
 */
private fun getSampleData(): List<QGridItem> {
    return listOf(
        SampleGridItem(1, "卡片一", "这是第一张卡片的内容描述信息"),
        SampleGridItem(2, "卡片二", "这是第二张卡片的内容"),
        SampleGridItem(3, "卡片三", "较短内容"),
        SampleGridItem(4, "卡片四", "这是第四张卡片的内容描述信息，内容较长，可能会被截断"),
        SampleGridItem(5, "卡片五", "这是第五张卡片"),
        SampleGridItem(6, "卡片六", "这是第六张卡片的内容描述"),
        SampleGridItem(7, "卡片七", "这是第七张卡片的内容"),
        SampleGridItem(8, "卡片八", "这是第八张卡片的内容描述信息"),
        SampleGridItem(9, "卡片九", "这是第九张卡片的内容")
    )
}

/**
 * 生成分组示例数据
 */
private fun getGroupedSampleData(): Map<String, List<QGridItem>> {
    val items = getSampleData()
    return mapOf(
        "第一分组" to items.subList(0, 3),
        "第二分组" to items.subList(3, 6),
        "第三分组" to items.subList(6, 9)
    )
}

/**
 * 生成带图片的示例数据
 */
private fun getImageSampleData(): List<QImageGridItem> {
    // 示例图片URL，实际使用时应替换为真实图片URL
    val sampleImageUrls = listOf(
        "https://picsum.photos/id/237/200/300", // 狗
        "https://picsum.photos/id/433/200/300", // 熊
        "https://picsum.photos/id/1074/200/300", // 猫
        "https://picsum.photos/id/219/200/300", // 海滩
        "https://picsum.photos/id/244/200/300", // 树
        "https://picsum.photos/id/338/200/300", // 山
        "https://picsum.photos/id/164/200/300", // 海
        "https://picsum.photos/id/188/200/300", // 道路
        "https://picsum.photos/id/111/200/300"  // 花
    )
    
    return getSampleData().mapIndexed { index, item ->
        SampleImageGridItem(
            id = item.id as Int,
            title = item.title,
            content = item.content,
            imageUrl = sampleImageUrls[index]
        )
    }
}

/**
 * QGrid组件预览内容
 */
@Composable
fun QGridPreviewContent() {
    val basicItems = getSampleData()
    val groupedItems = getGroupedSampleData()
    val imageItems = getImageSampleData()
    
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Text(
                text = "像素风格网格组件示例",
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = XiangsutiFont,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        
        // 基础卡片网格
        item {
            Text(
                text = "基础卡片网格",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            QCardGrid(
                items = basicItems.take(4),
                columns = 2,
                contentPadding = PaddingValues(8.dp),
                title = "固定列数网格 (2列)",
                cardStyle = QCardGridStyle.DEFAULT,
                modifier = Modifier.height(280.dp)
            )
        }

        item {
            QCardGrid(
                items = basicItems.take(6),
                columnsMode = QGridColumnsMode.ADAPTIVE,
                minColumnWidth = 120.dp,
                contentPadding = PaddingValues(8.dp),
                title = "自适应列数网格 (最小宽度120dp)",
                cardStyle = QCardGridStyle.COLORED,
                modifier = Modifier.height(350.dp)
            )
        }

        // 带分组的卡片网格
        item {
            Text(
                text = "分组卡片网格",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            QGroupedCardGrid(
                groupedItems = groupedItems,
                columns = 2,
                contentPadding = PaddingValues(8.dp),
                title = "分组网格示例",
                cardStyle = QCardGridStyle.DEFAULT,
                modifier = Modifier.height(550.dp)
            )
        }

        // 方形卡片网格
        item {
            Text(
                text = "等比例卡片网格",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            QSquareCardGrid(
                items = basicItems.take(9),
                columns = 3,
                contentPadding = PaddingValues(4.dp),
                title = "等比例网格 (3列)",
                cardStyle = QCardGridStyle.OUTLINED,
                modifier = Modifier.height(350.dp)
            )
        }

        // 带边框的网格
        item {
            Text(
                text = "带边框的网格",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            QOutlinedGrid(
                title = "带边框的网格示例",
                columns = 2,
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.height(350.dp)
            ) {
                items(4) { index ->
                    QGridCard(
                        item = basicItems[index],
                        height = 100.dp,
                        cardStyle = QCardGridStyle.DEFAULT
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .shadow(
                                2.dp,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .border(
                                BorderStroke(
                                    2.dp,
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                                ),
                                RectangleShape
                            )
                            .padding(
                                vertical = 8.dp,
                                horizontal = 12.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "标题行",
                            fontFamily = XiangsutiFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                items(3) { index ->
                    QGridCard(
                        item = basicItems[index + 4],
                        height = 100.dp,
                        cardStyle = QCardGridStyle.COLORED
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                            .padding(vertical = 4.dp)
                    )
                }

                items(2) { index ->
                    QGridCard(
                        item = basicItems[index + 7],
                        height = 100.dp,
                        cardStyle = QCardGridStyle.ELEVATED
                    )
                }
            }
        }

        // 图片卡片网格
        item {
            Text(
                text = "图片卡片网格",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            QImageCardGrid(
                items = imageItems.take(4),
                columns = 2,
                contentPadding = PaddingValues(8.dp),
                title = "图片卡片网格示例",
                cardStyle = QCardGridStyle.DEFAULT,
                imageAspectRatio = 16f / 9f,
                showTitle = true,
                showContent = true,
                modifier = Modifier.height(400.dp)
            )
        }

        item {
            QImageCardGrid(
                items = imageItems.take(6),
                columnsMode = QGridColumnsMode.ADAPTIVE,
                minColumnWidth = 120.dp,
                contentPadding = PaddingValues(8.dp),
                title = "自适应图片网格",
                cardStyle = QCardGridStyle.COLORED,
                imageAspectRatio = 4f / 3f,
                showTitle = true,
                showContent = false,
                modifier = Modifier.height(350.dp)
            )
        }

        item {
            QPhotoGrid(
                items = imageItems.take(9),
                columns = 3,
                contentPadding = PaddingValues(4.dp),
                title = "照片墙示例",
                aspectRatio = 1f,
                showTitles = false,
                modifier = Modifier.height(350.dp)
            )
        }
    }
} 