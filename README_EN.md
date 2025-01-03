# NBT

> Beans to NBT features are not currently supported for kotlin's 'data class', only `Java class bean` and `java record bean`.

[![MIT License](https://img.shields.io/github/license/CoderFrish/NBT?style=flat-square)](LICENSE)
![Version](https://img.shields.io/badge/version-5.0.0_Lastest-light_green?style=flat-square)
![Releases Downloads](https://img.shields.io/github/downloads/CoderFrish/NBT/total?style=flat-square)
![Repo Stars](https://shields.io/github/stars/CoderFrish/NBT?style=flat-square)
![Repo Forks](https://shields.io/github/forks/CoderFrish/NBT?style=flat-square)

NBT (full name: binary naming tag (`N`amed`B`inary`T`ags))\
It is a storage format for Minecraft game saves and some game data.

<a href="https://github.com/CoderFrish/NBT/wiki">Wiki</a>
&nbsp;|&nbsp;
<a href="https://github.com/CoderFrish/NBT/issues">Issues</a>
&nbsp;|&nbsp;
<a href="https://github.com/CoderFrish/NBT/blob/master/CHANGES.md">Updates</a>
&nbsp;|&nbsp;
[中文](README.md)

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
        <groupId>me.coderfrish</groupId>
        <artifactId>nbt</artifactId>
        <version>Latest_Version</version>
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
    implementation group: 'me.coderfrish', name: 'nbt', version: 'Latest_Version'
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
    implementation("me.coderfrish:nbt:Latest_Version")
}
```

## (3) Other
If you want to contribute code, you can fork the repository and commit your code to your fork. \
If there is a bug, please post an issue and we will try to help you.

## (4) Please give us a free Star⭐
> Etiquette is light and friendship is important, and each of you is⭐ the driving force for us to continue to move forward
