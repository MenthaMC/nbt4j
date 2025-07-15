---
title: 快速开始

next:
  link: /docs/write
  text: NBT 文件写入
---

# 快速开始

## Maven 安装
```xml
<repositories>
    <repository>
        <id>menthamc</id>
        <url>https://repo.menthamc.com/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.coderfrish.nbt</groupId>
        <artifactId>nbt</artifactId>
        <version>7.0.0</version>
    </dependency>
</dependencies>
```

## Gradle(Groovy) 安装
```groovy
repositories {
    maven {
        url 'https://repo.menthamc.com/repository/maven-public/'
        name 'menthamc'
    }
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation group: 'me.coderfrish.nbt', name: 'nbt', version: '7.0.0'
}
```

## Gradle(Kotlin) 安装
```kotlin
repositories {
    maven("https://repo.menthamc.com/repository/maven-public/")
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("me.coderfrish.nbt:nbt:7.0.0")
}
```
