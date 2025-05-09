# QUI 开发者指南

本文档提供给QUI组件库的贡献者和开发者使用，包含组件库的开发、测试和发布流程。

## 开发环境

- Android Studio Iguana | 2023.2.1 或更高版本
- JDK 11
- Gradle 8.0+
- Kotlin 1.9.0+
- Jetpack Compose 1.5.0+

## 项目结构

```
QZWX_UI/
├── app/                            # 主模块
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/qzwx/app/      # 源代码目录
│   │   │   │   ├── ui/             # UI组件
│   │   │   │   │   ├── qbutton/    # 按钮组件
│   │   │   │   │   ├── qcard/      # 卡片组件
│   │   │   │   │   └── ...         # 其他组件
│   │   │   │   ├── navigation/     # 导航组件
│   │   │   │   └── theme/          # 主题
│   │   │   └── res/                # 资源文件
│   │   ├── test/                   # 单元测试
│   │   └── androidTest/            # 仪器测试
│   ├── build.gradle.kts            # 构建配置
├── gradle/                         # Gradle配置
├── scripts/                        # 构建脚本
├── .gitignore                      # Git忽略文件
├── build.gradle.kts                # 项目构建配置
├── CHANGELOG.md                    # 更新日志
├── jitpack.yml                     # JitPack配置
├── LICENSE                         # 许可证文件
├── MAVEN_GUIDE.md                  # Maven使用指南
├── README.md                       # 项目说明
└── settings.gradle.kts             # Gradle设置
```

## 开发流程

### 1. 设置开发环境

1. 克隆仓库：
   ```bash
   git clone https://github.com/7qzwx/QUI.git
   cd QUI
   ```

2. 打开项目：
   - 使用Android Studio打开项目
   - 等待Gradle同步完成

### 2. 创建新组件

所有组件应遵循以下命名和文件结构：

1. 在`app/src/main/java/qzwx/app/ui/`下创建新的组件目录，例如`qswitch/`

2. 创建组件代码文件，遵循如下命名：
   - 主要组件文件：`QSwitches.kt`
   - 预览文件：`QSwitchPreview.kt`
   - 文档文件：`QSwitchREADME.md`

3. 组件应该遵循QUI的设计规范：
   - 支持Material 3主题
   - 支持亮色/暗色模式
   - 提供清晰的参数文档
   - 包含默认预览

示例组件结构：

```kotlin
@Composable
fun QSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SwitchColors = QSwitchDefaults.colors()
) {
    // 组件实现
}

object QSwitchDefaults {
    @Composable
    fun colors(
        checkedThumbColor: Color = MaterialTheme.colorScheme.primary,
        checkedTrackColor: Color = MaterialTheme.colorScheme.primaryContainer,
        // ...其他默认颜色
    ): SwitchColors {
        // 返回颜色配置
    }
}
```

### 3. 测试组件

1. 创建预览函数：
   ```kotlin
   @Preview(showBackground = true)
   @Composable
   fun QSwitchPreview() {
       AppTheme {
           Column {
               QSwitch(checked = true, onCheckedChange = {})
               QSwitch(checked = false, onCheckedChange = {})
               QSwitch(checked = true, onCheckedChange = {}, enabled = false)
           }
       }
   }
   ```

2. 添加到导航系统：
   - 在`NavRoutes`中添加新路由
   - 在`NavGraph`中添加新组件

3. 验证组件在各种设备和主题下的表现

### 4. 代码提交

1. 确保代码遵循Kotlin代码规范

2. 提交前本地测试：
   ```bash
   ./gradlew clean build
   ```

3. 提交代码：
   ```bash
   git add .
   git commit -m "添加QSwitch组件"
   git push origin branch-name
   ```

4. 创建Pull Request

## 发布流程

### 1. 更新版本号

1. 修改`app/build.gradle.kts`中的版本号

2. 更新`CHANGELOG.md`文件，记录版本变更

3. 更新`README.md`中的引用示例

### 2. 测试构建

```bash
./gradlew clean
./gradlew publishReleasePublicationToLocalRepoRepository
```

### 3. 发布版本

1. 提交所有更改到GitHub

2. 创建新的GitHub Release，标签格式为"v1.x.x"

3. 在JitPack上激活新版本

## 代码风格指南

### 命名规范

- 组件名称：以`Q`开头，使用大驼峰命名法，例如`QButton`
- 函数名称：使用小驼峰命名法，例如`onCheckedChange`
- 参数名称：使用小驼峰命名法，例如`backgroundColor`
- 常量名称：使用大写下划线命名法，例如`DEFAULT_PADDING`

### 注释规范

每个公开的组件和函数应包含以下注释：

```kotlin
/**
 * QSwitch - 开关组件
 * 一个符合Material设计的开关控件，用于切换布尔状态
 *
 * @param checked 开关当前状态
 * @param onCheckedChange 状态变化回调
 * @param modifier 应用于组件的修饰符
 * @param enabled 是否启用组件
 * @param colors 自定义颜色配置
 */
```

### 预览函数

每个组件应提供以下预览：

1. 基本用法预览
2. 所有可能状态的预览（启用/禁用/选中等）
3. 自定义样式预览

### 文档要求

每个组件应包含一个README.md文件，内容包括：

1. 组件描述
2. 基本用法示例
3. 所有可配置参数说明
4. 高级用法示例

## 参考资源

- [Material 3 设计指南](https://m3.material.io/)
- [Jetpack Compose 文档](https://developer.android.com/jetpack/compose)
- [Kotlin 代码规范](https://kotlinlang.org/docs/coding-conventions.html)

---

如有疑问，请联系项目维护者或在GitHub上提交Issue。 