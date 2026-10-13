# 随机句子

点击按钮，随机展示 36 个句子中的一句。

## 如何替换成自己的句子

打开 `app/src/main/res/values/strings.xml`，找到 `<string-array name="sentences">`，
把里面的 `<item>` 一条条换成你自己的即可。**数量不限**（不必正好 36 条），
代码会自动按实际数量随机。

## 如何构建运行

1. 用 Android Studio 打开本目录（首次打开会自动下载 Gradle 8.7 和依赖）。
2. 点击 Run ▶，选择模拟器或连接真机即可。
3. 如需命令行构建 APK：先执行 `gradle wrapper`（或让 Android Studio 自动生成），
   再运行 `./gradlew assembleDebug`，APK 输出在 `app/build/outputs/apk/debug/`。

## 技术说明

- Kotlin + 传统 XML 布局，无任何第三方依赖（不需要 AndroidX）
- minSdk 21（Android 5.0+），覆盖几乎所有在用设备
