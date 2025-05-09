# QChip 像素风格标签组件

QChip 是一个具有像素风格的标签组件库，提供了丰富的样式变体和功能，适用于各种场景的标签、徽章和状态指示。所有组件都保持了像素风格设计特点：直角边框、明显的边框线条、像素风字体和适当的阴影效果。

## 设计特点

- **像素风格**：直角边框、2dp边框宽度、阴影效果，符合像素游戏视觉风格
- **多种变体**：支持8种颜色变体，满足不同场景需求
- **多种形状**：矩形、圆角矩形、像素边缘
- **交互支持**：支持点击事件，可用于筛选、标记和导航
- **图标支持**：支持前置图标、后置图标或两侧都有图标
- **状态管理**：支持选中/未选中、启用/禁用状态
- **徽章变体**：提供计数徽章和像素风格徽章
- **响应式设计**：自动适配深色/浅色主题
- **多种尺寸**：小、中、大三种尺寸

## 组件列表

1. **QChip** - 基础标签组件，支持多种样式和交互
2. **QBadge** - 数字徽章组件，用于显示计数或状态
3. **QPixelBadge** - 像素风格徽章，用于显示成就或特殊标记

## 基础用法

### 1. 基础标签 (QChip)

```kotlin
// 基础标签
QChip(
    text = "基础标签",
    modifier = Modifier
)

// 可点击标签
QChip(
    text = "可点击标签",
    onClick = { /* 处理点击事件 */ }
)

// 不可用标签
QChip(
    text = "禁用标签",
    enabled = false
)

// 选中状态标签
QChip(
    text = "选中标签",
    selected = true
)
```

### 2. 带图标的标签

```kotlin
// 前置图标标签
QChip(
    text = "前置图标",
    leadingIcon = Icons.Default.Star
)

// 后置图标标签
QChip(
    text = "后置图标",
    trailingIcon = Icons.Default.Close
)

// 两侧图标标签
QChip(
    text = "两侧图标",
    leadingIcon = Icons.Default.Favorite,
    trailingIcon = Icons.Default.Done
)
```

### 3. 不同样式变体

```kotlin
// 主色调标签
QChip(
    text = "主色标签",
    variant = QChipVariant.PRIMARY
)

// 次要色调标签
QChip(
    text = "次色标签",
    variant = QChipVariant.SECONDARY
)

// 成功标签
QChip(
    text = "成功",
    variant = QChipVariant.SUCCESS,
    leadingIcon = Icons.Default.Check
)

// 警告标签
QChip(
    text = "警告",
    variant = QChipVariant.WARNING,
    leadingIcon = Icons.Default.Warning
)

// 错误标签
QChip(
    text = "错误",
    variant = QChipVariant.ERROR,
    leadingIcon = Icons.Default.Error
)

// 信息标签
QChip(
    text = "信息",
    variant = QChipVariant.INFO,
    leadingIcon = Icons.Default.Info
)

// 边框样式标签
QChip(
    text = "边框",
    variant = QChipVariant.OUTLINE
)

// 表面样式标签
QChip(
    text = "表面",
    variant = QChipVariant.SURFACE
)
```

### 4. 不同尺寸标签

```kotlin
// 小尺寸标签
QChip(
    text = "小标签",
    size = QChipSize.SMALL
)

// 中等尺寸标签
QChip(
    text = "中标签",
    size = QChipSize.MEDIUM
)

// 大尺寸标签
QChip(
    text = "大标签",
    size = QChipSize.LARGE
)
```

### 5. 不同形状标签

```kotlin
// 矩形标签
QChip(
    text = "矩形",
    shape = QChipShape.RECTANGLE
)

// 圆角标签
QChip(
    text = "圆角",
    shape = QChipShape.ROUNDED
)

// 像素边缘标签
QChip(
    text = "像素边缘",
    shape = QChipShape.PIXEL
)
```

### 6. 徽章组件 (QBadge)

```kotlin
// 基础数字徽章
QBadge(
    count = 5
)

// 带颜色变体的徽章
QBadge(
    count = 25,
    variant = QChipVariant.SECONDARY
)

// 超出最大值的徽章
QBadge(
    count = 100,  // 将显示为 "99+"
    variant = QChipVariant.ERROR
)

// 图标徽章
QBadge(
    count = 0,
    icon = Icons.Default.Notifications,
    variant = QChipVariant.INFO
)
```

### 7. 像素风格徽章 (QPixelBadge)

```kotlin
// 基础像素徽章
QPixelBadge(
    icon = Icons.Default.Star
)

// 自定义颜色徽章
QPixelBadge(
    icon = Icons.Default.Favorite,
    backgroundColor = MaterialTheme.colorScheme.secondary
)

// 自定义尺寸徽章
QPixelBadge(
    icon = Icons.Default.Add,
    backgroundColor = MaterialTheme.colorScheme.tertiary,
    size = 48.dp
)
```

## 交互式标签示例

```kotlin
// 在Composable函数中
var selected by remember { mutableStateOf(false) }

QChip(
    text = if (selected) "已选择" else "未选择",
    selected = selected,
    onClick = { selected = !selected },
    leadingIcon = if (selected) Icons.Default.Check else null
)
```

## 用途建议

- **信息标记**：使用标签标识内容类型或状态
- **筛选选项**：用作筛选器的可选项
- **标签云**：使用多个标签组成标签云
- **状态指示**：使用不同颜色和图标表示不同状态
- **计数显示**：使用QBadge显示通知或消息计数
- **成就标志**：使用QPixelBadge作为游戏中的成就或标志

## 样式定制

标签组件使用MaterialTheme的colorScheme，因此会自动适应应用的主题设置。

主要使用的颜色包括：
- primary/secondary: 用于标签的主体颜色
- onPrimary/onSecondary: 用于标签上的文字颜色
- outline: 用于边框样式标签的边框颜色
- surface/surfaceVariant: 用于表面样式标签的背景色
- onSurface: 用于表面样式标签的文字颜色

## 最佳实践

1. **一致性**：在同一界面中使用相同变体的标签，保持视觉一致性
2. **简洁文本**：标签文本应简短明了，通常不超过3个词
3. **有意义的图标**：只在图标能增强理解时使用图标
4. **正确的变体**：根据语义选择合适的颜色变体
5. **适当的尺寸**：根据上下文选择合适的尺寸，通常在列表中使用小标签，在突出显示时使用大标签
6. **交互反馈**：为可点击标签提供明确的视觉反馈（selected状态） 