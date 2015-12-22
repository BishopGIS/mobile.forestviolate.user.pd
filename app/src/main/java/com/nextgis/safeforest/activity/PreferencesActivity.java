/*
 * Project: Forest violations
 * Purpose: Mobile application for registering facts of the forest violations.
 * Author:  Dmitry Baryshnikov (aka Bishop), bishop.dev@gmail.com
 * *****************************************************************************
 * Copyright (c) 2015-2015. NextGIS, info@nextgis.com
 *
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * For more information, please refer to <http://unlicense.org>

 */

package com.nextgis.safeforest.activity;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;

import com.nextgis.safeforest.MainApplication;
import com.nextgis.safeforest.R;
import com.nextgis.maplib.util.Constants;
import com.nextgis.maplibui.activity.NGPreferenceActivity;
import com.nextgis.maplibui.util.SettingsConstantsUI;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Application preference
 */
public class PreferencesActivity extends NGPreferenceActivity {
    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        final CheckBoxPreference syncSwitch = (CheckBoxPreference) findPreference(SettingsConstantsUI.KEY_PREF_SYNC_PERIODICALLY);
        if(null != syncSwitch){
            SharedPreferences settings = getSharedPreferences(Constants.PREFERENCES,
                    Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
            long timeStamp = settings.getLong(com.nextgis.maplib.util.SettingsConstants.KEY_PREF_LAST_SYNC_TIMESTAMP, 0);
            if (timeStamp > 0) {
                syncSwitch.setSummary(getString(R.string.last_sync_time) + ": " +
                        new SimpleDateFormat().format(new Date(timeStamp)));
            }
        }

        final ListPreference syncPeriod = (ListPreference) findPreference( SettingsConstantsUI.KEY_PREF_SYNC_PERIOD);
        if(null != syncPeriod){

            int id = syncPeriod.findIndexOfValue(syncPeriod.getValue());
            CharSequence summary = syncPeriod.getEntries()[id];
            syncPeriod.setSummary(summary);

            syncPeriod.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    long value = Long.parseLong(newValue.toString());
                    int id = ((ListPreference) preference).findIndexOfValue((String) newValue);
                    CharSequence summary =
                            ((ListPreference) preference).getEntries()[id];
                    preference.setSummary(summary);

                    preference.getSharedPreferences()
                            .edit()
                            .putLong(SettingsConstantsUI.KEY_PREF_SYNC_PERIOD_SEC_LONG, value)
                            .commit();

                    MainApplication app = (MainApplication) getApplication();

                    final Account account = app.getAccount(getString(R.string.account_name));
                    ContentResolver.addPeriodicSync(
                            account, app.getAuthority(), Bundle.EMPTY, value);

                    return true;
                }
            });
        }

        final ListPreference appTheme = (ListPreference) findPreference( SettingsConstantsUI.KEY_PREF_THEME);
        if(null != appTheme){
            int id = appTheme.findIndexOfValue(appTheme.getValue());
            CharSequence summary = appTheme.getEntries()[id];
            appTheme.setSummary(summary);
        }

        final ListPreference lpCoordinateFormat = (ListPreference) findPreference( SettingsConstantsUI.KEY_PREF_COORD_FORMAT);
        if (null != lpCoordinateFormat) {
            lpCoordinateFormat.setSummary(lpCoordinateFormat.getEntry());

            lpCoordinateFormat.setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(
                                Preference preference,
                                Object newValue) {
                            int value = Integer.parseInt(newValue.toString());
                            CharSequence summary =
                                    ((ListPreference) preference).getEntries()[value];
                            preference.setSummary(summary);

                            String preferenceKey = preference.getKey() + "_int";
                            preference.getSharedPreferences()
                                    .edit()
                                    .putInt(preferenceKey, value)
                                    .commit();

                            return true;
                        }
                    });
        }
    }
}
