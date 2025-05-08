package qzwx.app.ui.qbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import qzwx.app.ui.theme.AppTheme
import qzwx.app.ui.theme.XiangsutiFont

/**
 * QButtonPreview - 按钮组件预览
 * 展示所有Q风格按钮组件的样式和用法
 */
@Preview(showBackground = true)
@Composable
fun QButtonPreview() {
    AppTheme {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Q风格标题
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Q风格按钮预览",
                        fontFamily = XiangsutiFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                // 主要按钮
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "QPrimaryButton - 主要按钮",
                        fontFamily = XiangsutiFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    QPrimaryButton(
                        text = "START",
                        onClick = { /* TODO */ }
                    )
                    
                    QPrimaryButton(
                        text = "SAVE GAME",
                        onClick = { /* TODO */ },
                        leadingIcon = Icons.Default.Check
                    )
                    
                    QPrimaryButton(
                        text = "DISABLED",
                        onClick = { /* TODO */ },
                        enabled = false
                    )
                }
                
                // 次要按钮 - 普通
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "QSecondaryButton - 次要按钮(普通)",
                        fontFamily = XiangsutiFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    QSecondaryButton(
                        text = "SCENE",
                        onClick = { /* TODO */ },
                        variant = QButtonVariant.NORMAL
                    )
                    
                    QSecondaryButton(
                        text = "SETTING",
                        onClick = { /* TODO */ },
                        leadingIcon = Icons.Default.Settings,
                        variant = QButtonVariant.NORMAL
                    )
                }
                
                // 次要按钮 - 强调色
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "QSecondaryButton - 次要按钮(强调色)",
                        fontFamily = XiangsutiFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    QSecondaryButton(
                        text = "LOAD",
                        onClick = { /* TODO */ },
                        variant = QButtonVariant.ACCENT
                    )
                    
                    QSecondaryButton(
                        text = "NEW GAME",
                        onClick = { /* TODO */ },
                        leadingIcon = Icons.Default.Add,
                        variant = QButtonVariant.ACCENT
                    )
                }
                
                // 次要按钮 - 表面色
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "QSecondaryButton - 次要按钮(表面色)",
                        fontFamily = XiangsutiFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    QSecondaryButton(
                        text = "OPTIONS",
                        onClick = { /* TODO */ },
                        variant = QButtonVariant.SURFACE
                    )
                    
                    QSecondaryButton(
                        text = "INVENTORY",
                        onClick = { /* TODO */ },
                        leadingIcon = Icons.Default.Home,
                        variant = QButtonVariant.SURFACE
                    )
                    
                    QSecondaryButton(
                        text = "DISABLED",
                        onClick = { /* TODO */ },
                        variant = QButtonVariant.SURFACE,
                        enabled = false
                    )
                }
                
                // 图标按钮
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "QIconButton - 图标按钮",
                        fontFamily = XiangsutiFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        QIconButton(
                            icon = Icons.Default.Settings,
                            onClick = { /* TODO */ },
                            contentDescription = "设置"
                        )
                        
                        QIconButton(
                            icon = Icons.Default.Add,
                            onClick = { /* TODO */ },
                            contentDescription = "添加"
                        )
                        
                        QIconButton(
                            icon = Icons.Default.Home,
                            onClick = { /* TODO */ },
                            contentDescription = "主页"
                        )
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        QIconButton(
                            icon = Icons.Default.Settings,
                            onClick = { /* TODO */ },
                            enabled = false,
                            contentDescription = "禁用状态"
                        )
                    }
                }
            }
        }
    }
} 