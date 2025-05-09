# QZWX UI 组件库

QZWX UI是一个基于Jetpack Compose的现代化Android UI组件库，提供了丰富的自定义组件，帮助开发者快速构建美观且功能强大的应用界面。

## 特性

- 基于Jetpack Compose构建
- 现代化设计风格
- 包含丰富的UI组件
- 高度可定制化
- 简单易用的API

## 组件列表

- QButton - 按钮组件
- QCard - 卡片组件
- QDialog - 对话框组件
- QImage - 图片组件
- QInput - 输入组件
- QListItem - 列表项组件
- QLoading - 加载组件
- QSnackbar - 提示消息组件
- QTabs - 标签页组件
- QTopBar - 顶部栏组件
- QBottomBar - 底部栏组件
- QSlider - 滑块组件
- QDivider - 分隔符组件
- QChip - 标签组件
- QGrid - 网格组件

## 安装

### Gradle

在项目级别的build.gradle中添加JitPack仓库：

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

在应用模块的build.gradle中添加依赖：

```groovy
dependencies {
    implementation 'com.github.7qzwx:QUI:1.0.1'
}
```

对于Kotlin DSL (build.gradle.kts)：

```kotlin
dependencies {
    implementation("com.github.yourusername:qzwx-ui:1.0.1")
}
```

## 使用示例

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import qzwx.ui.library.qbutton.QPrimaryButton
import qzwx.ui.library.theme.AppTheme

@Composable
fun MyScreen() {
    AppTheme {
        QPrimaryButton(
            text = "点击我",
            onClick = { /* 处理点击事件 */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyScreen() {
    MyScreen()
}
```

## 贡献

欢迎通过提交Issue或Pull Request的方式贡献代码。

## 许可

此项目基于Apache 2.0许可证开源。详情请参阅LICENSE文件。 