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

package com.nextgis.safeforest.util;

public interface SettingsConstants
{
    String AUTHORITY             = "com.nextgis.safeforest.provider";
    String SITE_URL = "http://176.9.38.120/fv";
    String KOSOSNIMKI_URL = "http://{a,b,c}.tile.cart.kosmosnimki.ru/rs/{z}/{x}/{y}.png";
    String VIOLATIONS_URL = "http://maps.kosmosnimki.ru/TileService.ashx?request=gettile&layername=8D71968D94F644B5BFD7123A2937ADFC&srs=EPSG:3857&z={z}&x={x}&y={y}&format=png";



    String KEY_PREF_USERMINX = "user_minx";
    String KEY_PREF_USERMINY = "user_miny";
    String KEY_PREF_USERMAXX = "user_maxx";
    String KEY_PREF_USERMAXY = "user_maxy";

    /**
     * map preference
     */
    String KEY_PREF_COORD_FORMAT = "coordinates_format";

    /**
     * Preference key - not UI
     */
    String KEY_PREF_SCROLL_X      = "map_scroll_x";
    String KEY_PREF_SCROLL_Y      = "map_scroll_y";
    String KEY_PREF_ZOOM_LEVEL    = "map_zoom_level";
    String KEY_PREF_MAP_FIRST_VIEW = "map_first_view";
    String KEY_PREF_SHOW_LOCATION = "map_show_loc";
    String KEY_PREF_SHOW_INFO     = "map_show_info";

    /**
     * Preference keys - in UI
     */
    String KEY_PREF_USER_ID             = "user_id";
    String KEY_PREF_SHOW_LAYES_LIST     = "show_layers_list";
    String KEY_PREF_ACCURATE_LOC        = "accurate_coordinates_pick";
    String KEY_PREF_ACCURATE_GPSCOUNT   = "accurate_coordinates_pick_count";
    String KEY_PREF_ACCURATE_CE         = "accurate_type";
    String KEY_PREF_TILE_SIZE           = "map_tile_size";
    String KEY_PREF_SHOW_ZOOM_CONTROLS  = "show_zoom_controls";
}
