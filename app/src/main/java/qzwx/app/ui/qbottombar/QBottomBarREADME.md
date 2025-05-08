# QZWX 底部导航栏组件库

QZWX 底部导航栏组件库是像素风格 UI 组件库的一部分，提供一系列用于应用底部导航的组件。这些组件保持了像素风格的视觉特征，如直角边框、明显的边框和阴影，同时提供现代的导航体验。

## 组件列表

1. `QBottomBar` - 标准底部导航栏，带图标和文本标签
2. `QBottomBarCompact` - 紧凑型底部导航栏，仅显示图标
3. `QBadgeBottomBar` - 带徽章的底部导航栏，支持显示未读消息数量

## 组件用法

### QBottomBar

标准底部导航栏，提供图标和文本标签。

```kotlin
val items = listOf(
    BottomBarItem(icon = Icons.Default.Home, label = "首页"),
    BottomBarItem(icon = Icons.Default.Favorite, label = "收藏"),
    BottomBarItem(icon = Icons.Default.Notifications, label = "通知"),
    BottomBarItem(icon = Icons.Default.AccountCircle, label = "我的")
)

var selectedIndex by remember { mutableStateOf(0) }

QBottomBar(
    items = items,                        // 导航项列表
    selectedItemIndex = selectedIndex,    // 当前选中项索引
    onItemSelected = { selectedIndex = it }, // 选择回调
    // 其他可选参数
    showLabels = true,                    // 是否显示文本标签
    variant = QBottomBarVariant.PRIMARY   // 样式变体
)
```

### QBottomBarCompact

紧凑型底部导航栏，只显示图标，不显示文本标签。

```kotlin
QBottomBarCompact(
    items = items,                        // 导航项列表
    selectedItemIndex = selectedIndex,    // 当前选中项索引
    onItemSelected = { selectedIndex = it }, // 选择回调
    // 其他可选参数
    variant = QBottomBarVariant.PRIMARY   // 样式变体
)
```

### QBadgeBottomBar

带徽章的底部导航栏，可以显示未读消息数量等。

```kotlin
val badgeItems = listOf(
    BottomBarItemWithBadge(icon = Icons.Default.Home, label = "首页", badgeCount = 0),
    BottomBarItemWithBadge(icon = Icons.Default.Favorite, label = "收藏", badgeCount = 5),
    BottomBarItemWithBadge(icon = Icons.Default.Notifications, label = "通知", badgeCount = 99),
    BottomBarItemWithBadge(icon = Icons.Default.Settings, label = "设置", badgeCount = 999)
)

QBadgeBottomBar(
    items = badgeItems,                   // 带徽章的导航项列表
    selectedItemIndex = selectedIndex,    // 当前选中项索引
    onItemSelected = { selectedIndex = it }, // 选择回调
    // 其他可选参数
    variant = QBottomBarVariant.PRIMARY   // 样式变体
)
```

## 数据类

### BottomBarItem

底部导航栏项目的数据类。

```kotlin
data class BottomBarItem(
    val icon: ImageVector,    // 导航图标
    val label: String         // 文本标签
)
```

### BottomBarItemWithBadge

带徽章的底部导航栏项目数据类。

```kotlin
data class BottomBarItemWithBadge(
    val icon: ImageVector,    // 导航图标
    val label: String,        // 文本标签
    val badgeCount: Int = 0   // 徽章数量（0表示不显示）
)
```

## 样式变体

每种底部导航栏都支持三种不同的样式变体，可以通过`variant`参数设置：

1. `QBottomBarVariant.PRIMARY` - 使用主题的主要颜色（默认）
2. `QBottomBarVariant.SECONDARY` - 使用主题的次要颜色
3. `QBottomBarVariant.SURFACE` - 使用主题的表面颜色

## 设计特点

- **像素风格**：所有组件都使用直角边框和像素风格设计，与QZWX UI库的其他组件保持视觉一致性
- **统一字体**：使用商务体(XiangsutiFont)确保视觉一致性
- **明显的边框**：使用2dp粗边框增强像素风格的外观
- **阴影效果**：4dp阴影增强立体感
- **主题适配**：自动适配应用的深色/浅色主题
- **多种变体**：提供多种颜色变体满足不同应用需求
- **徽章支持**：支持显示未读消息数量等状态信息
- **选中状态**：选中项有明显的视觉区分

## 使用建议

1. 在整个应用中保持底部导航栏样式的一致性
2. 底部导航栏中的项目不应超过5个，以确保每个项目有足够的点击区域
3. 对于较小的屏幕，可以考虑使用`QBottomBarCompact`以节省空间
4. 使用`QBadgeBottomBar`时，避免所有项目都显示徽章，这会使界面显得杂乱
5. 徽章数量较大时（超过99），会自动显示为"99+"，以保持视觉整洁 