# QLoading 加载组件

QLoading是一套像素风格的加载和进度指示器组件，具有多种样式和丰富的自定义选项。

## 组件特点

- 像素风格的视觉效果
- 支持多种加载动画样式
- 可自定义颜色、大小、边框等
- 支持深色/浅色主题自动适配
- 内置进度条和加载覆盖层功能

## 组件列表

1. `QLoading` - 基础加载组件，支持多种样式和大小
2. `QProgressBar` - 可显示明确进度的进度条
3. `QProgressPanel` - 带标签的进度条面板
4. `QLoadingOverlay` - 加载状态覆盖层

## 使用方法

### 1. QLoading 基础加载指示器

```kotlin
// 基础用法
QLoading(
    style = QLoadingStyle.CIRCULAR,  // 样式：CIRCULAR, PIXEL, BAR, DOTS
    size = QLoadingSize.MEDIUM,      // 大小：SMALL, MEDIUM, LARGE
    color = MaterialTheme.colorScheme.primary,  // 主色调
    backgroundColor = MaterialTheme.colorScheme.surface,  // 背景色
    borderWidth = 2.dp,  // 边框宽度
    borderColor = MaterialTheme.colorScheme.outline,  // 边框颜色
    text = "加载中..."  // 可选文本
)
```

### 2. QProgressBar 进度条

```kotlin
// 基础进度条
QProgressBar(
    progress = 0.75f,  // 进度值 (0.0 - 1.0)
    height = 16.dp,    // 高度
    showPercentage = true,  // 是否显示百分比
    progressColor = MaterialTheme.colorScheme.primary,  // 进度颜色
    backgroundColor = MaterialTheme.colorScheme.surface  // 背景色
)
```

### 3. QProgressPanel 带标签的进度条

```kotlin
// 带标签的进度条
QProgressPanel(
    progress = 0.5f,
    label = "下载中...",
    progressColor = MaterialTheme.colorScheme.primary,
    showPercentage = true
)
```

### 4. QLoadingOverlay 加载覆盖层

```kotlin
// 带加载覆盖层的内容
QLoadingOverlay(
    isLoading = viewModel.isLoading,  // 是否显示加载状态
    style = QLoadingStyle.CIRCULAR,   // 加载器样式
    text = "正在加载数据..."           // 加载提示文本
) {
    // 内容区域
    YourContentComposable()
}
```

## 参数说明

### QLoading

| 参数 | 类型 | 默认值 | 说明 |
|------|------|-------|------|
| style | QLoadingStyle | CIRCULAR | 加载动画样式 |
| size | QLoadingSize | MEDIUM | 加载动画大小 |
| color | Color | MaterialTheme.colorScheme.primary | 主色调 |
| backgroundColor | Color | MaterialTheme.colorScheme.surface | 背景色 |
| borderWidth | Dp | 2.dp | 边框宽度 |
| borderColor | Color | MaterialTheme.colorScheme.outline | 边框颜色 |
| text | String? | null | 显示文本 |
| modifier | Modifier | Modifier | 修饰符 |

### QProgressBar

| 参数 | 类型 | 默认值 | 说明 |
|------|------|-------|------|
| progress | Float | - | 进度值 (0.0 - 1.0) |
| height | Dp | 16.dp | 进度条高度 |
| backgroundColor | Color | MaterialTheme.colorScheme.surface | 背景色 |
| progressColor | Color | MaterialTheme.colorScheme.primary | 进度颜色 |
| borderWidth | Dp | 2.dp | 边框宽度 |
| borderColor | Color | MaterialTheme.colorScheme.outline | 边框颜色 |
| showPercentage | Boolean | false | 是否显示百分比 |

### QLoadingOverlay

| 参数 | 类型 | 默认值 | 说明 |
|------|------|-------|------|
| isLoading | Boolean | - | 是否显示加载状态 |
| style | QLoadingStyle | CIRCULAR | 加载动画样式 |
| color | Color | MaterialTheme.colorScheme.primary | 主色调 |
| backgroundColor | Color | MaterialTheme.colorScheme.surface.copy(alpha = 0.9f) | 背景色 |
| text | String? | "加载中..." | 加载提示文本 |
| content | @Composable () -> Unit | - | 内容区域 |

## 样式枚举

### QLoadingStyle

- `CIRCULAR` - 圆形进度动画
- `PIXEL` - 像素点动画
- `BAR` - 条形进度动画
- `DOTS` - 点阵动画

### QLoadingSize

- `SMALL` - 小尺寸 (32.dp)
- `MEDIUM` - 中等尺寸 (48.dp)
- `LARGE` - 大尺寸 (64.dp) 