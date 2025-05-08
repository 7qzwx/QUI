# QZWX 顶部导航栏组件库

QZWX 顶部导航栏组件库是像素风格 UI 组件库的一部分，提供一系列用于应用顶部导航的组件。这些组件保持了像素风格的视觉特征，如直角边框、明显的边框和阴影，同时提供现代的导航体验。

## 组件列表

1. `QTopBar` - 标准顶部导航栏，适用于大多数页面
2. `QCenterTopBar` - 居中标题的顶部导航栏，适用于需要强调标题的页面
3. `QLargeTopBar` - 大标题顶部导航栏，适用于详情页面或需要显示长标题的页面

## 组件用法

### QTopBar

标准顶部导航栏，提供左侧导航图标、中间标题和右侧操作按钮。

```kotlin
QTopBar(
    title = "主页",                         // 标题文本
    // 其他可选参数
    navigationIcon = Icons.Default.Menu,    // 左侧导航图标
    onNavigationClick = { /* 点击处理 */ }, // 导航图标点击回调
    actions = listOf(                       // 右侧操作按钮列表
        TopBarAction(
            icon = Icons.Default.Search,
            onClick = { /* 点击处理 */ },
            contentDescription = "搜索"
        ),
        TopBarAction(
            icon = Icons.Default.MoreVert,
            onClick = { /* 点击处理 */ },
            contentDescription = "更多"
        )
    ),
    variant = QTopBarVariant.PRIMARY        // 样式变体
)
```

### QCenterTopBar

居中标题的顶部导航栏，标题居中显示。

```kotlin
QCenterTopBar(
    title = "个人资料",                     // 标题文本
    // 其他可选参数
    navigationIcon = Icons.Default.ArrowBack, // 左侧导航图标
    onNavigationClick = { /* 点击处理 */ },   // 导航图标点击回调
    actions = listOf(                         // 右侧操作按钮列表
        TopBarAction(
            icon = Icons.Default.Settings,
            onClick = { /* 点击处理 */ },
            contentDescription = "设置"
        )
    ),
    variant = QTopBarVariant.PRIMARY          // 样式变体
)
```

### QLargeTopBar

大标题顶部导航栏，提供更大更突出的标题展示。

```kotlin
QLargeTopBar(
    title = "文章详情",                     // 标题文本
    // 其他可选参数
    navigationIcon = Icons.Default.ArrowBack, // 左侧导航图标
    onNavigationClick = { /* 点击处理 */ },   // 导航图标点击回调
    actions = listOf(                         // 右侧操作按钮列表
        TopBarAction(
            icon = Icons.Default.Share,
            onClick = { /* 点击处理 */ },
            contentDescription = "分享"
        ),
        TopBarAction(
            icon = Icons.Default.Favorite,
            onClick = { /* 点击处理 */ },
            contentDescription = "收藏"
        )
    ),
    variant = QTopBarVariant.PRIMARY          // 样式变体
)
```

## 样式变体

每种顶部栏都支持三种不同的样式变体，可以通过`variant`参数设置：

1. `QTopBarVariant.PRIMARY` - 使用主题的主要颜色（默认）
2. `QTopBarVariant.SECONDARY` - 使用主题的次要颜色
3. `QTopBarVariant.SURFACE` - 使用主题的表面颜色

## 操作按钮

通过`TopBarAction`数据类创建操作按钮，需要提供以下参数：

```kotlin
TopBarAction(
    icon = Icons.Default.Search,         // 按钮图标
    onClick = { /* 点击处理 */ },        // 点击回调
    contentDescription = "搜索"          // 无障碍描述
)
```

## 预设工具

`QTopBarDefaults`对象提供了一些常用的预设工具：

```kotlin
// 预设图标
val backIcon = QTopBarDefaults.BackIcon    // 返回图标
val menuIcon = QTopBarDefaults.MenuIcon    // 菜单图标
val moreIcon = QTopBarDefaults.MoreIcon    // 更多图标

// 预设操作按钮
val backAction = QTopBarDefaults.BackAction { /* 返回操作 */ }
val menuAction = QTopBarDefaults.MenuAction { /* 菜单操作 */ }
val moreAction = QTopBarDefaults.MoreAction { /* 更多操作 */ }
```

## 设计特点

- **像素风格**：所有组件都使用直角边框和像素风格设计，与QZWX UI库的其他组件保持视觉一致性
- **统一字体**：使用商务体(XiangsutiFont)确保视觉一致性
- **明显的边框**：使用2dp粗边框增强像素风格的外观
- **阴影效果**：4dp阴影增强立体感
- **主题适配**：自动适配应用的深色/浅色主题
- **多种变体**：提供多种颜色变体满足不同页面需求

## 使用建议

1. 在同一应用中尽量保持顶部栏样式的一致性
2. 根据页面内容和层级选择合适的顶部栏类型
3. 使用`QTopBar`作为大多数页面的默认选择
4. 使用`QCenterTopBar`突出显示页面标题
5. 使用`QLargeTopBar`展示详情页面或需要更多上下文信息的页面 