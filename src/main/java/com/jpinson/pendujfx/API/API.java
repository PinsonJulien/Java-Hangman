package com.jpinson.pendujfx.API;

import java.io.IOException;
import java.net.*;

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
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            conn.disconnect();
            return true;
        } catch (IOException ignored) {
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
