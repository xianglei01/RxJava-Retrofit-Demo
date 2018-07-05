package com.example.domain;

import com.example.domain.listener.CallBack;

/**
 * @author xianglei
 * @created 2018/7/4 10:55
 */
public class UseCaseHandler {

    private final UseCaseScheduler useCaseScheduler;

    private static class Holder {
        private final static UseCaseHandler INSTANCE = new UseCaseHandler();
    }

    private UseCaseHandler() {
        useCaseScheduler = new UseCaseThreadPoolScheduler();
    }

    public static UseCaseHandler getInstance() {
        return Holder.INSTANCE;
    }

    public void execute(final UseCase useCase, final CallBack callBack) {
        if (!useCase.isNeedThread()) {
            //同步task不需要新建线程
            useCase.run(callBack);
            return;
        }
        useCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run(new DataCallBack(callBack));
            }
        });
    }

    public class DataCallBack<T> implements CallBack<T> {

        private CallBack<T> callBack;

        public DataCallBack(CallBack<T> callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onSuccess(T data) {
            useCaseScheduler.notifyResponse(data, callBack);
        }

        @Override
        public void onError() {
            useCaseScheduler.onError(callBack);
        }
    }
}
