package com.jpinson.pendujfx.API;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public abstract class API {
    protected final URL url;

    public API (final String url) {
        URL tmpURL;

        try {
            tmpURL = new URL(url);
        } catch (MalformedURLException e) {
            tmpURL = null;
        }

        this.url = tmpURL;
    }

    public boolean isAvailable() {
        try {
            final URLConnection conn = this.url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    protected URI buildURI (String path, String... params) {
        StringBuilder str = new StringBuilder(url.toString());
        str.append(path);

        if (params.length > 0) {
            str.append('?');

            for (String param : params) {
                str.append('&');
                str.append(param);
            }
        }

        return URI.create(str.toString());
    }
}
