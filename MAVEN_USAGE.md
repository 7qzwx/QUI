# QUI组件库使用指南

QUI是一个基于Jetpack Compose的现代化Android UI组件库，本文档介绍如何在其他项目中引入并使用此组件库。

## 添加依赖

### 步骤1：添加JitPack仓库

在项目级的`settings.gradle.kts`文件中添加JitPack仓库：

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 步骤2：添加依赖

在应用模块的`build.gradle.kts`文件中添加QUI依赖：

```kotlin
dependencies {
    implementation("com.github.7qzwx:qui:1.0")
}
```

## 使用组件示例

以下是一些基本组件的使用示例：

### 按钮（QButton）

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import qzwx.app.ui.qbutton.QPrimaryButton
import qzwx.app.ui.theme.AppTheme

@Composable
fun LoginScreen() {
    AppTheme {
        QPrimaryButton(
            text = "登录",
            onClick = { /* 处理登录逻辑 */ }
        )
    }
}
```

### 卡片（QCard）

```kotlin
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import qzwx.app.ui.qcard.QCard
import qzwx.app.ui.theme.AppTheme

@Composable
fun ProfileCard() {
    AppTheme {
        QCard(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("这是一个用户资料卡片")
        }
    }
}
```

## 版本更新

当发布新版本时，只需更新依赖版本号即可：

```kotlin
dependencies {
    implementation("com.github.7qzwx:qui:1.1") // 更新到新版本
}
```

## 主题集成

QUI组件库提供了一个默认的Material 3主题，你可以在你的应用中使用它：

```kotlin
import androidx.compose.runtime.Composable
import qzwx.app.ui.theme.AppTheme

@Composable
fun MyApp() {
    AppTheme {
        // 你的应用内容
    }
}
```

如需自定义主题，可以传递参数：

```kotlin
AppTheme(
    darkTheme = isDarkTheme,
    dynamicColor = true // 启用Material You动态颜色
) {
    // 应用内容
}
```

## 故障排除

如果遇到依赖冲突，可以排除冲突的依赖：

```kotlin
implementation("com.github.7qzwx:qui:1.0") {
    exclude(group = "com.conflicting.dependency", module = "module-name")
}
```

---

更多详细信息和组件文档，请访问：https://github.com/7qzwx/QUI 