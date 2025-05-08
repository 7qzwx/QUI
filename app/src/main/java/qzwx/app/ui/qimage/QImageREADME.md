# QZWX 图片组件库

QZWX 图片组件库是像素风格 UI 组件库的一部分，提供了多种样式的图片显示组件，包括基础图片、异步加载图片、头像和图片面板。所有组件都遵循像素风格设计原则，具有直角边框、明显的边框线条和适当的阴影效果。

## 设计特点

- **像素风格**：直角边框、2dp边框宽度、阴影效果
- **多种形状**：矩形、圆角、圆形、像素化边缘
- **异步加载**：支持网络图片加载，提供加载状态和错误状态显示
- **强大定制**：可自定义边框宽度、颜色、背景色等
- **响应式设计**：自动适配深色/浅色主题
- **辅助功能**：所有图片必须提供contentDescription增强无障碍性
- **专用组件**：提供头像、图片面板等特殊用途组件

## 组件列表

1. **QImage** - 基础图片组件，支持多种形状和样式
2. **QAsyncImage** - 异步加载图片组件，支持网络图片加载
3. **QAvatar** - 头像专用组件，默认为圆形
4. **QAsyncAvatar** - 异步加载头像组件
5. **QImagePanel** - 图片面板组件，支持添加标题

## 基础用法

### 基础图片

```kotlin
// 基础矩形图片
QImage(
    painter = painterResource(id = R.drawable.my_image),
    contentDescription = "我的图片",
    modifier = Modifier
        .size(200.dp)
        .padding(16.dp)
)

// 圆角图片
QImage(
    painter = painterResource(id = R.drawable.my_image),
    contentDescription = "圆角图片",
    shape = QImageShape.ROUNDED,
    modifier = Modifier.size(200.dp)
)

// 圆形图片
QImage(
    painter = painterResource(id = R.drawable.my_image),
    contentDescription = "圆形图片",
    shape = QImageShape.CIRCLE,
    modifier = Modifier.size(200.dp)
)

// 像素化边缘图片
QImage(
    painter = painterResource(id = R.drawable.my_image),
    contentDescription = "像素边缘图片",
    shape = QImageShape.PIXEL,
    borderWidth = 4.dp,
    borderColor = MaterialTheme.colorScheme.primary,
    modifier = Modifier.size(200.dp)
)
```

### 异步加载图片

```kotlin
QAsyncImage(
    imageUrl = "https://example.com/image.jpg",
    contentDescription = "网络图片",
    shape = QImageShape.ROUNDED,
    modifier = Modifier.size(200.dp)
)

// 自定义加载状态
QAsyncImage(
    imageUrl = "https://example.com/image.jpg",
    contentDescription = "网络图片",
    shape = QImageShape.ROUNDED,
    loadingContent = { 
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "加载中...")
        }
    },
    errorContent = {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "加载失败")
        }
    },
    modifier = Modifier.size(200.dp)
)
```

### 头像组件

```kotlin
// 默认头像
QAvatar(
    painter = painterResource(id = R.drawable.avatar),
    contentDescription = "用户头像",
    size = 64.dp
)

// 自定义头像样式
QAvatar(
    painter = painterResource(id = R.drawable.avatar),
    contentDescription = "用户头像",
    size = 80.dp,
    shape = QImageShape.RECTANGLE,
    borderWidth = 4.dp,
    borderColor = MaterialTheme.colorScheme.tertiary
)

// 异步加载头像
QAsyncAvatar(
    imageUrl = "https://example.com/avatar.jpg",
    contentDescription = "用户头像",
    size = 64.dp
)
```

### 图片面板

```kotlin
QImagePanel(
    painter = painterResource(id = R.drawable.landscape),
    contentDescription = "风景照片",
    title = "美丽的风景",
    shape = QImageShape.ROUNDED,
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
)
```

## 组件详解

### QImage

`QImage`是一个基础图片组件，支持多种形状和样式。

#### 参数说明

| 参数名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| painter | Painter | - | 图片资源 |
| contentDescription | String? | - | 图片描述，用于无障碍 |
| modifier | Modifier | Modifier | 修饰符 |
| shape | QImageShape | RECTANGLE | 图片形状 |
| contentScale | ContentScale | Crop | 内容缩放模式 |
| borderWidth | Dp | 2.dp | 边框宽度 |
| borderColor | Color | outline | 边框颜色 |
| backgroundColor | Color | surface | 背景颜色 |
| elevation | Dp | 2.dp | 阴影高度 |
| colorFilter | ColorFilter? | null | 颜色滤镜 |

### QAsyncImage

`QAsyncImage`是用于异步加载网络图片的组件，支持加载状态和错误状态显示。

#### 参数说明

| 参数名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| imageUrl | String | - | 图片URL |
| contentDescription | String? | - | 图片描述，用于无障碍 |
| modifier | Modifier | Modifier | 修饰符 |
| shape | QImageShape | RECTANGLE | 图片形状 |
| contentScale | ContentScale | Crop | 内容缩放模式 |
| borderWidth | Dp | 2.dp | 边框宽度 |
| borderColor | Color | outline | 边框颜色 |
| backgroundColor | Color | surface | 背景颜色 |
| elevation | Dp | 2.dp | 阴影高度 |
| colorFilter | ColorFilter? | null | 颜色滤镜 |
| loadingContent | @Composable () -> Unit | DefaultLoadingContent | 加载中显示内容 |
| errorContent | @Composable () -> Unit | DefaultErrorContent | 错误状态显示内容 |

### QAvatar

`QAvatar`是一个头像专用组件，默认为圆形。

#### 参数说明

| 参数名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| painter | Painter | - | 头像图片资源 |
| contentDescription | String? | - | 头像描述，用于无障碍 |
| modifier | Modifier | Modifier | 修饰符 |
| shape | QImageShape | CIRCLE | 头像形状 |
| borderWidth | Dp | 2.dp | 边框宽度 |
| borderColor | Color | primary | 边框颜色 |
| backgroundColor | Color | surface | 背景颜色 |
| elevation | Dp | 2.dp | 阴影高度 |
| size | Dp | 48.dp | 头像尺寸 |

### QAsyncAvatar

`QAsyncAvatar`是一个异步加载头像的组件，默认为圆形。

#### 参数说明

| 参数名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| imageUrl | String | - | 头像URL |
| contentDescription | String? | - | 头像描述，用于无障碍 |
| modifier | Modifier | Modifier | 修饰符 |
| shape | QImageShape | CIRCLE | 头像形状 |
| borderWidth | Dp | 2.dp | 边框宽度 |
| borderColor | Color | primary | 边框颜色 |
| backgroundColor | Color | surface | 背景颜色 |
| elevation | Dp | 2.dp | 阴影高度 |
| size | Dp | 48.dp | 头像尺寸 |
| loadingContent | @Composable () -> Unit | CircularProgressIndicator | 加载中显示内容 |
| errorContent | @Composable () -> Unit | 图标占位符 | 错误状态显示内容 |

### QImagePanel

`QImagePanel`是一个图片面板组件，支持添加标题。

#### 参数说明

| 参数名 | 类型 | 默认值 | 说明 |
|------|------|------|------|
| painter | Painter | - | 图片资源 |
| contentDescription | String? | - | 图片描述，用于无障碍 |
| title | String? | null | 图片标题 |
| modifier | Modifier | Modifier | 修饰符 |
| shape | QImageShape | RECTANGLE | 图片形状 |
| borderWidth | Dp | 2.dp | 边框宽度 |
| borderColor | Color | outline | 边框颜色 |
| backgroundColor | Color | surface | 背景颜色 |
| elevation | Dp | 4.dp | 阴影高度 |

## 形状变体

### QImageShape 枚举

| 变体 | 说明 |
|------|------|
| RECTANGLE | 矩形，直角边缘 |
| ROUNDED | 圆角矩形，使用MaterialTheme.shapes.medium |
| CIRCLE | 圆形，使用MaterialTheme.shapes.extraLarge |
| PIXEL | 像素化边缘，具有像素游戏风格的边框 |

## 最佳实践

1. **始终提供contentDescription**
   - 为每个图片提供有意义的描述，提高无障碍性
   - 纯装饰性图片可以传入null

2. **选择合适的形状**
   - 用户头像通常使用CIRCLE形状
   - 内容图片通常使用RECTANGLE或ROUNDED形状
   - 像素游戏风格界面使用PIXEL形状

3. **异步加载优化**
   - 对于列表中的图片，考虑使用loadingContent减少转圈圈的视觉干扰
   - 为大图片提供低分辨率的占位图

4. **合理设置边框**
   - 边框宽度建议为2dp到4dp，保持像素风格
   - 边框颜色应与应用整体配色方案协调

5. **响应式适配**
   - 对于不同尺寸的屏幕，设置合适的约束条件
   - 可以使用aspectRatio保持图片比例

## 可访问性考虑

- 所有图片组件都需要提供contentDescription，以支持屏幕阅读器
- 纯装饰性图片可以将contentDescription设为null
- 加载失败状态应提供明确的错误提示
- 交互性图片应确保有足够大的点击区域 