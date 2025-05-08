# QSlider 像素风格滑动条组件

QSlider是一套像素风格的滑动条组件，基于Material 3设计，但经过定制以适应像素游戏风格的UI。所有滑动条组件都具有统一的视觉风格，包括直角边框、像素化元素和鲜明的色彩对比。

## 特性

- 像素风格：直角边框、无圆角、清晰的边缘
- 支持深色/浅色主题
- 响应式设计与无障碍支持
- 多种滑动条变体满足不同使用场景
- 可自定义值范围、步进和标签格式

## 组件列表

### 1. QSlider - 基础滑动条

最基础的滑动条组件，支持值标签显示和步进控制。

```kotlin
QSlider(
    value = sliderValue,                 // 当前值
    onValueChange = { sliderValue = it }, // 值变化回调
    modifier = Modifier,                  // 修饰符
    enabled = true,                       // 是否启用
    valueRange = 0f..1f,                  // 值范围
    steps = 0,                            // 步数
    showLabel = false,                    // 是否显示值标签
    labelFormat = { "%.0f".format(it) },  // 值格式化函数
    contentDescription = "基础滑动条"      // 无障碍描述
)
```

### 2. QRangeSlider - 带范围的滑动条

用于在特定范围内选择值的滑动条，如音量、亮度等。

```kotlin
QRangeSlider(
    value = sliderValue,                 // 当前值
    onValueChange = { sliderValue = it }, // 值变化回调
    valueRange = 0f..100f,                // 值范围
    steps = 10,                           // 步数
    showLabel = true,                     // 是否显示值标签
    labelFormat = { "${it.toInt()}" }     // 值格式化函数
)
```

### 3. QTickSlider - 带刻度的滑动条

带有刻度标记的滑动条，适用于需要精确选择特定值的场景。

```kotlin
QTickSlider(
    value = sliderValue,                 // 当前值
    onValueChange = { sliderValue = it }, // 值变化回调
    valueRange = 0f..100f,                // 值范围
    steps = 5,                            // 步数
    showValue = true,                     // 是否显示当前值
    showTickLabels = true,                // 是否显示刻度标签
    tickLabelStep = 1,                    // 刻度标签步长
    labelFormat = { "%.0f".format(it) }   // 值格式化函数
)
```

### 4. QVolumeSlider - 音量滑动条

专门用于音量控制的滑动条，默认范围为0-100，带百分比显示。

```kotlin
QVolumeSlider(
    value = volumeValue,                  // 当前音量值
    onValueChange = { volumeValue = it }, // 值变化回调
    enabled = true,                       // 是否启用
    contentDescription = "音量控制"        // 无障碍描述
)
```

### 5. QLevelSlider - 游戏等级滑动条

专门用于游戏等级选择的滑动条，默认显示为"LV.X"格式。

```kotlin
QLevelSlider(
    value = levelValue,                  // 当前等级值
    onValueChange = { levelValue = it }, // 值变化回调
    maxLevel = 20,                       // 最大等级
    enabled = true,                      // 是否启用
    contentDescription = "等级选择"       // 无障碍描述
)
```

## 设计特点

1. **像素风格滑块**：滑块采用方形设计，内部有像素十字图案
2. **直角边框**：所有元素都使用直角边框，没有圆角，增强像素感
3. **鲜明对比**：使用主题色彩系统，确保元素间有良好的色彩对比
4. **状态反馈**：不同状态（正常、禁用）有清晰的视觉差异
5. **刻度标记**：带刻度的滑动条使用像素风格的刻度线和标签

## 使用场景

- 音量/亮度控制
- 游戏设置（难度、速度等）
- 等级/进度选择
- 数值输入（年龄、数量等）
- 游戏内参数调整

## 无障碍支持

所有滑动条组件都支持无障碍功能：

- 通过contentDescription提供语音描述
- 支持键盘操作
- 禁用状态有明确的视觉差异
- 色彩对比度符合WCAG 2.1标准

## 自定义主题

滑动条组件使用MaterialTheme的colorScheme，因此会自动适应应用的主题设置。主要使用的颜色包括：

- primary: 滑块和已选择部分的颜色
- primaryContainer: 值标签背景色
- onPrimaryContainer: 值标签文字颜色
- outline: 边框颜色
- surfaceVariant: 轨道背景色

要自定义外观，可以在AppTheme中修改这些颜色。 