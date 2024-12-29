# NBT

> 注意：该版本为Preview版本，如有bug请反馈Issues

[![MIT License](https://img.shields.io/github/license/XieFrish2021/NBT?style=flat-square)](LICENSE)
![Version](https://img.shields.io/badge/version-Preview-yellow?style=flat-square)
![Releases Downloads](https://img.shields.io/github/downloads/XieFrish2021/NBT/total?style=flat-square)
![Repo Stars](https://shields.io/github/stars/XieFrish2021/NBT?style=flat-square)
![Repo Forks](https://shields.io/github/forks/XieFrish2021/NBT?style=flat-square)

NBT(全称：二进制命名标签(`N`amed`B`inary `T`ags))\
是Minecraft游戏存档及一些游戏数据的存储格式。

<a href="https://github.com/XieFrish2021/NBT/wiki">Wiki</a>
&nbsp;|&nbsp;
<a href="https://github.com/XieFrish2021/NBT/issues">Issues</a>
&nbsp;|&nbsp;
<a href="https://github.com/XieFrish2021/NBT/blob/master/CHANGES.md">Updates</a>

## (2) 用法
Maven
```xml
<repositories>
    <repository>
        <id>mentha-mc</id>
        <url>https://repo.menthamc.com/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>io.github.xiefrish2021</groupId>
        <artifactId>nbt</artifactId>
        <version>最新版本</version>
    </dependency>
</dependencies>
```

Gradle
```groovy
repositories {
    maven {
        url 'https://repo.menthamc.com/repository/maven-public/'
        name 'mentha-mc'
    }
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation group: 'io.github.xiefrish2021', name: 'nbt', version: '最新版本'
}
```

Gradle (Kotlin)
```groovy
repositories {
    maven("https://repo.menthamc.com/repository/maven-public/")
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("io.github.xiefrish2021:nbt:最新版本")
}
```

## (3) 其他
如果要贡献代码的话可以把仓库Fork下来，并把你的代码提交到你的Fork上。\
如果有BUG，请发布Issues，我们会尽量帮助你。

## (4) 请给我们一个免费的 Star⭐
> 礼轻情谊重，你们每一个Star⭐是我们继续前进的动力
