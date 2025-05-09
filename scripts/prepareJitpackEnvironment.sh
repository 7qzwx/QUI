#!/bin/bash

# 确保脚本执行出错时终止
set -e

# 检查是否有最新的Android SDK许可协议
yes | sdkmanager --licenses || true

# 确保项目的gradlew是可执行的
chmod +x ./gradlew 