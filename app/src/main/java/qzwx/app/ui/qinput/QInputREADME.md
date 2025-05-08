# QZWX 表单输入组件库

QZWX 表单输入组件库是像素风格 UI 组件库的一部分，提供一系列用于用户输入和表单交互的组件。这些组件保持了像素风格的视觉特征，如直角边框、明显的边框和阴影，同时提供现代的交互体验。

## 组件列表

### 基础输入组件

1. `QTextInput` - 基础文本输入框
2. `QPasswordInput` - 密码输入框
3. `QNumberInput` - 数字输入框

### 选择器组件

1. `QDropdown` - 下拉选择器
2. `QRadioGroup` - 单选按钮组

### 复选框和开关

1. `QCheckbox` - 复选框
2. `QSwitch` - 开关
3. `QCheckboxGroup` - 复选框组

## 组件用法

### QTextInput

基础文本输入框组件，支持各种自定义选项。

```kotlin
QTextInput(
    value = textValue,                    // 当前文本值
    onValueChange = { textValue = it },   // 文本变化回调
    label = "姓名",                       // 标签文本
    placeholder = "请输入你的姓名",        // 占位文本
    leadingIcon = Icons.Default.Person,   // 前置图标
    // 其他可选参数
    trailingIcon = null,                  // 尾部图标
    onTrailingIconClick = null,           // 尾部图标点击回调
    enabled = true,                       // 是否启用
    readOnly = false,                     // 是否只读
    isError = false,                      // 是否错误状态
    errorMessage = null,                  // 错误信息
    keyboardOptions = KeyboardOptions.Default, // 键盘选项
    keyboardActions = KeyboardActions.Default, // 键盘动作
    singleLine = true,                    // 是否单行
    maxLines = 1                          // 最大行数
)
```

### QPasswordInput

密码输入框组件，提供密码显示切换功能。

```kotlin
QPasswordInput(
    value = passwordValue,                // 当前密码值
    onValueChange = { passwordValue = it }, // 密码变化回调
    label = "密码",                       // 标签文本
    // 其他可选参数
    placeholder = "请输入密码",            // 占位文本
    enabled = true,                       // 是否启用
    isError = false,                      // 是否错误状态
    errorMessage = null                   // 错误信息
)
```

### QNumberInput

数字输入框组件，只允许输入数字。

```kotlin
QNumberInput(
    value = numberValue,                  // 当前数字值
    onValueChange = { numberValue = it }, // 数字变化回调
    label = "年龄",                       // 标签文本
    // 其他可选参数
    placeholder = "请输入年龄",            // 占位文本
    enabled = true,                       // 是否启用
    isError = false,                      // 是否错误状态
    errorMessage = null                   // 错误信息
)
```

### QDropdown

下拉选择器组件，支持泛型数据类型。

```kotlin
QDropdown(
    items = cities,                        // 选项列表
    selectedItem = selectedCity,           // 当前选中项
    onItemSelected = { selectedCity = it }, // 选择回调
    itemText = { it },                      // 项文本转换
    // 其他可选参数
    label = "城市",                         // 标签文本
    placeholder = "请选择城市",              // 占位文本
    enabled = true,                         // 是否启用
    isError = false,                        // 是否错误状态
    errorMessage = null,                    // 错误信息
    maxHeight = 5                           // 下拉菜单最大高度
)
```

### QRadioGroup

单选按钮组，支持垂直和水平布局。

```kotlin
QRadioGroup(
    items = genders,                        // 选项列表
    selectedItem = selectedGender,          // 当前选中项
    onItemSelected = { selectedGender = it }, // 选择回调
    itemText = { it },                       // 项文本转换
    // 其他可选参数
    label = "性别",                          // 标签文本
    enabled = true,                          // 是否启用
    isError = false,                         // 是否错误状态
    errorMessage = null,                     // 错误信息
    orientation = QRadioGroupOrientation.VERTICAL // 布局方向
)
```

### QCheckbox

复选框组件，可用于独立的布尔选项。

```kotlin
QCheckbox(
    checked = isAgreed,                     // 是否选中
    onCheckedChange = { isAgreed = it },    // 选中状态变化回调
    // 其他可选参数
    text = "我同意用户协议",                 // 文本标签
    enabled = true                           // 是否启用
)
```

### QSwitch

开关组件，用于切换设置。

```kotlin
QSwitch(
    checked = isDarkMode,                   // 是否开启
    onCheckedChange = { isDarkMode = it },  // 状态变化回调
    // 其他可选参数
    text = "深色模式",                      // 文本标签
    enabled = true                          // 是否启用
)
```

### QCheckboxGroup

复选框组，用于管理多个相关的复选框。

```kotlin
QCheckboxGroup(
    items = interests,                      // 选项列表
    selectedItems = selectedInterests,      // 当前选中项集合
    onItemCheckedChange = { item, isChecked -> 
        // 更新选中状态
        selectedInterests = if (isChecked) {
            selectedInterests + item
        } else {
            selectedInterests - item
        }
    },
    itemText = { it },                      // 项文本转换
    // 其他可选参数
    label = "兴趣爱好",                     // 标签文本
    enabled = true,                         // 是否启用
    isError = false,                        // 是否错误状态
    errorMessage = null,                    // 错误信息
    orientation = QCheckboxGroupOrientation.VERTICAL // 布局方向
)
```

## 设计特点

- **像素风格**：所有组件都使用直角边框和像素风格设计，与QZWX UI库的其他组件保持视觉一致性
- **统一字体**：使用商务体(XiangsutiFont)确保视觉一致性
- **明显的边框**：使用2dp粗边框增强像素风格的外观
- **错误状态**：支持错误状态显示，并提供错误信息提示
- **禁用状态**：所有组件都支持禁用状态，有适当的视觉反馈
- **主题适配**：自动适配应用的深色/浅色主题
- **泛型支持**：选择器和组类型组件支持泛型数据类型，提高灵活性

## 使用建议

1. 尽量保持表单布局的一致性，使用相同宽度的输入组件
2. 为表单组件提供明确的标签和错误信息
3. 对于复杂表单，可以组合使用不同类型的输入组件
4. 确保表单验证逻辑与UI错误状态保持同步 