# Q风格按钮组件使用指南

Q风格按钮组件采用经典游戏界面设计，具有直角矩形边框、商务体字体(xiangsuti)、明显的边框和阴影等特点，营造出复古游戏的视觉效果。适合开发游戏风格的应用、像素艺术应用或任何需要复古感的界面。

## 按钮类型

### 1. QPrimaryButton - 主按钮

`QPrimaryButton` 是主按钮，用于最关键的操作，如开始游戏、确认操作等。通常使用鲜艳的蓝色背景，白色文本，以及明显的边框。相当于Material Design中的`Button`。

```kotlin
QPrimaryButton(
    text = "START",
    onClick = { /* 处理点击事件 */ },
    // 可选参数
    modifier = Modifier.fillMaxWidth(),
    enabled = true,  // 是否启用
    leadingIcon = Icons.Filled.Check  // 可选的前导图标
)
```

### 2. QSecondaryButton - 次要按钮

`QSecondaryButton` 是次要按钮，提供三种变体，用于各种次要操作。相当于Material Design中的`FilledTonalButton`、`OutlinedButton`或`ElevatedButton`。

#### QButtonVariant.NORMAL - 普通变体

```kotlin
QSecondaryButton(
    text = "SCENE",
    onClick = { /* 处理点击事件 */ },
    variant = QButtonVariant.NORMAL,  // 默认值，可省略
    // 其他可选参数
)
```

#### QButtonVariant.ACCENT - 强调色变体

```kotlin
QSecondaryButton(
    text = "LOAD",
    onClick = { /* 处理点击事件 */ },
    variant = QButtonVariant.ACCENT,
    // 其他可选参数
)
```

#### QButtonVariant.SURFACE - 表面色变体

```kotlin
QSecondaryButton(
    text = "OPTIONS",
    onClick = { /* 处理点击事件 */ },
    variant = QButtonVariant.SURFACE,
    // 其他可选参数
)
```

### 3. QIconButton - 图标按钮

`QIconButton` 是图标按钮，只包含一个图标，适用于工具栏、操作选项等。相当于Material Design中的`IconButton`。

```kotlin
QIconButton(
    icon = Icons.Filled.Settings,
    onClick = { /* 处理点击事件 */ },
    contentDescription = "设置",  // 无障碍描述，重要！
    // 可选参数
    tint = MaterialTheme.colorScheme.primary  // 图标颜色
)
```

## 设计特点

1. **矩形边框**：使用直角矩形边框，体现像素艺术风格
2. **明显的边框**：所有按钮都有2dp宽的边框，增强像素感
3. **商务体字体**：使用自定义的商务体字体(xiangsuti)，营造特殊的视觉效果
4. **大写文本**：按钮文本通常使用大写字母，符合复古游戏界面风格
5. **鲜艳的配色**：使用更饱和的颜色，模拟复古游戏的视觉效果

## 最佳实践

1. 按钮文本保持简短（通常5-10个字符），符合游戏界面习惯
2. 为所有图标按钮提供contentDescription以支持无障碍功能
3. 组合使用不同变体的按钮来创建层次感
4. 将主要操作放在界面最显眼的位置，如屏幕中央或底部

## 深色模式支持

所有Q风格按钮组件均支持深色模式，会自动使用深色主题颜色。深色模式下使用更深的蓝色背景，模拟夜间游戏界面。 