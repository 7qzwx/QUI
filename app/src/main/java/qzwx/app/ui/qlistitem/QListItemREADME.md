# QZWX 列表项组件库

QZWX 列表项组件库是像素风格 UI 组件库的一部分，提供一系列用于展示列表数据的组件。这些组件保持了像素风格的视觉特征，同时提供现代的列表交互体验。

## 组件列表

1. `QListItem` - 标准列表项组件，带有标题、可选的描述文本和图标
2. `QBorderedListItem` - 带边框的列表项组件，更加强调像素风格

## 组件用法

### QListItem

标准列表项组件，提供常见的列表项布局，支持前置图标、尾部图标、标题和描述文本。

```kotlin
QListItem(
    title = "个人资料",                     // 列表项标题
    subtitle = "查看和编辑您的个人信息",      // 可选的描述文本
    leadingIcon = Icons.Default.Person,   // 可选的前置图标
    trailingIcon = Icons.Default.KeyboardArrowRight, // 可选的尾部图标
    leadingIconTint = MaterialTheme.colorScheme.primary, // 前置图标颜色
    trailingIconTint = MaterialTheme.colorScheme.onSurfaceVariant, // 尾部图标颜色
    onClick = { /* 点击处理 */ },          // 可选的点击回调
    variant = QListItemVariant.SURFACE,   // 样式变体
    showDivider = true,                  // 是否显示底部分隔线
    modifier = Modifier.fillMaxWidth()    // 可选的修饰符
)
```

### QBorderedListItem

带边框的列表项组件，提供明显的边框轮廓，更加突出像素风格。

```kotlin
QBorderedListItem(
    title = "个人资料",                     // 列表项标题
    subtitle = "查看和编辑您的个人信息",      // 可选的描述文本
    leadingIcon = Icons.Default.Person,   // 可选的前置图标
    trailingIcon = Icons.Default.KeyboardArrowRight, // 可选的尾部图标
    leadingIconTint = MaterialTheme.colorScheme.primary, // 前置图标颜色
    trailingIconTint = MaterialTheme.colorScheme.onSurfaceVariant, // 尾部图标颜色
    onClick = { /* 点击处理 */ },          // 可选的点击回调
    variant = QListItemVariant.SURFACE,   // 样式变体
    borderWidth = 2f,                     // 边框宽度
    modifier = Modifier.padding(horizontal = 16.dp) // 可选的修饰符
)
```

## 样式变体

每种列表项组件都支持三种不同的样式变体，可以通过`variant`参数设置：

1. `QListItemVariant.PRIMARY` - 使用主题的主要颜色
2. `QListItemVariant.SECONDARY` - 使用主题的次要颜色
3. `QListItemVariant.SURFACE` - 使用主题的表面颜色（默认）

## 设计特点

- **像素风格**：组件使用直角边框和像素风格设计，与QZWX UI库的其他组件保持视觉一致性
- **统一字体**：使用商务体(XiangsutiFont)确保视觉一致性
- **明显的边框**：`QBorderedListItem`使用2dp粗边框增强像素风格的外观
- **多种变体**：提供多种颜色变体满足不同应用需求
- **无波纹效果**：移除Material默认的波纹效果，保持像素风格的简洁
- **灵活配置**：支持自定义图标、文本、点击事件等多种配置

## 使用建议

1. 根据内容重要性选择合适的列表项变体：
   - 对于重要或突出的列表项，使用`QListItemVariant.PRIMARY`
   - 对于次要分类或辅助功能，使用`QListItemVariant.SECONDARY`
   - 对于一般内容，使用`QListItemVariant.SURFACE`
2. 在长列表中使用`QListItem`并保留分隔线，以便清晰区分各个项目
3. 对于独立或特殊的列表项，使用`QBorderedListItem`增强视觉区分度
4. 标题文本应简洁明了，描述文本应提供有价值的补充信息
5. 合理使用前置图标和尾部图标：
   - 前置图标通常用于表示列表项的类型或分类
   - 尾部图标通常用于指示操作（如前进箭头）或状态（如勾选标记） 