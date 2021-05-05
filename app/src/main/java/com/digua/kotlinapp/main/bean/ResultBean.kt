package com.digua.kotlinapp.main.bean

import com.digua.kotlinapp.utils.LoginUtil

/**
 * 接口https://wanandroid.com/article/listproject/0/json 返回的Response数据
 *
 * @author RunningDigua
 * @date 2021/3/15
 */
class ResultBean constructor(name: String) {

    private var mName: String = name

    constructor() : this(name = "longlong") {
        LoginUtil.e("TAG", "ResultBean-constructor--name:$mName")
    }

    init {
        LoginUtil.e("TAG", "ResultBean-init--name:$mName")
    }

    /**
     * curPage : 1
     * datas : [{"apkLink":"","audit":1,"author":"cdalwyn","canEdit":false,"chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"基于Mvvm模式集成谷歌官方推荐的JetPack组件库LiveData+ViewModel+DataBinding，以ARouter为组件路由， 集成了登录注册、收藏、分享、搜索、浏览热门博客和项目、添加Todo待办功能等，Koin实现依赖注入、Retrofit+Rxjava2组合实现网络请求、腾讯MMKV实现高性能缓存等","descMd":"","envelopePic":"https://www.wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":true,"host":"","id":17630,"link":"https://www.wanandroid.com/blog/show/2960","niceDate":"14小时前","niceShareDate":"14小时前","origin":"","prefix":"","projectLink":"https://github.com/cdalwyn/mvvmcomponent","publishTime":1615740436000,"realSuperChapterId":293,"selfVisible":0,"shareDate":1615740436000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"Kotlin开发的 Jetpack+Mvvm+组件化 玩安卓客户端","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 40
     * size : 15
     * total : 598
     */
    var curPage = 0
    var offset = 0
    var isOver = false
    var pageCount = 0
    var size = 0
    var total = 0
    var datas: List<DatasBean>? = null

    class DatasBean {
        /**
         * apkLink :
         * audit : 1
         * author : cdalwyn
         * canEdit : false
         * chapterId : 294
         * chapterName : 完整项目
         * collect : false
         * courseId : 13
         * desc : 基于Mvvm模式集成谷歌官方推荐的JetPack组件库LiveData+ViewModel+DataBinding，以ARouter为组件路由， 集成了登录注册、收藏、分享、搜索、浏览热门博客和项目、添加Todo待办功能等，Koin实现依赖注入、Retrofit+Rxjava2组合实现网络请求、腾讯MMKV实现高性能缓存等
         * descMd :
         * envelopePic : https://www.wanandroid.com/resources/image/pc/default_project_img.jpg
         * fresh : true
         * host :
         * id : 17630
         * link : https://www.wanandroid.com/blog/show/2960
         * niceDate : 14小时前
         * niceShareDate : 14小时前
         * origin :
         * prefix :
         * projectLink : https://github.com/cdalwyn/mvvmcomponent
         * publishTime : 1615740436000
         * realSuperChapterId : 293
         * selfVisible : 0
         * shareDate : 1615740436000
         * shareUser :
         * superChapterId : 294
         * superChapterName : 开源项目主Tab
         * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
         * title : Kotlin开发的 Jetpack+Mvvm+组件化 玩安卓客户端
         * type : 0
         * userId : -1
         * visible : 1
         * zan : 0
         */
        var apkLink: String? = null
        var audit = 0
        var author: String? = null
        var isCanEdit = false
        var chapterId = 0
        var chapterName: String? = null
        var isCollect = false
        var courseId = 0
        var desc: String? = null
        var descMd: String? = null
        var envelopePic: String? = null
        var isFresh = false
        var host: String? = null
        var id = 0
        var link: String? = null
        var niceDate: String? = null
        var niceShareDate: String? = null
        var origin: String? = null
        var prefix: String? = null
        var projectLink: String? = null
        var publishTime: Long = 0
        var realSuperChapterId = 0
        var selfVisible = 0
        var shareDate: Long = 0
        var shareUser: String? = null
        var superChapterId = 0
        var superChapterName: String? = null
        var title: String? = null
        var type = 0
        var userId = 0
        var visible = 0
        var zan = 0
        var tags: List<TagsBean>? = null

        class TagsBean {
            /**
             * name : 项目
             * url : /project/list/1?cid=294
             */
            var name: String? = null
            var url: String? = null
        }
    }
}