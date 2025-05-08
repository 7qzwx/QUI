package qzwx.app.ui.qinput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import qzwx.app.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun QTextInputPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                var textValue by remember { mutableStateOf("") }
                var emailValue by remember { mutableStateOf("") }
                var passwordValue by remember { mutableStateOf("") }
                var numberValue by remember { mutableStateOf("") }
                var searchValue by remember { mutableStateOf("") }
                var errorValue by remember { mutableStateOf("错误示例") }
                
                Text(
                    text = "基础输入框",
                    style = MaterialTheme.typography.titleLarge
                )
                
                // 基础输入框
                QTextInput(
                    value = textValue,
                    onValueChange = { textValue = it },
                    label = "姓名",
                    placeholder = "请输入你的姓名",
                    leadingIcon = Icons.Default.Person
                )
                
                // 电子邮件输入框
                QTextInput(
                    value = emailValue,
                    onValueChange = { emailValue = it },
                    label = "电子邮件",
                    placeholder = "请输入你的电子邮件",
                    leadingIcon = Icons.Default.Email,
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                
                // 密码输入框
                QPasswordInput(
                    value = passwordValue,
                    onValueChange = { passwordValue = it },
                    label = "密码"
                )
                
                // 数字输入框
                QNumberInput(
                    value = numberValue,
                    onValueChange = { numberValue = it },
                    label = "年龄",
                    placeholder = "请输入你的年龄"
                )
                
                // 搜索输入框
                QTextInput(
                    value = searchValue,
                    onValueChange = { searchValue = it },
                    placeholder = "搜索...",
                    leadingIcon = Icons.Default.Search
                )
                
                // 错误状态输入框
                QTextInput(
                    value = errorValue,
                    onValueChange = { errorValue = it },
                    label = "错误示例",
                    placeholder = "请输入内容",
                    isError = true,
                    errorMessage = "输入内容不符合要求"
                )
                
                // 禁用输入框
                QTextInput(
                    value = "禁用输入框",
                    onValueChange = { },
                    label = "禁用状态",
                    enabled = false
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QSelectorsPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // 下拉选择器测试数据
                val cities = listOf("北京", "上海", "广州", "深圳", "杭州", "南京", "成都", "重庆")
                var selectedCity by remember { mutableStateOf<String?>(null) }
                
                // 单选按钮组测试数据
                val genders = listOf("男", "女", "其他")
                var selectedGender by remember { mutableStateOf<String?>(null) }
                
                // 水平单选按钮组测试数据
                val paymentMethods = listOf("微信支付", "支付宝", "银行卡")
                var selectedPayment by remember { mutableStateOf<String?>(null) }
                
                Text(
                    text = "选择器组件",
                    style = MaterialTheme.typography.titleLarge
                )
                
                // 下拉选择器
                QDropdown(
                    items = cities,
                    selectedItem = selectedCity,
                    onItemSelected = { selectedCity = it },
                    itemText = { it },
                    label = "城市",
                    placeholder = "请选择城市",
                    modifier = Modifier.fillMaxWidth()
                )
                
                // 错误状态下拉选择器
                QDropdown(
                    items = cities,
                    selectedItem = null,
                    onItemSelected = { },
                    itemText = { it },
                    label = "错误示例",
                    placeholder = "请选择城市",
                    isError = true,
                    errorMessage = "请选择一个城市",
                    modifier = Modifier.fillMaxWidth()
                )
                
                // 垂直单选按钮组
                QRadioGroup(
                    items = genders,
                    selectedItem = selectedGender,
                    onItemSelected = { selectedGender = it },
                    itemText = { it },
                    label = "性别",
                    modifier = Modifier.fillMaxWidth()
                )
                
                // 水平单选按钮组
                QRadioGroup(
                    items = paymentMethods,
                    selectedItem = selectedPayment,
                    onItemSelected = { selectedPayment = it },
                    itemText = { it },
                    label = "支付方式",
                    orientation = QRadioGroupOrientation.HORIZONTAL,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QCheckboxesPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // 复选框测试数据
                var isAgreed by remember { mutableStateOf(false) }
                var isSubscribed by remember { mutableStateOf(true) }
                var isEnabled by remember { mutableStateOf(false) }
                
                // 开关测试数据
                var isDarkMode by remember { mutableStateOf(false) }
                var isNotificationEnabled by remember { mutableStateOf(true) }
                
                // 复选框组测试数据
                val interests = listOf("游戏", "音乐", "电影", "阅读", "旅行", "美食")
                var selectedInterests by remember { mutableStateOf(setOf("游戏", "音乐")) }
                
                Text(
                    text = "复选框和开关",
                    style = MaterialTheme.typography.titleLarge
                )
                
                // 独立复选框
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "复选框",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        QCheckbox(
                            checked = isAgreed,
                            onCheckedChange = { isAgreed = it },
                            text = "我同意用户协议和隐私政策"
                        )
                        
                        QCheckbox(
                            checked = isSubscribed,
                            onCheckedChange = { isSubscribed = it },
                            text = "订阅最新消息"
                        )
                        
                        QCheckbox(
                            checked = isEnabled,
                            onCheckedChange = { isEnabled = it },
                            text = "启用高级功能",
                            enabled = false
                        )
                    }
                }
                
                // 开关
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "开关",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        QSwitch(
                            checked = isDarkMode,
                            onCheckedChange = { isDarkMode = it },
                            text = "深色模式"
                        )
                        
                        QSwitch(
                            checked = isNotificationEnabled,
                            onCheckedChange = { isNotificationEnabled = it },
                            text = "通知提醒"
                        )
                        
                        QSwitch(
                            checked = true,
                            onCheckedChange = { },
                            text = "禁用开关",
                            enabled = false
                        )
                    }
                }
                
                // 复选框组
                QCheckboxGroup(
                    items = interests,
                    selectedItems = selectedInterests,
                    onItemCheckedChange = { item, isChecked ->
                        selectedInterests = if (isChecked) {
                            selectedInterests + item
                        } else {
                            selectedInterests - item
                        }
                    },
                    itemText = { it },
                    label = "兴趣爱好（垂直排列）",
                    modifier = Modifier.fillMaxWidth()
                )
                
                // 水平复选框组
                QCheckboxGroup(
                    items = interests.take(3),
                    selectedItems = selectedInterests,
                    onItemCheckedChange = { item, isChecked ->
                        selectedInterests = if (isChecked) {
                            selectedInterests + item
                        } else {
                            selectedInterests - item
                        }
                    },
                    itemText = { it },
                    label = "兴趣爱好（水平排列）",
                    orientation = QCheckboxGroupOrientation.HORIZONTAL,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
} 