# QZWX 标签页组件库

QZWX 标签页组件库是像素风格 UI 组件库的一部分，提供用于创建标签页导航的组件。这些组件保持了像素风格的视觉特征，如直角边框、明显的边框和阴影，同时提供现代的标签页导航体验。

## 组件列表

1. `QTabs` - 标准标签页，适用于标签较少的场景
2. `QScrollableTabs` - 可滚动标签页，适用于标签较多的场景

## 组件用法

### QTabs

标准标签页，按比例平均分配宽度，适用于最多5个标签的场景。

```kotlin
val tabs = listOf("首页", "推荐", "关注", "热门")
var selectedTabIndex by remember { mutableStateOf(0) }

QTabs(
    tabs = tabs,                         // 标签列表
    selectedTabIndex = selectedTabIndex, // 当前选中标签的索引
    onTabSelected = { selectedTabIndex = it }, // 选择回调
    // 其他可选参数
    variant = QTabsVariant.PRIMARY       // 样式变体
)
```

### QScrollableTabs

可滚动标签页，适用于标签数量较多的场景，可以水平滚动查看所有标签。

```kotlin
val scrollableTabs = listOf(
    "全部", "游戏", "音乐", "电影", "动画", "美食", "旅游", 
    "科技", "体育", "教育", "时尚"
)
var selectedScrollableTab by remember { mutableStateOf(0) }

QScrollableTabs(
    tabs = scrollableTabs,                       // 标签列表
    selectedTabIndex = selectedScrollableTab,    // 当前选中标签的索引
    onTabSelected = { selectedScrollableTab = it }, // 选择回调
    // 其他可选参数
    variant = QTabsVariant.PRIMARY               // 样式变体
)
```

## 样式变体

每种标签页组件都支持三种不同的样式变体，可以通过`variant`参数设置：

1. `QTabsVariant.PRIMARY` - 使用主题的主要颜色（默认）
2. `QTabsVariant.SECONDARY` - 使用主题的次要颜色
3. `QTabsVariant.SURFACE` - 使用主题的表面颜色

## 设计特点

- **像素风格**：所有组件都使用直角边框和像素风格设计，与QZWX UI库的其他组件保持视觉一致性
- **统一字体**：使用商务体(XiangsutiFont)确保视觉一致性
- **明显的边框**：使用2dp粗边框增强像素风格的外观
- **阴影效果**：4dp阴影增强立体感
- **主题适配**：自动适配应用的深色/浅色主题
- **多种变体**：提供多种颜色变体满足不同应用需求
- **选中状态**：选中项有明显的视觉区分，包括背景色、边框、文字加粗等效果
- **指示器**：当前选中标签下方有明显的指示线

## 使用建议

1. 根据标签数量选择合适的组件：
   - 对于4个或更少的标签，使用`QTabs`
   - 对于5个或更多的标签，使用`QScrollableTabs`
2. 标签文本应简洁明了，避免过长的文本
3. 确保选中标签的视觉反馈明显，以提供良好的用户体验
4. 在使用`QTabs`时，请确保标签数量不要过多，以避免文本拥挤 