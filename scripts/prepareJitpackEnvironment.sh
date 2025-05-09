#!/bin/bash

# 确保脚本执行出错时终止
set -e

# 输出调试信息
echo "====== 准备JitPack构建环境 ======"
echo "Java版本:"
java -version

# 检查是否有最新的Android SDK许可协议
echo "接受Android SDK许可协议..."
yes | sdkmanager --licenses || true

# 确保项目的gradlew是可执行的
echo "设置gradlew可执行权限..."
chmod +x ./gradlew 

# 显示Gradle版本
echo "Gradle版本:"
./gradlew --version

# 显示项目结构
echo "项目结构:"
ls -la

echo "====== 环境准备完成 ======" 