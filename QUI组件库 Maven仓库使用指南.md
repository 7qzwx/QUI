# QUI组件库 Maven仓库使用指南

本文档详细介绍如何使用、测试和更新QUI组件库的Maven仓库，适用于库的开发者和使用者。

## 一、本地Maven仓库

QUI组件库支持发布到本地Maven仓库进行测试。这在正式发布到远程仓库之前非常有用，可以验证组件库的打包和依赖是否正确。

### 1.1 发布到本地仓库

执行以下命令将组件库发布到本地Maven仓库：

```bash
# 清理项目
./gradlew clean

# 构建并发布到本地仓库
./gradlew publishReleasePublicationToLocalRepoRepository
```

本地仓库位置：
```
app/build/local-repo/com/github/7qzwx/qui/{version}/
```

### 1.2 在同一项目中测试本地仓库

要在同一机器上的其他项目中测试本地仓库，添加以下配置到项目的settings.gradle.kts：

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        // 添加本地仓库路径（使用绝对路径）
        maven { url = uri("C:/Users/MECHREVO/Desktop/QZWX_AS_Projects/QZWX_UI/app/build/local-repo") }
    }
}
```

然后在应用模块的build.gradle.kts中添加依赖：

```kotlin
dependencies {
    implementation("com.github.7qzwx:qui:1.0")
}
```

## 二、JitPack远程仓库

QUI组件库使用JitPack作为远程Maven仓库，这是一个免费、易用的Maven仓库托管服务。

### 2.1 发布到JitPack

1. 确保项目根目录包含以下文件：
   - jitpack.yml
   - gradle.properties
   - LICENSE

2. 提交代码到GitHub
   ```bash
   git add .
   git commit -m "准备发布版本1.0"
   git push origin main
   ```

3. 在GitHub仓库中创建Release：
   - 点击"Releases"
   - 点击"Create a new release"
   - 标签(Tag)填写"v1.0"
   - 填写发布标题和说明
   - 点击"Publish release"

4. 访问JitPack激活构建：
   - 打开 https://jitpack.io
   - 搜索 `github.com/7qzwx/QUI`
   - 点击"Look up"
   - 找到版本v1.0，点击"Get it"
   - 等待构建完成

### 2.2 在其他项目中使用JitPack

在项目的settings.gradle.kts中添加JitPack仓库：

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

在应用模块的build.gradle.kts中添加依赖：

```kotlin
dependencies {
    implementation("com.github.7qzwx:qui:1.0")
}
```

## 三、版本更新

### 3.1 准备新版本

1. 修改app/build.gradle.kts中的版本号：
   ```kotlin
   afterEvaluate {
       publishing {
           publications {
               create<MavenPublication>("release") {
                   groupId = "com.github.7qzwx"
                   artifactId = "qui"
                   version = "1.1" // 更新版本号
                   // ...
               }
           }
       }
   }
   ```

2. 修改README.md中的版本引用：
   ```markdown
   ```groovy
   dependencies {
       implementation 'com.github.7qzwx:qui:1.1'
   }
   ```

3. 更新CHANGELOG.md记录变更内容：
   ```markdown
   ## v1.1 (2025-09-10)
   
   ### 新增功能
   - 添加QSwitch开关组件
   - 为QButton添加新的样式变体
   
   ### 修复
   - 修复QCard在暗色模式下的显示问题
   ```

### 3.2 测试新版本

1. 本地测试：
   ```bash
   ./gradlew publishReleasePublicationToLocalRepoRepository
   ```

2. 在测试项目中验证新功能和修复

### 3.3 发布新版本

1. 提交代码到GitHub
   ```bash
   git add .
   git commit -m "版本1.1发布"
   git push origin main
   ```

2. 创建新的GitHub Release，标签为"v1.1"

3. 在JitPack上激活新版本

## 四、版本控制最佳实践

### 4.1 语义化版本

QUI组件库使用语义化版本命名规则：

- **主版本号**：当进行不兼容的API更改时
- **次版本号**：当添加功能但保持向后兼容时
- **修订号**：当进行向后兼容的问题修复时

例如：1.0.0、1.1.0、1.1.1

### 4.2 版本命名规范

- 正式版本：1.0.0
- 预发布版本：1.0.0-alpha.1、1.0.0-beta.1、1.0.0-rc.1
- 开发版本：1.0.0-SNAPSHOT

### 4.3 每个版本的Javadoc

JitPack支持为每个版本生成Javadoc，确保在build.gradle.kts中配置：

```kotlin
publishing {
    singleVariant("release") {
        withSourcesJar()
        withJavadocJar()
    }
}
```

## 五、常见问题

### 5.1 版本冲突

当使用者项目中有与QUI组件库依赖冲突时，可以使用以下方式解决：

```kotlin
implementation("com.github.7qzwx:qui:1.0") {
    exclude(group = "com.conflicting.group", module = "conflicting-module")
}
```

### 5.2 JitPack构建失败

如果JitPack构建失败：

1. 检查JitPack日志，解决报告的具体问题
2. 确保jitpack.yml文件正确设置JDK版本
3. 验证脚本权限：scripts/prepareJitpackEnvironment.sh

### 5.3 如何处理紧急修复

1. 从主分支创建一个热修复分支
2. 仅修复指定问题，不添加新功能
3. 递增修订号版本（例如1.0.0 → 1.0.1）
4. 创建Pull Request并合并到主分支
5. 创建新的GitHub Release

## 六、本地Maven仓库结构

```
app/build/local-repo/
└── com/
    └── github/
        └── 7qzwx/
            └── qui/
                └── 1.0/
                    ├── qui-1.0.apk             # 主要组件库文件
                    ├── qui-1.0.apk.md5         # MD5校验和
                    ├── qui-1.0.apk.sha1        # SHA1校验和 
                    ├── qui-1.0.apk.sha256      # SHA256校验和
                    ├── qui-1.0.apk.sha512      # SHA512校验和
                    ├── qui-1.0.pom             # POM文件（包含依赖信息）
                    └── qui-1.0.pom.sha*        # POM文件校验和
```

## 七、使用Maven本地缓存

Gradle会在以下路径缓存下载的依赖：

- Windows: `C:/Users/{username}/.gradle/caches/modules-2/files-2.1/`
- MacOS/Linux: `~/.gradle/caches/modules-2/files-2.1/`

如需清除缓存强制重新下载:

```bash
./gradlew --refresh-dependencies
```

---

如有任何问题，请在GitHub项目仓库提交Issue：https://github.com/7qzwx/QUI/issues 