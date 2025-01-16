# NBT

[![MIT License](https://img.shields.io/github/license/CoderFrish/NBT?style=flat-square)](LICENSE)
![Version](https://img.shields.io/badge/version-6.0.0_Preview-yellow?style=flat-square)
![Releases Downloads](https://img.shields.io/github/downloads/CoderFrish/NBT/total?style=flat-square)
![Repo Stars](https://shields.io/github/stars/CoderFrish/NBT?style=flat-square)
![Repo Forks](https://shields.io/github/forks/CoderFrish/NBT?style=flat-square)

[//]: # (![Version]&#40;https://img.shields.io/badge/version-6.0.0_Lastest-light_green?style=flat-square&#41;)

NBT(全称：二进制命名标签(`N`amed`B`inary `T`ags))\
是Minecraft游戏存档及一些游戏数据的存储格式。

<a href="https://github.com/CoderFrish/NBT/wiki">维基</a>
&nbsp;|&nbsp;
<a href="https://github.com/CoderFrish/NBT/issues">问题</a>
&nbsp;|&nbsp;
<a href="https://github.com/CoderFrish/NBT/blob/master/CHANGES.md">历史更新</a>
&nbsp;|&nbsp;
[English](README_EN.md)

## (2) 用法
Maven
```xml
<repositories>
    <repository>
        <id>frish-repo</id>
        <url>https://frish.menthamc.com/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.coderfrish.nbt</groupId>
        <artifactId>nbt</artifactId>
        <version>最新版本</version>
    </dependency>
</dependencies>
```

Gradle
```groovy
repositories {
    maven {
        url 'https://frish.menthamc.com/repository/maven-public/'
        name 'frish-repo'
    }
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation group: 'me.coderfrish.nbt', name: 'nbt', version: '最新版本'
}
```

Gradle (Kotlin)
```groovy
repositories {
    maven("https://frish.menthamc.com/repository/maven-public/")
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("me.coderfrish.nbt:nbt:最新版本")
}
```

## (3) 其他
如果要贡献代码的话可以把仓库Fork下来，并把你的代码提交到你的Fork上，并发布Pull Request。\
如果有BUG，请发布Issues，我们会尽量帮助你。

## (4) 请给我们一个免费的 Star⭐
> 礼轻情谊重，你们每一个Star⭐是我们继续前进的动力
