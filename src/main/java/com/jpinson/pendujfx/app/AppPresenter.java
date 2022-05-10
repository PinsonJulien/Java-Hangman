package com.jpinson.pendujfx.app;

import com.jpinson.pendujfx.enums.MusicEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.framework.presenter.ParentPresenter;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.EnumMap;

public class AppPresenter
    extends ParentPresenter
    <
        AppView,
        PresenterEnum,
        ChildPresenter<?, ?>
    >
    implements AppPresenterListener
{
    private final EnumMap<MusicEnum, Media> musicList = new EnumMap<>(MusicEnum.class);
    private MediaPlayer mediaPlayer;

    public AppPresenter(
        AppView appView
    ) {
        super(appView, new EnumMap<>(PresenterEnum.class));
        this.init();
    }

    // Interfaces
    @Override
    public void init() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void selectMusic (MusicEnum e) {
        Media media = this.musicList.get(e);
        if (this.mediaPlayer != null) this.stopMusic();

        this.mediaPlayer = new MediaPlayer(media);
        this.mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });

        this.mediaPlayer.play();
    }

    // Methods
    public void addMusic (MusicEnum e, Media media) {
        this.musicList.put(e, media);
    }

    public void stopMusic () {
        this.mediaPlayer.stop();
    }
}
