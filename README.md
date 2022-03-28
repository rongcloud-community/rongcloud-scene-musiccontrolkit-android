<h1 align="center"> 场景化音乐播放 </h>

<p align="center">
<a href="https://github.com/rongcloud-community/rongcloud-scene-musiccontrolkit-android">
<img alt="Maven Central" src="https://img.shields.io/maven-central/v/cn.rongcloud.sdk/rcmusiccontrolkit_lib">
</a>

<a href="https://github.com/rongcloud-community/rongcloud-scene-musiccontrolkit-android">
<img src="https://img.shields.io/cocoapods/l/RCSceneChatroomKit.svg?style=flat">
</a>

<a href="https://github.com/rongcloud-community/rongcloud-scene-musiccontrolkit-android">
<img src="https://img.shields.io/badge/%20in-java%208-orange.svg">
</a>

</p>

## 简介

场景化音乐 Kit 是融云为开发者提供的开源项目，适用于语聊房、电台房、视频直播等常见社交场景。Kit 封装了音乐播放列表，音乐库列表，音量控制，氛围控制等常见 UI 组件。开发者可以通过 JSON
配置文件自定义 UI 属性，也可以通过远端动态配置。

## 集成

### maven仓库集成

1. 在 project 的 build.gradle 添加融云仓库

```
allprojects {
    repositories {
        // 融云 maven 仓库
        maven { url "https://maven.rongcloud.cn/repository/maven-releases/" }
    }
}
```

2. module 的 build.gradle 文件中的 dependencies 节点添加如下代码：

```
dependencies {
    // kit基础lib
    implementation 'cn.rongcloud.sdk:rccorekit_lib:1.0.0'
    // 聊天室lib
    implementation 'cn.rongcloud.sdk:rcmusiccontrolkit_lib:1.0.0'
}
```

### 本地依赖集成

下载源码，把 ChatRoomKit 作为Module依赖到项目中

## 功能

场景化聊天室内部按功能分为四个部分：

- 音乐播放列表

  <img src= "https://tva1.sinaimg.cn/large/e6c9d24ely1h0pqh1wxgvj20u01pwmzr.jpg"  height="375" alt="RCSceneChatroomKit">

- 音乐库列表

  <img src= "https://tva1.sinaimg.cn/large/e6c9d24ely1h0pqh7z9boj20u01pw422.jpg"  height="375" alt="RCSceneChatroomKit">

- 音量控制

  <img src= "https://tva1.sinaimg.cn/large/e6c9d24ely1h0pqh7e9qxj20u01pwacm.jpg"  height="375" alt="RCSceneChatroomKit">

- 氛围控制

  <img src= "https://tva1.sinaimg.cn/large/e6c9d24ely1h0pqh7o5l3j20u01pwq5l.jpg"  height="375" alt="RCSceneChatroomKit">

## 配置项

场景化聊天室可以通过 JSON 文件进行 UI 配置，可参照 `MusicControlKit` 下 assets 文件夹下的 `MusicControlKit.json`
,如果是远程依赖可以复制一份 `MusicControlKit.json` 放在项目app下的 assets 下做修改，如果是本地依赖可直接修改。

## 使用

### 在需要弹出音乐控制的地方调用如下方法

```java
// 显示音乐弹框
RCMusicControlEngine.getInstance().showDialog(getSupportFragmentManager(),new RCMusicKitListener());
```

### 实现 `RCMusicKitListener` 接口

该接口继承了 `OnMusicDataSourceListener` (数据获取回调)，`OnMusicOperateListener` (音乐操作回调)
, `OnMusicPlayerListener` (音乐控制回调)
三个接口，可以实现具体方法进行音乐控件的数据展示及控制，详细用法见demo中的 [MusicControlManager](./app/src/main/java/cn/rongcloud/musiccontrolkitdemo/musiccontrolkit/MusicControlManager.java)

### 音乐操作 `RCMusicControlEngine`

- 音乐实体类 Music

```java
public class Music<T> implements Serializable {
    /**
     * 音乐服务器地址
     */
    @SerializedName("fileUrl")
    private String fileUrl;
    /**
     * 音乐图片
     */
    @SerializedName("coverUrl")
    private String coverUrl;
    /**
     * 音乐名称
     */
    @SerializedName("musicName")
    private String musicName;
    /**
     * 作者
     */
    @SerializedName("author")
    private String author;
    /**
     * 专辑
     */
    @SerializedName("albumName")
    private String albumName;
    /**
     * 本地文件路径
     */
    @SerializedName("path")
    private String path;
    /**
     * 文件大小（字节）
     */
    @SerializedName("size")
    private long size;
    /**
     * 下载状态
     */
    private LoadState loadState = LoadState.UN_LOAD;
    /**
     * 音乐 id
     */
    @SerializedName("musicId")
    private String musicId;

    /**
     * 拓展字段，可根据业务传入
     */
    private T extra;

    public enum LoadState {
        /**
         * 正在下载
         */
        LOADING,
        /**
         * 已经下载
         */
        LOADED,
        /**
         * 未下载
         */
        UN_LOAD
    }
}
```

- 音乐列表中添加音乐

```java
// 批量添加
RCMusicControlEngine.getInstance().addMusicList(List<Music> musicList);

// 单个添加
        RCMusicControlEngine.getInstance().addMusic(Music music);
```

- 音乐列表中删除音乐

```java
RCMusicControlEngine.getInstance().deleteMusic(Music music);
```

- 播放

```java
RCMusicControlEngine.getInstance().playMusic(Music music)
```

- 暂停

```java
RCMusicControlEngine.getInstance().pauseMusic()
```

- 停止

```java
RCMusicControlEngine.getInstance().stopMusic()
```

- 播放下一首

```java
// loop 是否循环列表，默认 false
RCMusicControlEngine.getInstance().playNextMusic(boolean loop)
```

- 置顶

```java
/**
 * 置顶音乐
 * 这里置顶的逻辑是：把要操作的歌曲移到正在播放歌曲的下面，
 * 如果当前没有正在播放的歌曲则播放该歌曲，位置不变化
 */
RCMusicControlEngine.getInstance().topMusic(Music music)
```

- 获取播放列表数据

```java
RCMusicControlEngine.getInstance().getMusicList();
```

- 是否正在播放

```java
RCMusicControlEngine.getInstance().isPlaying();
```

- 获取正在播放的音乐

```java
RCMusicControlEngine.getInstance().getPlayingMusic();
```

- 重置监听和数据

```java
RCMusicControlEngine.getInstance().release();
```

## 其他

如有任何疑问请提交 issue

