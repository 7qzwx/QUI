# QZWX 卡片组件库

QZWX 卡片组件库是像素风格 UI 组件库的一部分，提供一系列用于内容展示和分组的卡片组件。这些组件保持了像素风格的视觉特征，如直角边框、明显的边框和阴影，同时提供灵活的内容布局选项。

## 组件列表

1. `QCard` - 标准卡片组件，带有标题和内容区域
2. `QOutlinedCard` - 轮廓卡片组件，仅有边框而无背景色
3. `QHeaderCard` - 带头部区域的卡片组件，顶部有特殊背景色的区域

## 组件用法

### QCard

标准卡片组件，提供基本的卡片布局，包含可选的标题和内容区域。

```kotlin
QCard(
    title = "卡片标题",                     // 可选的卡片标题
    variant = QCardVariant.SURFACE,       // 样式变体
    elevation = 4.dp,                     // 阴影高度
    borderWidth = 2.dp,                   // 边框宽度
    modifier = Modifier.fillMaxWidth()    // 可选的修饰符
) {
    // 卡片内容，可以是任何Composable
    Text(
        text = "这是卡片内容",
        color = MaterialTheme.colorScheme.onSurface,
        fontFamily = XiangsutiFont
    )
}
```

### QOutlinedCard

轮廓卡片组件，只有边框而无背景色，适合轻量化展示内容。

```kotlin
QOutlinedCard(
    title = "轮廓卡片",                    // 可选的卡片标题
    variant = QCardVariant.PRIMARY,       // 样式变体
    borderWidth = 2.dp,                   // 边框宽度
    modifier = Modifier.fillMaxWidth()    // 可选的修饰符
) {
    // 卡片内容，可以是任何Composable
    Text(
        text = "这是轮廓卡片内容",
        color = MaterialTheme.colorScheme.primary,
        fontFamily = XiangsutiFont
    )
}
```

### QHeaderCard

带头部区域的卡片组件，上部有特殊背景色的区域，适合分类展示内容。

```kotlin
QHeaderCard(
    variant = QCardVariant.PRIMARY,       // 样式变体
    elevation = 4.dp,                     // 阴影高度
    borderWidth = 2.dp,                   // 边框宽度
    modifier = Modifier.fillMaxWidth(),   // 可选的修饰符
    headerContent = {
        // 头部区域内容，可以是任何Composable
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
    // 卡片主体内容，可以是任何Composable
    Text(
        text = "这是带头部区域的卡片内容",
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontFamily = XiangsutiFont
    )
}
```

## 样式变体

每种卡片组件都支持三种不同的样式变体，可以通过`variant`参数设置：

1. `QCardVariant.PRIMARY` - 使用主题的主要颜色
2. `QCardVariant.SECONDARY` - 使用主题的次要颜色
3. `QCardVariant.SURFACE` - 使用主题的表面颜色（默认）

## 设计特点

- **像素风格**：所有组件都使用直角边框和像素风格设计，与QZWX UI库的其他组件保持视觉一致性
- **统一字体**：使用商务体(XiangsutiFont)确保视觉一致性
- **明显的边框**：使用2dp粗边框增强像素风格的外观
- **阴影效果**：4dp默认阴影增强立体感
- **主题适配**：自动适配应用的深色/浅色主题
- **多种变体**：提供多种颜色变体满足不同应用需求
- **灵活布局**：支持多种卡片布局方式，包括标准、轮廓和带头部区域的卡片

## 使用建议

1. 根据内容重要性选择合适的卡片变体：
   - 对于重要内容，使用`QCardVariant.PRIMARY`
   - 对于次要内容，使用`QCardVariant.SECONDARY`
   - 对于一般内容，使用`QCardVariant.SURFACE`
2. 标题设置应简洁明了，避免过长的标题
3. 卡片内容可以包含任何Composable组件，但建议保持内容简洁和结构化
4. 使用`QHeaderCard`时，头部区域应突出显示分类或主题信息
5. 使用`QOutlinedCard`时，适合用于非重点内容或需要轻量化展示的场景 