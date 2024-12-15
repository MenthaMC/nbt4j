# 版本更新 (3.0.0开始算起)：

### 3.3.0 - 更新
 - 修改了部分代码
 - `get(String)Lio/github/xiefrish2021/api/ITag`方法修改成 `get(String)Lio/github/xiefrish2021/compound/NBTElement`
 - 添加了一些kotlin成分：如委托，语法糖

#### 3.2.0 - 更新
 - 删除或合并了一些不必要的接口
 - `NBT.newInstance()`用法更改为 `NBT.getInstance()`

#### 3.1.0 - 更新
 - 修改了SNBTReader里面readArray的会报unchecked的某条代码
 - 把Compound，Array<V extends ITag>等等的接口迁移到了`xyz.frish2021.nbt.api`包
 - 修改了NBT类的基本用法由`new NBT()` 修改成`NBT.newInstance()` 并且添加了单例模式
 - `NBT.newInstance()` 添加了synchronized关键字以防止多次初始化影响线程安全
 - 修复了生成IntArray和ByteArray的SNBT却生成成了List的SNBT。

#### 3.0.0 - 更新
 - 重写了库的代码
 - 允许通过Compound实例生成SNBT
 -
