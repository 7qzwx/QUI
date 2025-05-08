# QDialog 像素风格对话框组件

QDialog 是一个具有像素风格的对话框组件，提供了多种样式和变体，适用于不同的交互场景。所有对话框都保持了像素风格设计特点：直角边框、明显的边框线条、商务体字体和适当的阴影效果。

## 设计特点

- **直角边框**：所有对话框都采用直角设计，符合像素风格游戏的视觉特点
- **明显的边框**：具有2dp的边框，增强像素风格效果
- **色彩鲜明**：根据不同变体使用不同的配色方案
- **顶部标题区域**：每个对话框都有明显区分的标题区域
- **适配主题**：自动适配深色/浅色主题
- **多种变体**：支持PRIMARY、SECONDARY、SURFACE三种样式变体
- **一致的按钮布局**：确认对话框和输入对话框的按钮采用等宽设计，高度固定为48dp，确保视觉一致性
- **美观的输入框**：输入对话框中的输入框经过特别美化，带有阴影和双重边框，并根据对话框变体调整样式

## 基础用法

### 基础对话框 (QDialog)

基础对话框提供了最大的自定义灵活性，可以自由定制内容区域。建议将关闭按钮放在右侧以保持交互一致性。

```kotlin
var showDialog by remember { mutableStateOf(false) }

if (showDialog) {
    QDialog(
        visible = true,
        onDismiss = { showDialog = false },
        title = "对话框标题",
        variant = QDialogVariant.SURFACE  // 可选：PRIMARY, SECONDARY, SURFACE
    ) {
        Text(
            text = "这是对话框的内容区域，可以放置任何Composable组件。",
            fontFamily = XiangsutiFont,
            style = MaterialTheme.typography.bodyLarge
        )
        
        // 推荐将按钮放在右侧
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            QPrimaryButton(
                text = "关闭",
                onClick = { showDialog = false }
            )
        }
    }
}

// 显示对话框
Button(onClick = { showDialog = true }) {
    Text("显示对话框")
}
```

### 确认对话框 (QConfirmDialog)

确认对话框用于需要用户确认某个操作的场景，提供了确认和取消两个按钮。按钮采用等宽设计，确保视觉平衡，并固定高度为48dp。

```kotlin
var showConfirmDialog by remember { mutableStateOf(false) }

if (showConfirmDialog) {
    QConfirmDialog(
        visible = true,
        title = "确认操作",
        message = "您确定要执行此操作吗？此操作无法撤销。",
        confirmText = "确认",  // 可选，默认为"确认"
        cancelText = "取消",   // 可选，默认为"取消"
        variant = QDialogVariant.PRIMARY,  // 可选：PRIMARY, SECONDARY, SURFACE
        onConfirm = { 
            // 处理确认逻辑
            showConfirmDialog = false 
        },
        onDismiss = { 
            // 处理取消逻辑
            showConfirmDialog = false 
        }
    )
}

// 显示确认对话框
Button(onClick = { showConfirmDialog = true }) {
    Text("显示确认对话框")
}
```

### 输入对话框 (QInputDialog)

输入对话框包含一个输入字段，用于收集用户输入的场景。按钮布局与确认对话框保持一致，采用等宽设计。输入框经过特别美化，带有阴影和双重边框，并根据对话框变体调整样式。

```kotlin
var showInputDialog by remember { mutableStateOf(false) }

if (showInputDialog) {
    QInputDialog(
        visible = true,
        title = "请输入",
        message = "请输入您的用户名：",  // 可选
        placeholder = "用户名",        // 可选
        initialValue = "",            // 可选，默认为空字符串
        confirmText = "确认",          // 可选，默认为"确认"
        cancelText = "取消",           // 可选，默认为"取消"
        variant = QDialogVariant.SECONDARY,  // 可选：PRIMARY, SECONDARY, SURFACE
        onConfirm = { inputValue -> 
            // 处理输入值
            println("用户输入：$inputValue")
            showInputDialog = false 
        },
        onDismiss = { 
            // 处理取消逻辑
            showInputDialog = false 
        }
    )
}

// 显示输入对话框
Button(onClick = { showInputDialog = true }) {
    Text("显示输入对话框")
}
```

## 属性说明

### QDialog

| 属性名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| visible | Boolean | - | 控制对话框的显示和隐藏 |
| onDismiss | () -> Unit | - | 对话框被关闭时的回调 |
| title | String | - | 对话框标题 |
| variant | QDialogVariant | SURFACE | 对话框样式变体 |
| dismissOnClickOutside | Boolean | true | 点击外部是否关闭对话框 |
| dismissOnBackPress | Boolean | true | 按返回键是否关闭对话框 |
| elevation | Dp | 8.dp | 对话框阴影大小 |
| borderWidth | Dp | 2.dp | 对话框边框宽度 |
| content | @Composable ColumnScope.() -> Unit | - | 对话框内容区域 |

### QConfirmDialog

| 属性名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| visible | Boolean | - | 控制对话框的显示和隐藏 |
| title | String | - | 对话框标题 |
| message | String | - | 对话框消息内容 |
| confirmText | String | "确认" | 确认按钮文本 |
| cancelText | String | "取消" | 取消按钮文本 |
| variant | QDialogVariant | SURFACE | 对话框样式变体 |
| onConfirm | () -> Unit | - | 点击确认按钮的回调 |
| onDismiss | () -> Unit | - | 点击取消按钮或关闭对话框的回调 |

### QInputDialog

| 属性名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| visible | Boolean | - | 控制对话框的显示和隐藏 |
| title | String | - | 对话框标题 |
| message | String? | null | 对话框消息内容(可选) |
| placeholder | String | "" | 输入框占位符文本 |
| initialValue | String | "" | 输入框初始值 |
| confirmText | String | "确认" | 确认按钮文本 |
| cancelText | String | "取消" | 取消按钮文本 |
| variant | QDialogVariant | SURFACE | 对话框样式变体 |
| onConfirm | (String) -> Unit | - | 点击确认按钮的回调，参数为输入值 |
| onDismiss | () -> Unit | - | 点击取消按钮或关闭对话框的回调 |

## 样式变体

QDialog 组件支持三种样式变体，可以通过 `variant` 参数进行设置：

- **QDialogVariant.PRIMARY**：使用主色调作为标题背景，主色容器色作为内容背景
- **QDialogVariant.SECONDARY**：使用次要色调作为标题背景，次要色容器色作为内容背景
- **QDialogVariant.SURFACE**：使用表面变体色作为标题背景，表面色作为内容背景（默认）

不同的样式变体适用于不同的场景：
- PRIMARY：用于重要或主要的交互场景
- SECONDARY：用于辅助或次要的交互场景
- SURFACE：用于一般性的交互场景

## 按钮布局指南

### 基础对话框

基础对话框允许自定义内容，但建议遵循以下布局规则：
- 将关闭或确认按钮放在右侧
- 使用`Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd)`包裹按钮
- 如果有多个按钮，可以根据需要自定义布局，但保持操作按钮的区分度

### 确认对话框和输入对话框

这两种预设对话框已经使用了一致的按钮布局：
- 取消按钮（次要操作）位于左侧
- 确认按钮（主要操作）位于右侧
- 两个按钮采用等宽设计，固定高度为48dp，确保视觉平衡

## 输入框样式

QInputDialog中的输入框经过特殊美化，具有以下特点：

- 双重边框设计：外层边框与内部输入框边框形成层次感
- 与对话框变体匹配的颜色：根据对话框的variant参数自动调整边框和背景颜色
- 阴影效果：增加4dp的阴影，提升视觉层次感
- 半透明背景：根据变体使用带有透明度的背景色，增强与整体对话框的融合感

这种设计确保输入框在对话框中更加突出，同时保持整体视觉的一致性。