# QDivider 像素风格分隔符组件

QDivider是一套像素风格的分隔符组件，基于Material 3设计，但经过定制以适应像素游戏风格的UI。提供多种分隔线样式和变体，适用于不同的内容分隔场景。

## 特性

- 多种分隔线类型：实线、点线、虚线、像素点、锯齿等
- 支持带文字的分隔符
- 段落分隔和装饰分隔符
- 支持自定义颜色、粗细和缩进
- 完全适应深色/浅色主题

## 组件列表

### 1. QDivider - 基础分隔符

最基础的分隔符组件，支持多种分隔线样式。

```kotlin
QDivider(
    modifier = Modifier,                  // 修饰符
    type = QDividerType.SOLID,            // 分隔符类型
    thickness = 1.dp,                     // 线条粗细
    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), // 颜色
    startIndent = 0.dp,                   // 起始缩进
    endIndent = 0.dp                      // 结束缩进
)
```

分隔符类型（QDividerType）:
- SOLID: 实线分隔符
- DOTTED: 点线分隔符
- DASHED: 虚线分隔符
- PIXEL: 像素点分隔符
- ZIGZAG: 锯齿分隔符

### 2. QTextDivider - 带文字分隔符

中间带有文字标记的分隔符，适用于章节、部分标题等场景。

```kotlin
QTextDivider(
    text = "第一章",                      // 显示的文字
    modifier = Modifier,                  // 修饰符
    type = QDividerType.SOLID,            // 分隔符类型
    thickness = 1.dp,                     // 线条粗细
    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), // 线条颜色
    textColor = MaterialTheme.colorScheme.primary, // 文字颜色
    startIndent = 16.dp,                  // 起始缩进
    endIndent = 16.dp                     // 结束缩进
)
```

### 3. QSectionDivider - 段落分隔符

带有明显标记的段落分隔符，适用于内容分段。

```kotlin
QSectionDivider(
    modifier = Modifier,                  // 修饰符
    color = MaterialTheme.colorScheme.primary, // 颜色
    thickness = 2.dp,                     // 线条粗细
    startIndent = 16.dp,                  // 起始缩进
    sectionMarkWidth = 24.dp              // 段落标记宽度
)
```

### 4. QPixelDivider - 像素风格装饰分隔符

由小像素点组成的装饰性分隔符，增强像素风格视觉效果。

```kotlin
QPixelDivider(
    modifier = Modifier,                  // 修饰符
    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), // 颜色
    pixelSize = 2.dp,                     // 像素点大小
    startIndent = 16.dp,                  // 起始缩进
    endIndent = 16.dp                     // 结束缩进
)
```

## 使用场景

- **内容分隔**：使用基础分隔符分隔不同内容区域
- **章节标题**：使用带文字分隔符作为章节或段落标题
- **内容分组**：使用段落分隔符区分不同段落或内容组
- **装饰分隔**：使用像素分隔符作为装饰分隔线，增强界面像素风格

## 样式定制

分隔符组件使用MaterialTheme的colorScheme，因此会自动适应应用的主题设置。

主要使用的颜色包括：
- outline: 默认分隔线颜色（通常半透明）
- primary/secondary/tertiary: 可用于强调不同类型的分隔线
- surface: 带文字分隔符的文字背景色

## 设计指南

1. **一致性**：在同一界面中保持分隔符类型一致，除非有特殊强调需求
2. **颜色透明度**：普通分隔符建议使用半透明颜色，避免过于醒目
3. **间距**：分隔符周围保持足够的间距，通常上下各16dp以上
4. **粗细**：根据分隔内容的层级选择合适的粗细，层级越高粗细越大
5. **缩进**：考虑使用缩进增强层次感，特别是在列表中

## 无障碍支持

分隔符主要用于视觉分隔，不包含交互功能，因此无障碍性支持主要体现在：

- 分隔符颜色与背景有足够对比度
- 在带文字分隔符中提供清晰可读的文本
- 使用合适的大小和间距确保视觉可识别性 