# QZWX 弹出提示组件库

QZWX 弹出提示组件库是像素风格 UI 组件库的一部分，提供了两种主要的弹出提示组件：`QSnackbar` 和 `QToast`。它们遵循像素风格设计原则，具有直角边框、明显的边框线条和适当的阴影效果，适用于不同类型的消息提示场景。

## 设计特点

- **像素风格**：直角边框、2dp边框宽度、阴影效果
- **四种提示类型**：信息、成功、警告、错误（每种类型配有对应的Material Design图标）
- **响应式设计**：自动适配深色/浅色主题
- **灵活的位置**：支持顶部和底部显示
- **动画效果**：平滑的出现和消失动画，包括滑入/滑出和淡入/淡出效果
- **可交互**：支持添加操作按钮和关闭按钮
- **自动消失**：支持自定义显示时长
- **无障碍支持**：所有图标都提供清晰的contentDescription
- **图标颜色**：所有图标使用onBackground颜色以确保在各种背景下可见

## 组件列表

1. **QSnackbar** - 标准弹出提示，支持用户交互，可包含操作按钮
2. **QToast** - 简洁轻量的提示，无交互，适合简短消息
3. **QSnackbarHost** - Snackbar的宿主容器，用于管理和显示Snackbar
4. **QSnackbarHostState** - 管理Snackbar的状态

## 基础用法

### 在应用中集成Snackbar系统

在你的主布局中添加`QSnackbarHost`：

```kotlin
@Composable
fun MyApp() {
    val snackbarHostState = remember { QSnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    Scaffold(
        // 其他内容...
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            // 你的应用内容...
            
            // 添加Snackbar宿主
            QSnackbarHost(hostState = snackbarHostState)
        }
    }
    
    // 在需要显示Snackbar的地方
    Button(onClick = {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "这是一条消息",
                type = QSnackbarType.INFO
            )
        }
    }) {
        Text("显示Snackbar")
    }
}
```

### 显示不同类型的Snackbar

```kotlin
// 信息提示
scope.launch {
    snackbarHostState.showSnackbar(
        message = "文件已保存",
        type = QSnackbarType.INFO
    )
}

// 成功提示
scope.launch {
    snackbarHostState.showSnackbar(
        message = "操作成功完成",
        type = QSnackbarType.SUCCESS
    )
}

// 警告提示
scope.launch {
    snackbarHostState.showSnackbar(
        message = "警告：磁盘空间不足",
        type = QSnackbarType.WARNING
    )
}

// 错误提示
scope.launch {
    snackbarHostState.showSnackbar(
        message = "错误：操作失败",
        type = QSnackbarType.ERROR
    )
}
```

### 添加操作按钮

```kotlin
scope.launch {
    snackbarHostState.showSnackbar(
        message = "文件已上传",
        type = QSnackbarType.SUCCESS,
        action = QSnackbarAction(
            icon = Icons.Default.Share,
            onClick = { /* 分享操作 */ },
            contentDescription = "分享"
        )
    )
}
```

### 自定义显示时长

```kotlin
// 显示4秒（默认）
scope.launch {
    snackbarHostState.showSnackbar(
        message = "这是一条普通消息",
        duration = 4000
    )
}

// 显示直到用户关闭（持久显示）
scope.launch {
    snackbarHostState.showSnackbar(
        message = "这是一条重要消息，需要用户确认",
        duration = 0 // 0表示不自动关闭
    )
}
```

### 更改显示位置

```kotlin
// 底部显示（默认）
scope.launch {
    snackbarHostState.showSnackbar(
        message = "这是底部显示的消息",
        position = QSnackbarPosition.BOTTOM
    )
}

// 顶部显示
scope.launch {
    snackbarHostState.showSnackbar(
        message = "这是顶部显示的消息",
        position = QSnackbarPosition.TOP
    )
}
```

### 使用Toast

Toast更轻量，适合简短的提示信息，不支持交互：

```kotlin
@Composable
fun MyScreen() {
    // 在需要显示Toast的地方
    val scope = rememberCoroutineScope()
    var toastData by remember { mutableStateOf<ToastData?>(null) }
    
    // 定义Toast数据结构
    data class ToastData(
        val message: String,
        val type: QSnackbarType = QSnackbarType.INFO,
        val position: QSnackbarPosition = QSnackbarPosition.BOTTOM
    )
    
    // 显示Toast（如果有数据）
    toastData?.let { data ->
        showQToast(
            message = data.message,
            type = data.type,
            position = data.position
        )
    }
    
    Button(onClick = { 
        scope.launch {
            // 设置Toast数据触发显示
            toastData = ToastData(
                message = "操作已完成",
                type = QSnackbarType.SUCCESS
            )
            // 短暂延迟后重置数据，确保Toast动画完成
            delay(2500)
            toastData = null
        }
    }) {
        Text("显示Toast")
    }
}
```

## 组件详解

### QSnackbar

`QSnackbar`是一个功能完整的提示组件，支持自定义内容和操作。

#### 参数说明

| 参数名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| message | String | - | 提示消息内容 |
| modifier | Modifier | Modifier | 修饰符 |
| type | QSnackbarType | INFO | 提示类型 |
| elevation | Dp | 4.dp | 阴影高度 |
| action | QSnackbarAction? | null | 操作按钮 |
| showCloseIcon | Boolean | true | 是否显示关闭按钮 |
| borderWidth | Dp | 2.dp | 边框宽度 |
| onDismiss | (() -> Unit)? | null | 关闭回调 |

### QToast

`QToast`是一个轻量级的提示组件，不支持交互，适合短暂显示。

#### 参数说明

| 参数名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| message | String | - | 提示消息内容 |
| modifier | Modifier | Modifier | 修饰符 |
| type | QSnackbarType | INFO | 提示类型 |
| elevation | Dp | 4.dp | 阴影高度 |
| borderWidth | Dp | 2.dp | 边框宽度 |

### QSnackbarHostState

`QSnackbarHostState`用于管理Snackbar的状态和显示逻辑。

#### 主要方法

| 方法名 | 参数 | 返回类型 | 说明 |
|------|------|------|------|
| showSnackbar | message: String, type: QSnackbarType, duration: Long, action: QSnackbarAction?, position: QSnackbarPosition | suspend | 显示一个Snackbar |
| dismissCurrentSnackbar | - | Unit | 关闭当前显示的Snackbar |

## 设计变体与样式

### 四种提示类型与对应图标

1. **INFO**：用于中性信息提示，默认使用表面色，使用`Icons.Default.Info`图标
2. **SUCCESS**：用于成功操作提示，使用主色调，使用`Icons.Default.Check`图标
3. **WARNING**：用于警告提示，使用次要色调，使用`Icons.Default.Warning`图标
4. **ERROR**：用于错误提示，使用错误色调，使用`Icons.Default.Error`图标

### 两种位置变体

1. **BOTTOM**：在屏幕底部显示，适用于一般提示
2. **TOP**：在屏幕顶部显示，适用于重要提示

## 最佳实践

1. **根据消息类型选择合适的提示类型**：
   - 一般信息使用INFO
   - 操作成功使用SUCCESS
   - 需要注意的情况使用WARNING
   - 错误和失败使用ERROR

2. **合理使用动作按钮**：
   - 只在需要用户响应时添加操作按钮
   - 使用清晰简洁的图标表示操作

3. **设置合适的持续时间**：
   - 普通消息：3-4秒
   - 重要消息：设置为0（不自动关闭）
   - 简短通知：2秒左右

4. **灵活使用位置**：
   - 重要提示可放在顶部
   - 一般提示放在底部避免干扰

5. **选择合适的组件**：
   - 需要用户交互的场景使用QSnackbar
   - 简单通知无需交互的场景使用QToast

## 可访问性考虑

- 所有图标都提供contentDescription，以支持屏幕阅读器
- 文本颜色与背景对比度符合可访问性标准
- 消息文本清晰简洁，易于理解
- 交互按钮尺寸足够大，方便触控
- 图标使用onBackground颜色确保在各种场景下具有足够对比度 