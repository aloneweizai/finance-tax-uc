package com.abc.bean.userinfo;

import java.util.List;

/**
 * Created by andy on 2017/9/29.
 */
public class Event {
    private EventBO event;
    private List<ModelItemBO> modelItemList;

    public EventBO getEvent() {
        return event;
    }

    public void setEvent(EventBO event) {
        this.event = event;
    }

    public List<ModelItemBO> getModelItemList() {
        return modelItemList;
    }

    public void setModelItemList(List<ModelItemBO> modelItemList) {
        this.modelItemList = modelItemList;
    }
}
