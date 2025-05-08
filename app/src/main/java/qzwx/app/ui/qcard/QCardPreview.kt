package qzwx.app.ui.qcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

@Preview(showBackground = true)
@Composable
fun QCardPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // 标准卡片示例
                Text(
                    text = "标准卡片 - Surface变体",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                QCard(
                    title = "Surface卡片",
                    variant = QCardVariant.SURFACE
                ) {
                    Text(
                        text = "这是一个基础的Surface卡片示例，使用默认的表面颜色。卡片内容可以根据需要自定义，支持任何Composable内容。",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = XiangsutiFont
                    )
                }
                
                Text(
                    text = "标准卡片 - Primary变体",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                
                QCard(
                    title = "Primary卡片",
                    variant = QCardVariant.PRIMARY
                ) {
                    Text(
                        text = "这是一个使用主色调的卡片示例。适合用于强调重要内容或主要操作区域。",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = XiangsutiFont
                    )
                }
                
                Text(
                    text = "标准卡片 - Secondary变体",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                
                QCard(
                    title = "Secondary卡片",
                    variant = QCardVariant.SECONDARY
                ) {
                    Text(
                        text = "这是一个使用次要色调的卡片示例。适合用于次要内容或辅助功能区域。",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontFamily = XiangsutiFont
                    )
                }
                
                // 轮廓卡片示例
                Text(
                    text = "轮廓卡片示例",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                
                QOutlinedCard(
                    title = "轮廓卡片",
                    variant = QCardVariant.PRIMARY
                ) {
                    Text(
                        text = "这是一个轮廓卡片示例，只有边框而没有填充背景色。适合用于次要信息或需要轻量化展示的内容。",
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = XiangsutiFont
                    )
                }
                
                // 头部卡片示例
                Text(
                    text = "带头部区域的卡片",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                
                QHeaderCard(
                    variant = QCardVariant.PRIMARY,
                    headerContent = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "星标",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "推荐内容",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontFamily = XiangsutiFont,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                ) {
                    Text(
                        text = "这是一个带有头部区域的卡片示例。头部区域使用主色调背景，适合用于分类展示内容或突出显示重要信息。",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontFamily = XiangsutiFont
                    )
                }
                
                QHeaderCard(
                    variant = QCardVariant.SECONDARY,
                    headerContent = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "收藏",
                                tint = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "用户收藏",
                                color = MaterialTheme.colorScheme.onSecondary,
                                fontFamily = XiangsutiFont,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                ) {
                    Text(
                        text = "这是一个使用次要色调的头部卡片示例。头部区域使用次要色调背景，适合用于次要分类或辅助信息展示。",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontFamily = XiangsutiFont
                    )
                }
                
                // 无标题卡片示例
                Text(
                    text = "无标题卡片",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                
                QCard(
                    variant = QCardVariant.SURFACE
                ) {
                    Text(
                        text = "这是一个没有标题的卡片示例。适合用于简单内容展示或不需要标题的情况。",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = XiangsutiFont,
                        textAlign = TextAlign.Center
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
} 