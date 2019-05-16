package com.pojo;

import com.entities.MediaInfoDO;
import com.entities.MediaRefDO;

public class MediaRefVO extends MediaRefDO {

    private MediaInfoDO mediaInfoDO;

    public MediaInfoDO getMediaInfoDO() {
        return mediaInfoDO;
    }

    public void setMediaInfoDO(MediaInfoDO mediaInfoDO) {
        this.mediaInfoDO = mediaInfoDO;
    }
}
