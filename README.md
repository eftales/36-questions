# 36个问题

点击按钮，随机展示 36 个问题中的一个（Arthur Aron 的"36 Questions"中文版）。

## 功能

- **按顺序展示**：每次点「来一句」出现下一题，不重复
- **隐藏重置**：连续快速点击句子 7 次（两次点击间隔不超过 2 秒）即清空记录、重新开始，会有 Toast 提示
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
