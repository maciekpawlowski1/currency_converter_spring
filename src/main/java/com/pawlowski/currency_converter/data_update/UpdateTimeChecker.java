package com.pawlowski.currency_converter.data_update;

import org.springframework.stereotype.Component;

import static java.lang.Math.abs;

@Component
public class UpdateTimeChecker {

    private volatile long lastUpdateTime = 0;

    private boolean wasTodayUpdated()
    {
        long now = System.currentTimeMillis();
        return abs(now-lastUpdateTime) < 24*60*1000;
    }

    private void saveUpdateTime()
    {

        lastUpdateTime = System.currentTimeMillis();
    }

    public void doSomethingIfWasNotTodayUpdated(UpdateAction updateAction)
    {
        synchronized (this)
        {
            if(!wasTodayUpdated())
            {
                try {
                    updateAction.invoke();
                    saveUpdateTime();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    public interface UpdateAction
    {
        void invoke();
    }
}
