package com.harium.propan;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.harium.etyl.ad.AdHandler;
import com.harium.etyl.ad.DummyAdProvider;
import com.harium.etyl.android.AndroidEngine;
import com.harium.etyl.core.Engine;
import com.harium.etyl.loader.FontLoader;
import com.harium.etyl.loader.MultimediaLoader;
import com.harium.etyl.loader.image.ImageLoader;
import com.harium.propan.core.CoreGL;
import com.harium.propan.core.context.ApplicationGL;
import com.harium.propan.core.loader.MeshLoader;

public abstract class PropanMobile extends AndroidEngine<CoreGL> implements Engine<ApplicationGL> {

    private ApplicationGL application;

    public PropanMobile(int w, int h) {
        super(w, h);
        addLoader(ImageLoader.getInstance());
        addLoader(FontLoader.getInstance());
        addLoader(MultimediaLoader.getInstance());
        // 3D Loaders
        addLoader(MeshLoader.getInstance());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Ad Provider
        AdHandler.getInstance().setProvider(new DummyAdProvider());

        init();
    }

    /**
     * Based on Etyl.init() in etyl-gdx
     */
    public void init() {
        initialSetup();

        application = startApplication();
        application.setLoaded(false);
        core.setApplication(application);

        // Init Loaders
        super.init();
    }

    public CoreGL initCore() {
        return new CoreGL(w, h);
    }

    @Override
    public AndroidApplicationConfiguration buildConfiguration() {
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.r = 8;
        configuration.b = 8;
        configuration.g = 8;
        configuration.a = 8;
        return configuration;
    }

}
