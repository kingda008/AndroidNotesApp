package com.baoge.notes.annotations;

/**
 * 绑定activity
 * 实现类由apt生成
 */
public interface IBinder<T> {
    void bind(T target);
}
