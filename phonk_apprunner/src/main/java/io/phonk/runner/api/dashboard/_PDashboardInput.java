/*
 * Part of Phonk http://www.phonk.io
 * A prototyping platform for Android devices
 *
 * Copyright (C) 2013 - 2017 Victor Diaz Barrales @victordiaz (Protocoder)
 * Copyright (C) 2017 - Victor Diaz Barrales @victordiaz (Phonk)
 *
 * Phonk is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Phonk is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Phonk. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.phonk.runner.api.dashboard;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;

import io.phonk.runner.api.ProtoBase;
import io.phonk.runner.apprunner.AppRunner;
import io.phonk.runner.base.utils.StrUtils;

public class _PDashboardInput extends ProtoBase {

    private static final String TAG = "_PDashboardInput";
    String id;
    private jDashboardInputCB mCallback;

    public _PDashboardInput(AppRunner appRunner) {
        super(appRunner);
    }

    // --------- JDashboardInput add ---------//
    public interface jDashboardInputCB {
        void event(String responseString);
    }

    public void add(String name, int x, int y, int width, int height)
            throws UnknownHostException, JSONException {
        this.id = StrUtils.generateUUID();

        JSONObject values = new JSONObject()
                .put("id", id)
                .put("name", name)
                .put("type", "input")
                .put("x", x)
                .put("y", y)
                .put("width", width)
                .put("height", height);

        JSONObject msg = new JSONObject()
                .put("type", "widget")
                .put("action", "add")
                .put("values", values);

        //TODO change to events
        /*
        CustomWebsocketServer.getInstance(getContext()).send(msg);

        CustomWebsocketServer.getInstance(getContext()).addListener(id, new WebSocketListener() {
            @Override
            public void onUpdated(JSONObject jsonObject) {
                try {

                    final String val = jsonObject.getString("val");
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (mCallback != null) mCallback.event(val);
                        }
                    });
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        */

    }

    public void onSubmit(final jDashboardInputCB callbackfn) throws UnknownHostException {
        mCallback = callbackfn;
    }

    @Override
    public void __stop() {

    }
}
