package com.example.domain;

import com.example.domain.listener.CallBack;

/**
 * @author xianglei
 * @created 2018/7/4 10:44
 */
public abstract class UseCase<T> {

    /**
     * true  需要新建线程
     * false 不需要新建线程
     */
    private boolean needThread;

    public UseCase(boolean needThread) {
        this.needThread = needThread;
    }

    public abstract void run(CallBack<T> callBack);

    public boolean isNeedThread() {
        return needThread;
    }
}
