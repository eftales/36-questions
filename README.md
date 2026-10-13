# 36个问题

点击按钮，随机展示 36 个问题中的一个（Arthur Aron 的"36 Questions"中文版）。

## 功能

- 随机展示，**不重复**：出现过的问题不会再出现，直到点「重新开始」
- 「重新开始」按钮：清空历史记录，所有问题重新参与随机
- 进度记忆：关闭 App 再打开，进度不会丢失（存在手机本地，重置时清除）
- 界面显示剩余数量

## 如何修改问题内容

打开 `app/src/main/res/values/strings.xml`，找到 `<string-array name="sentences">`，
把里面的 `<item>` 一条条换成自己的即可，数量不限，代码自动适配。

## 如何构建 APK

推送到 GitHub 即自动编译（GitHub Actions），在仓库的 **Actions** 页面
下载 Artifacts 中的 `RandomSentence-APK`，解压得到 `app-debug.apk`。

## 技术说明

- Kotlin + 传统 XML 布局，无任何第三方依赖（不需要 AndroidX）
- minSdk 21（Android 5.0+），覆盖几乎所有在用设备
