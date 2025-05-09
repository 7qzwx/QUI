# QGrid - 像素风格网格组件

QGrid是一组用于显示网格布局的像素风格组件，适用于展示卡片、图片等内容。这组组件保持统一的像素风格设计，包括直角边框、像素字体和鲜艳的色彩，符合复古游戏界面风格。

## 主要特性

- 像素风格设计：直角形状、鲜明边框、复古游戏色彩
- 支持固定列数或自适应列数模式
- 提供多种卡片样式：默认、轮廓、高阴影、彩色
- 支持分组显示内容
- 支持图片展示
- 支持自定义高度和比例

## 包含组件

1. `QGrid` - 基础网格组件
2. `QOutlinedGrid` - 带边框的网格组件
3. `QCardGrid` - 卡片网格组件
4. `QGroupedCardGrid` - 分组卡片网格组件
5. `QSquareCardGrid` - 等比例卡片网格组件
6. `QImageCardGrid` - 带图片的卡片网格组件
7. `QPhotoGrid` - 照片墙样式网格组件

## 数据模型

使用这些组件需要实现以下接口：

- `QGridItem` - 基本网格项数据接口
- `QImageGridItem` - 带图片的网格项数据接口

```kotlin
interface QGridItem {
    val id: Any
    val title: String
    val content: String? // 可以为空
}

interface QImageGridItem : QGridItem {
    val imageUrl: String? // 图片URL，可以为空
}
```

## 使用示例

### 基础卡片网格

```kotlin
// 创建数据
val items = listOf(
    MyGridItem(1, "标题1", "内容1"),
    MyGridItem(2, "标题2", "内容2"),
    MyGridItem(3, "标题3", "内容3"),
    MyGridItem(4, "标题4", "内容4")
)

// 使用组件
QCardGrid(
    items = items,
    columns = 2, // 2列布局
    contentPadding = PaddingValues(8.dp),
    title = "我的卡片", // 可选标题
    cardStyle = QCardGridStyle.DEFAULT, // 卡片样式
    modifier = Modifier.height(300.dp), // 指定高度非常重要
    onItemClick = { item -> 
        // 点击事件处理
    }
)
```

### 自适应列数网格

```kotlin
QCardGrid(
    items = items,
    columnsMode = QGridColumnsMode.ADAPTIVE, // 自适应列数
    minColumnWidth = 120.dp, // 每列最小宽度
    contentPadding = PaddingValues(8.dp),
    title = "自适应网格",
    cardStyle = QCardGridStyle.COLORED,
    modifier = Modifier.height(350.dp)
)
```

### 分组卡片网格

```kotlin
// 创建分组数据
val groupedItems = mapOf(
    "第一组" to items.subList(0, 2),
    "第二组" to items.subList(2, 4)
)

QGroupedCardGrid(
    groupedItems = groupedItems,
    columns = 2,
    contentPadding = PaddingValues(8.dp),
    title = "分组展示",
    cardStyle = QCardGridStyle.DEFAULT,
    modifier = Modifier.height(350.dp)
)
```

### 等比例卡片网格

```kotlin
QSquareCardGrid(
    items = items,
    columns = 3, // 3列布局
    contentPadding = PaddingValues(4.dp),
    title = "方形卡片",
    aspectRatio = 1f, // 正方形
    cardStyle = QCardGridStyle.OUTLINED,
    modifier = Modifier.height(300.dp)
)
```

### 图片卡片网格

```kotlin
// 创建带图片的数据
val imageItems = listOf(
    MyImageGridItem(1, "图片1", "描述1", "https://example.com/image1.jpg"),
    MyImageGridItem(2, "图片2", "描述2", "https://example.com/image2.jpg")
)

QImageCardGrid(
    items = imageItems,
    columns = 2,
    contentPadding = PaddingValues(8.dp),
    title = "图片卡片",
    cardStyle = QCardGridStyle.DEFAULT,
    imageAspectRatio = 16f / 9f, // 16:9比例
    showTitle = true,
    showContent = true,
    modifier = Modifier.height(400.dp)
)
```

### 照片墙

```kotlin
QPhotoGrid(
    items = imageItems,
    columns = 3,
    contentPadding = PaddingValues(4.dp),
    title = "照片墙",
    aspectRatio = 1f, // 正方形照片
    showTitles = false, // 不显示标题
    modifier = Modifier.height(350.dp)
)
```

### 自定义网格内容

```kotlin
QOutlinedGrid(
    title = "自定义网格",
    columns = 2,
    contentPadding = PaddingValues(8.dp),
    modifier = Modifier.height(350.dp)
) {
    // 这里可以自定义网格内容
    items(4) { index ->
        QGridCard(
            item = gridItems[index],
            height = 100.dp,
            cardStyle = QCardGridStyle.DEFAULT
        )
    }
    
    // 添加一个跨越整行的标题
    item(span = { GridItemSpan(maxLineSpan) }) {
        // 标题内容
    }
    
    // 添加更多项
    items(3) { index ->
        // 更多项内容
    }
}
```

## 注意事项

1. **必须提供高度约束**：使用这些组件时，请务必通过`Modifier.height()`或`Modifier.fillMaxHeight()`提供高度约束，否则可能导致布局错误。

2. **列数与内容**：根据实际需求选择合适的列数和内容量，以确保良好的显示效果。

3. **卡片样式**：有四种卡片样式可选：
   - `DEFAULT` - 标准样式，浅色背景
   - `OUTLINED` - 轮廓样式，突出边框
   - `ELEVATED` - 高阴影样式，强调立体感
   - `COLORED` - 彩色样式，使用主题的容器颜色

4. **图片加载**：图片加载使用Coil库，确保已添加相关依赖：
   ```
   implementation "io.coil-kt:coil-compose:2.4.0"
   ``` 