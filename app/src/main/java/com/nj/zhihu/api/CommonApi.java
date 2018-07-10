package com.nj.zhihu.api;

import com.nj.zhihu.bean.BeforeStories;
import com.nj.zhihu.bean.BeforeThemeStories;
import com.nj.zhihu.bean.LatestStories;
import com.nj.zhihu.bean.LaunchImage;
import com.nj.zhihu.bean.StoryContent;
import com.nj.zhihu.bean.StoryContentExtra;
import com.nj.zhihu.bean.StoryContentLongComment;
import com.nj.zhihu.bean.StoryContentShortComment;
import com.nj.zhihu.bean.Themes;
import com.nj.zhihu.bean.ThemesContent;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 用于描述网络请求的接口
 * Created by Administrator on 2018-06-29.
 */

public interface CommonApi {

    /**
     * 获取加载页面图片
     * @return 返回的结果用于Rxjava
     */
    @GET("/api/7/prefetch-launch-images/1080*1920")
    Observable<LaunchImage> getLaunchImage();

    /**
     * 获取主题的数据
     * @return
     */
    @GET("/api/4/themes")
    Observable<Themes> getThemesList();

    /**
     * 获取最新新闻的数据
     * @return
     */
    @GET("/api/4/news/latest")
    Observable<LatestStories> getLatestStories();

    /**
     * 根据日期获取前一天的数据
     * @return
     */
    @GET("/api/4/news/before/{date}")
    Observable<BeforeStories> getBeforeStorise(@Path("date") String date);

    /**
     * 根据id获取新闻内容
     * @param id
     * @return
     */
    @GET("/api/4/news/{id}")
    Observable<StoryContent> getStoryContent(@Path("id") int id);

    /**
     * 获取信息的额外数据
     * @param id
     * @return
     */
    @GET("/api/4/story-extra/{id}")
    Observable<StoryContentExtra> getStoryContentExtra(@Path("id") int id);

    /**
     * 获取某个主题下的数据
     * @param id
     * @return
     */
    @GET("/api/4/theme/{id}")
    Observable<ThemesContent> getThemeContent(@Path("id") int id);

    /**
     * 获取某个主题下，某个id之前的数据
     * @param id
     * @param story_id
     * @return
     */
    @GET("/api/4/theme/{id}/before/{story_id}")
    Observable<BeforeThemeStories> getBeforeThemesContent(@Path("id") int id, @Path("story_id") int story_id);

    /**
     * 根据新闻的id获取长评论信息
     * @param id
     * @return
     */
    @GET("/api/4/story/{id}/long-comments")
    Observable<StoryContentLongComment> getStoryContentLongComments(@Path("id") int id);

    /**
     * 根据新闻的id获取短评论信息
     * @param id
     * @return
     */
    @GET("/api/4/story/{id}/short-comments")
    Observable<StoryContentShortComment> getStoryContentShortComments(@Path("id") int id);


}
